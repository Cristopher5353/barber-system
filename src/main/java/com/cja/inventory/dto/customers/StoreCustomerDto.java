package com.cja.inventory.dto.customers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreCustomerDto {
    private Long id;
    @Pattern(regexp = "^[A-Za-z0-9áéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre del cliente es obligatorio")
    private String name;
    @Pattern(regexp = "^[A-Za-z0-9áéíóúÁÉÍÓÚñÑ ]+$", message = "El documento del cliente es obligatorio")
    private String document;
    @Email(message = "Por favor ingresa un correo correcto")
    private String email;
    @Pattern(regexp = "\\d{9}", message = "El Teléfono debe tener 9 dígitos")
    private String phone;
}