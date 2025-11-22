package com.example.sheep.domain.model.gateway;

/**
 * Puerto de salida para enviar notificaciones al ganadero.
 * Mantiene la arquitectura limpia permitiendo múltiples implementaciones (SNS, email, etc.).
 */
public interface NotificacionGateway {
 void enviarHabilitacionCuenta(Long ganaderoId, String emailDestino, String nombreGanadero);
 void enviarAnalisisListo(Long ganaderoId, String emailDestino, String nombreGanadero, String referenciaAnalisis);
}

