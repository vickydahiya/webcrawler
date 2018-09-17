package com.myApp.Service;

import com.myApp.Repo.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class JobsService {

    @Autowired
    JobsRepository jobsRepository;

    public void addJobs(Jobs job){
        jobsRepository.save(job);
    }

    public Optional<Jobs> retriveJob(Long id){
        return  jobsRepository.findById(id);
    }
}
