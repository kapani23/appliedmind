package com.appliedmind.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.bg.BulgarianAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.suggest.Lookup;
import org.apache.lucene.search.suggest.analyzing.AnalyzingInfixSuggester;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

	private static final String ID = "id";
	private static final String DESCRIPTION = "description";

	private IndexSearcher dirSearcher;
	private AnalyzingInfixSuggester analyzingSuggester;

	@Autowired
	ResourceLoader resourceLoader;
	Map<String, SearchableIdentity> SERVICES_CACHE = new HashedMap<>();

	@Autowired
	private StaticDataService staticDataService = null;

	@PostConstruct
	public void initialize() throws Exception {
		// Index that is using stopwords and stems for better search functionality
		ByteBuffersDirectory directory = new ByteBuffersDirectory();

		// Second index is created for the autocomplete functionality.
		// The autocomplete analyzer needs to be simple so the suggestions are as they
		// are in the text.
		Analyzer autocompleteAnalyzer = new Analyzer() {
			@Override
			protected TokenStreamComponents createComponents(String s) {
				Tokenizer source = new StandardTokenizer();
				TokenFilter filter = new LowerCaseFilter(source);
				return new TokenStreamComponents(source, filter);
			}
		};
		ByteBuffersDirectory autocompleteDirectory = new ByteBuffersDirectory();

		try (IndexWriter directoryWriter = new IndexWriter(directory, new IndexWriterConfig(new BulgarianAnalyzer()));
				IndexWriter autocompleteDirectoryWriter = new IndexWriter(autocompleteDirectory,
						new IndexWriterConfig(autocompleteAnalyzer))) {

			Collection<ServicesSearchableIdentity> fetchAllServiceToServicesCategoryMapping = staticDataService
					.fetchAllServiceToServicesCategoryMapping();
			for (ServicesSearchableIdentity searchableIdentity : fetchAllServiceToServicesCategoryMapping) {
				Document doc = new Document();
				doc.add(new StoredField(ID, searchableIdentity.getId()));
				doc.add(new TextField(DESCRIPTION, searchableIdentity.getValue(), Field.Store.YES));

				Document autocompleteDoc = new Document();
				autocompleteDoc.add(new StringField(DESCRIPTION, searchableIdentity.getValue(), Field.Store.YES));
				autocompleteDirectoryWriter.addDocument(autocompleteDoc);
			}
		}

		// Using Lucene's suggester for the autocomplete functionality
		buildAnalyzingSuggester(autocompleteDirectory, autocompleteAnalyzer);

		DirectoryReader indexReader = DirectoryReader.open(directory);
		dirSearcher = new IndexSearcher(indexReader);
	}

	public List<SearchableIdentity> search(String keyword) throws ParseException, IOException {
		QueryParser parser = new QueryParser(DESCRIPTION, new BulgarianAnalyzer());
		Query query = parser.parse(keyword);
		TopDocs topDocs = dirSearcher.search(query, 10);
		List<SearchableIdentity> searchEntities = new ArrayList<>();
		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			Document document = dirSearcher.doc(scoreDoc.doc);
			SearchableIdentity searchEntity = new SearchableIdentity(document.get(ID), document.get(DESCRIPTION));
			searchEntities.add(searchEntity);
		}

		return searchEntities;
	}

	public List<String> suggestTermsFor(String term) throws IOException {
		List<Lookup.LookupResult> lookup = analyzingSuggester.lookup(term, false, 5);
		List<String> suggestions = lookup.stream().map(a -> a.key.toString()).collect(Collectors.toList());
		return suggestions;
	}

	public void buildAnalyzingSuggester(Directory autocompleteDirectory, Analyzer autocompleteAnalyzer)
			throws IOException {
		DirectoryReader sourceReader = DirectoryReader.open(autocompleteDirectory);
		LuceneDictionary dict = new LuceneDictionary(sourceReader, DESCRIPTION);
		analyzingSuggester = new AnalyzingInfixSuggester(autocompleteDirectory, autocompleteAnalyzer);
		analyzingSuggester.build(dict);
	}

}
