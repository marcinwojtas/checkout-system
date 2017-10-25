package com.pragmaticcoders.checkout.command;

import org.springframework.core.GenericTypeResolver;

public interface CommandExecutor<T extends Command> {

    void execute(T command) throws Exception;

    default boolean canExecute(Command command) {
        Class executorType = GenericTypeResolver
            .resolveTypeArgument(getClass(), CommandExecutor.class);

        return executorType == command.getClass();
    }
}
