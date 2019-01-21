package com.myApp.Repo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

import lombok.Builder;

@Builder
@Entity
public class JobsResult {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "jobresult_entity_seq_gen")
    @SequenceGenerator(name= "jobresult_entity_seq_gen", sequenceName = "JOBS_RESULT_SEQUENCE")
    private long id;
	@Column(name="IMAGE_URL", columnDefinition="CLOB NOT NULL") 
	@Lob 
    private String imageUrl;
    private long subJobId;
    private long parentJobId;
    

	public JobsResult() {
    	
    }

    public JobsResult(long id, String imageUrl, long subJobId, long parentJobId) {
		super();
		this.id = id;
		this.imageUrl = imageUrl;
		this.subJobId = subJobId;
		this.parentJobId = parentJobId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
    public long getSubJobId() {
		return subJobId;
	}

	public void setSubJobId(long subJobId) {
		this.subJobId = subJobId;
	}

	public long getParentJobId() {
		return parentJobId;
	}

	public void setParentJobId(long parentJobId) {
		this.parentJobId = parentJobId;
	}
}
