package com.cb.daulat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cb.daulat.entity.EmployerEntity;
import com.cb.daulat.entity.UserEntity;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<EmployerEntity, Long> {
    Optional<EmployerEntity> findByUser(UserEntity user);
    Optional<EmployerEntity> findByUser_Email(String email); // convenient lookup by user email
}
