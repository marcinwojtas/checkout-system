package com.pragmaticcoders.checkout.view;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class OrderView {
    private UUID id;
    private List<Item> items;
    private BigDecimal discount;
    private BigDecimal totalPrice;
    private String status;

    @AllArgsConstructor
    @Getter
    public static class Item {
        private UUID id;
        private Integer quantity;
        private String name;
        private BigDecimal price;
    }
}
