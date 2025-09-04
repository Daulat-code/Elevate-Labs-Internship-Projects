package com.cb.daulat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cb.daulat.entity.BookEntity;


public interface BookRepository extends JpaRepository<BookEntity, Long>,JpaSpecificationExecutor<BookEntity>, PagingAndSortingRepository<BookEntity, Long> {
}
