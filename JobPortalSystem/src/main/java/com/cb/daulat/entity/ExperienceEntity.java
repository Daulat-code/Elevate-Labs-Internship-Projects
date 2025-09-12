package com.cb.daulat.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ExperienceEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String company;
    private String role;
    private Integer years;
    @ManyToOne @JoinColumn(name="user_id")
    private UserEntity user;
}
