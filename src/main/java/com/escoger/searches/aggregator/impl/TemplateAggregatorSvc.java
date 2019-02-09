package com.escoger.searches.aggregator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.escoger.searches.algo.ReservoirRandomSearch;
import com.escoger.searches.service.SearchApiServiceImpl;
import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class TemplateAggregatorSvc {

	
	
	@Autowired
	SearchApiServiceImpl searchData;
	
	@Autowired
	ReservoirRandomSearch reservoirSearch;
	
	public final ObjectNode aggregateResponse(String searchKey){
		
		getMobRandomSearch();
		getMobRandomFiteredMainSearchData( searchKey);
		getTvRandomSearch();
		getTvRandomFiteredMainSearchData( searchKey);
		getTabRandomSearch();
		getTabRandomFiteredMainSearchData( searchKey);
		ObjectNode objNode = getAggregatedResponseFromAllApi();
		return objNode;
	}
	
	public abstract ObjectNode getMobRandomSearch();
	public abstract ObjectNode getTvRandomSearch();
	public abstract ObjectNode getTabRandomSearch();
	public abstract ObjectNode getMobRandomFiteredMainSearchData(String key);
	public abstract ObjectNode getTvRandomFiteredMainSearchData(String key);
	public abstract ObjectNode getTabRandomFiteredMainSearchData(String key);
	public abstract ObjectNode getAggregatedResponseFromAllApi();
	
}
