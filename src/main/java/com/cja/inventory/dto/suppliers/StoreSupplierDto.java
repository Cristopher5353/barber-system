package com.cja.inventory.dto.suppliers;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreSupplierDto {
    private Long id;
    @Pattern(regexp = "^[A-Za-z0-9áéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre del proveedor es obligatorio")
    private String name;
    @Pattern(regexp = "20\\d{11}", message = "El RUC debe iniciar con '20' y tener un total de 13 caracteres")
    private String ruc;
    @Pattern(regexp = "^[A-Za-z0-9áéíóúÁÉÍÓÚñÑ ]+$", message = "La dirección del proveedor es obligatoria")
    private String address;
    @Pattern(regexp = "\\d{9}", message = "El Teléfono debe tener 9 dígitos")
    private String phone;
    private int status;
}
