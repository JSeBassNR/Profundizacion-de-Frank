package com.example.sheep.infraestructure.notification;

import com.example.sheep.domain.model.gateway.NotificacionGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Implementación simple de NotificacionGateway.
 * Actualmente solo registra en logs. Sustituir por integración real (SNS, SES, Email, SMS, etc.).
 */
@Slf4j
@Component
public class NotificacionGatewayImpl implements NotificacionGateway {
 @Override
 public void enviarHabilitacionCuenta(Long ganaderoId, String emailDestino, String nombreGanadero) {
 log.info("[NOTIFICACION] Habilitación de cuenta -> id={}, email={}, nombre={}", ganaderoId, emailDestino, nombreGanadero);
 }

 @Override
 public void enviarAnalisisListo(Long ganaderoId, String emailDestino, String nombreGanadero, String referenciaAnalisis) {
 log.info("[NOTIFICACION] Análisis listo -> id={}, email={}, nombre={}, ref={}", ganaderoId, emailDestino, nombreGanadero, referenciaAnalisis);
 }
}

