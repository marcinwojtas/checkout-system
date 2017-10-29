package com.pragmaticcoders.checkout.domain;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class Price {
    private BigDecimal price;
    private Integer quantity;
}
