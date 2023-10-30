package com.xm.api.repository;

import com.xm.api.model.PriceMonthStat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PriceMonthRepository extends JpaRepository<PriceMonthStat, Long> {
    List<PriceMonthStat> getPriceMonthStatByDtMonthOrderByNormalizedPriceDesc(LocalDate dtMonth);

    PriceMonthStat getPriceMonthStatByDtMonthAndSymbol_Symbol(LocalDate dtMonth, String symbol);
}
