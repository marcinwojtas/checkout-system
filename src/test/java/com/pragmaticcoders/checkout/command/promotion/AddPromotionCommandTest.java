package com.pragmaticcoders.checkout.command.promotion;

import com.pragmaticcoders.checkout.dto.PromotionDto;
import org.junit.Test;

import java.util.HashSet;
import java.util.UUID;

import static org.junit.Assert.*;

public class AddPromotionCommandTest {

    @Test
    public void testCreateAndGet() {
        UUID uuid = UUID.randomUUID();
        PromotionDto dto = new PromotionDto(UUID.randomUUID(), new HashSet<>(), 21);

        AddPromotionCommand command = new AddPromotionCommand(uuid, dto);
        assertEquals(uuid, command.getUuid());
        assertEquals(dto, command.getDto());
    }
}