package com.indit.pricelist.contexts.shop.pricelists.infra.JPA;

import com.indit.pricelist.contexts.shop.pricelists.config.DdbbBaseTest;
import com.indit.pricelist.contexts.shop.pricelists.domain.repositories.PriceListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class PriceListRepositoryTest extends DdbbBaseTest {
    @Autowired
    private PriceListRepository priceListRepository;

    @Test
    void testFindPriceListByDateProductAndBrand_givenExistingProductBrandDate_returns2PriceList(){
        LocalDateTime currentEffectiveDate= LocalDateTime.of(2020, 6, 15, 0, 0, 0);
        Long currentProductId= 35455L;
        int currentBrandId= 1;

        var priceLists= priceListRepository.findPriceListByDateProductAndBrand(currentEffectiveDate, currentProductId, currentBrandId);

        assertThat(priceLists.size()).isEqualTo(2);
        assertThat(priceLists.get(0).getPriceListId()).isEqualTo(1);
        assertThat(priceLists.get(1).getPriceListId()).isEqualTo(3);
    }
}