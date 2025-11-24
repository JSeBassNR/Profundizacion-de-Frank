package com.example.sheep.infraestructure.mapper;

import com.example.sheep.infraestructure.driver_adapters.jpa_repository.AnalisisData;
import com.example.sheep.infraestructure.dto.AnalisisResponse;
import org.springframework.stereotype.Component;

@Component
public class MapperAnalisis {
    public AnalisisResponse toResponse(AnalisisData analisis) {
        if (analisis == null) return null;
        AnalisisResponse resp = new AnalisisResponse();
        resp.setId(analisis.getId());
        resp.setUsuarioId(analisis.getUsuarioId());
        resp.setReferencia(analisis.getReferencia());
        resp.setResultado(analisis.getResultado());
        resp.setFecha(analisis.getFecha());
        return resp;
    }
}
