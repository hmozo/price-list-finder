package com.indit.pricelist.contexts.shop.pricelists.application.ports.out;

import com.indit.pricelist.contexts.shop.pricelists.application.model.PriceList;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceListRepository {
    List<PriceList> findPriceListByDateProductAndBrand(LocalDateTime effectiveDate, Long productId, int brandId);
}
