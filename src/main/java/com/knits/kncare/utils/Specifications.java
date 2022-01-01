package com.knits.kncare.utils;

import java.time.LocalDateTime;

public class Specifications {

    public static <T> org.springframework.data.jpa.domain.Specification<T> specAfter(String columnName, LocalDateTime after) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get(columnName), after);
    }

    public static <T> org.springframework.data.jpa.domain.Specification<T> specBefore(String columnName, LocalDateTime before) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get(columnName), before);
    }

    public static <T> org.springframework.data.jpa.domain.Specification<T> specEquals(String columnName, Object value) {
        return (root, query, builder) -> builder.equal(root.get(columnName), value);
    }

    public static <T> org.springframework.data.jpa.domain.Specification<T> specLike(String columnName, String value) {
        return (root, query, builder) -> builder.like(root.get(columnName), value);
    }
}
