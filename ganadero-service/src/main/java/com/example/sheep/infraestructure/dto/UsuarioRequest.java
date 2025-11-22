package com.example.sheep.infraestructure.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request para crear un usuario del servicio de autenticación.
 * Alineado con el modelo Usuario del microservicio Sheep.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {
 @NotBlank
 @Size(min =2)
 private String nombreCompleto;
 @NotBlank
 @Email
 private String email;
 // Regex corregida: se escapan correctamente el guion y \s para Java
 @Pattern(regexp = "^[0-9+()\\-\\s]{0,20}$")
 private String telefono;
 @NotBlank
 private String rol; // productor | veterinario | admin
 @NotBlank
 private String passwordHash;
 @NotNull
 private Boolean activo;
 // Campo opcional adicional
 private String ubicacion;
}

