package com.appliedmind.search;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
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
import org.apache.lucene.search.suggest.analyzing.AnalyzingSuggester;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AppliedMindDictionaryHolder {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private Map<String, AppliedMindDictionaryHolder> YOGA_DICTIONARY = new HashMap<>();
	private Map<String, AppliedMindDictionaryHolder> MUSIC_DICTIONARY = new HashMap<>();
	private Map<String, AppliedMindDictionaryHolder> DANCE_DICTIONARY = new HashMap<>();
	private Map<String, AppliedMindDictionaryHolder> CLASSES_DICTIONARY = new HashMap<>();
	private Map<String, AppliedMindDictionaryHolder> CIVIL_SERVICES_DICTIONARY = new HashMap<>();

	private StandardAnalyzer GENERIC_ANALYZER = null;
	Directory GENERIC_DIRECTORY = new RAMDirectory();
	Analyzer autocompleteAnalyzer = null;
	ByteBuffersDirectory autocompleteDirectory = null;

	@PostConstruct
	public void init() {
		// The autocomplete analyzer needs to be simple so the
		// suggestions are as they are in the text.
		autocompleteAnalyzer = new Analyzer() {
			@Override
			protected TokenStreamComponents createComponents(String s) {
				Tokenizer source = new StandardTokenizer();
				TokenFilter filter = new LowerCaseFilter(source);
				return new TokenStreamComponents(source, filter);
			}
		};
		autocompleteDirectory = new ByteBuffersDirectory();

		try {
			LOGGER.debug("Initializing Directory");
			createGenericDictionary();
			LOGGER.debug("Intialized Directory");
		} catch (Exception e) {
			LOGGER.debug("Error creating Directory", e);
			e.printStackTrace();
		}
	}

	public void createGenericDictionary() throws Exception {
		// GENERIC_ANALYZER = new StandardAnalyzer();
		// IndexWriterConfig indexWriterConfig = new
		// IndexWriterConfig(GENERIC_ANALYZER);
		// IndexWriter writter = new IndexWriter(GENERIC_DIRECTORY,indexWriterConfig);

		IndexWriter autocompleteDirectoryWriter = new IndexWriter(autocompleteDirectory,
				new IndexWriterConfig(autocompleteAnalyzer));
		Document document = new Document();

		document.add(new TextField("Base_Category", "Yoga", Field.Store.YES));
		document.add(new TextField("Base_Category", "Math", Field.Store.YES));
		document.add(new TextField("Base_Category", "Social Science", Field.Store.YES));
		document.add(new TextField("Base_Category", "Chemistry", Field.Store.YES));
		document.add(new TextField("Base_Category", "Biology", Field.Store.YES));
		document.add(new TextField("Base_Category", "Physics", Field.Store.YES));
		document.add(new TextField("Base_Category", "French", Field.Store.YES));
		document.add(new TextField("Base_Category", "Java", Field.Store.YES));
		document.add(new TextField("Base_Category", "Python", Field.Store.YES));
		document.add(new TextField("Base_Category", "C++", Field.Store.YES));
		document.add(new TextField("Base_Category", "Rust", Field.Store.YES));
		document.add(new TextField("Base_Category", "Data Science", Field.Store.YES));
		document.add(new TextField("Base_Category", "Artificial Intelligence", Field.Store.YES));
		document.add(new TextField("Base_Category", "Data Structures & Algorithms", Field.Store.YES));
		document.add(new TextField("Base_Category", "English", Field.Store.YES));
		document.add(new TextField("Base_Category", "Spanish", Field.Store.YES));
		document.add(new TextField("Base_Category", "Hindi", Field.Store.YES));
		document.add(new TextField("Base_Category", "Meditation", Field.Store.YES));
		document.add(new TextField("Base_Category", "German", Field.Store.YES));
		document.add(new TextField("Base_Category", "IIT Exam", Field.Store.YES));
		document.add(new TextField("Base_Category", "UPSC Exam", Field.Store.YES));
		document.add(new TextField("Base_Category", "NET Exam", Field.Store.YES));
		document.add(new TextField("Base_Category", "SAT Math", Field.Store.YES));
		document.add(new TextField("Base_Category", "SAT English", Field.Store.YES));
		document.add(new TextField("Base_Category", "Cooking", Field.Store.YES));
		document.add(new TextField("Base_Category", "Drawing", Field.Store.YES));
		document.add(new TextField("Base_Category", "GMAT", Field.Store.YES));
		document.add(new TextField("Base_Category", "Painting", Field.Store.YES));
		document.add(new TextField("Base_Category", "GRE", Field.Store.YES));
		document.add(new TextField("Base_Category", "Rubik's cube", Field.Store.YES));
		document.add(new TextField("Base_Category", "TOEFL", Field.Store.YES));
		document.add(new TextField("Base_Category", "ILETS", Field.Store.YES));
		document.add(new TextField("Base_Category", "CAT", Field.Store.YES));
		document.add(new TextField("Base_Category", "MCAT", Field.Store.YES));
		autocompleteDirectoryWriter.addDocument(document);
		autocompleteDirectoryWriter.close();

	}

//	public void createYogaDictionary() {
//		
//		StandardAnalyzer analyzer = new StandardAnalyzer();
//		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
//		IndexWriter writter = new IndexWriter(GENERIC_DIRECTORY, indexWriterConfig);
//		Document document = new Document();
//		 
//		document.add(new TextField("Bikram Yoga", "Vinyasa yoga is popular and is taught at most studios and gyms", Field.Store.YES));
//		document.add(new TextField("Ashtanga Yoga", "Ashtanga means “eight limbs” and encompasses a yogic lifestyle", Field.Store.YES));
//		document.add(new TextField("Iyengar Yoga", "Also based on the Eight Limbs of Yoga, Iyengar yoga is named after B.K.S", Field.Store.YES));
//		document.add(new TextField("Bikram Yoga", "Also based on the Eight Limbs of Yoga, Iyengar yoga is named after B.K.S", Field.Store.YES));
//		document.add(new TextField("Jivamukti Yoga", "Also based on the Eight Limbs of Yoga, Iyengar yoga is named after B.K.S", Field.Store.YES));
//		document.add(new TextField("Sivananda Yoga", "Also based on the Eight Limbs of Yoga, Iyengar yoga is named after B.K.S", Field.Store.YES));		
//		
//		writter.addDocument(document);
//		writter.close();
//	}

	AnalyzingSuggester analyzingSuggester = null;
	private static final String DESCRIPTION = "description";

	public void buildAnalyzingSuggester(Directory autocompleteDirectory, Analyzer autocompleteAnalyzer)
			throws IOException {
		DirectoryReader sourceReader = DirectoryReader.open(autocompleteDirectory);
		LuceneDictionary dict = new LuceneDictionary(sourceReader, DESCRIPTION);
		analyzingSuggester = new AnalyzingSuggester(autocompleteDirectory, "autocomplete_temp", autocompleteAnalyzer);
		analyzingSuggester.build(dict);
	}

	public void createDanceDictionary() {

	}

	public List<Document> searchIndex(String inField, String queryString) {
		try {
			Query query = new QueryParser(inField, GENERIC_ANALYZER).parse(queryString);

			IndexReader indexReader = DirectoryReader.open(GENERIC_DIRECTORY);
			IndexSearcher searcher = new IndexSearcher(indexReader);
			TopDocs topDocs = searcher.search(query, 10);
			List<Document> documents = new ArrayList<>();
			for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
				documents.add(searcher.doc(scoreDoc.doc));
			}

			return documents;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Document> searchIndex(Query query) {
		try {
			IndexReader indexReader = DirectoryReader.open(GENERIC_DIRECTORY);
			IndexSearcher searcher = new IndexSearcher(indexReader);
			TopDocs topDocs = searcher.search(query, 10);
			List<Document> documents = new ArrayList<>();
			for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
				documents.add(searcher.doc(scoreDoc.doc));
			}
			return documents;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<String> suggestTermsFor(String term) throws IOException {
		List<Lookup.LookupResult> lookup = analyzingSuggester.lookup(term, false, 5);
		List<String> suggestions = lookup.stream().map(a -> a.key.toString())
				.collect(java.util.stream.Collectors.toList());

		return suggestions;
	}

}
