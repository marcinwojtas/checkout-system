package com.pragmaticcoders.checkout.query.order;

import com.pragmaticcoders.checkout.query.Query;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class SingleOrderQuery implements Query {
    private UUID id;
}
