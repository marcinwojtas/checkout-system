package com.pragmaticcoders.checkout.command.order;

import com.pragmaticcoders.checkout.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class ConfirmOrderCommand implements Command {
    private UUID id;
}
