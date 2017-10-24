package com.pragmaticcoders.checkout.command.item;

import com.pragmaticcoders.checkout.dto.ItemDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class AddItemCommandTest {

    @Test
    public void testCreateAndGet() {
        UUID uuid = UUID.randomUUID();
        ItemDto dto = new ItemDto(UUID.randomUUID(), "name", new ArrayList<>());

        AddItemCommand command = new AddItemCommand(uuid, dto);

        assertEquals(uuid, command.getUuid());
        assertEquals(dto, command.getItemDto());
    }
}