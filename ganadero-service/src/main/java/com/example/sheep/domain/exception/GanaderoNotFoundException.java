package com.example.sheep.domain.exception;

public class GanaderoNotFoundException extends RuntimeException {
 public GanaderoNotFoundException(Long id) {
 super("Ganadero no encontrado: " + id);
 }
}
