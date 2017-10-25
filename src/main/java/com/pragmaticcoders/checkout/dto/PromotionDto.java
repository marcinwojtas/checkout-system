package com.pragmaticcoders.checkout.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PromotionDto {
    @NotNull
    @Size(min = 1)
    private Set<UUID> items;
    @NotNull
    private Integer discount;
}