package com.cb.daulat.spec;

import org.springframework.data.jpa.domain.Specification;

import com.cb.daulat.entity.BookEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookSpecifications {

    public static Specification<BookEntity> titleContains(String keyword) {
        return (root, query, cb) ->
                (keyword == null || keyword.isEmpty())
                        ? null
                        : cb.like(cb.lower(root.get("title")), "%" + keyword.toLowerCase() + "%");
    }

    public static Specification<BookEntity> authorIdEquals(Long authorId) {
        return (root, query, cb) ->
                (authorId == null)
                        ? null
                        : cb.equal(root.get("author").get("id"), authorId);
    }

    public static Specification<BookEntity> genreEquals(String genre) {
        return (root, query, cb) ->
                (genre == null || genre.isEmpty())
                        ? null
                        : cb.equal(cb.lower(root.get("genre")), genre.toLowerCase());
    }

    public static Specification<BookEntity> priceBetween(BigDecimal min, BigDecimal max) {
        return (root, query, cb) -> {
            if (min != null && max != null) {
                return cb.between(root.get("price"), min, max);
            } else if (min != null) {
                return cb.greaterThanOrEqualTo(root.get("price"), min);
            } else if (max != null) {
                return cb.lessThanOrEqualTo(root.get("price"), max);
            } else {
                return null;
            }
        };
    }

    public static Specification<BookEntity> publishedDateBetween(LocalDate from, LocalDate to) {
        return (root, query, cb) -> {
            if (from != null && to != null) {
                return cb.between(root.get("publishedDate"), from, to);
            } else if (from != null) {
                return cb.greaterThanOrEqualTo(root.get("publishedDate"), from);
            } else if (to != null) {
                return cb.lessThanOrEqualTo(root.get("publishedDate"), to);
            } else {
                return null;
            }
        };
    }
}