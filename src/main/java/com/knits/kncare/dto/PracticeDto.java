package com.knits.kncare.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.knits.kncare.model.PracticeAttachment;
import com.knits.kncare.model.Rate;
import com.knits.kncare.model.Tag;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonView(Views.Common.class)

public class PracticeDto {
    private Long id;
    private String title;
    private String description;

    private Set<Tag> tags;
    private List<Rate> rates;

    @ToString.Exclude
    @JsonBackReference
    private List<PracticeAttachment> practiceAttachments;
    private ResponseFileDto responseFileDto;

}
