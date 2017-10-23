package com.pragmaticcoders.checkout.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Entity(name = "orders")
public class Order {

    @Id
    @Getter
    private Integer id;

    private Map<Item, Integer> items;

    @Getter
    private Integer price;

    private Set<Promotion> promotions;

    public Order(Map<Item, Integer> items, Integer price, Set<Promotion> promotions) {
        this.items = items;
        this.price = price;
        this.promotions = promotions;
    }

    public Map<Item, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public Set<Promotion> getPromotions() {
        return Collections.unmodifiableSet(promotions);
    }

    public void calculate() {
        Integer price = 0;

        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            Item item = entry.getKey();
            Integer quantity = entry.getValue();

            price += item.getPrice(quantity);
        }

        for (Promotion promotion : promotions) {
            price -= promotion.getDiscount();
        }

        this.price = price;
    }
}
