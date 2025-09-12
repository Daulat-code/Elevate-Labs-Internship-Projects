package com.cb.daulat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployerRequest {
    private String companyName;
    private String companyDescription;
    private String website;
    private String address;
    private String phone;
    private String logoPath; // optional: filename or URL

}
