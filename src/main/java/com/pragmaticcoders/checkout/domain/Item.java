package com.pragmaticcoders.checkout.domain;

import lombok.*;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.*;

@Entity
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Item {

    @Id
    @Getter
    private UUID id;

    @Getter
    private String name;

    private List<Price> prices;

    // not here, probably
    public Integer getPrice(Integer quantity) {
        // TODO
        return quantity * 10;
    }

    public List<Price> getPrices() {
        return Collections.unmodifiableList(prices);
    }
}