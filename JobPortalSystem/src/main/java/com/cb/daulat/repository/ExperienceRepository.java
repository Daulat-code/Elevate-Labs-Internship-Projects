package com.cb.daulat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cb.daulat.entity.ExperienceEntity;
import com.cb.daulat.entity.UserEntity;

public interface ExperienceRepository extends JpaRepository<ExperienceEntity, Long> {
    List<ExperienceEntity> findByUser(UserEntity user);
}
