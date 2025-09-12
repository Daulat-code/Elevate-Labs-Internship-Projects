package com.cb.daulat.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cb.daulat.entity.SkillEntity;
import com.cb.daulat.entity.UserEntity;

public interface SkillRepository extends JpaRepository<SkillEntity, Long> {
    List<SkillEntity> findByUser(UserEntity user);
}
