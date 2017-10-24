package com.pragmaticcoders.checkout.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class CommandBus {
    private Set<CommandExecutor> executors = new HashSet<>();

    @Autowired
    public void registerExecutors(CommandExecutor[] executors) {
        this.executors.addAll(Arrays.asList(executors));
    }

    void execute(Command command) throws Exception {
        for (CommandExecutor executor : executors) {
            if (executor.canExecute(command)) {
                //noinspection unchecked
                executor.execute(command);
                return;
            }
        }

        throw new Exception("Handler for command: " + command.getClass().toString() + " not found");
    }
}
