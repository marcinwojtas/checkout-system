package com.pragmaticcoders.checkout.calulator;

import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.domain.Price;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
final public class DefaultPriceCalculator implements PriceCalculator {

    @Override
    public BigDecimal calcCostOfItem(Item item, Integer quantity) throws InvalidPriceException {
        BigDecimal cost = BigDecimal.ZERO;

        List<Price> prices = new ArrayList<>(item.getPrices());
        prices.sort((o1, o2) -> o2.getQuantity().compareTo(o1.getQuantity()));

        if (prices.isEmpty() || prices.get(prices.size() - 1).getQuantity() != 1) {
            throw new InvalidPriceException();
        }

        while (quantity > 0) {
            for (Price price : prices) {
                if (price.getQuantity() <= quantity) {
                    cost = cost.add(price.getPrice());
                    quantity -= price.getQuantity();
                    break;
                }
            }
        }

        return cost;
    }
}
