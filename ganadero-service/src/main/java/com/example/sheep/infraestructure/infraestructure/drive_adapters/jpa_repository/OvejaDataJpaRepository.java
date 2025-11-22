package com.example.Sheep.infraestructure.infraestructure.drive_adapters.jpa_repository;

import com.example.Sheep.infraestructure.drive_adapters.jpa_repository.OvejaData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA de Oveja con consultas derivadas por convenci�n.
 */
@Repository
public interface OvejaDataJpaRepository extends JpaRepository<OvejaData, Long> {
    OvejaData findByIdentificacion(String identificacion);
    
    /**
     * Obtiene una p�gina de OvejaData asociada a un ganadero espec�fico.
     *
     * @param ganaderoId El ID del ganadero.
     * @param pageable   Objeto Pageable que contiene informaci�n de paginaci�n y clasificaci�n.
     * @return Una p�gina de OvejaData asociada al ganadero ID proporcionado.
     */
    Page<OvejaData> findByGanaderoId(Long ganaderoId, Pageable pageable);
}

