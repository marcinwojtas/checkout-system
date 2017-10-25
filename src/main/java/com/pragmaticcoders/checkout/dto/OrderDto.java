package com.pragmaticcoders.checkout.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class OrderDto {
    @NotNull
    @Size(min = 1)
    private List<Item> items;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    static public class Item {
        @NotNull
        private String id;
        @NotNull
        private Integer quantity;
    }
}