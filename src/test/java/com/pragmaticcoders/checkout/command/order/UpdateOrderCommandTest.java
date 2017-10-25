package com.pragmaticcoders.checkout.command.order;

import com.pragmaticcoders.checkout.dto.OrderDto;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class UpdateOrderCommandTest {

    @Test
    public void createTest() throws Exception {
        UUID uuid = UUID.randomUUID();
        OrderDto dto = mock(OrderDto.class);
        UpdateOrderCommand command = new UpdateOrderCommand(uuid, dto);

        assertEquals(uuid, command.getId());
        assertEquals(dto, command.getDto());
    }
}