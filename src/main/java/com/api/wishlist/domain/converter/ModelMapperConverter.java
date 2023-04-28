package com.api.wishlist.domain.converter;

import org.modelmapper.ModelMapper;

public interface ModelMapperConverter<DTO, ENTITY>  {

    default ENTITY toEntity(final DTO dto, Class<ENTITY> entityClass, final ModelMapper mapper) {
        return mapper.map(dto, entityClass);
    }

    default DTO toDto(final ENTITY entity, Class<DTO> dtoClass, final ModelMapper mapper) {
        return mapper.map(entity, dtoClass);
    }
}
