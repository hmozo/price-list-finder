package com.indit.pricelist.contexts.shop.pricelists.application;

import com.indit.pricelist.contexts.shop.pricelists.domain.exceptions.PriceListNotFoundException;
import com.indit.pricelist.contexts.shop.pricelists.domain.model.Brand;
import com.indit.pricelist.contexts.shop.pricelists.domain.model.PriceList;
import com.indit.pricelist.contexts.shop.pricelists.domain.model.ProductId;

import java.time.LocalDateTime;

public interface PriceListFinder {
    PriceList findPriceList(LocalDateTime effectiveDate, ProductId productId, Brand brandId) throws PriceListNotFoundException;
}
