# Sprint 1 - Estado actual del repo

## 1) Alcance implementado

### API disponible

- `POST /api/v1/merchants`
- `POST /api/v1/auth/token`
- `POST /api/v1/payments/intents`
- `POST /api/v1/transactions`
- `POST /api/v1/refunds`
- `POST /api/v1/webhooks`

Referencia: `src/main/java/com/paymentplatform/embeddedpayments/*/api/*Controller.java`

### Modulos y comportamiento actual

- `merchant`: registro de comercio, estado inicial `INACTIVE`, validacion de duplicado por email.
- `auth`: emision de JWT para `merchantId` y persistencia de sesion en `auth_session`.
- `payment`: creacion de payment intent con validacion de monto y moneda, estado `CREATED`.
- `transaction`: registro de transaccion con estado `SUCCEEDED`.
- `refund`: creacion de refund con estado `PENDING`.
- `webhooks`: endpoint tecnico que recibe payload y responde `202 accepted`.

## 2) Seguridad y manejo de errores

- JWT filter implementado: `src/main/java/com/paymentplatform/embeddedpayments/shared/security/JwtAuthenticationFilter.java`.
- Config actual permite todos los requests (`permitAll`), por lo que la seguridad aun no es restrictiva:
  `src/main/java/com/paymentplatform/embeddedpayments/shared/security/SecurityConfig.java`.
- Errores estandarizados con `errorCode`, `details`, `traceId`:
  `src/main/java/com/paymentplatform/embeddedpayments/shared/exception/GlobalExceptionHandler.java`.

## 3) Persistencia e infraestructura

- Script base de schema disponible: `db/001_create_schema.sql`.
- Entidades/repositories por modulo implementadas en `domain` + `infrastructure/repository`.
- Auditoria de eventos activa para registro de comercio (`audit_event`).

## 4) Demo funcional disponible

- Coleccion actualizada para flujo real del backend:
  `postman/embedded-payments-sprint1.postman_collection.json`.
- Environment local actualizado:
  `postman/embedded-payments-local.postman_environment.json`.
- Base URL local en docs/demo: `http://localhost:8085`.

## 5) Testing actual

- Tests presentes en repo:
  - `src/test/java/com/paymentplatform/embeddedpayments/EmbeddedPaymentsApplicationTests.java`
  - `src/test/java/com/paymentplatform/embeddedpayments/merchant/api/MerchantRegistrationApiTests.java`
- Cobertura automatizada fuerte en HU 1.1 (merchant registration).
- Cobertura automatizada limitada en auth/payment/transaction/refund/webhooks.

## 6) Estado de cierre Sprint 1

Sprint 1 queda cerrado con backend funcional para demo E2E y HU 1.1 validada.
El siguiente sprint debe enfocarse en robustecer reglas de negocio, seguridad
aplicada de forma real y cobertura de pruebas por modulo.

