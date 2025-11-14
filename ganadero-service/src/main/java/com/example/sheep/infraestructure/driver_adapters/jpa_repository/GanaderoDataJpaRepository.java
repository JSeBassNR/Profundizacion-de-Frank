package com.example.sheep.infraestructure.driver_adapters.jpa_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GanaderoDataJpaRepository extends JpaRepository<GanaderoData, Long> {
 GanaderoData findByEmail(String email);
}
