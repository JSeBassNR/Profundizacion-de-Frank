package com.example.sheep.infraestructure.mapper;

import com.example.sheep.domain.model.Ganadero;
import com.example.sheep.infraestructure.driver_adapters.jpa_repository.GanaderoData;
import org.springframework.stereotype.Component;

@Component
public class GanaderoMapper {
 public Ganadero toDomain(GanaderoData data){
 if(data==null) return null;
 Ganadero g = new Ganadero();
 g.setId(data.getId());
 g.setNombre(data.getNombre());
 g.setEmail(data.getEmail());
 g.setTelefono(data.getTelefono());
 g.setUbicacion(data.getUbicacion());
 return g;
 }
 public GanaderoData toData(Ganadero domain){
 if(domain==null) return null;
 GanaderoData d = new GanaderoData();
 d.setId(domain.getId());
 d.setNombre(domain.getNombre());
 d.setEmail(domain.getEmail());
 d.setTelefono(domain.getTelefono());
 d.setUbicacion(domain.getUbicacion());
 return d;
 }
}
