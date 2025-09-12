package com.cb.daulat.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import com.cb.daulat.entity.JobEntity;
import com.cb.daulat.service.serviceImpl.JobServiceImpl;

@Controller
public class JobController {
	private final JobServiceImpl jobService;

    public JobController(JobServiceImpl jobService) {
        this.jobService = jobService;
    }
    
    @GetMapping("/show")
    public String showCreateForm() {
        return "employer/editEmployer"; 
    }
    
    @GetMapping("/createJob")
    public String showCreateForm(Model model) {
        model.addAttribute("job", new JobEntity());
        return "employer/CreateJob"; // loads createJob.html
    }

    @PostMapping("/createJob")
    public ResponseEntity<JobEntity> createJob(@RequestBody JobEntity job, Authentication auth) {
        String email = auth.getName();
        JobEntity saved = jobService.createJob(job, email);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<Page<JobEntity>> searchJobs(@RequestParam(required = false) String q,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        Page<JobEntity> p = jobService.searchJobs(q, page, size);
        return ResponseEntity.ok(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobEntity> getJob(@PathVariable Long id) {
        return jobService.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
}
