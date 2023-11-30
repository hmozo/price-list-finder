package com.indit.pricelist.contexts.shop.pricelists.interfaces.web;

import com.indit.pricelist.contexts.shop.pricelists.domain.exceptions.ErrorResponseDetails;
import com.indit.pricelist.contexts.shop.pricelists.domain.exceptions.PriceListNotFoundException;
import com.indit.pricelist.contexts.shop.pricelists.domain.model.Brand;
import com.indit.pricelist.contexts.shop.pricelists.domain.model.PriceList;
import com.indit.pricelist.contexts.shop.pricelists.domain.model.ProductId;
import com.indit.pricelist.contexts.shop.pricelists.interfaces.web.utils.PriceListToPriceListResponseConverter;
import com.indit.pricelist.contexts.shop.pricelists.interfaces.web.dto.PriceListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indit.pricelist.contexts.shop.pricelists.application.PriceListFinder;


import java.time.LocalDateTime;

@Validated
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/price-lists")
public class PriceListController {
    private final PriceListFinder pricesFinder;
    private final PriceListToPriceListResponseConverter priceListToPriceListResponseConverter;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Get price-tariff applied to the product within a date-range",
            description = "Find the price-tariff based on a productId, brandId and effectiveDate"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PriceListResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDetails.class))),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDetails.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDetails.class)))
            }
    )
    public ResponseEntity<PriceListResponse> getPriceListByDateProductBrand(
            @RequestParam(name = "effective-date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime effectiveDate,
            @RequestParam(name = "product-id", required = true) Long productId,
            @RequestParam(name = "brand-id", required = true) int brandId) throws PriceListNotFoundException {
        log.info("Fetching priceList for effectiveDate={}, productId={}, brandId={}",
                effectiveDate, productId, brandId);
        PriceList priceList= pricesFinder.findPriceList(effectiveDate, ProductId.builder().value(productId).build(), Brand.builder().id(brandId).build());
        log.debug("PriceList found: {}", priceList);
        PriceListResponse priceListResponse= priceListToPriceListResponseConverter.convert(priceList);
        return ResponseEntity.ok(priceListResponse);
    }
}
