package com.cja.inventory.dto.categories;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreCategoryDto {
    private Long id;
    @Pattern(regexp = "^[A-Za-z0-9áéíóúÁÉÍÓÚñÑ ]+$", message = "La categoría solo debe contener letras y números")
    private String name;
}