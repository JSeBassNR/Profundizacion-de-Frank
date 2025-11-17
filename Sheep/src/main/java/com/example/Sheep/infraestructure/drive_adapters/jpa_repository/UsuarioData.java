package com.example.Sheep.infraestructure.drive_adapters.jpa_repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioData {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "id_usuario")
 private Long id;

 @Column(nullable = false)
 private String nombreCompleto;

 @Column(nullable = false, unique = true)
 private String email;

 private String telefono;

 @Column(nullable = false)
 private String rol;

 @Column(name = "password_hash", nullable = false)
 private String passwordHash;

 @Column(nullable = false)
 private Boolean activo;
}
