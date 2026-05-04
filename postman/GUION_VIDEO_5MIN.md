# 🎬 GUION VIDEO DEMO - VERSIÓN CORTA (5 MIN)

## INTRO (10 segundos)

```
"Buenos días. Soy [Tu nombre], voy a demostrar las 5 historias de usuario 
completadas en Sprint 1 del Embedded Payments Platform.

Veremos cómo se gestiona de forma segura la información de comercios, con 
autenticación, encriptación de datos sensibles, control de acceso basado en roles, 
y auditoría completa de todas las operaciones.

Usamos Swagger UI como interfaz para demostrar todos los endpoints."
```

**Acción**: Mostrar Swagger UI en http://localhost:8085/swagger-ui.html

---

## AUTHENTICATION (1 min = 10:00 a 11:00)

### Step 1: Admin Login

```
"Primero, necesitamos obtener un JWT token de administrador. 
Buscamos el endpoint de login."
```

**Acción en Swagger**:
1. Scroll hasta encontrar "POST /api/v1/auth/login"
2. Click "Try it out"
3. Mostrar Request body:
   ```json
   {
     "email": "admin@embedded-payments.com",
     "password": "admin@123",
     "role": "ROLE_ADMIN"
   }
   ```
4. Click "Execute"
5. Capturar el token del response

**Narración mientras ejecuta**:
```
"El sistema valida las credenciales y genera un JWT token válido por 60 minutos.
Note el campo 'merchant_id' en la respuesta - lo usaremos en los siguientes requests."
```

### Step 2: Merchant Login

```
"Ahora generamos un token para el comercio. Usamos el mismo endpoint pero 
con credenciales de merchant."
```

**Acción en Swagger**:
1. Vuelve a "POST /api/v1/auth/login"
2. Limpiar Request body anterior
3. Copiar:
   ```json
   {
     "email": "merchant@example.com",
     "password": "merchant@123",
     "role": "ROLE_MERCHANT"
   }
   ```
4. Click "Execute"
5. Guardar merchant_id y token

---

## HU 1.2: ACTUALIZAR CONTACTO (45 seg = 11:00 a 11:45)

```
"HU 1.2: Actualización de información de contacto.

El comercio puede actualizar su nombre de contacto y email.
Requiere autenticación JWT."
```

**Acción en Swagger**:
1. Buscar "PUT /api/v1/merchants/{id}/contact"
2. Click "Try it out"
3. Parámetro `id`: pegar `{merchant_id}`
4. Header `Authorization`: `Bearer {MERCHANT_TOKEN}`
5. Request body:
   ```json
   {
     "contact_name": "Juan Esteban Mosquera",
     "contact_email": "juan.mosquera@embedded-payments.com"
   }
   ```
6. Click "Execute"

**Mostrar en video**:
- La respuesta 200 OK
- El campo `message: "Contact information updated successfully"`
- Punto rojo: Mostrar que usamos el token del merchant

**Narración**:
```
"Note que el endpoint requiere autenticación. Sin el JWT token, 
recibiríamos un 401 Unauthorized.

La información se actualiza y se registra en auditoría automáticamente,
incluyendo quién lo hizo, cuándo, y qué cambió."
```

---

## HU 1.3: REGISTRAR DATOS BANCARIOS (45 seg = 11:45 a 12:30)

```
"HU 1.3: Registro de información bancaria.

Este es uno de los endpoints más críticos desde el punto de vista de seguridad.
Los datos del IBAN y routing se encriptan automáticamente con AES-256-GCM."
```

**Acción en Swagger**:
1. Buscar "PUT /api/v1/merchants/{id}/bank-account"
2. Click "Try it out"
3. Parámetro `id`: `{merchant_id}`
4. Header `Authorization`: `Bearer {MERCHANT_TOKEN}`
5. Request body:
   ```json
   {
     "iban": "ES9121000418450200051332",
     "routing_number": "021000021",
     "account_holder_name": "Empresa Payments S.L."
   }
   ```
6. Click "Execute"

**Mostrar en video**:
- El IBAN con formato español válido
- El routing number con 9 dígitos exactos
- Response 200 OK

**Narración**:
```
"El IBAN se valida contra el estándar ISO 7064.
El routing number es validado con el checksum correcto.

En base de datos, estos datos nunca se guardan en texto plano.
Se cifran con AES-256-GCM y solo pueden ser descifrados 
si tienes la clave de encriptación.

El sistema registra en auditoría que los datos fueron guardados, 
pero no guarda los datos sensibles sin cifrar en los logs."
```

---

## HU 1.4: ACTIVAR COMERCIO (45 seg = 12:30 a 13:15)

```
"HU 1.4: Activación de comercio.

Aquí vemos un control de acceso importante: 
SOLO los administradores pueden cambiar el estado de un comercio."
```

**Acción en Swagger**:
1. Buscar "PATCH /api/v1/merchants/{id}/activate"
2. Click "Try it out"
3. Parámetro `id`: `{merchant_id}`
4. Header `Authorization`: `Bearer {ADMIN_TOKEN}` ⚠️ IMPORTANTE: ADMIN, no merchant
5. Request body:
   ```json
   {
     "reason": "Documentos verificados y aprobados correctamente"
   }
   ```
6. Click "Execute"

**Mostrar en video**:
- Status 200 OK
- Campo `status: "ACTIVE"`
- Que en el header usamos el token ADMIN, no merchant

**Narración**:
```
"Observe que estamos usando el token de administrador ahora, no el del merchant.

La máquina de estados es estricta: solo permite transiciones válidas.
Por ejemplo, no se puede activar un comercio que ya está activo.

Si un merchant intentara activar su propio comercio, recibiría un 403 Forbidden.

El cambio de estado queda registrado en una tabla específica de auditoría 
llamada 'merchant_status_history' con la razón del cambio."
```

---

## HU 1.5: DESACTIVAR COMERCIO (45 seg = 13:15 a 14:00)

```
"HU 1.5: Desactivación de comercio.

Este es un endpoint crítico que impide que un comercio realice nuevas transacciones.
También requiere permisos de administrador."
```

**Acción en Swagger**:
1. Buscar "PATCH /api/v1/merchants/{id}/deactivate"
2. Click "Try it out"
3. Parámetro `id`: `{merchant_id}`
4. Header `Authorization`: `Bearer {ADMIN_TOKEN}`
5. Request body:
   ```json
   {
     "reason": "Demostración del endpoint en video"
   }
   ```
6. Click "Execute"

**Mostrar en video**:
- Status 200 OK
- Campo `status: "INACTIVE"`

**Narración**:
```
"El comercio cambió de estado ACTIVE a INACTIVE.

En el sistema, cuando un comercio está inactivo:
- No puede realizar nuevas transacciones de pago
- Las transacciones en progreso pueden completarse
- Esto proporciona una forma segura de pausar operaciones sin perder trabajo en progreso

Cada desactivación genera un evento de auditoría que nunca puede ser modificado."
```

---

## HU 1.6: CONSULTAR DETALLES (60 seg = 14:00 a 15:00)

```
"HU 1.6: Consulta de detalles del comercio.

Este endpoint es interesante porque implementa ENMASCARAMIENTO DE DATOS
en cumplimiento de estándares OWASP."
```

**Acción en Swagger - PRIMERA VEZ (como Merchant)**:
1. Buscar "GET /api/v1/merchants/{id}"
2. Click "Try it out"
3. Parámetro `id`: `{merchant_id}`
4. Header `Authorization`: `Bearer {MERCHANT_TOKEN}`
5. Click "Execute"

**Mostrar en video**:
```
"Como el propietario del comercio, vemos:
- contact_email: Completo (porque soy el dueño)
- bank_account_data: '[ENCRYPTED - Access granted]' (encriptado pero accesible)
- Todos los datos porque tengo permisos"
```

**Narración**:
```
"Cuando el propietario del comercio consulta su información, 
puede ver todos sus datos completos.

Pero note que los datos bancarios muestran '[ENCRYPTED - Access granted]'.
Esto indica que los datos están encriptados en base de datos y solo 
el sistema puede desencriptarlos usando la clave maestra."
```

**Acción en Swagger - SEGUNDA VEZ (como Admin - OPCIONAL)**:
1. Mismo endpoint "GET /api/v1/merchants/{id}"
2. Click "Try it out" de nuevo
3. Parámetro `id`: `{merchant_id}`
4. Header `Authorization`: `Bearer {ADMIN_TOKEN}`
5. Click "Execute"

**Mostrar en video**:
```
"Como administrador, veo los mismos campos, 
pero con acceso total a los datos desencriptados."
```

**Narración**:
```
"El administrador tiene acceso a todos los datos sin enmascaramiento.
Pero aun así, no se guardan en texto plano: se desencriptan solo cuando es necesario.

Si un tercer merchant intentara ver estos datos, 
el sistema aplicaría enmascaramiento:
- contact_email: '***@example.com' (ocultando el nombre)
- bank_account_data: '[RESTRICTED]' (no visible)

Esto es cumplimiento OWASP de protección de datos personales."
```

---

## CIERRE (10 seg = 15:00 a 15:10)

```
"Resumen de lo demostrado:"
```

**Mostrar en pantalla o narrar**:
```
✅ 5 Historias de Usuario completadas (HU 1.2-1.6)
✅ Autenticación JWT en todos los endpoints
✅ Control de acceso basado en roles (admin solo en activate/deactivate)
✅ Encriptación AES-256-GCM de datos sensibles
✅ Enmascaramiento de datos según permisos
✅ Auditoría completa de todas las operaciones
✅ Validación de formatos (IBAN, Routing Number, Email)
```

**Cierre**:
```
"El sistema está listo para producción.
Todos los endpoints están implementados, probados, y cumplen 
los estándares de seguridad OWASP.

Gracias por ver la demostración de Sprint 1."
```

---

## ⏱️ TIMELINE

```
0:00 - 0:10  →  Intro
0:10 - 1:10  →  Authentication (Admin + Merchant login)
1:10 - 1:55  →  HU 1.2 (Actualizar contacto)
1:55 - 2:40  →  HU 1.3 (Registrar banco)
2:40 - 3:25  →  HU 1.4 (Activar comercio)
3:25 - 4:10  →  HU 1.5 (Desactivar comercio)
4:10 - 5:10  →  HU 1.6 (Consultar detalles)
5:10 - 5:20  →  Cierre / Resumen
```

**Total: 5:20 minutos**

---

## 🎥 CHECKLIST ANTES DE GRABAR

- [ ] Backend corriendo en http://localhost:8085
- [ ] Swagger UI accesible y cargando correctamente
- [ ] Dos monitores (uno para script, uno para grabar) O imprimir script
- [ ] Micrófono funcionando bien
- [ ] Pantalla en 1920x1080 o similar
- [ ] Navegador en fullscreen sin distracciones
- [ ] Cookies/caché limpias del navegador
- [ ] Tokens guardados en un notepad visible
- [ ] Scroll lento y controlado
- [ ] Pausas para que se vea el response completo
- [ ] Narración clara y sin ruidos de fondo

---

## 💾 TOKENS PARA COPIAR/PEGAR RÁPIDO

```
Admin Token:       Bearer [generar en step 1]
Merchant Token:    Bearer [generar en step 2]
Merchant ID:       [guardar de response step 2]
```

---

**Creado**: 4 de Mayo de 2026  
**Versión**: 1.0  
**Duración Final**: 5:20 minutos  
**Listo para grabar**
