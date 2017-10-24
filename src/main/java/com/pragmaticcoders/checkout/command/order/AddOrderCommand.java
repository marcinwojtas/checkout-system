package com.pragmaticcoders.checkout.command.order;

import com.pragmaticcoders.checkout.command.Command;
import com.pragmaticcoders.checkout.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class AddOrderCommand implements Command {
    private UUID id;
    private OrderDto dto;
}
