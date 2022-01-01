package com.knits.kncare.mapper;

import com.knits.kncare.dto.EmailDto;
import com.knits.kncare.dto.EmployeeDto;
import com.knits.kncare.dto.FileDBDto;
import com.knits.kncare.model.Email;
import com.knits.kncare.model.FileDB;
import com.knits.kncare.model.ems.Employee;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface FileDBMapper extends MapperInterface<FileDB, FileDBDto>{


    @Override
    FileDBDto toDto(FileDB model);

    @Override
    FileDB toModel(FileDBDto fileDBDto);

    @Override
    List<FileDBDto> toDtoList(List<FileDB> dtoList);
}
