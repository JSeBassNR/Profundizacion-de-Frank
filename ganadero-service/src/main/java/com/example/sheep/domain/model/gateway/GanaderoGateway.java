package com.example.sheep.domain.model.gateway;

import com.example.sheep.domain.model.Ganadero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GanaderoGateway {
 Ganadero guardar(Ganadero ganadero);
 Ganadero buscarPorId(Long id);
 Ganadero actualizar(Ganadero ganadero);
 void eliminar(Long id);
 Ganadero buscarPorEmail(String email);
 Page<Ganadero> obtenerPaginado(Pageable pageable);
}
