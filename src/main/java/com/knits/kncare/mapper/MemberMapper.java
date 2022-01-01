package com.knits.kncare.mapper;

import com.knits.kncare.dto.MemberDto;
import com.knits.kncare.model.Member;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;


/**
 * For all the available features and annotations see:
 * https://mapstruct.org/documentation/stable/reference/html/
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD, uses = {GroupMapper.class, EmployeeMapper.class, EmailMapper.class, GroupMembershipMapper.class})
public interface MemberMapper extends CycleAvoidingMapperInterface<Member, MemberDto> {

}
