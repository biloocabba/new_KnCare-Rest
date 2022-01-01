package com.knits.kncare.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;
import java.util.Set;

//@Mapper()
public interface MapperInterface<MDL, DTO> {

    DTO toDto(MDL model);

    MDL toModel(DTO dto);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    List<DTO> toDtoList(List<MDL> modelList);
    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    List<MDL> toModelList(List<DTO> dtoList);
    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    Set<DTO> toDtoSet(Set<MDL> modelSet);
    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    Set<MDL> toModelSet(Set<DTO> dtoSet);

}
