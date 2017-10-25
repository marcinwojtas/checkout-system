package com.pragmaticcoders.checkout.calulator;

import com.pragmaticcoders.checkout.domain.Item;

public interface PriceCalculator {
    Integer calcCostOfItem(Item item, Integer quantity) throws InvalidPriceException;
}
