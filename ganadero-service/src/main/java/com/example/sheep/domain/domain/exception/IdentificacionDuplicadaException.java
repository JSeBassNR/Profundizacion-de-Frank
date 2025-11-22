package com.example.Sheep.domain.domain.exception;

public class IdentificacionDuplicadaException extends RuntimeException {
 public IdentificacionDuplicadaException(String identificacion) {
 super("La identificaci�n ya existe: " + identificacion);
 }
}

