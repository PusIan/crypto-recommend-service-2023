package com.xm.api.service;

import com.xm.api.dto.StatDto;
import com.xm.api.dto.SymbolDto;

import java.time.LocalDate;
import java.util.List;

public interface RecommendService {
    List<StatDto> getMonthStat(Boolean approved, LocalDate date);

    StatDto getMonthStatPerSymbol(String symbol, LocalDate date);

    StatDto getBestDailyStat(Boolean approved, LocalDate date);

    List<SymbolDto> getCryptoList();
}
