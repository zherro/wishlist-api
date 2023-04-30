package com.api.wishlist.domain.converter;

import java.lang.reflect.ParameterizedType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public abstract class ModelConverter<DTO, ENTITY> implements ModelMapperConverter<DTO, ENTITY> {

    private final ModelMapper mapper;

    private final Class<DTO> dtoClass;
    private final Class<ENTITY> entityClass;

    protected ModelConverter(ModelMapper mapper) {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();

        this.dtoClass = (Class<DTO>) parameterizedType.getActualTypeArguments()[0];
        this.entityClass = (Class<ENTITY>) parameterizedType.getActualTypeArguments()[1];

        this.mapper = mapper;
    }

    public ENTITY toEntity(final DTO dto) {
        return toEntity(dto, entityClass, mapper);
    }

    public DTO toDto(final ENTITY entity) {
        return toDto(entity, dtoClass, mapper);
    }

    public void mergeToEntity(final DTO source, ENTITY destination) {
        mergeToEntity(source, destination, mapper);
    }

    public void mergeToDto(final ENTITY source, DTO destination) {
        mergeToDto(source, destination, mapper);
    }
}
