package com.cb.daulat.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cb.daulat.dto.AuthorDTO;
import com.cb.daulat.entity.AuthorEntity;
import com.cb.daulat.mapper.AuthorMapper;
import com.cb.daulat.repo.AuthorRepository;
import com.cb.daulat.service.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repo;

    public AuthorServiceImpl(AuthorRepository repo) {
        this.repo = repo;
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO dto) {
        AuthorEntity author = new AuthorEntity();
        author.setName(dto.getAuthorName());
        author.setBiography(dto.getAuthorBiography());
        return AuthorMapper.toDTO(repo.save(author));
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDTO getAuthorById(Long id) {
        return repo.findById(id)
                .map(AuthorMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Author not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDTO> autherList() {
        return repo.findAll().stream()
                .map(AuthorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDTO updateAuthorById(Long id, AuthorDTO dto) {
        AuthorEntity author = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author not found with id " + id));
        author.setName(dto.getAuthorName());
        author.setBiography(dto.getAuthorBiography());
        return AuthorMapper.toDTO(repo.save(author));
    }

    @Override
    public void deleteAuthorById(Long id) {
        repo.deleteById(id);
    }
}
