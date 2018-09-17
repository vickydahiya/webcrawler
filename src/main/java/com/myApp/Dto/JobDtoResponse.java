package com.myApp.Dto;

import com.myApp.Service.Jobs;
import org.springframework.beans.factory.annotation.Autowired;

public class JobDtoResponse {


    public JobDtoResponse() {
    	
    }

    private long id;

    public JobDtoResponse(Jobs job){
        this.id = job.getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}