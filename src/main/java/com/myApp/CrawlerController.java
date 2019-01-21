package com.myApp;

import com.myApp.Dto.StatusResponseDto;
import com.myApp.Dto.JobResultResponseDto;
import com.myApp.Dto.JobsRequestDto;
import com.myApp.JobDaoService.JobsService;
import com.myApp.Repo.Jobs;
import com.myApp.Repo.JobsResult;
import com.myApp.Utils.ApplicationConst;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CrawlerController {
	
	@Autowired
	private JobsService jobService;
	
	@PostMapping(path = "/jobs")
	public ResponseEntity<Map<String,Long>> createJob(@RequestBody JobsRequestDto jobRequestDto ) {
		
		long id = jobService.createJob(jobRequestDto);
		
		ResponseEntity<Map<String,Long>> response = null;
		
		
		Map<String,Long> job = new HashMap<>();
		
		job.put("id", id);
		response = new ResponseEntity<Map<String, Long>>(job, HttpStatus.ACCEPTED);
		return response;
		//Change the void to return job_ID
		//Things to do
		//1) Create a job and insert it into jobs table
 		//2) Take the input URL's which are in post body and call the service
		//3) return the job id 
		//Service will do the following
 		//4) inster the URL's in URL table
		//5) call the crawler to crawle the URL's
		//6) update the jobs table from in prgress to finish once done
		 
	}
	
	@GetMapping(path = "/jobs/{id}/status")
	public StatusResponseDto getStatus(@PathVariable Long id) {
		
		return jobService.findStatus(id);
		//Change the void to return Jobs object
		//Things to do
		//1) Get the job object for input id
		//2) return the job object
		
	}
	
	@GetMapping(path = "/jobs/{id}/results")
	public JobResultResponseDto getResults(@PathVariable Long id) {
		
		return jobService.findResult(id);
		
		//Change the void to return results object
		//Things to do
		//1) check job status, if job is done return the result object
		//2) If job is not done, send error
	}
}