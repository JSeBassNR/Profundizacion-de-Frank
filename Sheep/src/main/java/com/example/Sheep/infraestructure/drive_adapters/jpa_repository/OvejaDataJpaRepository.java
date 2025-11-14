package com.example.Sheep.infraestructure.drive_adapters.jpa_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OvejaDataJpaRepository extends JpaRepository<OvejaData, Long> {
 OvejaData findByIdentificacion(String identificacion);
}
