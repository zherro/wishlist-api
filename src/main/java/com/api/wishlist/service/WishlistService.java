package com.api.wishlist.service;

import com.api.wishlist.config.AppConfig;
import com.api.wishlist.config.exceptions.BusinessException;
import com.api.wishlist.domain.converter.WishlistConverter;
import com.api.wishlist.domain.dto.UserRegistryDTO;
import com.api.wishlist.domain.dto.WishItemDTO;
import com.api.wishlist.domain.model.WishItem;
import com.api.wishlist.repository.WishlistRepository;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.api.wishlist.config.exceptions.ExceptionMessage.ERROR_MAX_WISHLIST_LIMIT_MESSAGE;

@Slf4j
@RequiredArgsConstructor
@Service
public class WishlistService {

    private final AppConfig appConfig;
    private final WishlistRepository wishlistRepository;
    private final WishlistConverter converter;

    public void updateWishlist(final UserRegistryDTO userRegistry) {
        log.info("m=updateWishlist, start user wishlist registry for userId: {}", userRegistry.getUserId());

        Set<WishItem> userWishlist = wishlistRepository.findAllByUserId(userRegistry.getUserId())
                .orElse(new HashSet<>());

        userWishlist.addAll(
                userRegistry.getWishList().stream()
                        .map(wishItemDto -> converter.toEntity(wishItemDto, userRegistry.getUserId()))
                        .collect(Collectors.toSet()));

        log.info("m=updateWishlist, managing user wishlist registry for userId: {}", userRegistry.getUserId());

        if(userWishlist.size() > appConfig.getWishlistMaxSize()) {
            throw new BusinessException(ERROR_MAX_WISHLIST_LIMIT_MESSAGE);
        }

        saveWishlist(userWishlist);
    }

    /**
     *  save/update user data in database
     *
     * @param wishlist - WishItem
     */
    private void saveWishlist(final Set<WishItem> wishlist) {
        wishlistRepository.saveAll(wishlist);
    }

    /**
     *
     *
     * @param userId - String - user id
     * @param wishlistItemId - String - wishlist item id
     */
    public void removeUserItem(final String userId, final String wishlistItemId) {
        wishlistRepository.deleteByProductIdAndUserId(wishlistItemId, userId);
    }

    public void clearWishlistByUser(final String userId) {
        wishlistRepository.deleteAllByUserId(userId);
    }

    public Set<WishItemDTO> listWishlistByUser(final String userId) {
        var items = wishlistRepository.findAllByUserId(userId)
                .orElse(new HashSet<>());

        return items.stream().map(converter::toDto).collect(Collectors.toSet());
    }
}
