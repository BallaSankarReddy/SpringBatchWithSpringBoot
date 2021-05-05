package com.spring.batch.jobluncher;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringBatchJobLuncher {
	
private static final org.slf4j.Logger LOGGER= LoggerFactory.getLogger(SpringBatchJobLuncher.class);
	
	private final Job job;
	
	private final JobLauncher jobLauncher;

	@Autowired
	public SpringBatchJobLuncher(Job job, JobLauncher jobLauncher) {
		super();
		this.job = job;
		this.jobLauncher = jobLauncher;
	}
	

	
	@Scheduled(cron = "0 1 1 * * *")
	public void runSpringBootJobLuncher() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		LOGGER.info("Runing the SpringBatchJobLuncher.runSpringBootJobLuncher method");
		jobLauncher.run(job, newJobExecution());
		
	}



	private JobParameters newJobExecution() {
		LOGGER.info("Runing the newJobExecution....");
		Map<String, JobParameter> jobParameters = new HashMap<String, JobParameter>();
		
		JobParameter jobParameter = new JobParameter(new Date());
		jobParameters.put("CurrnetTime", jobParameter);
		LOGGER.info("Runing the newJobExecution....{jobParameters} :"+jobParameter.getValue());
		return new JobParameters(jobParameters);
	}


}
