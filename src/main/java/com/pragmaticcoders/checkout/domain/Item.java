package com.pragmaticcoders.checkout.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.*;

@Entity
@EqualsAndHashCode
public class Item {

    @Id
    @Getter
    private UUID id;

    @Getter
    private String name;

    private List<Price> prices;

    protected Item() {
    }

    public Item(UUID uuid, String name, List<Price> prices) {
        this.id = uuid;
        this.name = name;
        this.prices = prices;
    }

    public Integer getPrice(Integer quantity) {
        // TODO
        return quantity * 10;
    }

    public List<Price> getPriceList() {
        return Collections.unmodifiableList(prices);
    }
}