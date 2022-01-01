package com.knits.kncare.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonView(Views.Common.class)
public class EmailDto {

    private Long id;

    private String subject;

    private String content;

    private MemberDto createdBy;

    @JsonView(Views.EmailDetails.class)
    private Set<MemberDto> recipients = new HashSet<>();

    @JsonView(Views.EmailDetails.class)
    private Set<GroupDto> recipientGroups = new HashSet<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailDto emailDto = (EmailDto) o;
        return id.equals(emailDto.id) && Objects.equals(subject, emailDto.subject) && Objects.equals(content, emailDto.content) && Objects.equals(createdBy, emailDto.createdBy) && Objects.equals(recipients, emailDto.recipients) && Objects.equals(recipientGroups, emailDto.recipientGroups) && Objects.equals(createdAt, emailDto.createdAt) && Objects.equals(updatedAt, emailDto.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, content, createdBy, recipients, recipientGroups, createdAt, updatedAt);
    }
}
