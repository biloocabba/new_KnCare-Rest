package com.knits.kncare.mapper;

import com.knits.kncare.dto.PracticeDto;
import com.knits.kncare.model.Practice;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface PracticeMapper extends MapperInterface<Practice, PracticeDto> {

    @Override
    PracticeDto toDto(Practice practice);

    @Override
    Practice toModel(PracticeDto practiceDto);

    @Override
    List<PracticeDto> toDtoList(List<Practice> practiceList);
}
