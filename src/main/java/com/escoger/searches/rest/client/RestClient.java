package com.escoger.searches.rest.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class RestClient implements Callable<List<String>>{
	
		ObjectNode prodNode;
		
	 public RestClient(ObjectNode prodNode) {
		 
		 this.prodNode = prodNode;
	 }
	
	@Override
	public List<String> call() throws Exception {
		List<String> respList = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> prodMap = mapper.convertValue(prodNode, Map.class);
		
		Iterator itr =  prodMap.entrySet().iterator();
		while(itr.hasNext()) {
			Map.Entry entry = (Map.Entry) itr.next();
			String product = (String)entry.getKey();
			String url = (String) entry.getValue();
			String restResponse = mobRestCall(product ,url);
			respList.add(restResponse);
		}
		return respList;
	}



	private String mobRestCall(String product, String resourceUrl) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response
		  = restTemplate.getForEntity(resourceUrl , String.class);
		return response.toString();
	}
}