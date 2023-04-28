package com.api.wishlist.service;

import com.api.wishlist.domain.converter.UserConverter;
import com.api.wishlist.domain.dto.UserRegistryDTO;
import com.api.wishlist.domain.model.User;
import com.api.wishlist.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserRegistryService {

    private final UserRepository userRepository;
    private final UserConverter converter;

    /**
     *  This method verify if exists a registry for user.
     *  - If exists, then, CREATE a new wishlist registry for user
     *  - If not exists, then, UPDATE the exiting wishlist registry for user
     *
     * @param userRegistry - UserRegistryDTO
     * @return UserRegistryDTO
     */
    public UserRegistryDTO registry(final UserRegistryDTO userRegistry) {
        log.info("m=registry, start user wishlist registry for userId: {}", userRegistry.getUserId());

        var userData = userRepository.findById(userRegistry.getUserId());

        if(userData.isEmpty()) {
            userData = Optional.ofNullable(converter.toEntity(userRegistry));
        }

        log.info("m=registry, {} user wishlist registry for userId: {}", userData.map(User::isNew).get() ? "Creating" : "Updating", userRegistry.getUserId());

        var userUpdated = saveUser(userData.get());
        return converter.toDto(userUpdated);
    }

    /**
     *  save/update user data in database
     *
     * @param user - User
     * @return User
     */
    private User saveUser(final User user) {
        return userRepository.save(user);
    }


}
