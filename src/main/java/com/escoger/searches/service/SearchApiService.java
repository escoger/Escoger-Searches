package com.escoger.searches.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public interface SearchApiService {

	public ObjectNode getAllSearchBasedOnKey();
	public ObjectNode getAllRandomMobileSearch();
	public ObjectNode getAllRandomTVSearch();
	public ObjectNode getAllRandomTabSearch();
}
