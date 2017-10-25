package com.pragmaticcoders.checkout.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class PriceTest {

    @Test
    public void createTest() {
        Price price = new Price(1,2);
        assertEquals(Integer.valueOf(1), price.getPrice());
        assertEquals(Integer.valueOf(2), price.getQuantity());
    }
}