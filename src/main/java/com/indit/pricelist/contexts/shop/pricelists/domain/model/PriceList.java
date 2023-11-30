package com.indit.pricelist.contexts.shop.pricelists.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Getter
@ToString
public class PriceList {
    private Long priceListId;
    private ProductId productId;
    private Brand brand;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Price price;
    private int priority;
}
