package com.example.Sheep.domain.domain.exception;

public class OvejaNotFoundException extends RuntimeException {
 public OvejaNotFoundException(Long id) {
 super("Oveja no encontrada: " + id);
 }
}

