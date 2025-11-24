package com.example.sheep.infraestructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO para solicitar notificaci�n de an�lisis listo.
 */
@Data
public class AnalisisNotificacionRequest {
	@NotBlank
	@Size(max = 100)
	private String referencia; // identificador o código del análisis

	private String resultado; // resultado del análisis (opcional)
}
