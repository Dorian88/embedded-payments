# Sprint 2 - Pendientes y plan propuesto

## Objetivo

Pasar de un backend demostrable a un backend mas robusto para entorno real,
con foco en seguridad, reglas de negocio completas y calidad automatizada.

## Prioridad alta (debe quedar en Sprint 2)

1. Aplicar seguridad real en endpoints
   - Restringir endpoints de negocio con autenticacion JWT (no `permitAll`).
   - Definir que rutas son publicas y cuales requieren token.
   - Alinear roles/permisos con casos de uso (admin vs self registration).

2. Completar validaciones de negocio cruzadas
   - Verificar existencia de `merchantId` al crear payment intents.
   - Verificar existencia de `paymentIntentId` al crear transacciones.
   - Verificar existencia de `transactionId` al crear refunds.
   - Definir reglas de monto para evitar inconsistencias (ej: refund > transaccion).

3. Endpoints de consulta minima para operacion
   - Consultar merchant por id.
   - Consultar payment intent, transaccion y refund por id/estado.
   - Endpoints de health/ready si se necesitan para despliegue.

4. Incrementar pruebas automatizadas
   - Tests API para auth/payment/transaction/refund/webhooks.
   - Casos negativos de negocio y validacion.
   - Pruebas con seguridad activada para evitar regresiones.

## Prioridad media

1. Mejorar trazabilidad y observabilidad
   - Correlacion de logs con `traceId`.
   - Definir eventos auditables adicionales (payment, transaction, refund).

2. Harden de webhooks
   - Validar firma/origen del webhook.
   - Definir contrato de eventos aceptados.
   - Idempotencia para evitar reprocesamiento.

3. Alineacion modelo tecnico vs modelo canonico
   - Revisar convergencia entre tablas tecnicas (`auth_session`, `audit_event`)
     y tablas canonicas (`users`, `roles`, `permissions`, `audit_logs`) en `db/001_create_schema.sql`.

## Fuera de alcance sugerido para Sprint 2 (si hay presion de tiempo)

- Integraciones externas complejas (pasarela real de pagos).
- Reporteria avanzada.
- Multi-tenant avanzado.

## Criterio de salida sugerido para Sprint 2

- Seguridad aplicada en endpoints criticos y validada con tests.
- Flujo E2E mantiene funcionamiento con reglas de negocio reforzadas.
- Cobertura automatizada extendida a todos los modulos core.
- Documentacion y Postman actualizados al nuevo comportamiento.

