package com.escoger.searches.algo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.escoger.searches.property.SearchProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ReservoirRandomSearch {
	private static final Logger logger = LoggerFactory.getLogger(ReservoirRandomSearch.class);
	private static String[] productArr;
	private Random random;
	//public static final int SEARCH_RESPONSE_SIZE = 5;
	public static ObjectNode objNode;

	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	SearchProperty searchProperty;
	
	public ReservoirRandomSearch() {
		this.random = new Random();
	}
	// A function to randomly select k items from stream[0..n-1]. 

	public void selectKItems(String[] arr , int k, Map<String, String> matchedStringMapResult) 
	{ 
		logger.info("ENTER : selectKItems(String[] arr , int k, Map<String, String> matchedStringMapResult)");
		//initializing reservoir array of size k i.e initialize an array of size that we want to do random search in an array 
		// and then put first k elements in that array
		String[] reservoir = new String[k];
		objNode = mapper.createObjectNode();
		for(int i =0; i<reservoir.length ; i++)
			reservoir[i]=arr[i];

		for(int i=k+1 ; i < arr.length ; i++) {
			int j = random.nextInt(i) +1;
			if(j<k ) {
				reservoir[j] = arr[i];
				objNode.put(reservoir[j], matchedStringMapResult.get(reservoir[j]));
			}
		}
		
		for(int i=0;i<reservoir.length ; i++)
			System.out.println(reservoir[i]+" ");

		logger.info("EXIT : selectKItems(String[] arr , int k, Map<String, String> matchedStringMapResult)");
	} 


	private static Map<String,String> filteredProductList(Map prodMap, String keySearch) {
		logger.info("ENTER : Map<String,String> filteredProductList(Map prodMap, String keySearch for {} ",keySearch);
		Map<String,String> filteredProdMap = new HashMap<String,String>();
		if(prodMap == null) {
			System.out.println("object list is null");
		}else {
			Iterator itr =  prodMap.entrySet().iterator();
			while(itr.hasNext()) {
				Map.Entry entry = (Map.Entry) itr.next();
				String product = (String)entry.getKey();
				String tempProduct = product.toLowerCase();
				String keySrch = keySearch.toLowerCase();
				if(tempProduct.contains(keySrch)) {
					filteredProdMap.put(product, (String)prodMap.get(product));
				}
			}
		}
		productArr = filteredProdMap.keySet().toArray(new String[filteredProdMap.size()]);
		logger.info("EXIT : Map<String,String> filteredProductList(Map prodMap, String keySearch for {} ",keySearch);
		return filteredProdMap;
	}
	
	public ObjectNode getRandomFiteredMainSearchData(ObjectNode prodMap , String keySearch){
		logger.info("ENTER : ObjectNode getRandomFiteredMainSearchData(ObjectNode prodMap , String keySearch for {} ",keySearch);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> result = mapper.convertValue(prodMap, Map.class);
		Map<String , String> matchedStringMapResult = filteredProductList(result, keySearch);
		//check if matchedStringMapResult size is less than size of result
		//to be send back to the response than send matchedStringMapResult else call reservoir algorithm 
		if(productArr.length >= searchProperty.getRandomresponsesize()) {
			selectKItems(productArr,searchProperty.getRandomresponsesize(),matchedStringMapResult);
		}
		logger.info("EXIT : ObjectNode getRandomFiteredMainSearchData(ObjectNode prodMap , String keySearch for {} ",keySearch);
		return objNode;
		
	}
} 


