package com.example.sheep.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import com.example.sheep.domain.exception.InvalidEmailException;
import com.example.sheep.domain.exception.InvalidNameException;
import com.example.sheep.domain.exception.InvalidPhoneException;
import com.example.sheep.domain.exception.DomainValidationException;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Usuario {

    private Long id;
    private String nombre;
    private String email;
    private String password;
    private String rol;
    private Integer edad;
    private String numeroTelefono;
    private Boolean activo;
    private LocalDateTime fechaRegistro;
    private String ubicacion;

    private static final Set<String> ROLES_VALIDOS = Set.of("productor", "veterinario", "admin");

 public void validate() {
 if (nombre == null || nombre.isBlank() || nombre.trim().length() <2) {
 throw new InvalidNameException("Nombre es requerido y debe tener al menos2 caracteres");
 }
 if (email == null || email.isBlank() || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$") ) {
 throw new InvalidEmailException("Correo válido es requerido");
 }
 if (numeroTelefono != null && !numeroTelefono.isBlank()) {
 String phone = numeroTelefono.trim();
 if (!phone.matches("^[0-9+()\\-\\s]{7,20}$")) {
 throw new InvalidPhoneException("Teléfono inválido");
 }
 }
 if (rol == null || rol.isBlank() || !ROLES_VALIDOS.contains(rol.trim().toLowerCase())) {
 throw new DomainValidationException("Rol inválido. Debe ser uno de " + ROLES_VALIDOS);
 }

 if (activo == null) {
 throw new DomainValidationException("Activo es requerido");
 }
 // Set defaults
 if (fechaRegistro == null) {
 fechaRegistro = LocalDateTime.now();
 }
 // Normalizaciones
 rol = rol.trim().toLowerCase();
 email = email.trim().toLowerCase();
 nombre = nombre.trim();
 }

 /**
 * Helper para validar y retornar la instancia (fluido).
 */
 public Usuario validated() {
 validate();
 return this;
 }
}
