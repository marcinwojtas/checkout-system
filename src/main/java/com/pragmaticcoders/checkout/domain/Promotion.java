package com.pragmaticcoders.checkout.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collections;
import java.util.Set;

@Entity(name = "promotions")
public class Promotion {

    @Id
    @Getter
    private Integer id;

    private Set<Item> items;

    @Getter
    private Integer discount;

    protected Promotion() {
    }

    public Promotion(Set<Item> items, Integer discount) {
        this.items = items;
        this.discount = discount;
    }

    public Set<Item> getItems() {
        return Collections.unmodifiableSet(items);
    }
}