package com.knits.kncare.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.knits.kncare.model.status.Status.EmailSentStatus;
import lombok.*;

import javax.persistence.Id;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailSentDto {


    @Id
    @JsonView(Views.Common.class)
    private Long id;

    @JsonView(Views.Common.class)
    private String subject;

    @JsonView(Views.Common.class)
    private String content;

    @JsonView(Views.Common.class)
    private MemberDto recipient;

    @JsonView(Views.Common.class)
    private EmailSentStatus status;

    @JsonView(Views.Common.class)
    private LocalDateTime createdAt;

    @JsonView(Views.Common.class)
    private LocalDateTime updatedAt;

}
