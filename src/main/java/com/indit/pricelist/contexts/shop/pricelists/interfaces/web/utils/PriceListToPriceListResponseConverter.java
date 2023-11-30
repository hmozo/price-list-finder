package com.indit.pricelist.contexts.shop.pricelists.interfaces.web.utils;

import com.indit.pricelist.contexts.shop.pricelists.domain.model.PriceList;
import com.indit.pricelist.contexts.shop.pricelists.interfaces.web.dto.PriceListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PriceListToPriceListResponseConverter {
    public PriceListResponse convert(PriceList priceList){
        log.info("Converting PriceList into PriceListResponse");
        return PriceListResponse.builder()
                .priceListId(priceList.getPriceListId())
                .brandId(priceList.getBrand().getId())
                .productId(priceList.getProductId().getValue())
                .price(priceList.getPrice().getValue())
                .currency(priceList.getPrice().getCurrency())
                .startDate(priceList.getStartDate())
                .endDate(priceList.getEndDate())
                .build();
    }
}
