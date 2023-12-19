package com.indit.pricelist.contexts.shop.pricelists.infrastructure.mapper;

import com.indit.pricelist.contexts.shop.pricelists.application.model.Brand;
import com.indit.pricelist.contexts.shop.pricelists.application.model.Price;
import com.indit.pricelist.contexts.shop.pricelists.application.model.PriceList;
import com.indit.pricelist.contexts.shop.pricelists.application.model.ProductId;
import com.indit.pricelist.contexts.shop.pricelists.controller.mapper.PriceListToPriceListResponseMapper;
import com.indit.pricelist.contexts.shop.pricelists.infrastructure.PriceListEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
public interface PriceListEntityToPriceListMapper {
    static PriceListEntityToPriceListMapper MAPPER = Mappers.getMapper(PriceListEntityToPriceListMapper.class);
    @Mapping(target = "productId.value", source = "productId")
    @Mapping(target = "price.value", source = "price")
    @Mapping(target = "price.currency", source = "currency")
    PriceList convert(PriceListEntity priceListEntity);
}
