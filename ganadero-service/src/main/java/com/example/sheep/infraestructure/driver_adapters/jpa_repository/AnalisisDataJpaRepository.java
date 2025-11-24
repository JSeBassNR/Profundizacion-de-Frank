package com.example.sheep.infraestructure.driver_adapters.jpa_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalisisDataJpaRepository extends JpaRepository<AnalisisData, Long> {
    List<AnalisisData> findByUsuarioId(Long usuarioId);
}
