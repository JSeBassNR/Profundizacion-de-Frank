package com.example.sheep.domain.exception;

public class UsuarioNotFoundException extends RuntimeException {
 public UsuarioNotFoundException(Long id) {
 super("Ganadero no encontrado: " + id);
 }
}
