package com.myApp.Repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepository extends CrudRepository<Jobs,Long> {
	List<Jobs> findByParentId(Long parentId);

}
