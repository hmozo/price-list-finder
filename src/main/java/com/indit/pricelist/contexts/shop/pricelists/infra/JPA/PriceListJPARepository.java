package com.indit.pricelist.contexts.shop.pricelists.infra.JPA;

import com.indit.pricelist.contexts.shop.pricelists.domain.model.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceListJPARepository extends JpaRepository<PriceListEntity, Long> {

    @Query(value= "SELECT p FROM PriceListEntity p " +
            "JOIN FETCH p.brand b " +
            "WHERE p.startDate <= :effectiveDate " +
            "AND p.endDate >= :effectiveDate " +
            "AND p.productId = :productId " +
            "AND p.brand.id = :brandId")
    List<PriceListEntity> findPriceListByDateProductAndBrand(
            @Param("effectiveDate") LocalDateTime effectiveDate,
            @Param("productId") Long productId,
            @Param("brandId") int brandId
    );
}
