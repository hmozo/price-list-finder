package com.indit.pricelist.contexts.shop.pricelists.controller.mapper;

import com.indit.pricelist.contexts.shop.pricelists.application.model.PriceList;
import com.indit.pricelist.contexts.shop.pricelists.controller.dto.PriceListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceListToPriceListResponseMapper {
    static PriceListToPriceListResponseMapper MAPPER = Mappers.getMapper(PriceListToPriceListResponseMapper.class);
    @Mapping(target = "productId", source = "productId.value")
    @Mapping(target = "price", source = "price.value")
    @Mapping(target = "currency", source = "price.currency")
    PriceListResponse convert(PriceList priceList);
}
