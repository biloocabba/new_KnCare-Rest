package com.knits.kncare.dto.pages;

import com.fasterxml.jackson.annotation.JsonView;
import com.knits.kncare.dto.Views;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class JsonPageImpl <T> extends org.springframework.data.domain.PageImpl<T>{

    public JsonPageImpl(final List<T> content, final Pageable pageable, final long total) {
        super(content, pageable, total);
    }

    public JsonPageImpl(final List<T> content) {
        super(content);
    }

    public JsonPageImpl(final Page<T> page, final Pageable pageable) {
        super(page.getContent(), pageable, page.getTotalElements());
    }

    @JsonView(Views.Common.class)
    public int getTotalPages() {
        return super.getTotalPages();
    }

    @JsonView(Views.Common.class)
    public long getTotalElements() {
        return super.getTotalElements();
    }

    @JsonView(Views.Common.class)
    public boolean hasNext() {
        return super.hasNext();
    }

    @JsonView(Views.Common.class)
    public boolean isLast() {
        return super.isLast();
    }

    @JsonView(Views.Common.class)
    public boolean hasContent() {
        return super.hasContent();
    }

    @JsonView(Views.Common.class)
    public List<T> getContent() {
        return super.getContent();
    }
}
