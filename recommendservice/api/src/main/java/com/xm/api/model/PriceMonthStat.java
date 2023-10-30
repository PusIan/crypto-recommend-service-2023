package com.xm.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prices_month_stat")
@Getter
@Setter
public class PriceMonthStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dt_month")
    private LocalDate dtMonth;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "symbol")
    private Symbol symbol;
    @Column(name = "newest_price")
    private Double newestPrice;
    @Column(name = "oldest_price")
    private Double oldestPrice;
    @Column(name = "max_price")
    private Double maxPrice;
    @Column(name = "min_price")
    private Double minPrice;
    @Column(name = "normalized_price")
    private Double normalizedPrice;
}

