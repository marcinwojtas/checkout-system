package com.pragmaticcoders.checkout.command;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CommandBusTest {

    private CommandBus commandBus;
    private Command command;

    @Before
    public void setUp() {
        commandBus = new CommandBus();
        command = mock(Command.class);
    }
}