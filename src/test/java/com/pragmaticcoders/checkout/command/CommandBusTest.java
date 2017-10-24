package com.pragmaticcoders.checkout.command;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CommandBusTest {

    private CommandBus commandBus;
    private Command command;

    @Before
    public void setUp() {
        commandBus = new CommandBus();
        command = mock(Command.class);
    }

    @Test
    public void testExecute() throws Exception{
        CommandExecutor executor1 = mock(CommandExecutor.class);
        CommandExecutor executor2 = mock(CommandExecutor.class);
        CommandExecutor executor3 = mock(CommandExecutor.class);

        CommandExecutor[] commandExecutors = {executor1, executor2, executor3};

        commandBus.registerExecutors(commandExecutors);

        given(executor1.canExecute(command)).willReturn(false);
        given(executor2.canExecute(command)).willReturn(false);
        given(executor3.canExecute(command)).willReturn(true);

        commandBus.execute(command);
        verify(executor3, times(1)).execute(command);
    }

    @Test(expected = Exception.class)
    public void testExecuteThrowException() throws Exception{
        CommandExecutor executor1 = mock(CommandExecutor.class);
        CommandExecutor executor2 = mock(CommandExecutor.class);

        CommandExecutor[] commandExecutors = {executor1, executor2};

        commandBus.registerExecutors(commandExecutors);

        given(executor1.canExecute(command)).willReturn(false);
        given(executor2.canExecute(command)).willReturn(false);

        commandBus.execute(command);
    }
}