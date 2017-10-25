package com.pragmaticcoders.checkout.e2e;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest extends E2eTestCase {

    @Test
    public void addAndGetTest() throws Exception {
        UUID uuid1 = addItemAndGetUUID("one", new HashMap<Integer, Integer>() {{
            put(1, 5);
        }});
        UUID uuid2 = addItemAndGetUUID("two", new HashMap<Integer, Integer>() {{
            put(1, 10);
        }});
        Integer discount = 2;

        addPromotion(
            new ArrayList<UUID>() {{
                add(uuid1);
                add(uuid2);
            }},
            discount
        );

        String result = addOrder(new HashMap<String, Integer>() {{
            put(uuid1.toString(), 2);
            put(uuid2.toString(), 1);
        }})
            .andExpect(status().is(HttpStatus.CREATED.value()))
            .andExpect(jsonPath("$.total_price").value(18))
            .andExpect(jsonPath("$.discount").value(2))
            .andExpect(jsonPath("$.items[0].quantity").value(1))
            .andExpect(jsonPath("$.items[0].price").value(10))
            .andExpect(jsonPath("$.items[1].quantity").value(2))
            .andExpect(jsonPath("$.items[1].price").value(10))
            .andExpect(jsonPath("$.status").value("ORDERING"))
            .andReturn()
            .getResponse()
            .getContentAsString();

        String id = new JSONObject(result).get("id").toString();

        getOrder(id)
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$.total_price").value(18))
            .andExpect(jsonPath("$.discount").value(2))
            .andExpect(jsonPath("$.items[0].quantity").value(1))
            .andExpect(jsonPath("$.items[0].price").value(10))
            .andExpect(jsonPath("$.items[1].quantity").value(2))
            .andExpect(jsonPath("$.items[1].price").value(10))
            .andExpect(jsonPath("$.status").value("ORDERING"));
    }

    @Test
    public void testUpdate() throws Exception {
        UUID uuid1 = addItemAndGetUUID("one", new HashMap<Integer, Integer>() {{
            put(1, 5);
        }});
        UUID uuid2 = addItemAndGetUUID("two", new HashMap<Integer, Integer>() {{
            put(1, 10);
        }});
        UUID uuid3 = addItemAndGetUUID("three", new HashMap<Integer, Integer>() {{
            put(1, 7);
            put(2, 10);
        }});
        Integer discount = 2;

        addPromotion(
            new ArrayList<UUID>() {{
                add(uuid1);
                add(uuid2);
            }},
            discount
        );

        String result = addOrder(new HashMap<String, Integer>() {{
            put(uuid1.toString(), 2);
            put(uuid2.toString(), 1);
        }})
            .andExpect(status().is(HttpStatus.CREATED.value()))
            .andReturn()
            .getResponse()
            .getContentAsString();

        String id = new JSONObject(result).get("id").toString();

        updateOrder(id, new HashMap<String, Integer>() {{
            put(uuid1.toString(), 2);
            put(uuid3.toString(), 5);
        }}).andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$.total_price").value(37))
            .andExpect(jsonPath("$.discount").value(0))
            .andExpect(jsonPath("$.items[0].quantity").value(5))
            .andExpect(jsonPath("$.items[0].price").value(27))
            .andExpect(jsonPath("$.items[1].quantity").value(2))
            .andExpect(jsonPath("$.items[1].price").value(10))
            .andExpect(jsonPath("$.status").value("ORDERING"));
    }

    @Test
    public void confirmTest() throws Exception {
        UUID uuid1 = addItemAndGetUUID("one", new HashMap<Integer, Integer>() {{
            put(1, 5);
        }});
        UUID uuid2 = addItemAndGetUUID("two", new HashMap<Integer, Integer>() {{
            put(1, 10);
        }});
        Integer discount = 2;

        addPromotion(
            new ArrayList<UUID>() {{
                add(uuid1);
            }},
            discount
        );

        String result = addOrder(new HashMap<String, Integer>() {{
            put(uuid2.toString(), 1);
        }})
            .andExpect(status().is(HttpStatus.CREATED.value()))
            .andReturn()
            .getResponse()
            .getContentAsString();

        String id = new JSONObject(result).get("id").toString();

        confirmOrder(id)
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$.total_price").value(10))
            .andExpect(jsonPath("$.discount").value(0))
            .andExpect(jsonPath("$.items[0].quantity").value(1))
            .andExpect(jsonPath("$.items[0].price").value(10))
            .andExpect(jsonPath("$.status").value("PAYMENT"));
    }

    private ResultActions confirmOrder(String id) throws Exception {
        return mockMvc.perform(
            put("/order/" + id + "/confirm")
        );
    }

    private ResultActions getOrder(String id) throws Exception {
        return mockMvc.perform(
            get("/order/" + id)
        );
    }

    private ResultActions addOrder(Map<String, Integer> items) throws Exception {
        JSONObject jsonObject = prepare(items);

        return mockMvc.perform(
            post("/order")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toString())
        );
    }

    private ResultActions updateOrder(String id, Map<String, Integer> items) throws Exception {
        JSONObject jsonObject = prepare(items);

        return mockMvc.perform(
            put("/order/" + id)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toString())
        );
    }

    private JSONObject prepare(Map<String, Integer> items) throws Exception {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Map.Entry<String, Integer> item : items.entrySet()) {
            JSONObject itemJson = new JSONObject();
            itemJson.put("id", item.getKey());
            itemJson.put("quantity", item.getValue());

            jsonArray.put(itemJson);
            jsonObject.put("items", jsonArray);
        }

        return jsonObject;
    }
}
