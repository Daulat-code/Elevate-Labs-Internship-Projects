package com.cb.daulat.mapper;

import com.cb.daulat.dto.AuthorDTO;
import com.cb.daulat.entity.AuthorEntity;

public class AuthorMapper {

    public static AuthorDTO toDTO(AuthorEntity author) {
        if (author == null) return null;
        AuthorDTO dto = new AuthorDTO();
        dto.setAuthorId(author.getId());
        dto.setAuthorName(author.getName());
        dto.setAuthorBiography(author.getBiography());
        return dto;
    }

    public static AuthorEntity toEntity(AuthorDTO dto) {
        if (dto == null) return null;
        AuthorEntity author = new AuthorEntity();
        author.setId(dto.getAuthorId());
        author.setName(dto.getAuthorName());
        author.setBiography(dto.getAuthorBiography());
        return author;
    }
}
