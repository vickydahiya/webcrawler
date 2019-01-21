package com.myApp.Repo;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;

@Builder
@Entity
public class Jobs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "job_entity_seq_gen")
    @SequenceGenerator(name= "job_entity_seq_gen", sequenceName = "JOBS_SEQUENCE")
    private Long id;
	private String status;
    private Long parentId;
    private String URL;
    
    public Jobs() {
    	
    }
    
    public Jobs(Long id, String status, Long parent_id, String uRL) {
		super();
		this.id = id;
		this.status = status;
		this.parentId = parent_id;
		URL = uRL;
	}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getParent_id() {
		return parentId;
	}

	public void setParent_id(Long parent_id) {
		this.parentId = parent_id;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}
}
