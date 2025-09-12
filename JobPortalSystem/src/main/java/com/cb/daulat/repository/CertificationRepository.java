package com.cb.daulat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cb.daulat.entity.CertificationEntity;
import com.cb.daulat.entity.UserEntity;

public interface CertificationRepository extends JpaRepository<CertificationEntity, Long> {
    List<CertificationEntity> findByUser(UserEntity user);
}
