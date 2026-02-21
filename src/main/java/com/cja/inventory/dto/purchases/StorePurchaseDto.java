package com.cja.inventory.dto.purchases;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StorePurchaseDto {
    private Long supplierId;
    private String supplierName;
    private double total;
    private List<StorePurchaseDetailDto> purchaseDetail;
}