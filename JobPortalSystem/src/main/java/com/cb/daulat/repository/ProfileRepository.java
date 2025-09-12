package com.cb.daulat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cb.daulat.entity.ProfileEntity;
import com.cb.daulat.entity.UserEntity;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    Optional<ProfileEntity> findByUser(UserEntity user);
}