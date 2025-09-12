package com.cb.daulat.service.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cb.daulat.dto.EmployerRequest;
import com.cb.daulat.dto.EmployerResponse;
import com.cb.daulat.entity.EmployerEntity;
import com.cb.daulat.repository.EmployerRepository;
import com.cb.daulat.repository.UserRepo;
import com.cb.daulat.service.EmployerService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployerServiceImpl implements EmployerService {

    private final EmployerRepository employerRepository;
    private final UserRepo userRepository;

    public EmployerServiceImpl(EmployerRepository employerRepository, UserRepo userRepository) {
        this.employerRepository = employerRepository;
        this.userRepository = userRepository;
    }

    public EmployerResponse createEmployer(EmployerRequest request, String authenticatedUserEmail) {
        return userRepository.findByEmail(authenticatedUserEmail)
            .flatMap(user -> {
                if (employerRepository.findByUser(user).isPresent()) {
                    // choose to return empty to signal conflict (then handle with orElseThrow)
                    return Optional.<EmployerResponse>empty();
                }
                EmployerEntity employer = new EmployerEntity();
                employer.setUser(user);
                mapRequestToEntity(request, employer);
                employer.setCreatedAt(LocalDateTime.now());
                employer.setUpdatedAt(LocalDateTime.now());
                EmployerEntity saved = employerRepository.save(employer);
                return Optional.of(mapToResponse(saved));
            })
            .orElseThrow(() -> new RuntimeException("Authenticated user not found or profile already exists"));
    }

    @Override
    public EmployerResponse updateEmployer(Long id, EmployerRequest request, String authenticatedUserEmail) {
    	EmployerEntity existing = employerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employer not found"));

        // only owner (or admin) should update — here we check owner
        if (!existing.getUser().getEmail().equals(authenticatedUserEmail)) {
            throw new RuntimeException("Not authorized to update this employer profile");
        }

        mapRequestToEntity(request, existing);
        existing.setUpdatedAt(LocalDateTime.now());
        EmployerEntity saved = employerRepository.save(existing);
        return mapToResponse(saved);
    }

    @Override
    public EmployerResponse getEmployerById(Long id) {
    	EmployerEntity e = employerRepository.findById(id).orElseThrow(() -> new RuntimeException("Employer not found"));
        return mapToResponse(e);
    }

    @Override
    public EmployerResponse getEmployerByUserEmail(String userEmail) {
    	EmployerEntity e = employerRepository.findByUser_Email(userEmail).orElseThrow(() -> new RuntimeException("Employer not found for user"));
        return mapToResponse(e);
    }

    @Override
    public List<EmployerResponse> listAllEmployers() {
        return employerRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public void deleteEmployer(Long id, String authenticatedUserEmail) {
    	EmployerEntity e = employerRepository.findById(id).orElseThrow(() -> new RuntimeException("Employer not found"));
        if (!e.getUser().getEmail().equals(authenticatedUserEmail)) {
            throw new RuntimeException("Not authorized to delete this employer profile");
        }
        employerRepository.delete(e);
    }

    // --- helper mappers
    private EmployerResponse mapToResponse(EmployerEntity e) {
        EmployerResponse r = new EmployerResponse();
        r.setId(e.getId());
        r.setUserId(e.getUser() != null ? e.getUser().getId() : null);
        r.setUserEmail(e.getUser() != null ? e.getUser().getEmail() : null);
        r.setCompanyName(e.getCompanyName());
        r.setCompanyDescription(e.getCompanyDescription());
        r.setWebsite(e.getWebsite());
        r.setAddress(e.getAddress());
        r.setPhone(e.getPhone());
        r.setLogoPath(e.getLogoPath());
        r.setCreatedAt(e.getCreatedAt());
        r.setUpdatedAt(e.getUpdatedAt());
        return r;
    }

    private void mapRequestToEntity(EmployerRequest req, EmployerEntity e) {
        if (req.getCompanyName() != null) e.setCompanyName(req.getCompanyName());
        if (req.getCompanyDescription() != null) e.setCompanyDescription(req.getCompanyDescription());
        if (req.getWebsite() != null) e.setWebsite(req.getWebsite());
        if (req.getAddress() != null) e.setAddress(req.getAddress());
        if (req.getPhone() != null) e.setPhone(req.getPhone());
        if (req.getLogoPath() != null) e.setLogoPath(req.getLogoPath());
    }
}
