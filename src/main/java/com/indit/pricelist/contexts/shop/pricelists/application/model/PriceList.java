package com.indit.pricelist.contexts.shop.pricelists.application.model;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PriceList {
    private Long priceListId;
    private ProductId productId;
    private Integer brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Price price;
    private int priority;
}
