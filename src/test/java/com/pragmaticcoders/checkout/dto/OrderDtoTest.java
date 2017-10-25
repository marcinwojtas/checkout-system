package com.pragmaticcoders.checkout.dto;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class OrderDtoTest {

    @Test
    public void createTest() {
        OrderDto dto = new OrderDto(
            new ArrayList<OrderDto.Item>() {{
                add(new OrderDto.Item("one", 10));
            }}
        );

        assertEquals("one", dto.getItems().get(0).getId());
        assertEquals(Integer.valueOf(10), dto.getItems().get(0).getQuantity());
    }
}