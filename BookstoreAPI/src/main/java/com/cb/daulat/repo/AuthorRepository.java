package com.cb.daulat.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cb.daulat.entity.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
}
