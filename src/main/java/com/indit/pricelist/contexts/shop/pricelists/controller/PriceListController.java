package com.indit.pricelist.contexts.shop.pricelists.controller;

import com.indit.pricelist.contexts.shop.pricelists.application.ports.in.PriceListFinder;
import com.indit.pricelist.contexts.shop.pricelists.controller.mapper.PriceListToPriceListResponseMapper;
import com.indit.pricelist.contexts.shop.pricelists.application.model.Brand;
import com.indit.pricelist.contexts.shop.pricelists.application.model.PriceList;
import com.indit.pricelist.contexts.shop.pricelists.application.model.ProductId;
import com.indit.pricelist.contexts.shop.pricelists.application.exceptions.ErrorResponseDetails;
import com.indit.pricelist.contexts.shop.pricelists.controller.dto.PriceListResponse;
import com.indit.pricelist.contexts.shop.pricelists.controller.openapi.PriceListControllerBase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PriceListController implements PriceListControllerBase {
    private final PriceListFinder pricesFinder;

    @Operation(
            operationId = "getPriceListByDateProductBrand",
            summary = "Get price-tariff applied to the product within a date-range",
            description = "Find the price-tariff based on a productId, brandId and effectiveDate",
            tags = { "price-list-controller" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = PriceListResponse.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDetails.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDetails.class))
                    }),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDetails.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/v1/price-lists",
            produces = { "application/json" }
    )
    @Override
    public ResponseEntity<PriceListResponse> getPriceListByDateProductBrand(
            @NotNull @Parameter(name = "effective-date", description = "", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "effective-date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime effectiveDate,
            @NotNull @Parameter(name = "product-id", description = "", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "product-id", required = true) Long productId,
            @NotNull @Parameter(name = "brand-id", description = "", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "brand-id", required = true) Integer brandId
    ) {
            log.info("Fetching priceList for effectiveDate={}, productId={}, brandId={}",
                effectiveDate, productId, brandId);
        PriceList priceList= pricesFinder.findPriceList(effectiveDate, ProductId.builder().value(productId).build(), Brand.builder().id(brandId).build());
        log.debug("PriceList found: {}", priceList);
        PriceListResponse priceListResponse= PriceListToPriceListResponseMapper.MAPPER.convert(priceList);
        return ResponseEntity.ok(priceListResponse);
    }
}
