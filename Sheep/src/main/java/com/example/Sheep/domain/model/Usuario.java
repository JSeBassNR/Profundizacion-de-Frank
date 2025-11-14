package com.example.Sheep.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.example.Sheep.domain.exception.DomainValidationException;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Usuario {
 // Identificador único del usuario
 private Long idUsuario;
 private String nombreCompleto;
 private String email;
 private String telefono;
 // Roles válidos: productor, veterinario, admin
 private String rol;
 private String passwordHash;
 private LocalDateTime fechaRegistro;
 private Boolean activo;

 public void validate() {
 if (nombreCompleto == null || nombreCompleto.isBlank()) {
 throw new DomainValidationException("Nombre completo es requerido");
 }
 if (email == null || email.isBlank() || !email.contains("@")) {
 throw new DomainValidationException("Correo válido es requerido");
 }
 if (rol == null || rol.isBlank()) {
 throw new DomainValidationException("Rol es requerido");
 }
 if (passwordHash == null || passwordHash.isBlank()) {
 throw new DomainValidationException("PasswordHash es requerido");
 }
 if (activo == null) {
 throw new DomainValidationException("Campo activo es requerido");
 }
 }
}
