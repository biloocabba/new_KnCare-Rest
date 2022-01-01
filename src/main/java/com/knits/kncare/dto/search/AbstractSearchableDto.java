package com.knits.kncare.dto.search;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.knits.kncare.dto.search.Searchable;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@Data
public abstract class AbstractSearchableDto<T> implements Searchable<T> {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer limit;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer page;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String sort;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Sort.Direction dir;

    @Override
    @JsonIgnore
    public Specification<T> getSpecification() {
        Specification<T> spec = Specification.where(null);
        // TODO: 26/07/2021 add search by date
        return spec;
    }

    @Override
    @JsonIgnore
    public Pageable getPageable() {
        return PageRequest.of(
                (page != null) ? page : 0,
                (limit != null && limit >= 0) ? limit : 200,
                getSortSpec()
        );
    }

    @Override
    @JsonIgnore
    public Sort getSortSpec() {
        if (sort == null) return Sort.unsorted();
        return (dir != null && dir == Sort.Direction.DESC) ?
                Sort.by(sort).descending() : Sort.by(sort).ascending();
    }
}
