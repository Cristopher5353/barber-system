package com.cja.inventory.dto.customers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListCustomerDto {
    private Long id;
    private String name;
    private String document;
    private String email;
    private String phone;
    private int status;
    private String createdAt;
}
