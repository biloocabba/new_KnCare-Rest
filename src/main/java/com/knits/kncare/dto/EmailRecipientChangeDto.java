package com.knits.kncare.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class EmailRecipientChangeDto {

    @JsonView(Views.Common.class)
    private Long id;

    @JsonView(Views.Common.class)
    private Set<MemberDto> recipients;

}
