package com.escoger.searches.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;



@Service
public class SearchApiServiceImpl implements SearchApiService{
	private static final Logger logger = LoggerFactory.getLogger(SearchApiServiceImpl.class);

	@Autowired
	ObjectMapper mapper;
	
	/*@Autowired
	public SearchApiServiceImpl(ObjectMapper mapper) {
		this.mapper = mapper;
	}
*/
	
	@Override
	public ObjectNode getAllSearchBasedOnKey() {
		 ObjectNode objectNode = mapper.createObjectNode();
	        objectNode.put("Nokia", "http:localhost:8080/getNokiaMobile");
	        objectNode.put("Samsung", "http:localhost:8080/getSamsungMobile");
	        objectNode.put("Mi", "http:localhost:8080/getMiMobile");
	        objectNode.put("Nokia", "http:localhost:8080/getNokiaMobile");
	        objectNode.put("Nokia", "http:localhost:8080/getNokiaMobile");
	        objectNode.put("Nokia", "http:localhost:8080/getNokiaMobile");
	        objectNode.put("Nokia", "http:localhost:8080/getNokiaMobile");
	        objectNode.put("Nokia", "http:localhost:8080/getNokiaMobile");
		return objectNode;
	}

	@Override
	public ObjectNode getAllRandomMobileSearch() {
		ObjectNode mobileNode = mapper.createObjectNode();
		mobileNode.put("Nokia", "http:localhost:8080/getNokiaMobile");
		mobileNode.put("Samsung", "http:localhost:8080/getSamsungMobile");
		mobileNode.put("Mi", "http:localhost:8080/getMiMobile");
		mobileNode.put("Nokia", "http:localhost:8080/getNokiaMobile/Nokia");
		mobileNode.put("Nokia", "http:localhost:8080/getNokiaMobile");
		mobileNode.put("Nokia", "http:localhost:8080/getNokiaMobile");
		mobileNode.put("Nokia", "http:localhost:8080/getNokiaMobile");
		mobileNode.put("Nokia", "http:localhost:8080/getNokiaMobile");
		return mobileNode;
	}

	@Override
	public ObjectNode getAllRandomTVSearch() {
		 ObjectNode tvNode = mapper.createObjectNode();
		return tvNode;
	}

	@Override
	public ObjectNode getAllRandomTabSearch() {
		ObjectNode tabNode = mapper.createObjectNode();
		return tabNode;
	}

	

		

}
