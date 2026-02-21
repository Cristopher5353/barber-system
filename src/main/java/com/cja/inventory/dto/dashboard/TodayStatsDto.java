package com.cja.inventory.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodayStatsDto {
    private BigDecimal totalSales;
    private Long services;
    private Long customers;
}
