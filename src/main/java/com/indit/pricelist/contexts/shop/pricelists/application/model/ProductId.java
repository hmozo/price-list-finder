package com.indit.pricelist.contexts.shop.pricelists.application.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@Getter
public class ProductId {
    private Long value;

    public ProductId(Long productId){
        validateProductIdType(productId);
        value= productId;
    }

    private void validateProductIdType(Long productId) {
        if (productId == null || !(productId instanceof Long) || productId < 0) {
            log.error("ProductId wrong type");
            throw new IllegalArgumentException("Product ID must be a positive non-null value");
        }
    }
}
