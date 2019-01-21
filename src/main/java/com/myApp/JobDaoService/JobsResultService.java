package com.myApp.JobDaoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.myApp.Dto.JobResultResponseDto;
import com.myApp.JobManager.JobManager;
import com.myApp.Repo.Jobs;
import com.myApp.Repo.JobsResult;
import com.myApp.Repo.JobsResultRepository;

@Service
public class JobsResultService {
	
	private JobsResultRepository jobsResultRepository;
	
    @Autowired
    JobManager jobsManager;
	
	@Autowired
	public JobsResultService(JobsResultRepository jobsResultRepository) {
		super();
		this.jobsResultRepository = jobsResultRepository;
	}
	
	//@KafkaListener(topics = "jobs", groupId = "group-id")
	public void scanPendingJobs(String message) {
		//Long job_id = new Long();
		System.out.println(Long.valueOf(message));
		jobsManager.crawelUrl(Long.valueOf(message));
	}
}
