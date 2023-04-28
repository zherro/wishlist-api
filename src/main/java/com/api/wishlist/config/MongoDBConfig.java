//package com.api.wishlist.config;
//
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import javax.annotation.PostConstruct;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MongoDBConfig {
//
//    private String mongoConnectionUri;
//
//    @PostConstruct
//    public void createMongoDb() {
//            MongoClient client = MongoClients.create("mongodb://root:root@localhost:27017/wishlistDB");
//            client.getDatabase("wishlistDB");
//            client.close();
//    }
//}
