package com.cb.daulat.service.serviceImpl;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.cb.daulat.entity.JobEntity;
import com.cb.daulat.entity.UserEntity;
import com.cb.daulat.repository.JobRepository;
import com.cb.daulat.repository.UserRepo;
import com.cb.daulat.service.JobService;

import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final UserRepo userRepository;

    public JobServiceImpl(JobRepository jobRepository, UserRepo userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public JobEntity createJob(JobEntity job, String employerEmail) {
        UserEntity emp = userRepository.findByEmail(employerEmail).orElseThrow(() -> new RuntimeException("Employer not found"));
        job.setEmployer(emp);
        return jobRepository.save(job);
    }

    @Override
    public Page<JobEntity> searchJobs(String q, int page, int size) {
        Pageable p = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "postedAt"));
        if (q == null || q.isBlank()) {
            return jobRepository.findAll(p);
        }
        return jobRepository.findByTitleContainingIgnoreCase(q, p);
    }

    @Override
    public Optional<JobEntity> getById(Long id) { return jobRepository.findById(id); }
}
