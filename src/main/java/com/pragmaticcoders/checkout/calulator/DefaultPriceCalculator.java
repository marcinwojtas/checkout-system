package com.pragmaticcoders.checkout.calulator;

import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.domain.Price;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
final public class DefaultPriceCalculator implements PriceCalculator {

    @Override
    public Integer calcCostOfItem(Item item, Integer quantity) throws InvalidPriceException {
        Integer cost = 0;

        List<Price> prices = new ArrayList<>(item.getPrices());
        prices.sort((o1, o2) -> o2.getQuantity().compareTo(o1.getQuantity()));

        if (prices.isEmpty() || prices.get(prices.size() - 1).getQuantity() != 1) {
            throw new InvalidPriceException();
        }

        while (quantity > 0) {
            for (Price price : prices) {
                if (price.getQuantity() <= quantity) {
                    cost += price.getPrice();
                    quantity -= price.getQuantity();
                    break;
                }
            }
        }

        return cost;
    }
}
