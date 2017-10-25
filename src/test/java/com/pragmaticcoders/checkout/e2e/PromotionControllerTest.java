package com.pragmaticcoders.checkout.e2e;

import org.json.JSONObject;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PromotionControllerTest extends E2eTestCase {

    @Test
    public void createAndGetTest() throws Exception {
        UUID uuid1 = addItemAndGetUUID("one", new HashMap<Integer, Integer>() {{
            put(1, 5);
        }});
        UUID uuid2 = addItemAndGetUUID("two", new HashMap<Integer, Integer>() {{
            put(1, 10);
        }});
        Integer discount = 40;

        List<UUID> uuids = new ArrayList<>();
        uuids.add(uuid1);
        uuids.add(uuid2);

        String result = addPromotion(uuids, discount)
            .andExpect(status().is(HttpStatus.CREATED.value()))
            .andExpect(jsonPath("$.discount").value(discount))
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.items").isArray())
            .andExpect(jsonPath("$.items.length()").value(2))
            .andReturn()
            .getResponse()
            .getContentAsString();

        String id = new JSONObject(result).get("id").toString();

        getPromotion(id)
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$.discount").value(discount))
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.items").isArray())
            .andExpect(jsonPath("$.items.length()").value(2));

    }

    @Test
    public void testGetList() throws Exception {
        UUID uuid1 = addItemAndGetUUID("one", new HashMap<Integer, Integer>() {{
            put(1, 5);
        }});
        UUID uuid2 = addItemAndGetUUID("two", new HashMap<Integer, Integer>() {{
            put(1, 10);
        }});
        List<UUID> uuids = new ArrayList<>();
        uuids.add(uuid1);
        uuids.add(uuid2);

        Integer discount1 = 10;
        Integer discount2 = 20;
        Integer discount3 = 30;

        addPromotion(uuids, discount1);
        addPromotion(uuids, discount2);
        addPromotion(uuids, discount3);

        getList()
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$.length()").value(3))
            .andExpect(jsonPath("$[0].discount").value(discount1))
            .andExpect(jsonPath("$[1].discount").value(discount2))
            .andExpect(jsonPath("$[2].discount").value(discount3));
    }

    @Test
    public void testGetEmptyList() throws Exception {
        getList()
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    public void getUnExistedTest() throws Exception {
        getPromotion("0494aaed-caea-41f0-b238-33d029b2a681")
            .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void getWithInvalidIDTest() throws Exception {
        getPromotion("invalid-uuid")
            .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    private ResultActions getPromotion(String id) throws Exception {
        return mockMvc.perform(
            get("/promotion/" + id)
        );
    }

    private ResultActions getList() throws Exception {
        return mockMvc.perform(
            get("/promotion")
        );
    }
}
