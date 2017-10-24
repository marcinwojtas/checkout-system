package com.pragmaticcoders.checkout.repository;

import com.mongodb.client.MongoDatabase;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = MOCK)
public abstract class RepositoryTestCase {

    @Autowired
    Datastore datastore;

    protected void clearDb() {
        MongoDatabase db = datastore.getMongo().getDatabase("fongo_db");

        for(String collection : db.listCollectionNames()) {
            db.getCollection(collection).drop();
        }
    }
}
