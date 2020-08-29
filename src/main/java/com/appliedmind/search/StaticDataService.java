package com.appliedmind.search;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class StaticDataService {

	@Autowired
	ResourceLoader resourceLoader;

	private Map<String, ServiceCategory> SERVICES_CATEGORIES_CACHE_KEYED_BY_ID = new LinkedHashMap<>();
	private Map<String, PaymentInfo> PAYMENT_INFO_CACHE_KEYED_BY_ID = new LinkedHashMap<>();
	private Map<String, ServiceEngagementTerm> SERVICE_ENGAGEMENT_TERM_INFO_KEYED_BY_ID = new LinkedHashMap<>();

	private Map<String, ServiceCategory> SERVICES_CATEGORIES_CACHE_KEYED_BY_VALUE = new LinkedHashMap<>();
	private Map<String, PaymentInfo> PAYMENT_INFO_CACHE_KEYED_BY_VALUE = new LinkedHashMap<>();
	private Map<String, ServicesSearchableIdentity> SERVICE_TO_SERVICES_CATEGORY_MAP_KEYED_BY_VALUE = new LinkedHashMap<>();

	private Map<String, ServiceEngagementTerm> SERVICES_ENGAGEMENT_TERM_INFO_KEYED_BY_VALUE = new LinkedHashMap<>();

	@PostConstruct
	public void initialize() throws Exception {
		loadServicesCategories();
		loadServicesAndMapWithServicesCategory();
		loadPaymentsInfo();
		loadServiceEngagementTermInfo();
	}

	private void loadServicesCategories() throws Exception {
		Resource resource = resourceLoader.getResource("classpath:category_base.csv");
		InputStream inputStream = resource.getInputStream();

		Reader reader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(reader);
		// spit away the first line
		bufferedReader.readLine();
		try (CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.RFC4180)) {
			for (CSVRecord record : csvParser) {
				String id = record.get(0);
				String value = record.get(1);
				ServiceCategory serviceCategory = new ServiceCategory(id, value);
				SERVICES_CATEGORIES_CACHE_KEYED_BY_ID.put(serviceCategory.getId(), serviceCategory);
				SERVICES_CATEGORIES_CACHE_KEYED_BY_VALUE.put(serviceCategory.getValue(), serviceCategory);
			}
		}

	}

	private void loadPaymentsInfo() throws Exception {
		Resource resource = resourceLoader.getResource("classpath:payments_base.csv");
		InputStream inputStream = resource.getInputStream();

		Reader reader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(reader);
		// spit away the first line
		bufferedReader.readLine();
		try (CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.RFC4180)) {
			for (CSVRecord record : csvParser) {
				String id = record.get(0);
				String value = record.get(1);
				PaymentInfo paymentInfo = new PaymentInfo(id, value);
				PAYMENT_INFO_CACHE_KEYED_BY_ID.put(paymentInfo.getId(), paymentInfo);
				PAYMENT_INFO_CACHE_KEYED_BY_VALUE.put(paymentInfo.getPaymentType(), paymentInfo);
			}
		}
	}

	private void loadServiceEngagementTermInfo() throws Exception {
		Resource resource = resourceLoader.getResource("classpath:services_engagement_term_base.csv");
		InputStream inputStream = resource.getInputStream();

		Reader reader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(reader);
		// spit away the first line
		bufferedReader.readLine();
		try (CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.RFC4180)) {
			for (CSVRecord record : csvParser) {
				String id = record.get(0);
				String value = record.get(1);
				ServiceEngagementTerm engagementTerm = new ServiceEngagementTerm(id, value);
				SERVICE_ENGAGEMENT_TERM_INFO_KEYED_BY_ID.put(engagementTerm.getId(), engagementTerm);
				SERVICES_ENGAGEMENT_TERM_INFO_KEYED_BY_VALUE.put(engagementTerm.getEngagementTermType(),
						engagementTerm);
			}
		}
	}

	private void loadServicesAndMapWithServicesCategory() throws Exception {
		Resource resource = resourceLoader.getResource("classpath:services_with_category_mapping.csv");
		InputStream inputStream = resource.getInputStream();
		// InputStream icdStream =
		// SearchService.class.getResourceAsStream("category.csv");
		Reader reader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(reader);
		// spit away the first line
		bufferedReader.readLine();
		try (CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.RFC4180)) {
			for (CSVRecord record : csvParser) {
				String id = record.get(0);
				String value = record.get(1);
				String categoryId = record.get(2);
				ServiceCategory serviceCategory = this.getServiceCategoryById(categoryId);
				ServicesSearchableIdentity service = new ServicesSearchableIdentity(id, value, serviceCategory);
				SERVICE_TO_SERVICES_CATEGORY_MAP_KEYED_BY_VALUE.put(service.getValue().toUpperCase(), service);
			}
		}
	}

	public Collection<ServiceCategory> fetchAllServicesCategories() {
		return SERVICES_CATEGORIES_CACHE_KEYED_BY_ID.values();
	}

	public Collection<PaymentInfo> fetchAllPaymentsInfo() {
		return PAYMENT_INFO_CACHE_KEYED_BY_VALUE.values();
	}

	public Collection<ServiceEngagementTerm> fetchAllServicesEngagementTerms() {
		return SERVICES_ENGAGEMENT_TERM_INFO_KEYED_BY_VALUE.values();
	}

	public Collection<ServicesSearchableIdentity> fetchAllServiceToServicesCategoryMapping() {
		return SERVICE_TO_SERVICES_CATEGORY_MAP_KEYED_BY_VALUE.values();
	}

	private ServiceCategory getServiceCategoryById(String categoryId) {
		return SERVICES_CATEGORIES_CACHE_KEYED_BY_ID.get(categoryId);
	}

	public ServicesSearchableIdentity getService(String value) {
		return SERVICE_TO_SERVICES_CATEGORY_MAP_KEYED_BY_VALUE.get(value);
	}

}
