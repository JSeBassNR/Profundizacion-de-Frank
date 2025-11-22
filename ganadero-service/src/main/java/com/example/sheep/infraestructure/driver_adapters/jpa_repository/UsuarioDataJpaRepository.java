package com.example.sheep.infraestructure.driver_adapters.jpa_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioDataJpaRepository extends JpaRepository<UsuarioData, Long> {
    Optional<UsuarioData> findByEmail(String email);
}
