package com.pragmaticcoders.checkout.domain;

import lombok.*;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Promotion {

    @Id
    @Getter
    private UUID id;

    private Set<Item> items;

    @Getter
    private Integer discount;

    public Set<Item> getItems() {
        return Collections.unmodifiableSet(items);
    }
}