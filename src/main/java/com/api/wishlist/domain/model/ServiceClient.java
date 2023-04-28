package com.api.wishlist.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data
@Document(collection = ServiceClient.SERVICE_CLIENT_COLLECTION_NAME)
public class ServiceClient {

    public static final String SERVICE_CLIENT_COLLECTION_NAME = "cl_service_client";

    @Id
    private String id;

    private String name;

    private String clientKey;
    private String hashKey;

    public ServiceClient(String name, String clientKey, String hashKey) {
        this.name = name;
        this.clientKey = clientKey;
        this.hashKey = hashKey;
    }
}
