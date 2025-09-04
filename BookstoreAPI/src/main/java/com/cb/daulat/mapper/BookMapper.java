package com.cb.daulat.mapper;

import com.cb.daulat.dto.BookDTO;
import com.cb.daulat.entity.AuthorEntity;
import com.cb.daulat.entity.BookEntity;

public class BookMapper {

    public static BookDTO toDTO(BookEntity book) {
        if (book == null) return null;
        BookDTO dto = new BookDTO();
        dto.setBookId(book.getId());
        dto.setBookTitle(book.getTitle());
        dto.setBookGenre(book.getGenre());
        dto.setBookPublishedDate(book.getPublishedDate());
        dto.setBookPrice(book.getPrice());
        dto.setBookAuthorId(book.getAuthor().getId());
        return dto;
    }

    public static BookEntity toEntity(BookDTO dto, AuthorEntity author) {
        if (dto == null) return null;
        BookEntity book = new BookEntity();
        book.setId(dto.getBookId());
        book.setTitle(dto.getBookTitle());
        book.setGenre(dto.getBookGenre());
        book.setPublishedDate(dto.getBookPublishedDate());
        book.setPrice(dto.getBookPrice());
        book.setAuthor(author);
        return book;
    }
}
