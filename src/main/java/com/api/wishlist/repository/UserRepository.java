package com.api.wishlist.repository;

import com.api.wishlist.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
