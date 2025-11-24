package com.example.sheep.domain.usecase;

import com.example.sheep.infraestructure.driver_adapters.jpa_repository.AnalisisData;
import com.example.sheep.infraestructure.driver_adapters.jpa_repository.AnalisisDataJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalisisUseCase {
    private final AnalisisDataJpaRepository analisisRepository;

    public AnalisisData guardarAnalisis(AnalisisData analisis) {
        return analisisRepository.save(analisis);
    }

    public List<AnalisisData> obtenerPorUsuario(Long usuarioId) {
        return analisisRepository.findByUsuarioId(usuarioId);
    }
}
