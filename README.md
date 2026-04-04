# Embedded Payments Platform API

Backend API for an embedded payments platform built with Spring Boot using a modular monolith architecture.

## 🚀 Demo en nube

El servicio está desplegado en Render:

- **URL**: https://embedded-payments-1.onrender.com
- **Swagger**: https://embedded-payments-1.onrender.com/swagger-ui/index.html
- **API Docs**: https://embedded-payments-1.onrender.com/v3/api-docs

## Architecture

Root package: `com.paymentplatform.embeddedpayments`

Modules:
- `auth`
- `merchant`
- `payment`
- `transaction`
- `refund`

Shared modules:
- `shared.security`
- `shared.audit`
- `shared.logging`
- `shared.exception`

Infrastructure modules:
- `infrastructure.config`
- `infrastructure.persistence`
- `infrastructure.cache`
- `infrastructure.webhooks`

### Layer Rules

Each business module follows:
- `api`: REST controllers
- `application`: use cases
- `domain.entity`: domain entities
- `domain.repository`: repository interfaces
- `domain.services`: domain services
- `infrastructure.repository`: JPA implementations

Dependency direction:
- Controllers -> Use cases
- Use cases -> Domain services and domain repository interfaces
- Infrastructure repositories -> Domain repository interfaces

## Local Run

Requirements:
- Java 21+
- Maven Wrapper

```bash
./mvnw spring-boot:run
```

### Run with Docker

Build the image:

```bash
docker build -t embedded-payments .
```

Run the container:

```bash
docker run --rm -p 8085:8085 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/embeddedpayments \
  -e SPRING_DATASOURCE_USERNAME=postgres \
  -e SPRING_DATASOURCE_PASSWORD=postgres \
  -e JWT_SECRET=change-this-secret-key-change-this-secret-key \
  embedded-payments
```

## Deploy en Render

El proyecto está desplegado y funcional en Render. Para redesplegarlo o crear un nuevo servicio:

- `server.port=${PORT:8085}` para respetar el puerto inyectado por la plataforma.
- `Dockerfile` multi-stage para build reproducible.
- `render.yaml` usando `env: docker`.

Variables de entorno requeridas en Render:

- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `JWT_SECRET`
- `SPRING_JPA_HIBERNATE_DDL_AUTO=none`

Notas:

- La base de datos puede ser Render Postgres o Neon.
- Antes de la demo, hay que ejecutar `db/001_create_schema.sql` en la base seleccionada.
- El endpoint de salud y Swagger pueden usarse para validar el deploy.

## Testing el flujo en nube

Con la colección de Postman (`postman/embedded-payments-sprint1.postman_collection.json`):

1. Actualiza el `baseUrl` del environment a:
   ```json
   "baseUrl": "https://embedded-payments-1.onrender.com"
   ```

2. Ejecuta el flujo completo:
   - Registro de comercio
   - Generación de token
   - Payment intent
   - Transacción
   - Refund

## Tests

```bash
./mvnw test
```

The test profile uses in-memory H2.

## API Endpoints (initial skeleton)

- `POST /api/v1/auth/token`
- `POST /api/v1/merchants`
- `POST /api/v1/payments/intents`
- `POST /api/v1/transactions`
- `POST /api/v1/refunds`
- `POST /api/v1/webhooks`

Swagger UI:
- `/swagger-ui/index.html`

## Git Workflow

Main branches:
- `main`: stable code
- `develop`: integration

Feature branches from `develop`:
- `feature/auth-module`
- `feature/merchant-module`
- `feature/payment-module`
- `feature/transaction-module`
- `feature/refund-module`

Suggested setup:

```bash
git checkout -b develop
git push -u origin develop
```

Example feature flow:

```bash
git checkout develop
git pull
git checkout -b feature/auth-module
# work and commit
git checkout develop
git merge --no-ff feature/auth-module
```

## Commit Convention

- `feat:` new feature
- `fix:` bug fix
- `refactor:` internal improvement
- `docs:` documentation changes
- `chore:` tooling or config

