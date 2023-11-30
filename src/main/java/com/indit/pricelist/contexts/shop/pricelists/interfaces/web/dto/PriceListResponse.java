package com.indit.pricelist.contexts.shop.pricelists.interfaces.web.dto;


import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PriceListResponse implements Serializable {
    private Long productId;
    private int brandId;
    private Long priceListId;
    private BigDecimal price;
    private String currency;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
