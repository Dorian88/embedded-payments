# Sprint 1 - Cierre y estado actual

Este sprint se considera cerrado.

Este indice resume que ya esta implementado en el repo y que falta para Sprint 2.
La idea es que cualquier persona del equipo pueda revisar rapido el estado actual
sin recorrer todo el codigo.

## Lectura recomendada

1. `docs/sprint-1/estado-actual.md`
2. `docs/sprint-1/pendientes-sprint-2.md`
3. `docs/sprint-1/hu-1.1/demo-funcional.md`
4. `docs/sprint-1/hu-1.1/trazabilidad.md`

## Resumen ejecutivo

- Arquitectura modular monolith operativa (`auth`, `merchant`, `payment`, `transaction`, `refund`).
- Endpoints base de negocio habilitados bajo `/api/v1`.
- Flujo demo E2E disponible en Postman (merchant -> token -> payment intent -> transaction -> refund).
- HU 1.1 funcional y con pruebas API para casos principales.
- Sprint 2 enfocado en hardening: seguridad real, reglas de negocio faltantes y mas cobertura automatizada.

## Evidencias principales en repo

- API controllers: `src/main/java/com/paymentplatform/embeddedpayments/*/api/*Controller.java`
- Seguridad: `src/main/java/com/paymentplatform/embeddedpayments/shared/security/SecurityConfig.java`
- Manejo de errores: `src/main/java/com/paymentplatform/embeddedpayments/shared/exception/GlobalExceptionHandler.java`
- Coleccion demo: `postman/embedded-payments-sprint1.postman_collection.json`
- Environment local: `postman/embedded-payments-local.postman_environment.json`
- Tests actuales: `src/test/java/com/paymentplatform/embeddedpayments/merchant/api/MerchantRegistrationApiTests.java`

