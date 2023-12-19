package com.indit.pricelist.contexts.shop.pricelists.application.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@Getter
public class Brand {
    private int id;

    public Brand(int id){
        validateBrandIdType(id);
        this.id= id;
    }

    private void validateBrandIdType(Integer id) {
        if (id == null || !(id instanceof Integer) || id < 0) {
            log.error("BrandId wrong type");
            throw new IllegalArgumentException("Brand ID must be a positive non-null value");
        }
    }
}
