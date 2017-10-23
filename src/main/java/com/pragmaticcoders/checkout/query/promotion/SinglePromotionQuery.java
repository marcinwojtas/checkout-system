package com.pragmaticcoders.checkout.query.promotion;

import com.pragmaticcoders.checkout.query.Query;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class SinglePromotionQuery implements Query {
    private final UUID id;
}
