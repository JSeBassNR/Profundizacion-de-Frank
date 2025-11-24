package com.example.sheep.infraestructure.entry_points;

import com.example.sheep.domain.model.Usuario;
import com.example.sheep.infraestructure.dto.UsuarioResponse;
import com.example.sheep.domain.usecase.UsuarioUseCase;
import com.example.sheep.infraestructure.driver_adapters.jpa_repository.UsuarioData;
import com.example.sheep.infraestructure.mapper.MapperUsuario;
import com.example.sheep.infraestructure.dto.AnalisisNotificacionRequest;
import com.example.sheep.domain.usecase.AnalisisUseCase;
import com.example.sheep.infraestructure.mapper.MapperAnalisis;
import com.example.sheep.infraestructure.dto.AnalisisResponse;
import com.example.sheep.infraestructure.driver_adapters.jpa_repository.AnalisisData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/MachIA/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    @GetMapping("/{id}/ovejas/count")
    public ResponseEntity<Long> getOvejaCount(@PathVariable Long id) {
        // Llamada HTTP a Sheep para obtener el conteo
        try {
            java.net.URL url = new java.net.URL("http://localhost:9191/api/MachIA/ovejas/productor/" + id + "/count");
            java.net.HttpURLConnection con = (java.net.HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(3000);
            con.setReadTimeout(3000);
            int status = con.getResponseCode();
            if (status == 200) {
                try (java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    StringBuilder content = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    long count = Long.parseLong(content.toString());
                    return ResponseEntity.ok(count);
                }
            } else {
                return ResponseEntity.status(status).body(-1L);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(-1L);
        }
    }

    private final UsuarioUseCase usuarioUseCase;
    private final MapperUsuario mapperUsuario;
    private final AnalisisUseCase analisisUseCase;
    private final MapperAnalisis mapperAnalisis;
    @GetMapping("/{id}/analisis")
    public ResponseEntity<?> getAnalisisByUsuario(@PathVariable Long id) {
        var analisisList = analisisUseCase.obtenerPorUsuario(id);
        var responseList = analisisList.stream().map(mapperAnalisis::toResponse).toList();
        return ResponseEntity.ok(responseList);
    }

    @PostMapping("/save")
    public ResponseEntity<UsuarioResponse> saveUsuario(@RequestBody UsuarioData usuarioData){
        Usuario usuario = mapperUsuario.toUsuario(usuarioData);
        Usuario usuarioValidadoGuardado = usuarioUseCase.guardarUsuario(usuario);

        if(usuarioValidadoGuardado.getId() != null){
            return new ResponseEntity<>(mapperUsuario.toResponse(usuarioValidadoGuardado), HttpStatus.OK);
        }

        return new ResponseEntity<>(mapperUsuario.toResponse(usuarioValidadoGuardado), HttpStatus.CONFLICT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> findByIdUsuario(@PathVariable Long id){
        Usuario usuarioValidadoEncontrado = usuarioUseCase.buscarPorIdUsuario(id);
        if(usuarioValidadoEncontrado.getId() == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mapperUsuario.toResponse(usuarioValidadoEncontrado), HttpStatus.OK);
    }

    @DeleteMapping("/detele/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        try{
            usuarioUseCase.eliminarPorIdUsuario(id);
            return ResponseEntity.ok().body("Usuario eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<UsuarioResponse> updateUsuario(@RequestBody UsuarioData usuarioData){
        try{
            Usuario usuario = mapperUsuario.toUsuario(usuarioData);
            Usuario usuarioValidadActualizado = usuarioUseCase.actualizaUsuario(usuario);
            return new ResponseEntity<>(mapperUsuario.toResponse(usuarioValidadActualizado), HttpStatus.OK);
        }catch (Exception error){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody UsuarioData usuarioData){
        try{
            String mensajeRespuesta = usuarioUseCase.loginUsuario(usuarioData.getEmail(),usuarioData.getPassword());
            return new ResponseEntity<>(mensajeRespuesta, HttpStatus.OK);
        } catch (Exception error){
            return new ResponseEntity<>("Falló el logueo", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Page<UsuarioResponse>> findAllPaged(@RequestParam(defaultValue="0") int page,
                                                              @RequestParam(defaultValue="10") int size){
        var usuarios = usuarioUseCase.obtenerPaginado(PageRequest.of(page, size));
        // En paginación se responde 200 con página vacía para evitar error de tipo genérico.
        if(!usuarios.hasContent()) {
            return ResponseEntity.ok(Page.empty(usuarios.getPageable()));
        }
        var content = usuarios.getContent().stream()
                .map(mapperUsuario::toResponse)
                .collect(Collectors.toList());
        var pageResp = new PageImpl<>(content, usuarios.getPageable(), usuarios.getTotalElements());
        return ResponseEntity.ok(pageResp);
    }

    @PostMapping("/{id}/analisis/notificar")
    @PreAuthorize("hasAnyRole('VETERINARIO','ADMIN')")
    public ResponseEntity<Void> notificarAnalisis(@PathVariable Long id, @Valid @RequestBody AnalisisNotificacionRequest body){
        usuarioUseCase.notificarAnalisisListo(id, body.getReferencia());
        // Guardar el análisis en la base de datos
        var analisis = AnalisisData.builder()
            .usuarioId(id)
            .referencia(body.getReferencia())
            .resultado(body.getResultado())
            .fecha(java.time.LocalDateTime.now())
            .build();
        analisisUseCase.guardarAnalisis(analisis);
        return ResponseEntity.accepted().build();
    }
}



