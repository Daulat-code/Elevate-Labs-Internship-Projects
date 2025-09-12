package com.cb.daulat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cb.daulat.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
