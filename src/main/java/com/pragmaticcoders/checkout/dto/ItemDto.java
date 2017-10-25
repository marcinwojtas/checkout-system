package com.pragmaticcoders.checkout.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ItemDto {
    private String name;
    private List<Price> prices;

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class Price {
        private Integer quantity;
        private Integer price;
    }
}