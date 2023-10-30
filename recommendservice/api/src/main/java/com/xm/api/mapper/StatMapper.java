package com.xm.api.mapper;

import com.xm.api.dto.StatDto;
import com.xm.api.model.PriceDayStat;
import com.xm.api.model.PriceMonthStat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatMapper {
    @Mapping(target = "dt", source = "dtMonth")
    @Mapping(target = "symbol", source = "symbol.symbol")
    StatDto toStatDtoFromMonth(PriceMonthStat priceMonthStat);

    @Mapping(target = "symbol", source = "symbol.symbol")
    List<StatDto> toStatDtosFromMonth(List<PriceMonthStat> priceMonthStats);

    @Mapping(target = "dt", source = "dtDay")
    @Mapping(target = "symbol", source = "symbol.symbol")
    StatDto toStatDtoFromDay(PriceDayStat priceDayStat);

    List<StatDto> toStatDtosFromDay(List<PriceDayStat> priceDayStats);
}
