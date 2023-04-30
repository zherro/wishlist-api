package com.api.wishlist.config;

import com.api.wishlist.domain.model.User;
import com.api.wishlist.domain.model.WishItem;
import com.api.wishlist.repository.UserRepository;
import com.api.wishlist.repository.WishlistRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MongoDBConfig {

    @Value("${api.wishlist.dbconnection.validate.uri}")
    private String mongoConnectionUri;

    @Value("${spring.data.mongodb.database}")
    private String database;


    private final UserRepository userRepository;
    private final WishlistRepository wishlistRepository;


    /**
     *  garante que o banco estara criado apos o start da aplicação
     */
    @Order(1)
    @PostConstruct
    public void createMongoDb() {
        createCollection(User.USER_COLLECTION_NAME);
        createCollection(WishItem.WISHLIST_COLLECTION_NAME);
    }

    private void createCollection(String collection) {
        MongoClient client = MongoClients.create(mongoConnectionUri);

        try{
            client.getDatabase(database).createCollection(collection);
        } catch (Exception ignored) {}
        finally { client.close(); }
    }
}
