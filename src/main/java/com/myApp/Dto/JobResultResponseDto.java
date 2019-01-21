package com.myApp.Dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobResultResponseDto {
	
    private long id;
    private Map<String,List<String>> results = new HashMap<>();
    
    public JobResultResponseDto() {
    	
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Map<String, List<String>> getResults() {
		return results;
	}

	public void setResults(Map<String, List<String>> results) {
		this.results = results;
	}
    
}
