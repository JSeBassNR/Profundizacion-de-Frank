package com.example.Sheep.domain.model.gateway;

import com.example.Sheep.domain.model.Oveja;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OvejaGateway {
 Oveja guardar(Oveja oveja);
 Oveja buscarPorId(Long id);
 Oveja actualizar(Oveja oveja);
 void eliminar(Long id);
 Oveja buscarPorIdentificacion(String identificacion);
 Page<Oveja> obtenerPaginado(Pageable pageable);
}
