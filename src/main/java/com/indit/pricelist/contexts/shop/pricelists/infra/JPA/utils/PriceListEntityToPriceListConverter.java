package com.indit.pricelist.contexts.shop.pricelists.infra.JPA.utils;

import com.indit.pricelist.contexts.shop.pricelists.domain.model.Brand;
import com.indit.pricelist.contexts.shop.pricelists.domain.model.Price;
import com.indit.pricelist.contexts.shop.pricelists.domain.model.PriceList;
import com.indit.pricelist.contexts.shop.pricelists.domain.model.ProductId;
import com.indit.pricelist.contexts.shop.pricelists.infra.JPA.PriceListEntity;
import com.indit.pricelist.contexts.shop.pricelists.interfaces.web.dto.PriceListResponse;
import org.springframework.stereotype.Component;

@Component
public class PriceListEntityToPriceListConverter {
    public PriceList convert(PriceListEntity priceListEntity){
        return PriceList.builder()
                .priceListId(priceListEntity.getPriceListId())
                .brand(Brand.builder()
                        .id(priceListEntity.getBrand().getId())
                        .name(priceListEntity.getBrand().getName()).build())
                .productId(ProductId.builder().value(priceListEntity.getProductId()).build())
                .price(Price.builder()
                        .value(priceListEntity.getPrice())
                        .currency(priceListEntity.getCurrency()).build())
                .startDate(priceListEntity.getStartDate())
                .endDate(priceListEntity.getEndDate())
                .priority(priceListEntity.getPriority())
                .build();
    }
}
