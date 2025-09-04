package com.cb.daulat.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cb.daulat.dto.BookDTO;
import com.cb.daulat.service.BookService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO bookDto) {
        return new ResponseEntity<>(service.createBook(bookDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public BookDTO get(@PathVariable Long id) {
        return service.getBookById(id);
    }
    
    @GetMapping("/all")
    public List<BookDTO> list() {
        return service.bookList();
    }

    @GetMapping("/search")
    public Page<BookDTO> search(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishedFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishedTo,
//            @Parameter(hidden = true)
            @PageableDefault(size = 10, sort = "title") Pageable pageable
    ) {
        return service.search(q, authorId, genre, minPrice, maxPrice, publishedFrom, publishedTo, pageable);
    }

    @PutMapping("/{id}")
    public BookDTO update(@PathVariable Long id, @RequestBody BookDTO dto) {
        return service.updateBookById(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}
