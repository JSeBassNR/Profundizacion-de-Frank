package com.example.Sheep.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.example.Sheep.domain.exception.DomainValidationException;

/**
 * Entidad de dominio (Aggregate Root) que representa una oveja.
 *
 * Nota de arquitectura:
 * - Para mantener bajo acoplamiento entre microservicios, no se referencia
 * la entidad remota de Ganadero; se almacena únicamente su identificador
 * en el campo {@code ganaderoId}.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Oveja {
 // Identificador único interno
 private Long idOveja;
 // Código o chapa de la oveja (único en el corral)
 private String identificacion;
 private String raza;
 private Integer edad; // en años
 // Sexo: TRUE = hembra, FALSE = macho (según BD)
 private Boolean sexo;
 // Estado de salud: libre
 private String estadoSalud;
 private LocalDateTime fechaRegistro;
 // Identificador del ganadero (microservicio externo)
 private Long ganaderoId;

 /**
 * Reglas de validación de la entidad.
 * - Identificación requerida.
 * - Edad no puede ser negativa.
 */
 public void validate() {
 if (identificacion == null || identificacion.isBlank()) {
 throw new DomainValidationException("Identificación de la oveja es requerida");
 }
 if (edad != null && edad <0) {
 throw new DomainValidationException("Edad no puede ser negativa");
 }
 }
}
