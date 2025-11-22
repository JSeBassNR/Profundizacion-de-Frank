package com.example.Sheep.infraestructure.infraestructure.entry_points.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OvejaRequestDTO {
 @NotBlank(message = "La identificaci�n es requerida")
 private String identificacion;
 private String raza;
 @Min(value =0, message = "La edad no puede ser negativa")
 private Integer edad;
 // TRUE hembra, FALSE macho
 private Boolean sexo;
 private String estadoSalud;
 // id del propietario (opcional)
 private Long propietarioId;
}

