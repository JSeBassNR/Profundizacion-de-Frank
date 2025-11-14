package com.example.Sheep.infraestructure.mapper;

import com.example.Sheep.domain.model.Oveja;
import com.example.Sheep.domain.model.Usuario;
import com.example.Sheep.infraestructure.drive_adapters.jpa_repository.OvejaData;
import org.springframework.stereotype.Component;

@Component
public class OvejaMapper {
 public Oveja toDomain(OvejaData data){
 if(data==null) return null;
 Oveja o = new Oveja();
 o.setIdOveja(data.getId());
 o.setIdentificacion(data.getIdentificacion());
 o.setRaza(data.getRaza());
 o.setEdad(data.getEdad());
 o.setSexo(data.getSexo());
 o.setEstadoSalud(data.getEstadoSalud());
 if(data.getPropietarioId()!=null){
 Usuario propietario = new Usuario();
 propietario.setIdUsuario(data.getPropietarioId());
 o.setPropietario(propietario);
 }
 return o;
 }
 public OvejaData toData(Oveja domain){
 if(domain==null) return null;
 OvejaData d = new OvejaData();
 d.setId(domain.getIdOveja());
 d.setIdentificacion(domain.getIdentificacion());
 d.setRaza(domain.getRaza());
 d.setEdad(domain.getEdad());
 d.setSexo(domain.getSexo());
 d.setEstadoSalud(domain.getEstadoSalud());
 d.setPropietarioId(domain.getPropietario()!=null? domain.getPropietario().getIdUsuario(): null);
 return d;
 }
}
