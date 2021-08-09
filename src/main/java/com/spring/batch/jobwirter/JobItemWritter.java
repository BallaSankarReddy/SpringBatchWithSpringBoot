package com.spring.batch.jobwirter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.spring.batch.dto.Teacher;

public class JobItemWritter implements ItemWriter<Teacher> {

	private static final org.slf4j.Logger LOGGER= LoggerFactory.getLogger(JobItemWritter.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public void write(List<? extends Teacher> items) throws Exception {
		LOGGER.info("Starting JobItemWritter.write Method......");	
		if(!items.isEmpty()) {

			LOGGER.info("Starting saving data in restApi servive......");	
			
			
		 
			items.stream().forEach(obj ->{
				LOGGER.info("befor saving data in restApi servive responce......"+obj.getId() +" "+obj.getName() +" "+obj.getSalary()+" "+obj.getStudId());
				
				obj.setId(obj.getId()+1);
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				HttpEntity<Object> entity= new HttpEntity<Object>(obj,headers);
				ResponseEntity<Teacher> exchange = restTemplate.exchange("http://localhost:9090/teacher/teacher/save", HttpMethod.POST, entity, Teacher.class);
				
				LOGGER.info("After saving data in restApi servive responce......"+exchange.getBody().getId());
			});
		}
	}

}
