package com.indit.pricelist.contexts.shop.pricelists.controller;

import com.indit.pricelist.contexts.shop.pricelists.application.services.PriceListFinderImpl;
import com.indit.pricelist.contexts.shop.pricelists.controller.mapper.PriceListToPriceListResponseMapper;
import com.indit.pricelist.contexts.shop.pricelists.application.model.Brand;
import com.indit.pricelist.contexts.shop.pricelists.application.model.Price;
import com.indit.pricelist.contexts.shop.pricelists.application.model.PriceList;
import com.indit.pricelist.contexts.shop.pricelists.application.model.ProductId;
import com.indit.pricelist.contexts.shop.pricelists.application.utils.FilterPriceLists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PriceListController.class)
@AutoConfigureMockMvc
class PriceListControllerIntTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PriceListToPriceListResponseMapper priceListToPriceListResponseMapper;
    @MockBean
    private PriceListFinderImpl priceListFinder;
    @MockBean
    private FilterPriceLists filterPriceLists;


    @Test
    void testGetPriceListByDateProductBrand_givenDateExistingProductAndBrand_shouldReturn200() throws Exception {
        ProductId productId= ProductId.builder().value(35455L).build();
        Long priceListId= 1L;
        Integer brandId= 1;;
        Price price= Price.builder().value(new BigDecimal(35.50)).currency("EUR").build();
        LocalDateTime startDate= LocalDateTime.of(2020, 6, 14, 15, 0, 0);
        LocalDateTime endDate= LocalDateTime.of(2020, 12, 31, 23, 59, 59);
        LocalDateTime inputEffectiveDate= LocalDateTime.of(2020, 6, 15, 0, 0, 0);
        String expectedStartDate= "2020-06-14T15:00:00";
        String expectedEndDate= "2020-12-31T23:59:59";
        Long inputProductId= 35455L;
        Integer inputBrandId= 1;;



        var priceList= PriceList.builder()
                .priceListId(priceListId)
                .brandId(brandId)
                .productId(productId)
                .price(price)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        when(priceListFinder.findPriceList(any(), any(), any())).thenReturn(priceList);

        mockMvc.perform(get("/v1/price-lists")
                .param("effective-date", inputEffectiveDate.toString())
                .param("product-id", inputProductId.toString())
                .param("brand-id", String.valueOf(inputBrandId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.priceListId").value(priceListId))
                .andExpect(jsonPath("$.productId").value(productId.getValue().toString()))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.startDate").value(expectedStartDate))
                .andExpect(jsonPath("$.endDate").value(expectedEndDate))
                .andExpect(jsonPath("$.price").value(price.getValue()))
                .andExpect(jsonPath("$.currency").value(price.getCurrency()));
    }

    @Test
    void testGetPriceListByDateProductBrand_givenProductBrandAndMissingDate_shouldThrowException400() throws Exception {
        mockMvc.perform(get("/v1/price-lists?product-id=2222&brand-id=1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value("400 BAD_REQUEST"))
                .andExpect(jsonPath("$.description").value("Required request parameter 'effective-date' for method parameter type LocalDateTime is not present"));
    }

    @Test
    void testGetPriceListByDateProductBrand_givenWrongDateType_shouldThrowException400() throws Exception {
        mockMvc.perform(get("/v1/price-lists?effective-date=2020-06-15T11:01:00&product-id=35455&brand-id=a"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.description").value("Invalid input parameter format"));
    }
}