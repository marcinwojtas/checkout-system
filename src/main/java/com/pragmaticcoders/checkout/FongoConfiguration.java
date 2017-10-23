package com.pragmaticcoders.checkout;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class FongoConfiguration {

    @Bean
    public MongoClient mongoClient() throws Exception {
        return new Fongo("fongo_server").getMongo();
    }

    @Bean
    public Datastore datastore() throws Exception {
        Morphia morphia = new Morphia();
        return morphia.createDatastore(mongoClient(), "fongo_db");
    }
}
