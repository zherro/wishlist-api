package com.api.wishlist.domain.converter;

import com.api.wishlist.domain.dto.WishItemDTO;
import com.api.wishlist.domain.model.WishItem;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class WishlistConverter extends ModelConverter<WishItemDTO, WishItem> {

    private final ModelMapper modelMapper;

    public WishlistConverter(final ModelMapper modelMapper) {
        super(modelMapper);
        this.modelMapper = modelMapper;
    }

    public WishItem toEntity(final WishItemDTO dto, final String userId) {
        var entity = toEntity(dto, WishItem.class, modelMapper);
        entity.setUserId(userId);
        return entity;
    }

}
