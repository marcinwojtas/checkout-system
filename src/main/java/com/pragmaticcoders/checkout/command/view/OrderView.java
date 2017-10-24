package com.pragmaticcoders.checkout.command.view;

import com.pragmaticcoders.checkout.domain.Item;
import com.pragmaticcoders.checkout.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class OrderView {

    @AllArgsConstructor
    @Getter
    public static class Item {
        private UUID id;
        private Integer quantity;
        private String name;
        private Integer price;
    }
    private UUID id;
    private List<Item> items;
    private Integer totalPrice;
    private String status;
}
