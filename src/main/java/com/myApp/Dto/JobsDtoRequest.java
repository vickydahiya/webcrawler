package com.myApp.Dto;

import java.util.ArrayList;
import java.util.List;

public class JobsDtoRequest {

    public void setKey(String key) {
        this.key = key;
    }

    private String key;
    private List<String> urls = new ArrayList<>();

    public JobsDtoRequest(){

    }

    public JobsDtoRequest(String key, List<String> urls){
        this.key = key;
        this.urls = urls;
    }

    public List<String> getUrls() {
        return urls;
    }

    public String getKey() {
        return key;
    }
}
