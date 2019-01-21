package com.myApp.Repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsResultRepository extends CrudRepository<JobsResult,Long>{
	List<JobsResult> findBySubJobId(Long subJobId);
	List<JobsResult> findByParentJobId(Long parentJobId);
}
