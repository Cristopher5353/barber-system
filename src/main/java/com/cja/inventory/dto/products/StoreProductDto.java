package com.cja.inventory.dto.products;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreProductDto {
	private Long id;
    @Pattern(regexp = "^[A-Za-z0-9áéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre del producto es obligatorio")
	private String name;

    @Pattern(regexp = "^[A-Za-z0-9áéíóúÁÉÍÓÚñÑ ]+$", message = "La descripción del producto es obligatoria")
	private String description;

	@Min(value = 1, message = "El precio de costo debe ser mayor que cero")
	private double costPrice;

	@Min(value = 1, message = "El precio de venta debe ser mayor que cero")
	private double salePrice;

    @Min(value = 1, message = "Debes seleccionar una categoría")
    private Long category;
}
