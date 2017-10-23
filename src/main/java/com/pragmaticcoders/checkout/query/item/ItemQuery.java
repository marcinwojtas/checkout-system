package com.pragmaticcoders.checkout.query.item;

import com.pragmaticcoders.checkout.query.Query;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ItemQuery implements Query {
    private final UUID id;
}
