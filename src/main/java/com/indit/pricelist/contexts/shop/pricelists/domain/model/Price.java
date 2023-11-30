package com.indit.pricelist.contexts.shop.pricelists.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Builder
@Getter
@AllArgsConstructor
public class Price {
    private BigDecimal value;
    private String currency;
}
