package com.cb.daulat.service;

import java.util.List;

import com.cb.daulat.dto.EmployerRequest;
import com.cb.daulat.dto.EmployerResponse;

public interface EmployerService {
	
	public EmployerResponse createEmployer(EmployerRequest request, String authenticatedUserEmail);
	public EmployerResponse updateEmployer(Long id, EmployerRequest request, String authenticatedUserEmail);
	public EmployerResponse getEmployerById(Long id);
	public EmployerResponse getEmployerByUserEmail(String userEmail);
	public List<EmployerResponse> listAllEmployers();
	public void deleteEmployer(Long id, String authenticatedUserEmail);

	
}
