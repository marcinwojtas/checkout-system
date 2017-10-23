package com.pragmaticcoders.checkout.command.promotion;

import com.pragmaticcoders.checkout.command.Command;
import com.pragmaticcoders.checkout.dto.PromotionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class AddPromotionCommand implements Command {
    private UUID uuid;
    private PromotionDto dto;
}
