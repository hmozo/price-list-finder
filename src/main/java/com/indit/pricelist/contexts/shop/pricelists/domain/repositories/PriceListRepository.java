package com.indit.pricelist.contexts.shop.pricelists.domain.repositories;

import com.indit.pricelist.contexts.shop.pricelists.domain.model.PriceList;
import com.indit.pricelist.contexts.shop.pricelists.infra.JPA.PriceListEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceListRepository {
    List<PriceList> findPriceListByDateProductAndBrand(LocalDateTime effectiveDate, Long productId, int brandId);
}
