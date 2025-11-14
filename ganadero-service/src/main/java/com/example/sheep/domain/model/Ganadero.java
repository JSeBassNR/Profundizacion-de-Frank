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
 // Identificador �nico del ganadero
 private Long id;
 // Nombre completo del ganadero
 private String nombre;
 // Correo electr�nico de contacto
 private String email;
 // Tel�fono de contacto (opcional)
 private String telefono;
 // Ubicaci�n o direcci�n (opcional)
 private String ubicacion;

 // Reglas de validaci�n de dominio
 public void validate() {
 if (nombre == null || nombre.isBlank()) {
 throw new DomainValidationException("Nombre de ganadero es requerido");
 }
 if (email == null || email.isBlank() || !email.contains("@")) {
 throw new DomainValidationException("Correo v�lido es requerido");
 }
 // tel�fono y ubicaci�n son opcionales
 }
}
