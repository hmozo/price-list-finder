package com.indit.pricelist.contexts.shop.pricelists.application;

import com.indit.pricelist.contexts.shop.pricelists.domain.exceptions.PriceListNotFoundException;
import com.indit.pricelist.contexts.shop.pricelists.domain.model.Brand;
import com.indit.pricelist.contexts.shop.pricelists.domain.model.PriceList;
import com.indit.pricelist.contexts.shop.pricelists.domain.model.ProductId;
import com.indit.pricelist.contexts.shop.pricelists.domain.repositories.PriceListRepository;
import com.indit.pricelist.contexts.shop.pricelists.domain.services.FilterPriceLists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class PriceListFinderImpl implements PriceListFinder {
    private final PriceListRepository priceListRepository;
    private final FilterPriceLists filterPriceLists;

    @Override
    public PriceList findPriceList(LocalDateTime effectiveDate, ProductId productId, Brand brand) throws PriceListNotFoundException {
        log.info("Finding PriceList by effectiveDate={}, productId={}, brandId={}", effectiveDate, productId.getValue(), brand.getId());
        var priceLists= priceListRepository.findPriceListByDateProductAndBrand(effectiveDate, productId.getValue(), brand.getId());
        log.debug("PriceLists found: {}", priceLists);
        if (priceLists.isEmpty()){
            log.error("PriceLists not found");
            throw new PriceListNotFoundException();
        }
        return filterPriceLists.filterByHighPriority(priceLists);
    }
}