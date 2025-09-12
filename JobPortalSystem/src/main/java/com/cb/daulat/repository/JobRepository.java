package com.cb.daulat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cb.daulat.entity.JobEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobRepository extends JpaRepository<JobEntity, Long> {
    Page<JobEntity> findByTitleContainingIgnoreCase(String q, Pageable pageable);
    Page<JobEntity> findByCompanyContainingIgnoreCase(String company, Pageable pageable);
    // add custom queries as needed
}

