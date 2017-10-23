package com.pragmaticcoders.checkout.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Entity
@EqualsAndHashCode
public class Order {

    @Id
    @Getter
    private UUID id;

    private Map<Integer, Item> items;

    @Getter
    private Integer price;

    private Set<Promotion> promotions;

    public Order(UUID id, Map<Integer, Item> items, Set<Promotion> promotions) {
        this.id = id;
        this.items = items;
        this.promotions = promotions;

        calculate();
    }

    public Map<Integer, Item> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public Set<Promotion> getPromotions() {
        return Collections.unmodifiableSet(promotions);
    }

    public void calculate() {
        Integer price = 0;

        for (Map.Entry<Integer, Item> entry : items.entrySet()) {
            Item item = entry.getValue();
            Integer quantity = entry.getKey();

            price += item.getPrice(quantity);
        }

        for (Promotion promotion : promotions) {
            price -= promotion.getDiscount();
        }

        this.price = price;
    }
}