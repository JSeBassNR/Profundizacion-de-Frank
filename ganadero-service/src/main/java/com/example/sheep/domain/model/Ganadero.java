package com.example.sheep.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.example.sheep.domain.exception.DomainValidationException;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Ganadero {
 // Identificador único del ganadero
 private Long id;
 // Nombre completo del ganadero
 private String nombre;
 // Correo electrónico de contacto
 private String email;
 // Teléfono de contacto (opcional)
 private String telefono;
 // Ubicación o dirección (opcional)
 private String ubicacion;

 // Reglas de validación de dominio
 public void validate() {
 if (nombre == null || nombre.isBlank()) {
 throw new DomainValidationException("Nombre de ganadero es requerido");
 }
 if (email == null || email.isBlank() || !email.contains("@")) {
 throw new DomainValidationException("Correo válido es requerido");
 }
 // teléfono y ubicación son opcionales
 }
}
