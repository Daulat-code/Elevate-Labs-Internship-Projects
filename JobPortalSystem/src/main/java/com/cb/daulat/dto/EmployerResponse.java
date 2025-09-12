package com.cb.daulat.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployerResponse {
    private Long id;
    private Long userId;
    private String userEmail;
    private String companyName;
    private String companyDescription;
    private String website;
    private String address;
    private String phone;
    private String logoPath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public EmployerResponse() {}

}
