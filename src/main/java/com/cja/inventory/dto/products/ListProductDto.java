package com.cja.inventory.dto.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListProductDto {
    private Long id;
    private String name;
    private String description;
    private int stock;
    private double costPrice;
    private double salePrice;
    private int status;
    private String createdAt;
	private String category;
}