package com.api.wishlist.domain.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistryDTO {

    private String userId;
    private String userName;
    private String userEmail;

    private Set<WishItemDTO> wishList;
}
