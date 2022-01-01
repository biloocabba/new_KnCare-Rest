package com.knits.kncare.mapper;

import com.knits.kncare.dto.RoleDto;
import com.knits.kncare.model.Role;
import org.mapstruct.Mapper;

/**
 * For all the available features and annotations see:
 * https://mapstruct.org/documentation/stable/reference/html/
 */
@Mapper(componentModel = "spring")
public interface RoleMapper extends MapperInterface<Role, RoleDto> {

}