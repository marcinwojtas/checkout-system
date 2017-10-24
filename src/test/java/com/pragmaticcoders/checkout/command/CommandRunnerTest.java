package com.pragmaticcoders.checkout.command;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

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