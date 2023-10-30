package com.xm.batchloader.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PriceRepository {
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void updateMonthStatistics() {
        jdbcTemplate.execute("delete from prices_month_stat");
        jdbcTemplate.execute("insert into prices_month_stat (symbol, dt_month, oldest_price, newest_price, min_price, max_price, normalized_price)\n" +
                "with sel1 as (\n" +
                "select symbol, date_trunc('month', dt) month_dt, min(dt) min_dt, max(dt) max_dt,\n" +
                "       min(price) min_price, max(price) max_price from prices\n" +
                "group by date_trunc('month', dt), symbol),\n" +
                "    sel2 as (\n" +
                "        select sel1.symbol, sel1.month_dt, p_oldest.price oldest_price, p_newest.price newset_price,\n" +
                "               sel1.min_price, sel1.max_price, (max_price-min_price)/min_price normalized_perice from sel1\n" +
                "                 inner join prices p_oldest on p_oldest.dt = sel1.min_dt and p_oldest.symbol = sel1.symbol\n" +
                "                 inner join prices p_newest on p_newest.dt = sel1.max_dt and p_newest.symbol = sel1.symbol\n" +
                "    )\n" +
                "select * from sel2;");
    }

    @Transactional
    public void updateDayStatistic() {
        jdbcTemplate.execute("delete from prices_day_stat");
        jdbcTemplate.execute("insert into prices_day_stat (symbol, dt_day, oldest_price, newest_price, min_price, max_price, normalized_price)\n" +
                "with sel1 as (\n" +
                "select symbol, date_trunc('day', dt) day_dt, min(dt) min_dt, max(dt) max_dt,\n" +
                "       min(price) min_price, max(price) max_price from prices\n" +
                "group by date_trunc('day', dt), symbol),\n" +
                "    sel2 as (\n" +
                "        select sel1.symbol, sel1.day_dt, p_oldest.price oldest_price, p_newest.price newset_price,\n" +
                "               sel1.min_price, sel1.max_price, (max_price-min_price)/min_price normalized_perice from sel1\n" +
                "                 inner join prices p_oldest on p_oldest.dt = sel1.min_dt and p_oldest.symbol = sel1.symbol\n" +
                "                 inner join prices p_newest on p_newest.dt = sel1.max_dt and p_newest.symbol = sel1.symbol\n" +
                "    )\n" +
                "select * from sel2;");
    }
}
