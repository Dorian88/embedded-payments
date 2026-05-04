# 🎬 DEMO VIDEO - ARCHIVOS PARA GRABAR EN SWAGGER UI

## 📂 Estructura de Archivos para Demo Video

Este directorio contiene todo lo necesario para grabar un video de demostración de 5 minutos usando Swagger UI directamente en el navegador.

---

## 📋 ARCHIVOS DE DEMO (NUEVOS)

### 1. **GUION_VIDEO_5MIN.md** ⭐ LEER PRIMERO
**Descripción**: Guion completo en español para narración del video
- Intro + 5 HUs + Cierre
- Narración lista para leer
- Timeline detallado (0:00 - 5:20)
- Checklist pre-grabación
- Instrucciones exactas de qué mostrar en cada paso

**Uso**: Leer mientras grabas o memorizar antes

---

### 2. **SWAGGER_DEMO_SCRIPT.md** ⭐ GUÍA TÉCNICA
**Descripción**: Instrucciones paso a paso técnicas
- Cada endpoint con detalles
- URL parameters explicados
- Headers requeridos
- JSON request bodies listos para copiar
- Expected responses para validar ejecución

**Uso**: Referencia técnica durante la grabación

---

### 3. **SWAGGER_REQUESTS_JSON.md** ⭐ COPY-PASTE RÁPIDO
**Descripción**: JSONs limpios sin explicaciones
- 8 requests numerados
- Solo JSON bodies
- Tabla de reemplazo de variables
- Tabla de respuestas esperadas

**Uso**: Copiar/pegar rápidamente en Swagger UI

---

## 🚀 CÓMO GRABAR EL VIDEO

### PASO 0: Preparación (5 minutos)

1. **Iniciar backend**:
   ```bash
   cd /Users/juanestebanmosquera/embedded-payments
   ./mvnw spring-boot:run
   ```
   Esperar hasta ver:
   ```
   Started EmbeddedPaymentsApplication in X seconds
   ```

2. **Abrir Swagger UI** en navegador:
   ```
   http://localhost:8085/swagger-ui.html
   ```

3. **Preparar entorno de grabación**:
   - Abre **GUION_VIDEO_5MIN.md** en pantalla 2 o impreso
   - Abre **SWAGGER_REQUESTS_JSON.md** en otra pestaña
   - Prepara 2 notepad/texto:
     - Uno para guardar Admin Token
     - Uno para guardar Merchant Token y Merchant ID

4. **Configurar software de grabación** (OBS, ScreenFlow, etc.):
   - Resolución: 1920x1080
   - Audio: Micrófono activado
   - Frame rate: 30 fps mínimo

---

### PASO 1: Grabación (5 minutos 20 segundos)

**Sigue el orden exacto de GUION_VIDEO_5MIN.md**:

1. **0:00-0:10**: Lee Intro mientras muestras Swagger UI
2. **0:10-1:10**: Execute Request 1 y 2 (auth)
3. **1:10-1:55**: Execute Request 3 (HU 1.2)
4. **1:55-2:40**: Execute Request 4 (HU 1.3)
5. **2:40-3:25**: Execute Request 5 (HU 1.4)
6. **3:25-4:10**: Execute Request 6 (HU 1.5)
7. **4:10-5:10**: Execute Request 7-8 (HU 1.6)
8. **5:10-5:20**: Lee Cierre/Resumen

---

### PASO 2: Edición (Opcional)

Si grabaste varias tomas, edita para:
- Eliminar silencios
- Ajustar volumen de narración
- Agregar subtítulos con HU numbers
- Exportar en HD

---

## 📊 ROADMAP RÁPIDO

```
⏱️  0:00 - INTRO
     │
     └──→ Presentación Sprint 1 + contexto
     
⏱️  0:10 - AUTH
     │
     ├──→ 0:10-0:40: Admin Login (POST /auth/login)
     └──→ 0:40-1:10: Merchant Login (POST /auth/login)
     
⏱️  1:10 - HU 1.2
     │
     └──→ 1:10-1:55: Update Contact (PUT /merchants/{id}/contact)
     
⏱️  1:55 - HU 1.3
     │
     └──→ 1:55-2:40: Register Bank Account (PUT /merchants/{id}/bank-account)
     
⏱️  2:40 - HU 1.4
     │
     └──→ 2:40-3:25: Activate Merchant (PATCH /merchants/{id}/activate)
     
⏱️  3:25 - HU 1.5
     │
     └──→ 3:25-4:10: Deactivate Merchant (PATCH /merchants/{id}/deactivate)
     
⏱️  4:10 - HU 1.6
     │
     ├──→ 4:10-4:40: Query as Merchant (GET /merchants/{id})
     └──→ 4:40-5:10: Query as Admin (GET /merchants/{id})
     
⏱️  5:10 - CIERRE
     │
     └──→ 5:10-5:20: Resumen seguridad + agradecimiento
```

---

## 🔒 SECURITY FEATURES A DESTACAR EN VIDEO

### HU 1.2: Autenticación
```
"Todos los endpoints requieren JWT token en header Authorization"
→ Mostrar header en Swagger
```

### HU 1.3: Encriptación
```
"Datos bancarios cifrados AES-256-GCM, nunca en texto plano"
→ Mostrar IBAN con checksum válido
```

### HU 1.4 & 1.5: Control de Acceso
```
"Solo ROLE_ADMIN puede cambiar estado - si merchant intenta, obtiene 403 Forbidden"
→ Mostrar que usamos ADMIN token, no merchant
```

### HU 1.6: Data Masking
```
"Como propietario ves todo, otro merchant vería datos enmascarados"
→ Mostrar response con contact_email visible vs [ENCRYPTED]
```

---

## ✅ CHECKLIST ANTES DE GRABAR

- [ ] Backend corriendo (`./mvnw spring-boot:run`)
- [ ] Swagger UI cargando en http://localhost:8085/swagger-ui.html
- [ ] GUION_VIDEO_5MIN.md abierto (pantalla 2 o impreso)
- [ ] SWAGGER_REQUESTS_JSON.md abierto para copiar
- [ ] 2 notepad abiertos (para tokens)
- [ ] Micrófono funcionando y sin ruido de fondo
- [ ] Resolución 1920x1080 o similar
- [ ] Software de grabación configurado (OBS, ScreenFlow, etc.)
- [ ] Navegador sin tabs distractores
- [ ] Zoom de navegador en 100% (Ctrl+0)

---

## ⚠️ ERRORES COMUNES

| Error | Solución |
|---|---|
| 404 en Swagger | Backend no corre en puerto 8085, verificar `./mvnw spring-boot:run` |
| 401 Unauthorized | Token no copiado correctamente, asegurar `Bearer {token}` |
| 403 Forbidden | Usando merchant token en admin-only endpoint, cambiar a ADMIN token |
| Micrófono muy bajo | Subir volumen en sistema + en software grabación |
| Video cortado | Extender tiempo de grabación en settings, objetivo es 5:20 mínimo |

---

## 📝 ESTRUCTURA ESPERADA DEL VIDEO

```
INTRO (narración)              →  0:00 - 0:10  (10 seg)
AUTH Admin (ejecución)         →  0:10 - 0:40  (30 seg)
AUTH Merchant (ejecución)      →  0:40 - 1:10  (30 seg)
HU 1.2 (narración + ejecución) →  1:10 - 1:55  (45 seg)
HU 1.3 (narración + ejecución) →  1:55 - 2:40  (45 seg)
HU 1.4 (narración + ejecución) →  2:40 - 3:25  (45 seg)
HU 1.5 (narración + ejecución) →  3:25 - 4:10  (45 seg)
HU 1.6 (narración + ejecución) →  4:10 - 5:10  (60 seg)
CIERRE (resumen)               →  5:10 - 5:20  (10 seg)
─────────────────────────────────────────────
TOTAL                          →             (5:20)
```

---

## 🎯 KEY METRICS PARA MOSTRAR EN VIDEO

✅ **5 HUs completadas** (1.2-1.6)
✅ **5 Endpoints implementados** con especificación técnica
✅ **JWT Authentication** en todos
✅ **AES-256-GCM Encryption** para datos sensibles
✅ **OWASP Data Masking** según permisos
✅ **Auditoría completa** de operaciones
✅ **Role-Based Access Control** (admin vs merchant)
✅ **Validación de entrada** (IBAN, Routing, Email)
✅ **State machine** (transiciones válidas)
✅ **Recuperación de errores** (403, 401, 400, 404)

---

## 📂 OTROS ARCHIVOS EN DIRECTORIO (Referencia)

- **embedded-payments-demo.postman_collection.json**: Colección Postman (si prefieres Postman en lugar de Swagger)
- **embedded-payments-demo.postman_environment.json**: Environment local Postman
- **embedded-payments-demo-production.postman_environment.json**: Environment producción Postman
- **DEMO_GUIDE.md**: Guía Postman (si cambias de idea)
- **README.md**: Índice general Postman

---

## 🎬 DESPUÉS DE GRABAR

1. **Exportar video**: MP4, 1080p, 30fps mínimo
2. **Verificar audio**: Narración clara sin ruidos
3. **Verificar contenido**:
   - [ ] Todos 5 endpoints visible
   - [ ] Todos los status 200 OK
   - [ ] Tokens copiados correctamente
   - [ ] Respuestas JSON legibles
4. **Subir a repositorio** o compartir enlace
5. **Agregar a sprint review** como deliverable

---

## 📚 DOCUMENTACIÓN RELACIONADA

- **ESPECIFICACION_ENDPOINTS.md**: Spec técnica de endpoints (en docs/sprint-1/hu-1.2-a-1.6/)
- **SPRINT_SUMMARY.md**: Resumen Sprint 1 completo
- **REVIEW_CHECKLIST.md**: Checklist para review de sprint

---

## 🎓 TIPS PROFESIONALES

1. **Audio**:
   - Habla claro y pausado (no rápido)
   - Pausa 2 segundos entre frases
   - Evita "umm", "uh", "bueno"

2. **Visual**:
   - Scroll lento en Swagger
   - Dejar respuesta JSON visible 3+ segundos
   - Zoom en campos importantes

3. **Narración**:
   - Explica QUÉ ves (respuesta)
   - Explica POR QUÉ es importante (seguridad)
   - Evita tecnicismos sin explicación

4. **Pacing**:
   - No apures, 5:20 es límite pero no requiere ir muy rápido
   - Cada HU merece 45-60 segundos de airtime
   - Pausa antes de cambiar de endpoint

---

**Versión**: 1.0  
**Creado**: 4 de Mayo de 2026  
**Listo para grabar**: ✅  
**Duracion meta**: 5:20 minutos máximo  

¡A grabar! 🎥
