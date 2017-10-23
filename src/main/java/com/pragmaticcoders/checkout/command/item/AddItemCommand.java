package com.pragmaticcoders.checkout.command.item;

import com.pragmaticcoders.checkout.command.Command;
import com.pragmaticcoders.checkout.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class AddItemCommand implements Command {
    private UUID uuid;
    private ItemDto itemDto;
}
