package com.example.Sheep.infraestructure.entry_points.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class OvejaResponseDTO {
 Long id;
 String identificacion;
 String raza;
 Integer edad;
 Boolean sexo;
 String estadoSalud;
 LocalDateTime fechaRegistro;
 Long propietarioId;
}
