package com.pragmaticcoders.checkout;

import com.mongodb.MongoClient;
import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.domain.Order;
import com.pragmaticcoders.checkout.domain.Promotion;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class MongoConfiguration {
    private final String host;
    private final String port;
    private final String database;

    public MongoConfiguration(
        @Value("${mongo.host}") String host,
        @Value("${mongo.port}") String port,
        @Value("${mongo.database}") String database
    ) {
        this.host = host;
        this.port = port;
        this.database = database;
    }

    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(host, Integer.parseInt(port));
    }

    @Bean
    public Datastore datastore() {
        Morphia morphia = new Morphia();
        morphia.map(Order.class, Item.class, Promotion.class);

        Datastore datastore = morphia.createDatastore(mongoClient(), database);
        datastore.ensureIndexes();

        return datastore;
    }
}
