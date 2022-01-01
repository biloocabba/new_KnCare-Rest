package com.knits.kncare.mapper;

import com.knits.kncare.dto.EmployeeDto;
import com.knits.kncare.model.ems.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends CycleAvoidingMapperInterface<Employee, EmployeeDto> {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

}
