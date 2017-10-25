package com.pragmaticcoders.checkout.view;

import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;


public class ItemViewTest {

    @Test
    public void createTest() {
        UUID uuid = UUID.randomUUID();
        ItemView view = new ItemView(
            uuid,
            "name",
            new ArrayList<ItemView.Price>() {{
                add(new ItemView.Price(1, 20));
            }}
        );

        assertEquals(uuid, view.getId());
        assertEquals("name", view.getName());
        assertEquals(Integer.valueOf(1), view.getPrices().get(0).getQuantity());
        assertEquals(Integer.valueOf(20), view.getPrices().get(0).getPrice());
    }
}