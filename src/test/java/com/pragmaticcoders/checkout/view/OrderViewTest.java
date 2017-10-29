package com.pragmaticcoders.checkout.view;

import org.junit.Test;

import java.math.BigDecimal;
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
                add(new OrderView.Item(uuidPrice, 20, "foo", BigDecimal.valueOf(10)));
            }},
            BigDecimal.valueOf(200),
            BigDecimal.valueOf(300),
            "test"
        );

        assertEquals(uuid, view.getId());
        assertEquals(uuidPrice, view.getItems().get(0).getId());
        assertEquals(Integer.valueOf(20), view.getItems().get(0).getQuantity());
        assertEquals(BigDecimal.valueOf(10), view.getItems().get(0).getPrice());
        assertEquals("foo", view.getItems().get(0).getName());
        assertEquals(BigDecimal.valueOf(300), view.getTotalPrice());
        assertEquals(BigDecimal.valueOf(200), view.getDiscount());
        assertEquals("test", view.getStatus());

    }
}