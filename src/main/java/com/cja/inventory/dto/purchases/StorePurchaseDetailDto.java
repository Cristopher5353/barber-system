package com.cja.inventory.dto.purchases;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StorePurchaseDetailDto {
    private Long productId;
    private String productName;
    private double price;
    private int quantity;
    private double subtotal;
}