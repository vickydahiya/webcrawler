package com.myApp;

import com.myApp.Dto.JobDtoResponse;
import com.myApp.Dto.JobsDtoRequest;
import com.myApp.Service.Jobs;
import com.myApp.Service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CrawlerController {

    public static long count;

    //private Jobs job;
    //@Autowired

    @RequestMapping(value = "/jobs", method = RequestMethod.POST)
    @ResponseBody
    public JobDtoResponse jobsScheduler(@RequestBody JobsDtoRequest jobsDtoRequest){


        JobsService jobsService = new JobsService();

        Jobs job = new Jobs(jobsDtoRequest);

        jobsDtoRequest.getUrls().stream().forEach(e -> System.out.println(e));
        System.out.println(jobsDtoRequest.getKey());

        //System.out.println(job.getId());

        try {
            System.out.println(job.getUrls());
            jobsService.addJobs(job);
            CrawlerController.count++;
        } catch ( NullPointerException npe){
            npe.printStackTrace();
        }

        JobDtoResponse jobDtoResponse = new JobDtoResponse(job);
        System.out.println(jobDtoResponse.getId());
        return jobDtoResponse;
    }
}