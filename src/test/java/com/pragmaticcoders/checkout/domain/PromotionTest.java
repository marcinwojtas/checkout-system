package com.pragmaticcoders.checkout.domain;

import org.junit.Test;

import java.util.HashSet;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class PromotionTest {

    @Test
    public void createAndModifyTest() {
        UUID uuid = UUID.randomUUID();
        Promotion promotion = new Promotion(
            uuid,
            new HashSet<Item>() {{
                add(mock(Item.class));
            }},
            10
        );

        promotion.getItems().add(mock(Item.class));

        assertEquals(uuid, promotion.getId());
        assertEquals(1, promotion.getItems().size());
        assertEquals(Integer.valueOf(10), promotion.getDiscount());
    }
}