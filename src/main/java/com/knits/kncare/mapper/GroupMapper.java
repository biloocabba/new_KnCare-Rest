package com.knits.kncare.mapper;

import com.knits.kncare.dto.GroupDto;
import com.knits.kncare.dto.MemberDto;
import com.knits.kncare.model.Group;
import com.knits.kncare.model.GroupMembership;
import com.knits.kncare.model.Member;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                GroupMembershipMapper.class
        }
)
public interface GroupMapper extends MapperInterface<Group, GroupDto> {

    @Override
    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(source = "model.groupMemberships", target = "groupMemberships", ignore = true)
//    @Mapping(source = "model.groupMemberships", target = "memberIds", qualifiedByName = "memberId")
//    @Mapping(source = "model.groupMemberships", target = "members", qualifiedByName = "member")
//    @Mapping(source = "model.groupMemberships", target = "groupMemberships", qualifiedByName = "groupDtoMapping")
    GroupDto toDto(Group model);

    @Override
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "dto.name", target = "name")
    @Mapping(source = "dto.memberIds", target = "memberIds")
    @Mapping(source = "dto.description", target = "description")
    @Mapping(source = "dto.groupMemberships", target = "groupMemberships", qualifiedByName = "toModelGroupMembership")
    Group toModel(GroupDto dto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "memberIds", ignore = true)
//    @Mapping(target = "members", ignore = true)
    @Mapping(target = "groupMemberships", ignore = true)
    void updateGroupFromDto(GroupDto dto, @MappingTarget Group entity);


}
