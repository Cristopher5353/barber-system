package com.cja.inventory.dto.sales;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreSaleDto {
    private Long clientId;
    private String paymentMethod;
    private List<SaleItemDto> items;
}
