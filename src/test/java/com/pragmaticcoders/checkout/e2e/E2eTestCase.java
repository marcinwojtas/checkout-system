package com.pragmaticcoders.checkout.e2e;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;
import java.util.UUID;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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

    ResultActions addItem(String name, Map<Integer, Integer> prices) throws Exception {
        JSONArray priceDtos = new JSONArray();

        for (Map.Entry<Integer, Integer> entry : prices.entrySet()) {
            priceDtos.put(
                new JSONObject()
                    .put("price", entry.getValue())
                    .put("quantity", entry.getKey())
            );
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("prices", priceDtos);

        return mockMvc.perform(
            post("/item")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toString())
        );
    }

    UUID addItemAndGetUUID(String name, Map<Integer, Integer> prices) throws Exception {
        String response = addItem(name,prices).andReturn().getResponse().getContentAsString();
        String id = new JSONObject(response).get("id").toString();

        return UUID.fromString(id);
    }
}
