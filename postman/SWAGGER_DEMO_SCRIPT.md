# SWAGGER DEMO - Requests Listos para Pegar
## Embedded Payments Platform - Sprint 1 Demo Video (5 min)

---

## 📋 INSTRUCCIONES PREVIAS

### Antes de empezar:
1. **Iniciar backend** (si no está corriendo):
   ```bash
   cd /Users/juanestebanmosquera/embedded-payments
   ./mvnw spring-boot:run
   ```

2. **Abrir Swagger UI** en navegador:
   ```
   http://localhost:8085/swagger-ui.html
   ```

3. **Preparar datos** (guardar en notas):
   - Admin JWT Token: (se genera en primer request)
   - Merchant ID: (se genera en segundo request)
   - Merchant JWT Token: (se genera en segundo request)

---

## 🔐 STEP 1: GENERAR JWT TOKEN ADMIN (30 segundos)

### Endpoint: POST /api/v1/auth/login

**Request Body** (copiar exacto):
```json
{
  "email": "admin@embedded-payments.com",
  "password": "admin@123",
  "role": "ROLE_ADMIN"
}
```

**Instrucciones Swagger**:
1. Buscar: "POST /api/v1/auth/login"
2. Click "Try it out"
3. Copiar y pegar el JSON arriba en el Request body
4. Click "Execute"

**Esperado**:
- ✅ Status: 200 OK
- ✅ Response contiene: `"token": "eyJhbGciOi..."`
- ✅ Response contiene: `"merchant_id": "uuid"`

**Para video**: Capturar el token completo y guardarlo.

---

## 👤 STEP 2: GENERAR JWT TOKEN MERCHANT (30 segundos)

### Endpoint: POST /api/v1/auth/login

**Request Body** (copiar exacto):
```json
{
  "email": "merchant@example.com",
  "password": "merchant@123",
  "role": "ROLE_MERCHANT"
}
```

**Instrucciones Swagger**:
1. Buscar nuevamente: "POST /api/v1/auth/login"
2. Click "Try it out"
3. Copiar y pegar el JSON arriba
4. Click "Execute"

**Esperado**:
- ✅ Status: 200 OK
- ✅ Response contiene JWT token
- ✅ Response contiene merchant_id

**Para video**: Capturar este token también (será diferente del admin).

---

## 📝 HU 1.2: ACTUALIZAR INFORMACIÓN DE CONTACTO (45 segundos)

### Endpoint: PUT /api/v1/merchants/{id}/contact

**URL Parameters**:
- `{id}` = merchant_id del step 2

**Headers** (Swagger añade automáticamente):
```
Authorization: Bearer {MERCHANT_JWT_TOKEN}
Content-Type: application/json
```

**Request Body** (copiar exacto):
```json
{
  "contact_name": "Juan Esteban Mosquera",
  "contact_email": "juan.mosquera@embedded-payments.com"
}
```

**Instrucciones Swagger**:
1. Buscar: "PUT /api/v1/merchants/{id}/contact"
2. En parámetro `id`, pegar: `{merchant_id}`
3. En header `Authorization`, pegar: `Bearer {MERCHANT_JWT_TOKEN}`
4. Copiar JSON del Request body
5. Click "Execute"

**Esperado**:
- ✅ Status: 200 OK
- ✅ Response contiene: `"contact_name": "Juan Esteban Mosquera"`
- ✅ Response contiene: `"message": "Contact information updated successfully"`

**Para video**: 
- Mostrar request con parámetros y header
- Mostrar response confirmando actualización
- Narración: "HU 1.2 completada: Actualizar información de contacto del comercio"

---

## 🏦 HU 1.3: REGISTRAR DATOS BANCARIOS (45 segundos)

### Endpoint: PUT /api/v1/merchants/{id}/bank-account

**URL Parameters**:
- `{id}` = merchant_id del step 2

**Headers**:
```
Authorization: Bearer {MERCHANT_JWT_TOKEN}
Content-Type: application/json
```

**Request Body** (copiar exacto):
```json
{
  "iban": "ES9121000418450200051332",
  "routing_number": "021000021",
  "account_holder_name": "Empresa Payments S.L."
}
```

**Instrucciones Swagger**:
1. Buscar: "PUT /api/v1/merchants/{id}/bank-account"
2. En parámetro `id`, pegar: `{merchant_id}`
3. En header `Authorization`, pegar: `Bearer {MERCHANT_JWT_TOKEN}`
4. Copiar JSON del Request body
5. Click "Execute"

**Esperado**:
- ✅ Status: 200 OK
- ✅ Response contiene: `"message": "Bank account registered successfully"`
- ✅ Response contiene: `"status": "ACTIVE"`

**Para video**:
- Mostrar IBAN válido con checksum ISO 7064
- Mostrar Routing Number válido (9 dígitos)
- Narración: "HU 1.3: Registrar datos bancarios. Datos encriptados con AES-256-GCM"

---

## ✅ HU 1.4: ACTIVAR COMERCIO (45 segundos)

### Endpoint: PATCH /api/v1/merchants/{id}/activate

**URL Parameters**:
- `{id}` = merchant_id del step 2

**Headers**:
```
Authorization: Bearer {ADMIN_JWT_TOKEN}
Content-Type: application/json
```

**Request Body** (copiar exacto):
```json
{
  "reason": "Documentos verificados y aprobados correctamente"
}
```

**Instrucciones Swagger**:
1. Buscar: "PATCH /api/v1/merchants/{id}/activate"
2. En parámetro `id`, pegar: `{merchant_id}`
3. En header `Authorization`, pegar: `Bearer {ADMIN_JWT_TOKEN}` (del step 1)
4. Copiar JSON del Request body
5. Click "Execute"

**Esperado**:
- ✅ Status: 200 OK
- ✅ Response contiene: `"status": "ACTIVE"`
- ✅ Response contiene: `"message": "Merchant activated successfully"`

**Para video**:
- IMPORTANTE: Mostrar que usamos ADMIN token (no merchant)
- Mostrar transición de estado: INACTIVE → ACTIVE
- Narración: "HU 1.4: Solo administrador puede activar comercios. Control de acceso mediante ROLE_ADMIN"

---

## ❌ HU 1.5: DESACTIVAR COMERCIO (45 segundos)

### Endpoint: PATCH /api/v1/merchants/{id}/deactivate

**URL Parameters**:
- `{id}` = merchant_id del step 2

**Headers**:
```
Authorization: Bearer {ADMIN_JWT_TOKEN}
Content-Type: application/json
```

**Request Body** (copiar exacto):
```json
{
  "reason": "Demostración de desactivación en video"
}
```

**Instrucciones Swagger**:
1. Buscar: "PATCH /api/v1/merchants/{id}/deactivate"
2. En parámetro `id`, pegar: `{merchant_id}`
3. En header `Authorization`, pegar: `Bearer {ADMIN_JWT_TOKEN}`
4. Copiar JSON del Request body
5. Click "Execute"

**Esperado**:
- ✅ Status: 200 OK
- ✅ Response contiene: `"status": "INACTIVE"`
- ✅ Response contiene: `"message": "Merchant deactivated successfully"`

**Para video**:
- Mostrar cambio de estado: ACTIVE → INACTIVE
- Narración: "HU 1.5: Desactivación de comercio. Bloquea nuevas transacciones. Auditoría registra razón y timestamp"

---

## 🔍 HU 1.6: CONSULTAR DETALLES CON ENMASCARAMIENTO (60 segundos)

### Endpoint: GET /api/v1/merchants/{id}

**URL Parameters**:
- `{id}` = merchant_id del step 2

**Headers**:
```
Authorization: Bearer {MERCHANT_JWT_TOKEN}
Content-Type: application/json
```

**Instrucciones Swagger**:
1. Buscar: "GET /api/v1/merchants/{id}"
2. En parámetro `id`, pegar: `{merchant_id}`
3. En header `Authorization`, pegar: `Bearer {MERCHANT_JWT_TOKEN}`
4. NO necesita Request Body
5. Click "Execute"

**Esperado**:
- ✅ Status: 200 OK
- ✅ Response contiene: todos los campos (merchant como propietario ve todo)
- ✅ Notar: `contact_email`: completo (porque es propietario)
- ✅ Notar: `bank_account_data`: "ENCRYPTED" (datos cifrados con AES-256-GCM)

**Para video - DOS VECES**:

**Primera ejecución (como Merchant - propietario)**:
- Narración: "Consulta como propietario del comercio - ve datos completos"
- Mostrar que contact_email está visible
- Mostrar que bank_account_data muestra "[ENCRYPTED - Access granted]"

**Segunda ejecución (opcional - como Admin)**:
- Cambiar header a: `Bearer {ADMIN_JWT_TOKEN}`
- Narración: "Consulta como administrador - acceso total a datos desencriptados"
- Mostrar que admin ve todo incluyendo datos bancarios

---

## ✨ RESUMEN PARA EL VIDEO (Últimos 10 segundos)

**Mostrar en pantalla** (o narrar):
```
✅ HU 1.2: Actualización de contacto
✅ HU 1.3: Registro de datos bancarios encriptados
✅ HU 1.4: Activación de comercios (admin only)
✅ HU 1.5: Desactivación de comercios (admin only)
✅ HU 1.6: Consulta con enmascaramiento de datos

🔐 Seguridad:
   - JWT Authentication en todos los endpoints
   - Role-Based Access Control (RBAC)
   - AES-256-GCM encryption para datos sensibles
   - OWASP data masking (***@domain.com)
   - Auditoría completa de todas las operaciones
```

---

## 🎬 DATOS PARA COPIAR/PEGAR RÁPIDO

### Token Admin (del step 1):
```
Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9... (guardar después de step 1)
```

### Token Merchant (del step 2):
```
Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9... (guardar después de step 2)
```

### Merchant ID (del step 2):
```
{uuid-generado} (copiar del response del step 2)
```

### IBAN Válido:
```
ES9121000418450200051332
```

### Routing Válido:
```
021000021
```

---

## ⏱️ TIMING TOTAL: 5 MINUTOS

| Step | Contenido | Tiempo |
|---|---|---|
| Intro | Presentación | 10 seg |
| 1 | Auth Admin | 30 seg |
| 2 | Auth Merchant | 30 seg |
| HU 1.2 | Actualizar contacto | 45 seg |
| HU 1.3 | Registrar banco | 45 seg |
| HU 1.4 | Activar comercio | 45 seg |
| HU 1.5 | Desactivar comercio | 45 seg |
| HU 1.6 | Consultar detalles | 60 seg |
| Cierre | Resumen seguridad | 10 seg |
| **TOTAL** | | **5:20 min** |

---

## 🎥 RECOMENDACIONES PARA GRABAR

1. **Resolución**: 1920x1080 mínimo
2. **Audio**: Narración clara sin ruido de fondo
3. **Scroll**: Lento y controlado en Swagger
4. **Highlights**:
   - Capturar tokens completos
   - Mostrar status codes (200, 403, 401)
   - Resaltar JSON responses
   - Mostrar tiempos de respuesta

5. **Narración** (ejemplo):
   ```
   "Demostración de Sprint 1 - Historias de Usuario 1.2 a 1.6.
   
   Comenzamos autenticando al administrador y al comercio para obtener tokens JWT.
   
   HU 1.2: Actualizamos la información de contacto del comercio.
   
   HU 1.3: Registramos los datos bancarios de forma segura. Estos datos se 
   encriptan automáticamente con AES-256-GCM según estándares de seguridad.
   
   HU 1.4: Activamos el comercio. Solo los administradores tienen permiso para 
   cambiar el estado de un comercio.
   
   HU 1.5: Desactivamos el comercio. Esto bloquea automáticamente nuevas 
   transacciones de pago.
   
   HU 1.6: Consultamos los detalles del comercio. Note que como propietario 
   vemos todos los datos, pero en el sistema hay enmascaramiento de datos 
   sensibles para otros usuarios por cumplimiento OWASP.
   
   Todas las operaciones quedan registradas en auditoría con timestamp y usuario."
   ```

---

## ❓ TROUBLESHOOTING

**Si Swagger no carga**:
```bash
Asegurar que backend está corriendo en puerto 8085
curl http://localhost:8085/swagger-ui.html
```

**Si token expira**:
- Regenerar con step 1 o 2
- Los tokens son válidos por 60 minutos

**Si 403 Forbidden**:
- Usar ADMIN token para activate/deactivate
- Usar MERCHANT token para contact/bank-account

**Si 404 Not Found**:
- Verificar que merchant_id esté correcto
- Debe coincidir exactamente con UUID generado

---

**Creado**: 4 de Mayo de 2026  
**Versión**: 1.0  
**Duración**: 5 minutos  
**Formato**: Swagger UI en navegador  
