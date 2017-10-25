package com.pragmaticcoders.checkout.e2e;

import org.json.JSONObject;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ItemControllerTest extends E2eTestCase {

    @Test
    public void createAndGetTest() throws Exception {

        Map<Integer, Integer> prices = new HashMap<Integer, Integer>() {{
            put(1, 3);
            put(2, 5);
        }};

        String response = addItem("one", prices)
            .andExpect(status().is(HttpStatus.CREATED.value()))
            .andExpect(jsonPath("$.name").value("one"))
            .andExpect(jsonPath("$.prices[0].quantity").value(1))
            .andExpect(jsonPath("$.prices[0].price").value(3))
            .andExpect(jsonPath("$.prices[1].quantity").value(2))
            .andExpect(jsonPath("$.prices[1].price").value(5))
            .andReturn()
            .getResponse()
            .getContentAsString();

        JSONObject jsonObject = new JSONObject(response);
        String id = jsonObject.get("id").toString();

        getItem(id)
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.name").value("one"))
            .andExpect(jsonPath("$.prices[0].quantity").value(1))
            .andExpect(jsonPath("$.prices[0].price").value(3))
            .andExpect(jsonPath("$.prices[1].quantity").value(2))
            .andExpect(jsonPath("$.prices[1].price").value(5));
    }

    @Test
    public void getListTest() throws Exception {
        Map<Integer, Integer> prices = new HashMap<Integer, Integer>() {{
            put(1, 3);
        }};

        addItem("one", prices);
        addItem("two", prices);
        addItem("three", prices);

        getList()
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$.length()").value(3))
            .andExpect(jsonPath("$[0].name").value("one"))
            .andExpect(jsonPath("$[1].name").value("two"))
            .andExpect(jsonPath("$[2].name").value("three"));
    }

    @Test
    public void getEmptyListTest() throws Exception {
        getList()
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$.length()").value(0));

    }

    @Test
    public void getUnExistedTest() throws Exception {
        getItem("0494aaed-caea-41f0-b238-33d029b2a681")
            .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void getWithInvalidIDTest() throws Exception {
        getItem("invalid-uuid")
            .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    private ResultActions getItem(String id) throws Exception {
        return mockMvc.perform(
            get("/item/" + id)
        );
    }

    private ResultActions getList() throws Exception {
        return mockMvc.perform(
            get("/item")
        );
    }
}
