package com.spring.batch;

import java.util.Date;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.JobExecutionExitCodeGenerator;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class SprinBatchWithSpringBootApplication {

	@Autowired
	static
	JobLauncher jobLauncher;
	@Autowired
	static
	 Job job ;
	public static void main(String[] args) throws Exception {
		//int run = run(args);
		//System.out.println(run);
		perform();
		SpringApplication.run(SprinBatchWithSpringBootApplication.class, args);
	}
	
	    public static void perform() throws Exception 
	    {

		 
	        JobParameters params = new JobParametersBuilder()
	                .addString("Teacher_BatchTask_Get_Teachers", String.valueOf(System.currentTimeMillis()))
	                .toJobParameters();
	        

	        JobExecution run = jobLauncher.run(job, params);
	        System.out.println(run.getId());
	    }

	
	public static int run(String[] args) throws Exception {
		
		
		   JobLauncher jobLauncher = null;

		  Job job = null;
	    String dateParam = new Date().toString();
	  //  JobParameters param = 	      new JobParametersBuilder().addString("date", dateParam).toJobParameters();
	            
	    System.out.println(dateParam);
	            
	  //  JobExecution execution = jobLauncher.run(job, param);
	    //System.out.println("Exit Status : " + execution.getStatus());
	    
	    
	    
		JobParameters jobParameters= new JobParametersBuilder().addDate("date", new Date()).toJobParameters();
		
		String jobname="Teacher_BatchTask_Get_Teachers";
		
		Map<String, String> getenv = System.getenv();
		
		
		if(System.getenv("OMS_JOB_TYPE")!=null) {
			
			if(System.getenv("OMS_JOB_TYPE").contains("BatchTask")) {
				jobname="taksJob";
				
			} else if(System.getenv("OMS_JOB_TYPE").equalsIgnoreCase("Teacher_BatchTask_Get_Teachers")) {
				jobname="TEACHERS_GET";
				
			}
			
		}
		ApplicationContext context = new ClassPathXmlApplicationContext(jobname);

		 jobLauncher = context.getBean(JobLauncher.class);
		 job = context.getBean(jobname, Job.class);
		 JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		
		 System.out.println(jobExecution.getId());
		return SpringApplication.exit(context, new JobExecutionExitCodeGenerator());
		
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	

}
