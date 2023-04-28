package com.api.wishlist.domain.converter;

import org.modelmapper.ModelMapper;

public interface ModelMapperConverter<DTO, ENTITY>  {

    default ENTITY toEntity(final DTO dto, Class<ENTITY> entityClass, final ModelMapper mapper) {
        return mapper.map(dto, entityClass);
    }

    default DTO toDto(final ENTITY entity, Class<DTO> dtoClass, final ModelMapper mapper) {
        return mapper.map(entity, dtoClass);
    }

    default void mergeToEntity(final DTO source, ENTITY destination, final ModelMapper mapper) {
        mapper.map(source, destination);
    }

    default void mergeToDto(final ENTITY source, DTO destination, final ModelMapper mapper) {
        mapper.map(source, destination);
    }
}
