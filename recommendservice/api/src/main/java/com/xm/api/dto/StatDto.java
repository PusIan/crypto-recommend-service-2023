package com.xm.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatDto {
    private LocalDate dt;
    private String symbol;
    private Double newestPrice;
    private Double oldestPrice;
    private Double maxPrice;
    private Double minPrice;
    private Double normalizedPrice;
}
