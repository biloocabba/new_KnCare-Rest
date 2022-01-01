package com.knits.kncare.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonView(Views.Common.class)
public class ResponseFileDto {
    private String name;
    private String url;
    private String type;
    private long size;
}
