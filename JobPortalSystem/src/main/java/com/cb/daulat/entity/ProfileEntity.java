package com.cb.daulat.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProfileEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

    private String fullName;
    private String phone;
    private String location;

    @Column(length = 2000)
    private String summary;

    private String picturePath; // store filename or URL
}
