package com.cb.daulat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookDTO {
    private Long bookId;

    @NotBlank
    @Size(min = 1, max = 200)
    private String bookTitle;
    private String bookGenre;
    private LocalDate bookPublishedDate;
    private double bookPrice;
    @NotNull
    private Long bookAuthorId;

}
