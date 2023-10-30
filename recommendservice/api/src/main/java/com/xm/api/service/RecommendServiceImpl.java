package com.xm.api.service;

import com.xm.api.dto.StatDto;
import com.xm.api.dto.SymbolDto;
import com.xm.api.mapper.StatMapper;
import com.xm.api.mapper.SymbolMapper;
import com.xm.api.model.PriceDayStat;
import com.xm.api.model.PriceMonthStat;
import com.xm.api.repository.PriceDayRepository;
import com.xm.api.repository.PriceMonthRepository;
import com.xm.api.repository.SymbolRepository;
import com.xm.api.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RecommendServiceImpl implements RecommendService {
    private final PriceMonthRepository priceMonthRepository;
    private final PriceDayRepository priceDayRepository;
    private final SymbolRepository symbolRepository;
    private final StatMapper statMapper;
    private final SymbolMapper symbolMapper;

    public List<StatDto> getMonthStat(Boolean approved, LocalDate date) {
        date = Utilities.fillDateToMonthIfNull(date);
        List<PriceMonthStat> result = priceMonthRepository.getPriceMonthStatByDtMonthOrderByNormalizedPriceDesc(date);
        if (approved) {
            result = result.stream().filter(x -> x.getSymbol().isApproved()).collect(Collectors.toList());
        }
        return statMapper.toStatDtosFromMonth(result);
    }

    public StatDto getMonthStatPerSymbol(String symbol, LocalDate date) {
        date = Utilities.fillDateToMonthIfNull(date);
        return statMapper.toStatDtoFromMonth(priceMonthRepository.getPriceMonthStatByDtMonthAndSymbol_Symbol(date, symbol));
    }

    public StatDto getBestDailyStat(Boolean approved, LocalDate date) {
        date = date == null ? LocalDate.now() : date;
        List<PriceDayStat> result = priceDayRepository.getPriceDayStatsByDtDayOrderByNormalizedPriceDesc(date);
        if (approved) {
            result = result.stream().filter(x -> x.getSymbol().isApproved()).collect(Collectors.toList());
        }
        return statMapper.toStatDtoFromDay(result.stream().findFirst().orElseThrow());
    }

    @Override
    public List<SymbolDto> getCryptoList() {
        return symbolMapper.toSymbolDtos(symbolRepository.findAll());
    }
}
