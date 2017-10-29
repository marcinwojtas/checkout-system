package com.pragmaticcoders.checkout.command.promotion;

import com.pragmaticcoders.checkout.dto.PromotionDto;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class AddPromotionCommandTest {

    @Test
    public void testCreateAndGet() {
        UUID uuid = UUID.randomUUID();
        PromotionDto dto = new PromotionDto(new HashSet<>(), BigDecimal.valueOf(21));

        AddPromotionCommand command = new AddPromotionCommand(uuid, dto);
        assertEquals(uuid, command.getUuid());
        assertEquals(dto, command.getDto());
    }
}