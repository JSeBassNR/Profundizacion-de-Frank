package com.example.sheep.infraestructure.driver_adapters.jpa_repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ganaderos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GanaderoData {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 @Column(nullable = false)
 private String nombre;
 @Column(nullable = false, unique = true)
 private String email;
 private String telefono;
 private String ubicacion;
}
