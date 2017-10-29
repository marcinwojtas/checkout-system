package com.pragmaticcoders.checkout.calulator;

import com.pragmaticcoders.checkout.domain.Item;

import java.math.BigDecimal;

public interface PriceCalculator {
    BigDecimal calcCostOfItem(Item item, Integer quantity) throws InvalidPriceException;
}
