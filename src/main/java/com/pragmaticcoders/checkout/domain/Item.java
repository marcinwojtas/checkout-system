package com.pragmaticcoders.checkout.domain;

import lombok.Getter;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
public class Item {

    @Id
    @Getter
    private UUID id;

    @Getter
    private String name;

    private Map<Integer, Integer> priceList;

    protected Item() {
    }

    public Item(UUID id, String name, Map<Integer, Integer> priceList) {
        this.id = id;
        this.name = name;
        this.priceList = new HashMap<>(priceList);
    }

    public Integer getPrice(Integer quantity) {
        return quantity * 10;
    }

    public Map<Integer, Integer> getPriceList() {
        return Collections.unmodifiableMap(priceList);
    }
}