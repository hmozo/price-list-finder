package com.indit.pricelist.contexts.shop.pricelists.application.utils;

import com.indit.pricelist.contexts.shop.pricelists.application.model.PriceList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FilterPriceLists {
    public PriceList filterByHighPriority(List<PriceList> priceLists){
        log.info("Filtering priceLists by highest priority");
        Optional<PriceList> highestPriorityPriceList = priceLists.stream()
                .max(Comparator.comparingInt(PriceList::getPriority));

        return highestPriorityPriceList.orElse(null);
    }
}