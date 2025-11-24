package com.example.sheep.application.config;

import com.example.sheep.domain.model.gateway.EncrypterGateway;
import com.example.sheep.domain.model.gateway.UsuarioGateway;
import com.example.sheep.domain.model.gateway.NotificacionGateway;
import com.example.sheep.domain.usecase.UsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {


    @Bean
    public UsuarioUseCase usuarioUseCase(UsuarioGateway usuarioGateway, NotificacionGateway notificacionGateway, EncrypterGateway encrypterGateway){
        return new UsuarioUseCase(usuarioGateway, notificacionGateway, encrypterGateway);
    }
}
