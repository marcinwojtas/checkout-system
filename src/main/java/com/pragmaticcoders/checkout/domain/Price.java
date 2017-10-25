package com.pragmaticcoders.checkout.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class Price {
    private Integer price;
    private Integer quantity;
}
