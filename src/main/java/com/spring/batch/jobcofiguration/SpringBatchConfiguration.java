package com.spring.batch.jobcofiguration;

import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.spring.batch.dto.Teacher;
import com.spring.batch.jobreader.JobItemRead;
import com.spring.batch.jobwirter.JobItemWritter;

@Configuration
public class SpringBatchConfiguration {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SpringBatchConfiguration.class);

	@Bean
	public ItemReader<Teacher> itemReader(org.springframework.core.env.Environment environment,
			RestTemplate restTemplate) {
		LOGGER.info("jobItemRead Method calling :>>>");
		ItemReader<Teacher> jobItemRead = new JobItemRead(environment.getRequiredProperty("rest.api.url"), restTemplate);
		LOGGER.info("jobItemRead URL details :>>>" + jobItemRead);
		return jobItemRead;

	}

	@Bean
	public ItemWriter<Teacher> itemWriter() {

		JobItemWritter jobItemWritter = new JobItemWritter();
		LOGGER.info("jobItemWritter  details :>>>" + jobItemWritter);
		return jobItemWritter;
	}
	
	
	@Bean
	public Step jobStep(ItemReader<Teacher> itemReader,ItemWriter<Teacher> itemWriter,StepBuilderFactory stepBuilderFactory) {
		
		TaskletStep build = stepBuilderFactory.get("OMS_JOB_TYPE")
						  .<Teacher ,Teacher>chunk(100)
						  .reader(itemReader)
						  .writer(itemWriter)
						  .build();
		LOGGER.info("jobStep  details :>>>" + build);
		return build;
		
	}
	
	@Bean
	public Job jobExcution(Step step, JobBuilderFactory jobBuilderFactory) {
		
		Job build = jobBuilderFactory.get("OMS_JOB")
		                 .incrementer(new RunIdIncrementer())
		                 .flow(step)
		                 .end()
		                 .build();
		LOGGER.info("jobStep & jobExcution  details :>>>" + build);
		return build;
		
	}
}
