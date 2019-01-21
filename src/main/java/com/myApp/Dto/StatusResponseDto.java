package com.myApp.Dto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myApp.Repo.Jobs;

public class StatusResponseDto {


    public StatusResponseDto() {
    	
    }

    private long id;
    private Map<String,Integer> status = new HashMap<>();

    public StatusResponseDto(Long id){
        this.id = id;
        status = new HashMap<>();
    }

    public Map<String, Integer> getStatus() {
		return status;
	}

	public void setStatus(Map<String, Integer> status) {
		this.status = status;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}