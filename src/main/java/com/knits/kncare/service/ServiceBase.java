package com.knits.kncare.service;

import com.knits.kncare.dto.search.AbstractSearchableDto;
import com.knits.kncare.mapper.MapperInterface;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public abstract class ServiceBase<MDL, DTO> extends AbstractSearchableDto<MDL> {

    protected final MapperInterface<MDL, DTO> mapper;

    public ServiceBase(MapperInterface<MDL, DTO> mapper) {
        this.mapper = mapper;
    }

    public DTO toDto(MDL model) {
        return mapper.toDto(model);
    }

    public MDL toModel(DTO dto) {
        return mapper.toModel(dto);
    }

    public List<DTO> toDtoList(List<MDL> models) {
        return mapper.toDtoList(models);
    }

    public Optional<DTO> toDtoOptional(Optional<MDL> optional) {
        return optional.map(mapper::toDto);
    }

    public Page<DTO> toDtoPage(Page<MDL> page) {
        return page.map(mapper::toDto);
    }

    public Set<DTO> toDtoSet(Set<MDL> modelSet) {return mapper.toDtoSet(modelSet);}
}