package com.pragmaticcoders.checkout.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ItemTest {

    @Test
    public void createAndModifyTest() {
        UUID uuid = UUID.randomUUID();
        List<Price> prices = new ArrayList<Price>() {{
            add(mock(Price.class));
        }};

        List<Price> expectedPrices = new ArrayList<>(prices);

        Item item = new Item(uuid, "foo", prices);

        item.getPrices().add(mock(Price.class));
        prices.add(mock(Price.class));

        assertEquals(uuid, item.getId());
        assertEquals("foo", item.getName());
        assertEquals(expectedPrices, item.getPrices());
    }
}