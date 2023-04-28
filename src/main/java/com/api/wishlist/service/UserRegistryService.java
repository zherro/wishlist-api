package com.api.wishlist.service;

import com.api.wishlist.config.exceptions.NotFoundException;
import com.api.wishlist.domain.converter.UserConverter;
import com.api.wishlist.domain.dto.UserRegistryDTO;
import com.api.wishlist.domain.model.User;
import com.api.wishlist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import static com.api.wishlist.config.exceptions.ExceptionMessage.ERROR_NOT_FOUND_MESSAGE;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserRegistryService {

    private final UserRepository userRepository;
    private final UserConverter converter;
    private  final MongoTemplate mongoTemplate;

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

        var userData = userRepository.findById(userRegistry.getUserId())
                .orElse(converter.toEntity(userRegistry));

        if(!userData.isNew()) {
            converter.mergeToEntity(userRegistry, userData);
        }

        log.info("m=registry, {} user wishlist registry for userId: {}", userData.isNew() ? "Creating" : "Updating", userRegistry.getUserId());

        var userUpdated = saveUser(userData);
        return converter.toDto(userUpdated);
    }

    public UserRegistryDTO findById(final String userId) {
        var userData = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ERROR_NOT_FOUND_MESSAGE));

        return converter.toDto(userData);
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
