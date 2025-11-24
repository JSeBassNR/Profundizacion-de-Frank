package com.example.sheep.infraestructure.security;

import com.example.sheep.domain.model.Usuario;
import com.example.sheep.domain.model.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UsuarioUserDetailsService implements UserDetailsService {

    private final UsuarioGateway usuarioGateway;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Usuario usuario = usuarioGateway.buscarPorEmail(username);
            if (usuario == null || usuario.getEmail() == null) {
                throw new UsernameNotFoundException("Usuario no encontrado: " + username);
            }

            String roleRaw = usuario.getRol() == null ? "USER" : usuario.getRol().trim().toUpperCase();
            // Ensure role prefix ROLE_ is handled by Spring automatically when using roles()
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + roleRaw);

            return User.withUsername(usuario.getEmail())
                    .password(usuario.getPassword())
                    .authorities(Collections.singletonList(authority))
                    .build();
        } catch (Exception e) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username, e);
        }
    }
}
