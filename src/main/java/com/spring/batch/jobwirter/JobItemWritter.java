package com.spring.batch.jobwirter;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

public class JobItemWritter implements ItemWriter<Object> {

	private static final org.slf4j.Logger LOGGER= LoggerFactory.getLogger(JobItemWritter.class);
	
	@Override
	public void write(List<? extends Object> items) throws Exception {
		LOGGER.info("Starting JobItemWritter.write Method......");		
	}

}
