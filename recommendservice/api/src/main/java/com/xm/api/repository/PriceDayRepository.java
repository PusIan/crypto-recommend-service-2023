package com.xm.api.repository;

import com.xm.api.model.PriceDayStat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface PriceDayRepository extends JpaRepository<PriceDayStat, Long> {
    List<PriceDayStat> getPriceDayStatsByDtDayOrderByNormalizedPriceDesc(LocalDate dtDay);
}
