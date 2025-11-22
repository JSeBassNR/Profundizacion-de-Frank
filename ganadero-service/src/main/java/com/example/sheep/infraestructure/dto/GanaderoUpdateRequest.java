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
 * Request para actualizar un usuario del servicio de autenticaci?n.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GanaderoUpdateRequest {
 @NotNull
 private Long idUsuario;
 @NotBlank
 @Size(min =2)
 private String nombreCompleto;
 @NotBlank
 @Email
 private String email;
 @Pattern(regexp = "^[0-9+()\-\s]{0,20}$")
 private String telefono;
 @NotBlank
 private String rol;
 @NotBlank
 private String passwordHash;
 @NotNull
 private Boolean activo;
 private String ubicacion;
}

