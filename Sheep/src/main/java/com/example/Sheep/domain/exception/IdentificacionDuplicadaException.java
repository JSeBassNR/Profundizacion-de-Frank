package com.example.Sheep.domain.exception;

public class IdentificacionDuplicadaException extends RuntimeException {
 public IdentificacionDuplicadaException(String identificacion) {
 super("La identificación ya existe: " + identificacion);
 }
}
