package com.pragmaticcoders.checkout.e2e;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;

@SpringBootTest(webEnvironment = MOCK)
@RunWith(SpringJUnit4ClassRunner.class)
@Profile("test")
public abstract class E2eTestCase {
    private static final String DATABASE = "fongo_db";

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MongoClient mongoClient;

    @Before
    public void setUp() {
        clearDb();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    private void clearDb() {
        MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE);

        for (String collection : mongoDatabase.listCollectionNames()) {
            mongoDatabase.getCollection(collection).drop();
        }
    }
}
