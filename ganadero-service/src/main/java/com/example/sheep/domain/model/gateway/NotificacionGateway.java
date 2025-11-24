package com.example.sheep.domain.model.gateway;

import com.example.sheep.domain.model.Notificacion;

public interface NotificacionGateway {

    void enviarMensaje(Notificacion mensajeJson);
}
