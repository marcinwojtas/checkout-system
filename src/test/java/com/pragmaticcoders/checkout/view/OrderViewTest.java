package com.pragmaticcoders.checkout.view;

import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class OrderViewTest {

    @Test
    public void createTest() {

        UUID uuid = UUID.randomUUID();
        UUID uuidPrice = UUID.randomUUID();

        OrderView view = new OrderView(
            uuid,
            new ArrayList<OrderView.Item>() {{
                add(new OrderView.Item(uuidPrice, 20, "foo", 10));
            }},
            200,
            "test"
        );

        assertEquals(uuid, view.getId());
        assertEquals(uuidPrice, view.getItems().get(0).getId());
        assertEquals(Integer.valueOf(20), view.getItems().get(0).getQuantity());
        assertEquals(Integer.valueOf(10), view.getItems().get(0).getPrice());
        assertEquals("foo", view.getItems().get(0).getName());
        assertEquals(Integer.valueOf(200), view.getTotalPrice());
        assertEquals("test", view.getStatus());

    }
}