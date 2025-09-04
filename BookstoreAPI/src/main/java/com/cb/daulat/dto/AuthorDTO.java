package com.cb.daulat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDTO {
    private Long authorId;

    @NotBlank
    @Size(min = 2, max = 100)
    private String authorName;

    private String authorBiography;
}
