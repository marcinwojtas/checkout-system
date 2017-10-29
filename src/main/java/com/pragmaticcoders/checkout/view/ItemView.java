package com.pragmaticcoders.checkout.view;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class ItemView {
    private UUID id;
    private String name;
    private List<Price> prices;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @EqualsAndHashCode
    public static class Price {
        private Integer quantity;
        private BigDecimal price;
    }
}
