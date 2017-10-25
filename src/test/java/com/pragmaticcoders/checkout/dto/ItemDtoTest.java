package com.pragmaticcoders.checkout.dto;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ItemDtoTest {

    @Test
    public void createTest() {
        ItemDto dto = new ItemDto(
            "name",
            new ArrayList<ItemDto.Price>() {{
                add(new ItemDto.Price(1, 3));
            }}
        );

        assertEquals("name", dto.getName());
        assertEquals(Integer.valueOf(1), dto.getPrices().get(0).getQuantity());
        assertEquals(Integer.valueOf(3), dto.getPrices().get(0).getPrice());
    }
}