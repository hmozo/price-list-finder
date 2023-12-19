package com.indit.pricelist.contexts.shop.pricelists.application.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PriceListNotFoundException extends RuntimeException{
    private final HttpStatus statusCode;

    public PriceListNotFoundException() {
        super("Price list not found");
        this.statusCode = HttpStatus.NOT_FOUND;
    }

    public PriceListNotFoundException(String message) {
        super(message);
        this.statusCode = HttpStatus.NOT_FOUND;
    }
}
