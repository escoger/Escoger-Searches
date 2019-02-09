package com.escoger.searches.aggregator.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.escoger.searches.algo.ReservoirRandomSearch;
import com.escoger.searches.property.SearchProperty;
import com.escoger.searches.rest.client.RestClient;
import com.escoger.searches.service.SearchApiServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class AggregatorServiceimpl extends  TemplateAggregatorSvc{
	Logger logger = LoggerFactory.getLogger(AggregatorServiceimpl.class);

	public ObjectNode prodNode;
	Map<String, ObjectNode> prodMap = new HashMap<>();
	
	@Autowired
	SearchProperty configProperty;
	
	@Autowired
	SearchApiServiceImpl svcImpl;

	@Autowired
	ReservoirRandomSearch randomSearch;
	
	@Autowired
	ObjectMapper objMapper;



	@Override
	public ObjectNode getAggregatedResponseFromAllApi() {
		int threadCount = 0 ;
	
		/*
		 * Iterate each of the productNode and call the respective client based on the key and value
		 * Need to check the logic how can we call the client as we need to pass certain parameters as well in the client call 
		 * put each response in a hashMap 
		 * put each hashmap in a objectNode 
		 */
		threadCount = configProperty.getThreadpoolcount();
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadCount);
        ObjectNode objNode = objMapper.createObjectNode();
        Iterator<Entry<String, ObjectNode>> iter = prodMap.entrySet().iterator();
       while(iter.hasNext())
        {
    	   Map.Entry<String, ObjectNode> entry = (Map.Entry) iter.next();
			String key = (String)entry.getKey();
    	   ObjectNode prodNode = (ObjectNode) entry.getValue();
        	RestClient restClient = new RestClient(prodNode);
        	Future<List<String>> result = executor.submit(restClient);
        	objNode.put(key, (JsonNode) result);
            
        }

		return objNode;
	}

	
	@Override
	public ObjectNode getMobRandomSearch() {
		prodNode = svcImpl.getAllRandomMobileSearch();

		return prodNode;
	}



	@Override
	public ObjectNode getTvRandomSearch() {
		prodNode = svcImpl.getAllRandomTVSearch();

		return prodNode;
	}



	@Override
	public ObjectNode getTabRandomSearch() {
		prodNode = svcImpl.getAllRandomTabSearch();

		return prodNode;
	}



	@Override
	public ObjectNode getMobRandomFiteredMainSearchData(String key) {
		prodNode = randomSearch.getRandomFiteredMainSearchData(prodNode, key);
		prodMap.put("Mobile" , prodNode);
		return prodNode;
	}



	@Override
	public ObjectNode getTvRandomFiteredMainSearchData(String key) {
		prodNode = randomSearch.getRandomFiteredMainSearchData(prodNode, key);
		prodMap.put("TV" , prodNode);
		return prodNode;
	}



	@Override
	public ObjectNode getTabRandomFiteredMainSearchData(String key) {
		prodNode = randomSearch.getRandomFiteredMainSearchData(prodNode, key);
		prodMap.put("Tab" , prodNode);
		return prodNode;
	}



}
