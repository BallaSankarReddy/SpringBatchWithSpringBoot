package com.spring.batch.jobreader;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.spring.batch.dto.Teacher;

public class JobItemRead implements ItemReader<Teacher> {

	private String apiURL = "http://localhost:9090/teacher/teacher";
	private RestTemplate restTemplate;
	private Integer nextIndex;
	private List<Teacher> objs;

	public JobItemRead(String apiURL, RestTemplate restTemplate) {
		super();
		this.apiURL = apiURL;
		this.restTemplate = restTemplate;
		this.nextIndex = 0;
	}

	
	
	private Boolean isDataInitialized() {
		return this.objs ==null;
	}

	
	private List<Teacher> fetchListOfObjects(){
		
		ResponseEntity<Teacher []> responceObject = restTemplate.getForEntity(apiURL, Teacher[].class);
		
		Teacher[] body = responceObject.getBody();
		
		return Arrays.asList(body);
		
	}



	@Override
	public Teacher read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if(isDataInitialized()) {
			objs=fetchListOfObjects();
			
		}
		Teacher object=null;
		if(nextIndex < objs.size()) {
			object=objs.get(nextIndex);
			nextIndex++;
			
		}else {
			nextIndex=0;
			objs=null;
		}
		
		return object;
	}
	}

