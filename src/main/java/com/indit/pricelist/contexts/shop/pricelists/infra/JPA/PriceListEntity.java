package com.indit.pricelist.contexts.shop.pricelists.infra.JPA;

import com.indit.pricelist.contexts.shop.pricelists.domain.model.Brand;
import com.indit.pricelist.contexts.shop.pricelists.domain.model.Price;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private BrandEntity brand;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long priceListId;
    private Long productId;
    private int priority;
    private BigDecimal price;
    private String currency;
}
