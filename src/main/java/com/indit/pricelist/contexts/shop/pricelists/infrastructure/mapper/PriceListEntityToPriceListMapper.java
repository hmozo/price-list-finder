package com.indit.pricelist.contexts.shop.pricelists.infrastructure.mapper;

import com.indit.pricelist.contexts.shop.pricelists.application.model.Brand;
import com.indit.pricelist.contexts.shop.pricelists.application.model.Price;
import com.indit.pricelist.contexts.shop.pricelists.application.model.PriceList;
import com.indit.pricelist.contexts.shop.pricelists.application.model.ProductId;
import com.indit.pricelist.contexts.shop.pricelists.infrastructure.PriceListEntity;
import org.springframework.stereotype.Component;

@Component
public class PriceListEntityToPriceListMapper {
    public PriceList convert(PriceListEntity priceListEntity){
        return PriceList.builder()
                .priceListId(priceListEntity.getPriceListId())
                .brandId(priceListEntity.getBrandId())
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
