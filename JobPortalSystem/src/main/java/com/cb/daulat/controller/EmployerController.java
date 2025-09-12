package com.cb.daulat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cb.daulat.dto.EmployerRequest;
import com.cb.daulat.dto.EmployerResponse;
import com.cb.daulat.entity.EmployerEntity;
import com.cb.daulat.service.EmployerService;

import java.security.Principal;
import java.util.List;

@Controller
public class EmployerController {

    private final EmployerService employerService;

    public EmployerController(EmployerService employerService) { this.employerService = employerService; }

    
    @GetMapping("/EmployerRegister")
    public String showCreateForm(Model model) {
        model.addAttribute("employer", new EmployerEntity());
        return "employer/register";
    }
    
    @GetMapping("/employerProfile")
    public String showProfile(Model model, Principal principal) {
        String userEmail = principal.getName();
        EmployerResponse employer = employerService.getEmployerByUserEmail(userEmail);
        model.addAttribute("employer", employer);
        return "employerProfile";
    }
    
    @GetMapping("/updateEmployer")
    public String editForm(@PathVariable Long id, Model model) {
        EmployerResponse e = employerService.getEmployerById(id);
        model.addAttribute("employer", e);
        return "editEmployer";
    }
    
    // Create employer profile for authenticated user
    @PostMapping("/createEmployer")
    public ResponseEntity<EmployerResponse> createEmployer(@RequestBody EmployerRequest request,
                                                           Authentication auth) {
        String email = auth.getName();
        EmployerResponse resp = employerService.createEmployer(request, email);
        return ResponseEntity.ok(resp);
    }

    // Update existing employer profile by id (only owner allowed)
    @PutMapping("/updateEmployer{id}")
    public ResponseEntity<EmployerResponse> updateEmployer(@PathVariable Long id,
                                                           @RequestBody EmployerRequest request,
                                                           Authentication auth) {
        String email = auth.getName();
        EmployerResponse resp = employerService.updateEmployer(id, request, email);
        return ResponseEntity.ok(resp);
    }

    // Get current user's employer profile
    @GetMapping("/me")
    public ResponseEntity<EmployerResponse> myEmployer(Authentication auth) {
        String email = auth.getName();
        EmployerResponse resp = employerService.getEmployerByUserEmail(email);
        return ResponseEntity.ok(resp);
    }

    // Public: get employer profile by id
    @GetMapping("/getEmployerById{id}")
    public ResponseEntity<EmployerResponse> getById(@PathVariable Long id) {
        EmployerResponse resp = employerService.getEmployerById(id);
        return ResponseEntity.ok(resp);
    }

    // List all employers (could be paged/filtered later)
    @GetMapping("/list")
    public ResponseEntity<List<EmployerResponse>> listAll() {
        List<EmployerResponse> list = employerService.listAllEmployers();
        return ResponseEntity.ok(list);
    }

    // Delete (owner only)
    @DeleteMapping("/deleteEmployerById{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication auth) {
        String email = auth.getName();
        employerService.deleteEmployer(id, email);
        return ResponseEntity.noContent().build();
    }
}

