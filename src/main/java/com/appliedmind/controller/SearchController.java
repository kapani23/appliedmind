package com.appliedmind.controller;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appliedmind.dto.search.ServiceSearchableIdentityResponse;
import com.appliedmind.search.SearchService;
import com.appliedmind.search.ServicesSearchableIdentity;
import com.appliedmind.search.StaticDataService;

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SearchController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private SearchService searchService = null;

	@Autowired
	private StaticDataService staticDataService = null;

	@GetMapping(path = "/base/{queryString}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ServiceSearchableIdentityResponse>> search(@PathVariable String queryString) {
		LOGGER.debug("Input received for searching " + queryString);
		List<String> suggestTermsFor = null;
		List<ServiceSearchableIdentityResponse> identities = new ArrayList<>();
		try {
			suggestTermsFor = searchService.suggestTermsFor(queryString);
			for (String eachTerm : suggestTermsFor) {
				ServicesSearchableIdentity fetchtService = staticDataService.getService(eachTerm.toUpperCase());
				ServiceSearchableIdentityResponse identityResponse = buildResposne(fetchtService);
				identities.add(identityResponse);
			}
		} catch (Exception e) {
			LOGGER.error("Error searching for term " + queryString, e);
			e.printStackTrace();
		}
		LOGGER.debug("Output for search " + identities.size());
		return ResponseEntity.ok(identities);
	}

	@GetMapping(path = "/base/{categoryId}/{queryString}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ServiceSearchableIdentityResponse>> search(@PathVariable String categoryId,
			@PathVariable String queryString) {
		LOGGER.debug("Input received for searching " + queryString);
		List<String> suggestTermsFor = null;
		List<ServiceSearchableIdentityResponse> identities = new ArrayList<>();
		try {
			suggestTermsFor = searchService.suggestTermsFor(queryString);
			for (String eachTerm : suggestTermsFor) {
				ServicesSearchableIdentity fetchtService = staticDataService.getService(eachTerm.toUpperCase());
				if (fetchtService.getCategoryId().equals(categoryId) || categoryId.equals("0")
						|| categoryId.equals("-1")) {
					ServiceSearchableIdentityResponse identityResponse = buildResposne(fetchtService);
					identities.add(identityResponse);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error searching for term " + queryString, e);
			e.printStackTrace();
		}
		LOGGER.debug("Output for search " + identities.size());
		return ResponseEntity.ok(identities);
	}

	private ServiceSearchableIdentityResponse buildResposne(ServicesSearchableIdentity fetchtService) {
		ServiceSearchableIdentityResponse identityResponse = new ServiceSearchableIdentityResponse();
		identityResponse.setId(fetchtService.getId());
		identityResponse.setValue(fetchtService.getValue());
		identityResponse.setCategoryId(fetchtService.getCategoryId());
		identityResponse.setCategoryValue(fetchtService.getCategoryValue());
		return identityResponse;
	}

}
