package com.cb.daulat.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private double price;
    private LocalDate publishedDate;
    private String genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;
}
