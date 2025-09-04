package com.cb.daulat.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cb.daulat.dto.BookDTO;
import com.cb.daulat.entity.AuthorEntity;
import com.cb.daulat.entity.BookEntity;
import com.cb.daulat.mapper.BookMapper;
import com.cb.daulat.repo.AuthorRepository;
import com.cb.daulat.repo.BookRepository;
import com.cb.daulat.service.BookService;
import com.cb.daulat.spec.BookSpecifications;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;

    public BookServiceImpl(BookRepository bookRepo, AuthorRepository authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    @Override
    public BookDTO createBook(BookDTO dto) {
        AuthorEntity author = authorRepo.findById(dto.getBookAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found with id " + dto.getBookAuthorId()));

        BookEntity book = new BookEntity();
        book.setTitle(dto.getBookTitle());
        book.setPublishedDate(dto.getBookPublishedDate());
        book.setPrice(dto.getBookPrice());
        book.setGenre(dto.getBookGenre());
        book.setAuthor(author);

        return BookMapper.toDTO(bookRepo.save(book));
    }

    @Override
    @Transactional(readOnly = true)
    public BookDTO getBookById(Long id) {
        return bookRepo.findById(id)
                .map(BookMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id " + id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> bookList() {
        return bookRepo.findAll().stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookDTO> search(String q, Long authorId, String genre,
                                BigDecimal minPrice, BigDecimal maxPrice,
                                LocalDate publishedFrom, LocalDate publishedTo,
                                Pageable pageable) {
        Specification<BookEntity> spec = Specification.where(BookSpecifications.titleContains(q))
                .and(BookSpecifications.authorIdEquals(authorId))
                .and(BookSpecifications.genreEquals(genre))
                .and(BookSpecifications.priceBetween(minPrice, maxPrice))
                .and(BookSpecifications.publishedDateBetween(publishedFrom, publishedTo));

        return bookRepo.findAll(spec, pageable).map(BookMapper::toDTO);
    }

    @Override
    public BookDTO updateBookById(Long id, BookDTO dto) {
        BookEntity book = bookRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id " + id));

        book.setTitle(dto.getBookTitle());
        book.setPublishedDate(dto.getBookPublishedDate());
        book.setPrice(dto.getBookPrice());
        book.setGenre(dto.getBookGenre());

        if (dto.getBookAuthorId() != null) {
            AuthorEntity author = authorRepo.findById(dto.getBookAuthorId())
                    .orElseThrow(() -> new IllegalArgumentException("Author not found with id " + dto.getBookAuthorId()));
            book.setAuthor(author);
        }

        return BookMapper.toDTO(bookRepo.save(book));
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepo.deleteById(id);
    }
}
