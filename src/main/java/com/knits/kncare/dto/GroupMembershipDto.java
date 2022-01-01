package com.knits.kncare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroupMembershipDto {

    private Long id;
    private GroupDto group;
    private MemberDto member;
    private String createdAt;
    private String updatedAt;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupMembershipDto that = (GroupMembershipDto) o;
        return id.equals(that.id) && Objects.equals(group, that.group) && Objects.equals(member, that.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
