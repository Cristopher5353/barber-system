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
public class BarberRankingDto {
    private Long barberId;
    private String barberName;
    private Long servicesCount;
    private BigDecimal totalAmount;
}
