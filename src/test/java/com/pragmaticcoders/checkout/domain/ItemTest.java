package com.pragmaticcoders.checkout.domain;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void testCreation() {

        Map<Integer, Integer> prices = new HashMap<Integer, Integer>(){{
            put(1,2);
            put(3,4);
        }};

        Map<Integer, Integer> expectedPrices = new HashMap<>(prices);

        Item item = new Item("A", prices);
        assertEquals("A", item.getName());

        assertEquals(item.getPriceList(), expectedPrices);
    }
}