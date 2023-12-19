package com.indit.pricelist.contexts.shop.pricelists.infrastructure;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "prices")
public class PriceListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long priceListId;
    private Long productId;
    private int priority;
    private BigDecimal price;
    private String currency;
    private int brandId;
}
