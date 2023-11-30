package com.indit.pricelist.contexts.shop.pricelists.infra.JPA;

import com.indit.pricelist.contexts.shop.pricelists.domain.model.PriceList;
import com.indit.pricelist.contexts.shop.pricelists.domain.repositories.PriceListRepository;
import com.indit.pricelist.contexts.shop.pricelists.infra.JPA.utils.PriceListEntityToPriceListConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PriceListRepositoryImpl implements PriceListRepository {
    private final PriceListJPARepository priceListJPARepository;
    private final PriceListEntityToPriceListConverter priceListEntityToPriceListConverter;
    @Override
    public List<PriceList> findPriceListByDateProductAndBrand(LocalDateTime effectiveDate, Long productId, int brandId) {
        List<PriceListEntity> priceListEntities= priceListJPARepository.findPriceListByDateProductAndBrand(effectiveDate, productId, brandId);
        return priceListEntities.stream().map(entity->priceListEntityToPriceListConverter.convert(entity)).toList();
    }
}