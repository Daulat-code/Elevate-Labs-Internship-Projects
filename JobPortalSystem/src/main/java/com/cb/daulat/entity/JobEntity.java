package com.cb.daulat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "jobs")
public class JobEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    private String company;

    private String location;

    private String jobType; // Full-time, Part-time, Contract

    private Integer minExperience; // years

    private Long salaryFrom;
    private Long salaryTo;

    private boolean published = true; // visibility public/private could be an enum

    private LocalDateTime postedAt = LocalDateTime.now();


    @ManyToOne
    @JoinColumn(name="employer_id")
    private UserEntity employer;
}
