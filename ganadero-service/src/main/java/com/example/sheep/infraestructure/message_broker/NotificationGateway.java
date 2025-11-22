package com.example.sheep.infraestructure.message_broker;

import com.example.sheep.domain.model.Notificacion;

/**
 * Puerto interno para publicar mensajes a SQS (o cualquier broker).
 */
public interface NotificationGateway {
 void enviarMensaje(Notificacion mensajeCola);
}

