package com.indit.pricelist.contexts.shop.pricelists.application;

import com.indit.pricelist.contexts.shop.pricelists.domain.exceptions.PriceListNotFoundException;
import com.indit.pricelist.contexts.shop.pricelists.domain.model.Brand;
import com.indit.pricelist.contexts.shop.pricelists.domain.model.Price;
import com.indit.pricelist.contexts.shop.pricelists.domain.model.PriceList;
import com.indit.pricelist.contexts.shop.pricelists.domain.model.ProductId;
import com.indit.pricelist.contexts.shop.pricelists.config.DdbbBaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class PriceListFinderIntTest extends DdbbBaseTest {
    @Autowired
    private PriceListFinder priceListFinder;

    private static Stream<Arguments> priceListExistingCases(){
        return Stream.of(
                Arguments.arguments(LocalDateTime.of(2020, 6, 15, 0, 0, 0), 35455L, 1,
                        3L, 1, "Xira",
                        35455L, LocalDateTime.of(2020, 6, 15, 0, 0, 0), LocalDateTime.of(2020, 6, 15, 11, 0, 0),
                        1, new BigDecimal("30.50").setScale(2, RoundingMode.HALF_UP), "EUR"),
                Arguments.arguments(LocalDateTime.of(2020, 6, 14, 10, 0, 0), 35455L, 1,
                        1L, 1, "Xira",
                        35455L, LocalDateTime.of(2020, 6, 14, 0, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                        0, new BigDecimal("35.50").setScale(2, RoundingMode.HALF_UP), "EUR"),
                Arguments.arguments(LocalDateTime.of(2020, 6, 14, 16, 0, 0), 35455L, 1,
                        2L, 1, "Xira",
                        35455L, LocalDateTime.of(2020, 6, 14, 15, 0, 0), LocalDateTime.of(2020, 6, 14, 18, 30, 0),
                        1, new BigDecimal("25.45").setScale(2, RoundingMode.HALF_UP), "EUR"),
                Arguments.arguments(LocalDateTime.of(2020, 6, 14, 21, 0, 0), 35455L, 1,
                        1L, 1, "Xira",
                        35455L, LocalDateTime.of(2020, 6, 14, 0, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                        0, new BigDecimal("35.50").setScale(2, RoundingMode.HALF_UP), "EUR"),
                Arguments.arguments(LocalDateTime.of(2020, 6, 15, 10, 0, 0), 35455L, 1,
                        3L, 1, "Xira",
                        35455L, LocalDateTime.of(2020, 6, 15, 0, 0, 0), LocalDateTime.of(2020, 6, 15, 11, 0, 0),
                        1, new BigDecimal("30.50").setScale(2, RoundingMode.HALF_UP), "EUR"),
                Arguments.arguments(LocalDateTime.of(2020, 6, 16, 21, 0, 0), 35455L, 1,
                        4L, 1, "Xira",
                        35455L, LocalDateTime.of(2020, 6, 15, 16, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                        1, new BigDecimal("38.95").setScale(2, RoundingMode.HALF_UP), "EUR")
        );
    }

    private static Stream<Arguments> priceListNonExistingCases(){
        return Stream.of(
                Arguments.arguments(LocalDateTime.of(2021, 6, 15, 0, 0, 0), 35455L, 1),
                Arguments.arguments(LocalDateTime.of(2020, 6, 15, 0, 0, 0), 35456L, 1),
                Arguments.arguments(LocalDateTime.of(2020, 6, 15, 0, 0, 0), 35455L, 2)
        );
    }

        @ParameterizedTest
    @MethodSource("priceListExistingCases")
    void testFindPriceList_givenExistingProductBrandDate_returnsPriceListInDateRangeWithHighestPriority(
            LocalDateTime inputEffectiveDate, Long inputProductId, int inputBrandId,
            Long expectedPriceListId, int expectedBrandId, String expectedBrandName,
            Long expectedProductId, LocalDateTime expectedStartDate, LocalDateTime expectedEndDate,
            int expectedPriority, BigDecimal expectedPriceValue, String expectedCurrency
    ) throws PriceListNotFoundException {
        var expectedPriceList= PriceList.builder()
                .priceListId(expectedPriceListId)
                .brand(Brand.builder().id(expectedBrandId).name(expectedBrandName).build())
                .productId(ProductId.builder().value(expectedProductId).build())
                .startDate(expectedStartDate)
                .endDate(expectedEndDate)
                .priority(expectedPriority)
                .price(Price.builder().value(expectedPriceValue).currency(expectedCurrency).build()).build();

        var priceList= priceListFinder.findPriceList(
                inputEffectiveDate, ProductId.builder().value(inputProductId).build(), Brand.builder().id(inputBrandId).build());

        assertThat(priceList).usingRecursiveComparison().isEqualTo(expectedPriceList);

    }

    @ParameterizedTest
    @MethodSource("priceListNonExistingCases")
    void testFindPriceList_givenNonExistingPriceListForProductBrandDate_throwsPriceListNotFoundException(
            LocalDateTime inputEffectiveDate, Long inputProductId, int inputBrandId
    ){
        assertThrows(PriceListNotFoundException.class, ()->priceListFinder.findPriceList(inputEffectiveDate, ProductId.builder().value(inputProductId).build(), Brand.builder().id(inputBrandId).build()));
    }

}