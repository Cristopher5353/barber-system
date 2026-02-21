package com.cja.inventory.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecentSaleDto {
    private LocalDateTime saleDate;
    private String itemName;
    private BigDecimal subtotal;
}
