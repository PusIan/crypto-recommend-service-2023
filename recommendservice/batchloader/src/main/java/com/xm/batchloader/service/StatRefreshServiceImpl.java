package com.xm.batchloader.service;

import com.xm.batchloader.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class StatRefreshServiceImpl implements StatRefreshService {
    private final PriceRepository priceRepository;

    @Override
    public void refreshMonthStat() {
        priceRepository.updateMonthStatistics();
    }

    @Override
    public void refreshDayStat() {
        priceRepository.updateDayStatistic();
    }
}
