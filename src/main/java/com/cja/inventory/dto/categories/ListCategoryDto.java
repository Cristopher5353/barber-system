package com.cja.inventory.dto.categories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListCategoryDto {
    private Long id;
	private String name;
	private int status;
	private String createdAt;
}
