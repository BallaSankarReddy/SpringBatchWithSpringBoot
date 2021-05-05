package com.spring.batch.jobreader;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class JobItemRead implements ItemReader<Object> {

	private String apiURL = "http://localhost:9090/teacher/teacher";
	private RestTemplate restTemplate;
	private Integer nextIndex;
	private List<Object> objs;

	public JobItemRead(String apiURL, RestTemplate restTemplate) {
		super();
		this.apiURL = apiURL;
		this.restTemplate = restTemplate;
		this.nextIndex = 0;
	}

	@Override
	public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		
		if(isDataInitialized()) {
			objs=fetchListOfObjects();
			
		}
		Object object;
		if(nextIndex < objs.size()) {
			object=objs.get(nextIndex);
			nextIndex++;
			
		}else {
			nextIndex=0;
			objs=null;
		}
		
		return objs;
	}
	
	private Boolean isDataInitialized() {
		return this.objs ==null;
	}

	
	private List<Object> fetchListOfObjects(){
		
		ResponseEntity<Object[]> responceObject = restTemplate.getForEntity(apiURL, Object[].class);
		
		Object[] body = responceObject.getBody();
		
		return Arrays.asList(body);
		
	}
}
