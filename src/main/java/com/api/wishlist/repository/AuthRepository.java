package com.api.wishlist.repository;

import com.api.wishlist.domain.model.ServiceClient;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthRepository extends MongoRepository<ServiceClient, String> {

    Optional<ServiceClient> findByClientKey(String clientKey);
}
