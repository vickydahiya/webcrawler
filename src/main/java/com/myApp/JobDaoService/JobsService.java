package com.myApp.JobDaoService;

import com.myApp.Dto.JobResultResponseDto;
import com.myApp.Dto.JobsRequestDto;
import com.myApp.Dto.StatusResponseDto;
import com.myApp.JobManager.JobManager;
import com.myApp.Repo.Jobs;
import com.myApp.Repo.JobsRepository;
import com.myApp.Repo.JobsResult;
import com.myApp.Repo.JobsResultRepository;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class JobsService {
	
    private JobsRepository jobsRepository;
    private JobsResultRepository jobsResultRepository;
    private StatusResponseDto statusResponseDto;
    private JobResultResponseDto jobResultDto;
    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    
    @Autowired
    JobManager jobsManager;
    
    //@Autowired
    //private KafkaTemplate<String, String> kafkaTemplate;
    

    @Autowired 
    public JobsService(JobsRepository jobsRepository, JobsResultRepository jobsResultRepository) {
		super();
		this.jobsRepository = jobsRepository;
		this.jobsResultRepository = jobsResultRepository;
		//this.statusResponseDto = statusResponseDto;
	}
    
    public long createJob(JobsRequestDto jobsRequestDto) {
    	
    	for (String s: jobsRequestDto.getUrls()) {
    		System.out.println(s);
    	}
    	

    	Jobs job = Jobs.builder()
    			.URL("Main Job")
    			.parentId(null)
    			.status(null)
    			.build();
    	
       	job = jobsRepository.save(job);
    	Long job_id = job.getId();
    	this.createSubJobs(jobsRequestDto, job_id);
    	//executorService.submit(() ->kafkaTemplate.send("jobs", String.valueOf(job_id)));
    	executorService.submit(() -> jobsManager.crawelUrl(job_id), null);
    	//jobsManager.crawelUrl(job.getId());

    	return job.getId();
    }
    
    public StatusResponseDto findStatus(Long id){
          List<Jobs> subJobs = jobsRepository.findByParentId(id);
          
          Map<String,Integer> result = new HashMap<>();
          
          for (Jobs job : subJobs) {
        	  String status = job.getStatus();
        	  if (!result.containsKey(status)) {
        		  result.put(status, 1);
        	  } else {
        		  result.put(status, result.get(status)+1);
        	  }
          }
          if (statusResponseDto == null) {
        	  statusResponseDto = new StatusResponseDto();
          }
          statusResponseDto.setId(id);
          statusResponseDto.setStatus(result);
          return statusResponseDto; 
    }
    
    public JobResultResponseDto findResult(Long id) {
    	List<Jobs> subJobs = jobsRepository.findByParentId(id);
    	
    	Map<String,List<String>> imageUrls = new HashMap<>();
    	
        for (Jobs job : subJobs) {
      	  long subJobId = job.getId();
      	  List<JobsResult> subJobResult = jobsResultRepository.findBySubJobId(subJobId);
      	  for (JobsResult jobResult: subJobResult) {
          	  if (!imageUrls.containsKey(job.getURL())) {
          		List<String> list = new ArrayList<String>();
          		list.add(jobResult.getImageUrl());
          		imageUrls.put(job.getURL(), list);
          	  } else {
          		  List<String> list = imageUrls.get(job.getURL());
          		  list.add(jobResult.getImageUrl());
          		  imageUrls.put(job.getURL(), list);
          	  }
      	  }

        }
        
        if (jobResultDto == null) {
        	jobResultDto = new JobResultResponseDto();
        }
        jobResultDto.setId(id);
        jobResultDto.setResults(imageUrls);;
    	
    	return jobResultDto;
    }
    
    private void createSubJobs(JobsRequestDto jobsRequestDto, Long job_id) {
    	for (String s: jobsRequestDto.getUrls()) {
    		Jobs subjob = Jobs.builder()
    				.URL(s)
    				.parentId(job_id)
    				.status("created")
    				.build();

        	if (subjob != null)
        		jobsRepository.save(subjob);
    	}
    }
    
}
