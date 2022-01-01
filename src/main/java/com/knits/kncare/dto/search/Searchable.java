package com.knits.kncare.dto.search;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public interface Searchable<T> {
    Specification<T> getSpecification();
    Pageable getPageable();
    Sort getSortSpec();
}
