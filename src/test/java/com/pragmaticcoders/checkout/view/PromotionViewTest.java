package com.pragmaticcoders.checkout.view;

import org.junit.Test;

import java.util.HashSet;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PromotionViewTest {

    @Test
    public void createTest() {
        UUID uuid = UUID.randomUUID();
        UUID itemUuid1 = UUID.randomUUID();
        UUID itemUuid2 = UUID.randomUUID();


        PromotionView view = new PromotionView(
            uuid,
            new HashSet<UUID>() {{
                add(itemUuid1);
                add(itemUuid2);
            }},
            2
        );

        assertEquals(uuid, view.getId());
        assertTrue(view.getItems().contains(itemUuid1));
        assertTrue(view.getItems().contains(itemUuid2));
    }
}