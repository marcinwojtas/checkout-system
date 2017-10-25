package com.pragmaticcoders.checkout.domain;

import lombok.*;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @Getter
    private UUID id;

    @Getter
    private String name;

    private List<Price> prices;

    public Item(UUID id, String name, List<Price> prices) {
        this.id = id;
        this.name = name;
        this.prices = new ArrayList<>(prices);
    }

    public List<Price> getPrices() {
        return new ArrayList<>(prices);
    }
}