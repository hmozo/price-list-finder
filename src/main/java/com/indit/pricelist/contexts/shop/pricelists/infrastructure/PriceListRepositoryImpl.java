package com.indit.pricelist.contexts.shop.pricelists.infrastructure;

import com.indit.pricelist.contexts.shop.pricelists.application.model.PriceList;
import com.indit.pricelist.contexts.shop.pricelists.application.ports.out.PriceListRepository;
import com.indit.pricelist.contexts.shop.pricelists.infrastructure.mapper.PriceListEntityToPriceListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PriceListRepositoryImpl implements PriceListRepository {
    private final PriceListJPARepository priceListJPARepository;
    private final PriceListEntityToPriceListMapper priceListEntityToPriceListConverter;
    @Override
    public List<PriceList> findPriceListByDateProductAndBrand(LocalDateTime effectiveDate, Long productId, int brandId) {
        List<PriceListEntity> priceListEntities= priceListJPARepository.findPriceListByDateProductAndBrand(effectiveDate, productId, brandId);
        return priceListEntities.stream().map(entity->priceListEntityToPriceListConverter.convert(entity)).toList();
    }
}