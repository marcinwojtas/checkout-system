package com.pragmaticcoders.checkout.dto;

import org.junit.Test;

import java.util.HashSet;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class PromotionDtoTest {
    @Test
    public void createTest() {
        PromotionDto dto = new PromotionDto(
            new HashSet<UUID>() {{
                add(UUID.randomUUID());
                add(UUID.randomUUID());
            }},
            40
        );

        assertEquals(Integer.valueOf(40), dto.getDiscount());
        assertEquals(2, dto.getItems().size());
    }
}