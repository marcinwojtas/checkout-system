package com.pragmaticcoders.checkout.domain;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PriceTest {

    @Test
    public void createTest() {
        Price price = new Price(BigDecimal.valueOf(1), 2);
        assertEquals(BigDecimal.valueOf(1), price.getPrice());
        assertEquals(Integer.valueOf(2), price.getQuantity());
    }
}