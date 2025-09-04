package com.cb.daulat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cb.daulat.dto.AuthorDTO;
import com.cb.daulat.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<AuthorDTO> create(@RequestBody AuthorDTO dto) {
        return new ResponseEntity<>(service.createAuthor(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public AuthorDTO get(@PathVariable Long id) {
        return service.getAuthorById(id);
    }

    @GetMapping("/all")
    public List<AuthorDTO> list() {
        return service.autherList();
    }

    @PutMapping("/{id}")
    public AuthorDTO update(@PathVariable Long id, @RequestBody AuthorDTO dto) {
        return service.updateAuthorById(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }
}
