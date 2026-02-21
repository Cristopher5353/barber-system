package com.cja.inventory.dto.sales;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListSaleDto {
    private Long id;
    private BigDecimal total;
    private String createdAt;
    private List<SaleItemDto> items;
}