package com.cja.inventory.dto.suppliers;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListSupplierDto {
    private Long id;
    private String name;
    private String ruc;
    private String address;
    private String phone;
    private int status;
    private String createdAt;
}