package com.pragmaticcoders.checkout.command.order;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class ConfirmOrderCommandTest {

    @Test
    public void createTest() throws Exception {
        UUID uuid = UUID.randomUUID();
        ConfirmOrderCommand command = new ConfirmOrderCommand(uuid);
        assertEquals(uuid, command.getId());
    }
}