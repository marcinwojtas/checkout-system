package com.pragmaticcoders.checkout.calulator;

import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.domain.Price;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class DefaultPriceCalculatorTest {

    private PriceCalculator priceCalculator;

    @Before
    public void setUp() throws Exception {
        priceCalculator = new DefaultPriceCalculator();
    }

    @Test
    public void onePriceOneQuantityPriceTest() throws Exception {
        Item item = getItemWithOnePrice(14);
        assertEquals(Integer.valueOf(14), priceCalculator.calcCostOfItem(item, 1));
    }

    @Test
    public void onePriceFewQuantityPriceTest() throws Exception {
        Item item = getItemWithOnePrice(14);
        assertEquals(Integer.valueOf(3 * 14), priceCalculator.calcCostOfItem(item, 3));
    }

    @Test
    public void fewPriceFewQuantityTest() throws Exception {
        Item item = new Item(
            UUID.randomUUID(),
            "few-price",
            new ArrayList<Price>() {{
                add(new Price(12,1));
                add(new Price(20,2));
            }}
        );

        assertEquals(Integer.valueOf(60), priceCalculator.calcCostOfItem(item, 6));
    }

    @Test
    public void fewPriceQuantityTest() throws Exception {
        Item item = new Item(
            UUID.randomUUID(),
            "few-price",
            new ArrayList<Price>() {{
                add(new Price(12,1));
                add(new Price(20,2));
                add(new Price(50,5));
            }}
        );

        assertEquals(Integer.valueOf(70), priceCalculator.calcCostOfItem(item, 7));
    }

    @Test(expected = InvalidPriceException.class)
    public void calcWithoutPrices() throws Exception {
        Item item = new Item(
            UUID.randomUUID(),
            "one-price",
            new ArrayList<>()
        );

        assertEquals(Integer.valueOf(3 * 14), priceCalculator.calcCostOfItem(item, 3));
    }

    @Test(expected = InvalidPriceException.class)
    public void calcWithInvalidPrice() throws Exception {
        Item item = new Item(
            UUID.randomUUID(),
            "one-price",
            new ArrayList<Price>() {{
                add(new Price(1,2));
            }}
        );

        assertEquals(Integer.valueOf(3 * 14), priceCalculator.calcCostOfItem(item, 3));
    }

    private Item getItemWithOnePrice(Integer price) {
        return new Item(
            UUID.randomUUID(),
            "one-price",
            new ArrayList<Price>() {{
                add(new Price(price, 1));
            }}
        );
    }
}