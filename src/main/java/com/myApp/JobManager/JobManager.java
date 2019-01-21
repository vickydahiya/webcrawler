package com.myApp.JobManager;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myApp.Repo.Jobs;
import com.myApp.Repo.JobsRepository;
import com.myApp.Repo.JobsResult;
import com.myApp.Repo.JobsResultRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class JobManager {
	
	@Autowired
	private JobsRepository jobsRepository;
	
	@Autowired
	private JobsResultRepository jobsResultRepository;
	
	@Autowired
	private JobWorker jobWorker;
	
	//private Set<String> pageVisited  = new HashSet<>();
	private List<String> pageToVisit = new LinkedList<>();
	private Map<String,List<String>> imageUrls = new HashMap<>();
	private final int LEVEL_OF_DEPTH = 1;
	private Long job_id;
	
	public JobManager() {
		
	}
	
	public JobManager(Long job_id) {
		this.job_id = job_id;
	}
	
	public void crawelUrl(Long job_id) {
		
		List<Jobs> subJobs = jobsRepository.findByParentId(job_id);
		
		for (Jobs job : subJobs) {
			
			Set<String> pageVisited  = new HashSet<>();
		
			String url = job.getURL();
			
			job.setStatus("inprogress");
			jobsRepository.save(job);
			
			pageToVisit.add(url);
			List<String> urlOnPage = jobWorker.crawlUrl(url);
			pageToVisit.addAll(urlOnPage);
			
			while (pageToVisit.size() > 0) {
				String s = pageToVisit.remove(0);
				if (!pageVisited.contains(s)) {
					pageVisited.add(s);
					List<String> imageOnPage = jobWorker.crawlUrlForImage(s);
					createResult(imageOnPage, job.getId(),job_id);
					imageUrls.put(s, imageOnPage);
				}
			}
			
			job.setStatus("completed");
			jobsRepository.save(job);
		}

	}
	
	private void createResult(List<String> imageOnPage, long subJobId, long parantJobId) {
		Set<String> sortDuplicateOut = new HashSet<>();
		for (String imageUrl: imageOnPage) {
			if (sortDuplicateOut.add(imageUrl)) {
				JobsResult jobsResult = JobsResult.builder()
						.imageUrl(imageUrl)
						.subJobId(subJobId)
						.parentJobId(parantJobId)
						.build();
						jobsResultRepository.save(jobsResult);
			}

		}
	}
	
	
}
