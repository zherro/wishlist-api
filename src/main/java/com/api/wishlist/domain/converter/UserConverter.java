package com.api.wishlist.domain.converter;

import com.api.wishlist.domain.dto.UserRegistryDTO;
import com.api.wishlist.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConverter  extends ModelConverter<UserRegistryDTO, User> {

    public UserConverter(final ModelMapper modelMapper) {
        super(modelMapper);
    }

}
