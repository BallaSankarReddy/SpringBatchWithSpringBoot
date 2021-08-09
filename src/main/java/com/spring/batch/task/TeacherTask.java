package com.spring.batch.task;

import java.util.Arrays;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.spring.batch.dto.Teacher;

@Component("Teacher_BatchTask_Get_Teachers")
public class TeacherTask {
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TeacherTask.class);
	
	private String apiURL = "http://localhost:9090/teacher/teacher";
	@Autowired
	private RestTemplate restTemplate;
	
	public List<Teacher> getTeachers(){
		LOGGER.info("Calling  Teacher_BatchTask_Get_Teachers job.....");
		List<Teacher> fetchListOfObjects = fetchListOfObjects();
		
		return null;
	}
private List<Teacher> fetchListOfObjects(){
		
		ResponseEntity<Teacher []> responceObject = restTemplate.getForEntity(apiURL, Teacher[].class);
		
		Teacher[] body = responceObject.getBody();
		
		return Arrays.asList(body);
		
	}

}
