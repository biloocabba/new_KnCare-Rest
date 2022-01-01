package com.knits.kncare.mapper;

import org.mapstruct.Context;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

public interface CycleAvoidingMapperInterface<MDL, DTO> extends MapperInterface<MDL, DTO> {

    @Override
    @Named("toDtoNewCyclic")
    default DTO toDto(MDL model) {
        return toDtoCyclic(model, new CycleAvoidingMappingContext());
    }

    @Override
    @Named("toDtoListNewCyclic")
    default List<DTO> toDtoList(List<MDL> modelList) {
        return toDtoListCyclic(modelList, new CycleAvoidingMappingContext());
    }

    @Override
    @Named("toDtoSetNewCyclic")
    default Set<DTO> toDtoSet(Set<MDL> modelSet) {
        return toDtoSetCyclic(modelSet, new CycleAvoidingMappingContext());
    }

    DTO toDtoCyclic(MDL model, @Context CycleAvoidingMappingContext context);

    Set<DTO> toDtoSetCyclic(Set<MDL> modelSet, @Context CycleAvoidingMappingContext context);

    List<DTO> toDtoListCyclic(List<MDL> dtoList, @Context CycleAvoidingMappingContext context);
}
