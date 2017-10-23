package com.pragmaticcoders.checkout.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Price {

    protected Price() {
    }

    private Integer price;
    private Integer quantity;
}
