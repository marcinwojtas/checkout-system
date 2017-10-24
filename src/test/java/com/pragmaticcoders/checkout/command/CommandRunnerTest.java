package com.pragmaticcoders.checkout.command;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CommandRunnerTest {

    private CommandRunner runner;
    private CommandBus bus;
    private Command command;

    @Before
    public void setUp() {
        bus = mock(CommandBus.class);
        command = mock(Command.class);
        runner = new CommandRunner(bus);
    }

    @Test
    public void testExecute() throws Exception {
        runner.run(command);
        verify(bus, times(1)).execute(command);
    }
}