package com.example.sheep.infraestructure.entry_points;

import com.example.sheep.domain.model.Usuario;
import com.example.sheep.infraestructure.dto.UsuarioResponse;
import com.example.sheep.domain.usecase.UsuarioUseCase;
import com.example.sheep.infraestructure.driver_adapters.jpa_repository.UsuarioData;
import com.example.sheep.infraestructure.mapper.MapperUsuario;
import com.example.sheep.infraestructure.dto.AnalisisNotificacionRequest;
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

    private final UsuarioUseCase usuarioUseCase;
    private final MapperUsuario mapperUsuario;

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

        // Authorization: allow ADMIN or the owner (matching principal username == email)
        var auth = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        String principalName = auth.getName();

        if(!isAdmin && (principalName == null || !principalName.equalsIgnoreCase(usuarioValidadoEncontrado.getEmail()))){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(mapperUsuario.toResponse(usuarioValidadoEncontrado), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
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
        return ResponseEntity.accepted().build();
    }
}




