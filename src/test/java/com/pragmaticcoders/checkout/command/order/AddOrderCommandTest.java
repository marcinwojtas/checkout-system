package com.pragmaticcoders.checkout.command.order;

import com.pragmaticcoders.checkout.dto.OrderDto;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class AddOrderCommandTest {

    @Test
    public void createAndGetTest() {
        UUID uuid = UUID.randomUUID();
        OrderDto dto = mock(OrderDto.class);
        AddOrderCommand command = new AddOrderCommand(uuid, dto);
        assertEquals(dto, command.getDto());
        assertEquals(uuid, command.getId());
    }
}