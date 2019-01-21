package com.myApp.Dto;


import java.util.HashSet;
import java.util.Set;


public class JobsRequestDto {

    private Set<String> urls = new HashSet<>();

    public JobsRequestDto(){
    	
    }
    
    public JobsRequestDto(Set<String> urls){
        this.urls = urls;
    }

    public Set<String> getUrls() {
        return urls;
    }
}
