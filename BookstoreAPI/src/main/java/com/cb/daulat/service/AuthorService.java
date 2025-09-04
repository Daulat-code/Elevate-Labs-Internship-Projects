package com.cb.daulat.service;

import java.util.List;

import com.cb.daulat.dto.AuthorDTO;

public interface AuthorService {

    AuthorDTO createAuthor(AuthorDTO dto);

    AuthorDTO getAuthorById(Long id);

    List<AuthorDTO> autherList();

    AuthorDTO updateAuthorById(Long id, AuthorDTO dto);

    void deleteAuthorById(Long id);
}
