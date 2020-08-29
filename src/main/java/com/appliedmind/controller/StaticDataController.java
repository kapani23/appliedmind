package com.appliedmind.controller;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appliedmind.dto.search.BaseInfoResponse;
import com.appliedmind.search.PaymentInfo;
import com.appliedmind.search.ServiceCategory;
import com.appliedmind.search.ServiceEngagementTerm;
import com.appliedmind.search.StaticDataService;

@RestController
@RequestMapping("/static")
@CrossOrigin(origins = "http://localhost:8084")
public class StaticDataController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private List<BaseInfoResponse> BASE_SERVICES_CATEGORIES_RESPONSE = new ArrayList<>();
	private List<BaseInfoResponse> BASE_PAYMENTS_INFO_RESPONSE = new ArrayList<>();
	private List<BaseInfoResponse> BASE_SERVICES_ENGAGEMENT_TERMS_RESPONSE = new ArrayList<>();

	@Autowired
	private StaticDataService staticDataService = null;

	@PostConstruct
	public void init() {
		Collection<ServiceCategory> fetchtAllServiceCategories = staticDataService.fetchAllServicesCategories();
		for (ServiceCategory baseCategory : fetchtAllServiceCategories) {
			BASE_SERVICES_CATEGORIES_RESPONSE.add(buildBaseServiceCategoryResposne(baseCategory));
		}

		Collection<PaymentInfo> fetchAllPaymentsInfo = staticDataService.fetchAllPaymentsInfo();
		for (PaymentInfo paymentInfo : fetchAllPaymentsInfo) {
			BASE_PAYMENTS_INFO_RESPONSE.add(buildBasePaymentInfoResposne(paymentInfo));
		}

		Collection<ServiceEngagementTerm> fetchAllServicesEngagementTerms = staticDataService
				.fetchAllServicesEngagementTerms();
		for (ServiceEngagementTerm serviceEngagementTerm : fetchAllServicesEngagementTerms) {
			BASE_SERVICES_ENGAGEMENT_TERMS_RESPONSE.add(buildBaseServiceEngagmentResposne(serviceEngagementTerm));
		}

	}

	@GetMapping(path = "/fetchAllServicesCategories", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BaseInfoResponse>> fetchAllServicesCategories() {
		LOGGER.debug("Input received for fetching all categories ");
		return ResponseEntity.ok(BASE_SERVICES_CATEGORIES_RESPONSE);
	}

	@GetMapping(path = "/fetchPaymentsInfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BaseInfoResponse>> fetchPaymentsInfo() {
		LOGGER.debug("Request recevied for fetching all payment info");
		return ResponseEntity.ok(BASE_PAYMENTS_INFO_RESPONSE);
	}

	@GetMapping(path = "/fetchServicesEngagmentTerms", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BaseInfoResponse>> fetchServicesEngagementTerms() {
		LOGGER.debug("Request recevied for fetching all services engagment terms");
		return ResponseEntity.ok(BASE_SERVICES_ENGAGEMENT_TERMS_RESPONSE);
	}

	private BaseInfoResponse buildBaseServiceCategoryResposne(ServiceCategory baseCategory) {
		BaseInfoResponse baseResponse = new BaseInfoResponse();
		baseResponse.setId(baseCategory.getId());
		baseResponse.setValue(baseCategory.getValue());
		return baseResponse;
	}

	private BaseInfoResponse buildBasePaymentInfoResposne(PaymentInfo paymentInfo) {
		BaseInfoResponse baseResponse = new BaseInfoResponse();
		baseResponse.setId(paymentInfo.getId());
		baseResponse.setValue(paymentInfo.getPaymentType());
		return baseResponse;
	}

	private BaseInfoResponse buildBaseServiceEngagmentResposne(ServiceEngagementTerm engagementTerm) {
		BaseInfoResponse baseResponse = new BaseInfoResponse();
		baseResponse.setId(engagementTerm.getId());
		baseResponse.setValue(engagementTerm.getEngagementTermType());
		return baseResponse;
	}

}
