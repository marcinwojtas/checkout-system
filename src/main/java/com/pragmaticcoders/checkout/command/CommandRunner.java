package com.pragmaticcoders.checkout.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandRunner {
    private final CommandBus commandBus;

    @Autowired
    public CommandRunner(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    public void run(Command command) throws Exception {
        commandBus.execute(command);
    }
}
