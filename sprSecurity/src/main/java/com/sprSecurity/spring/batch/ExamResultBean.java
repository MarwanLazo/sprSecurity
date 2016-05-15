package com.sprSecurity.spring.batch;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;

public class ExamResultBean {
	@Inject
	private JobLauncher	launcher;
	@Inject
	private Job			job;

	@PostConstruct
	private void init() {
//		try {
//			JobExecution execution = launcher.run(job, new JobParameters());
//			System.out.println("Job Exit Status : " + execution.getStatus());
//
//		} catch (JobExecutionException e) {
//			System.out.println("Job ExamResult failed");
//			e.printStackTrace();
//		}
	}
}
