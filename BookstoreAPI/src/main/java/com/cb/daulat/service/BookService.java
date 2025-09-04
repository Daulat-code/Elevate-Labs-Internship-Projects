package com.cb.daulat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cb.daulat.dto.BookDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {

    BookDTO createBook(BookDTO bookDto);

    BookDTO getBookById(Long id);
    
    List<BookDTO> bookList();

    Page<BookDTO> search(String q,
                         Long authorId,
                         String genre,
                         BigDecimal minPrice,
                         BigDecimal maxPrice,
                         LocalDate publishedFrom,
                         LocalDate publishedTo,
                         Pageable pageable);

    BookDTO updateBookById(Long id, BookDTO dto);

    void deleteBookById(Long id);
}
