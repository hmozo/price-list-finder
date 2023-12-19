package com.indit.pricelist.contexts.shop.pricelists.application.ports.in;

import com.indit.pricelist.contexts.shop.pricelists.application.exceptions.PriceListNotFoundException;
import com.indit.pricelist.contexts.shop.pricelists.application.model.Brand;
import com.indit.pricelist.contexts.shop.pricelists.application.model.PriceList;
import com.indit.pricelist.contexts.shop.pricelists.application.model.ProductId;

import java.time.LocalDateTime;

public interface PriceListFinder {
    PriceList findPriceList(LocalDateTime effectiveDate, ProductId productId, Brand brandId) throws PriceListNotFoundException;
}
