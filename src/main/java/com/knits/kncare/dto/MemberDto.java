package com.knits.kncare.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.knits.kncare.dto.search.AbstractSearchableDto;
import com.knits.kncare.model.GroupMembership;
import com.knits.kncare.model.Member;
import com.knits.kncare.model.Role;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonView(Views.Common.class)
public class MemberDto {

    @JsonView(Views.Common.class)
    private Long id;

    @JsonView(Views.Common.class)
    private LocalDate onBoardDate;

    @JsonView(Views.Common.class)
    private LocalDate offBoardDate;

    @JsonView(Views.RequestOnly.class)
    private Long employeePdmId;

    @JsonView(Views.MemberDetails.class)
    private EmployeeDto employee;

    @JsonView(Views.Common.class)
    private Role role;

    @JsonView(Views.MemberDetails.class)
    private Set<GroupMembershipDto> groupMemberships;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDto memberDto = (MemberDto) o;
        return id.equals(memberDto.id) && Objects.equals(employee, memberDto.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee);
    }
}
