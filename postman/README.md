# 📮 Colecciones Postman - Embedded Payments

## ⭐ ARCHIVOS NUEVOS - DEMO COLLECTION (Sprint 1 - HU 1.2-1.6)

### 1. **embedded-payments-demo.postman_collection.json** ⭐ RECOMENDADO PARA DEMO
Colección simplificada y optimizada para grabar video de demostración

**Contenidos**:
- ✅ 2 Setup: Auth admin + auth merchant (generan JWT tokens)
- ✅ HU 1.2: Actualizar información de contacto
- ✅ HU 1.3: Registrar datos bancarios (cifrados)
- ✅ HU 1.4: Activar comercio (Admin only)
- ✅ HU 1.5: Desactivar comercio (Admin only)
- ✅ HU 1.6: Consultar detalles con enmascaramiento
- ✅ 3 Error scenarios (validación, autenticación, autorización)
- ✅ 15 Tests con validaciones automáticas

**Optimizado para**:
- Demostración en video
- Cobertura 100% de HU 1.2-1.6
- Ejecución rápida (5-7 minutos)
- Todos los features de seguridad demostrados

### 2. **embedded-payments-demo.postman_environment.json** ⭐
Variables de entorno para ambiente **LOCAL (desarrollo)**

**Variables pre-configuradas**:
```
baseURL                    http://localhost:8085
admin_email                admin@embedded-payments.com
admin_password             admin@123
merchant_email             merchant@example.com
merchant_password          merchant@123
contact_name               Juan Esteban Mosquera
contact_email              juan.mosquera@embedded-payments.com
iban                       ES9121000418450200051332
routing_number             021000021
account_holder_name        Empresa Payments S.L.
activation_reason          Documentos verificados y aprobados...
deactivation_reason        Incumplimiento de términos de servicio
```

### 3. **embedded-payments-demo-production.postman_environment.json** ⭐
Variables de entorno para ambiente **PRODUCCIÓN (Render)**

**Diferencia principal**:
```
baseURL = https://embedded-payments-1.onrender.com
```

### 4. **DEMO_GUIDE.md** ⭐
Guía completa para grabar video de demostración

**Secciones**:
- Setup e instalación en Postman
- Flujo de demostración paso a paso (7-10 minutos)
- Explicación de cada HU y sus features de seguridad
- Tips para grabar video profesional
- Troubleshooting
- Datos de prueba y referencias

---

## 📥 Quick Start - DEMO (Nuevo)

### 1. Importar Colección
```
Postman → File → Import → embedded-payments-demo.postman_collection.json
```

### 2. Importar Environment (Local)
```
Postman → Environments → Import → embedded-payments-demo.postman_environment.json
```

### 3. Seleccionar Environment
```
Dropdown derecho (Environment selector) → Embedded Payments Demo Environment
```

### 4. Ejecutar Demo en Orden
```
1. Setup & Authentication → Generate Admin JWT Token
2. Setup & Authentication → Generate Merchant JWT Token
3. HU 1.2 - Update Merchant Contact
4. HU 1.3 - Register Bank Account
5. HU 1.4 - Activate Merchant
6. HU 1.5 - Deactivate Merchant
7. HU 1.6 - Get Merchant Details
```

Ver **DEMO_GUIDE.md** para detalles completos incluyendo video recording tips.

---

## 🧪 Tests Incluidos

### HU 1.2: Actualizar Contacto
- ✅ 1.2.1: Actualizar exitoso (200)
- ✅ 1.2.2: Email inválido (400)
- ✅ 1.2.3: Sin autenticación (403)

### HU 1.3: Registrar Datos Bancarios
- ✅ 1.3.1: Registrar exitoso (200)
- ✅ 1.3.2: IBAN inválido (400)
- ✅ 1.3.3: Routing inválido (400)
- ✅ 1.3.4: Comercio inactivo (403)

### HU 1.4: Activar Comercio
- ✅ 1.4: Activar exitoso (200) - ADMIN
- ✅ 1.4.1: Sin permisos (403) - No admin

### HU 1.5: Desactivar Comercio
- ✅ 1.5: Desactivar exitoso (200) - ADMIN
- ✅ 1.5.1: Sin permisos (403) - No admin

### HU 1.6: Consultar Detalles
- ✅ 1.6.1: Como ADMIN (ve todo)
- ✅ 1.6.2: Como PROPIETARIO (ve todo)
- ✅ 1.6.3: Como OTRO (enmascarado)
- ✅ 1.6.4: No encontrado (404)
- ✅ 1.6.5: Sin autenticación (401)

---

## 🔐 Casos de Seguridad Validados

### Autenticación
- ✅ JWT requerido en endpoints protegidos
- ✅ Sin token → 401 Unauthorized

### Autorización
- ✅ ROLE_ADMIN requerido para cambiar estado
- ✅ Merchant normal → 403 Forbidden

### Encriptación
- ✅ Datos bancarios cifrados (AES-256-GCM)
- ✅ Hash SHA-256 para búsquedas
- ✅ No visible en responses normales

### Enmascaramiento (OWASP)
- ✅ Email: `***@domain.com` (otros)
- ✅ IBAN: `****...1234` (otros)
- ✅ Datos bancarios: `[RESTRICTED]` (otros)
- ✅ Admin: Acceso total

### Validación
- ✅ Email RFC 5322
- ✅ IBAN ISO 7064 checksum
- ✅ Routing Number 9 dígitos
- ✅ Estados válidos (máquina)

---

## 📊 Verificar Auditoría

Después de ejecutar tests, validar en base de datos:

### Cambios de Contacto
```sql
SELECT * FROM merchant_audit_detail 
WHERE event_type = 'MERCHANT_CONTACT_UPDATED'
ORDER BY created_at DESC LIMIT 5;
```

### Historial de Estados
```sql
SELECT * FROM merchant_status_history 
WHERE merchant_id = 'TU-MERCHANT-ID'
ORDER BY created_at DESC;
```

### Todos los Eventos
```sql
SELECT * FROM audit_event 
WHERE entity_id = 'TU-MERCHANT-ID'
ORDER BY happened_at DESC;
```

### Datos Bancarios Cifrados
```sql
SELECT id, bank_account_hash, 
       LENGTH(bank_account_encrypted) as encrypted_length
FROM merchants 
WHERE id = 'TU-MERCHANT-ID';
```

---

## 🔧 Configuración Avanzada

### Usar en Diferentes Ambientes
```
Crear nuevo Environment para cada:
- localhost (development)
- staging.example.com
- api.example.com (producción)
```

### Generar Reportes
```bash
newman run embedded-payments-hu1.2-1.6.postman_collection.json \
  -e embedded-payments-hu1.2-1.6-environment.json \
  -r cli,html --reporter-html-export report.html
```

### CI/CD Integration
```bash
# En pipeline (GitHub Actions, GitLab CI, etc.)
newman run embedded-payments-hu1.2-1.6.postman_collection.json \
  -e embedded-payments-hu1.2-1.6-environment.json \
  --bail
```

### Postman Monitors (Cloud)
```
Postman Cloud → Create Monitor
- Ejecutar colección cada X minutos
- Recibir alertas por fallos
- Dashboard con historico
```

---

## 📝 Variables de Entorno

### Obligatorias
```
baseUrl        = URL del servidor API
adminToken     = JWT válido de admin
merchantToken  = JWT válido de merchant
```

### Opcionales (tienen defaults)
```
merchantEmail  = Email único para cada test
merchantName   = Nombre del comercio
contactName    = Nombre del contacto
contactEmail   = Email de contacto
iban           = IBAN válido (predeterminado)
routingNumber  = Routing válido (predeterminado)
accountHolder  = Titular de cuenta
```

---

## 🚀 Flujo Recomendado

```
1. Importar colección
2. Importar environment
3. Obtener tokens JWT
4. Ejecutar "0. SETUP" → Registrar comercio
5. Ejecutar HU 1.2 → HU 1.3 → HU 1.4 → HU 1.5 → HU 1.6
6. Validar auditoría con queries SQL
7. Revisar logs en servidor
```

---

## 📚 Documentación Relacionada

- `GUIA_POSTMAN_HU1.2-1.6.md`: Guía detallada
- `/docs/sprint-1/hu-1.2-a-1.6/ESPECIFICACION_ENDPOINTS.md`: API spec
- `/docs/sprint-1/hu-1.2-a-1.6/GUIA_EJECUCION_TESTING.md`: Testing manual
- `/docs/sprint-1/hu-1.2-a-1.6/REFERENCIA_RAPIDA.md`: Quick ref

---

## ❓ Preguntas Frecuentes

### ¿Cómo obtengo tokens JWT?
Ver **GUIA_POSTMAN_HU1.2-1.6.md** → "Configuración Inicial"

### ¿Qué hacer si un test falla?
Ver **GUIA_POSTMAN_HU1.2-1.6.md** → "Troubleshooting"

### ¿Cómo valido auditoría?
Ver **GUIA_POSTMAN_HU1.2-1.6.md** → "Verificar Auditoría en BD"

### ¿Puedo usar en producción?
⚠️ **NO**: Crear environment separado para prod con datos reales

### ¿Cómo exporto resultados?
```
Postman → File → Export → Seleccionar formato
```

---

## 📋 Checklist antes de Testing

- [ ] Servidor API corriendo en http://localhost:8080
- [ ] Base de datos accesible
- [ ] Colección importada
- [ ] Environment importado
- [ ] Tokens JWT obtenidos y configurados
- [ ] Variable baseUrl correcta
- [ ] Migrations ejecutadas

---

## 🎯 Cobertura de Testing

Esta colección cubre:
- ✅ 5 Historias de Usuario (HU 1.2-1.6)
- ✅ 15+ Casos de test
- ✅ Happy path y error cases
- ✅ Validaciones de entrada
- ✅ Autorización y autenticación
- ✅ Encriptación y enmascaramiento
- ✅ Auditoría

---

**Versión**: 1.0  
**Última actualización**: 30 de Abril de 2026  
**Estado**: ✅ Lista para usar


