package com.cb.daulat.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.cb.daulat.entity.JobEntity;

public interface JobService {
	
	public JobEntity createJob(JobEntity job, String employerEmail);
	public Page<JobEntity> searchJobs(String q, int page, int size);
	public Optional<JobEntity> getById(Long id);

}
