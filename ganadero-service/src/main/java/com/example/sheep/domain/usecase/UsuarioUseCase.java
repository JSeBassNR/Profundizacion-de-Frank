package com.example.sheep.domain.usecase;

import com.example.sheep.domain.model.Notificacion;
import com.example.sheep.domain.model.Usuario;
import com.example.sheep.domain.model.gateway.UsuarioGateway;
import com.example.sheep.domain.model.gateway.NotificacionGateway;
import com.example.sheep.domain.model.gateway.EncrypterGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RequiredArgsConstructor
public class UsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    private final NotificacionGateway notificacionGateway;
    private final EncrypterGateway encrypterGateway;

    public Usuario guardarUsuario(Usuario usuario){
        if(usuario.getEmail() == null || usuario.getPassword() == null){
            throw new NullPointerException("Email y password son obligatorios - guardarUsuario");
        }

        String passwordEncrypt = encrypterGateway.encrypt(usuario.getPassword());
        usuario.setPassword(passwordEncrypt);
        Usuario usuarioGuardado = usuarioGateway.guardar(usuario);

        Notificacion mensajeNotificacion = Notificacion.builder()
                .tipo("Registro Usuario")
                .email(usuario.getEmail())
                .numeroTelefono(usuario.getNumeroTelefono())
                .mensaje("Usuario registrado con exito")
                .build();

        notificacionGateway.enviarMensaje(mensajeNotificacion);

        return usuarioGuardado;
    }
    public void eliminarPorIdUsuario(Long id){
        try{
            usuarioGateway.eliminarPorId(id);
        }catch (Exception message){
            System.out.println(message.getMessage());
        }
    }

    public Usuario buscarPorIdUsuario(Long id){
        try {
            return usuarioGateway.buscarPorId(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            Usuario usuarioVacio = new Usuario();
            return usuarioVacio;
        }
    }

    public Usuario actualizaUsuario(Usuario usuario){
        if(usuario.getId() == null){
            throw new IllegalArgumentException("El id es obligatorio para actualizar");
        }
        // Si se provee password en la actualización, cifrarla. Si no, dejar que la capa de persistencia
        // preserve la contraseña existente.
        if(usuario.getPassword() != null && !usuario.getPassword().isBlank()){
            String passwordEncrypt = encrypterGateway.encrypt(usuario.getPassword());
            usuario.setPassword(passwordEncrypt);
        }

        return usuarioGateway.actualizarUsuario(usuario);
    }

    public Page<Usuario> obtenerPaginado(Pageable pageable){
        return usuarioGateway.obtenerPaginado(pageable);
    }

    public void notificarAnalisisListo(Long id, String referencia){
        Usuario usuario = usuarioGateway.buscarPorId(id);

        Notificacion mensajeNotificacion = Notificacion.builder()
                .tipo("Analisis Listo")
                .email(usuario.getEmail())
                .numeroTelefono(usuario.getNumeroTelefono())
                .mensaje("Analisis " + referencia + " listo")
                .build();

        notificacionGateway.enviarMensaje(mensajeNotificacion);
    }

    public String loginUsuario(String email, String password){
        try {
            Usuario usuarioLogueo = usuarioGateway.buscarPorEmail(email);

            if(usuarioLogueo.getEmail() == null || usuarioLogueo.getPassword() == null){
                return "Usuario no encontrado";
            }

            if(encrypterGateway.checkPass(password, usuarioLogueo.getPassword())){
                return "Credenciales Validas";
            } else {
                return "Creenciales Invalidas";
            }
        } catch (Exception e) {
            return "Usuario no encontrado";
        }
    }

}

