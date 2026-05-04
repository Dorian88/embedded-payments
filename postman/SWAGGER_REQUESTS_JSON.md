# 📋 REQUESTS JSON - COPIAR Y PEGAR DIRECTAMENTE

## Instrucciones:
1. Abre Swagger UI: http://localhost:8085/swagger-ui.html
2. Para cada request, sigue el formato: Endpoint → Click "Try it out" → Copiar/pegar JSON
3. Reemplaza `{merchant_id}`, `{ADMIN_TOKEN}`, `{MERCHANT_TOKEN}` con los valores reales

---

## REQUEST 1: AUTH ADMIN LOGIN
**Endpoint**: POST /api/v1/auth/login
```json
{
  "email": "admin@embedded-payments.com",
  "password": "admin@123",
  "role": "ROLE_ADMIN"
}
```
**Guardar del Response**: `token` y `merchant_id`

---

## REQUEST 2: AUTH MERCHANT LOGIN
**Endpoint**: POST /api/v1/auth/login
```json
{
  "email": "merchant@example.com",
  "password": "merchant@123",
  "role": "ROLE_MERCHANT"
}
```
**Guardar del Response**: `token` y `merchant_id`

---

## REQUEST 3: HU 1.2 - ACTUALIZAR CONTACTO
**Endpoint**: PUT /api/v1/merchants/{merchant_id}/contact

**Header**:
```
Authorization: Bearer {MERCHANT_TOKEN}
```

**Body**:
```json
{
  "contact_name": "Juan Esteban Mosquera",
  "contact_email": "juan.mosquera@embedded-payments.com"
}
```

---

## REQUEST 4: HU 1.3 - REGISTRAR BANCO
**Endpoint**: PUT /api/v1/merchants/{merchant_id}/bank-account

**Header**:
```
Authorization: Bearer {MERCHANT_TOKEN}
```

**Body**:
```json
{
  "iban": "ES9121000418450200051332",
  "routing_number": "021000021",
  "account_holder_name": "Empresa Payments S.L."
}
```

---

## REQUEST 5: HU 1.4 - ACTIVAR COMERCIO
**Endpoint**: PATCH /api/v1/merchants/{merchant_id}/activate

**Header**:
```
Authorization: Bearer {ADMIN_TOKEN}
```

**Body**:
```json
{
  "reason": "Documentos verificados y aprobados correctamente"
}
```

---

## REQUEST 6: HU 1.5 - DESACTIVAR COMERCIO
**Endpoint**: PATCH /api/v1/merchants/{merchant_id}/deactivate

**Header**:
```
Authorization: Bearer {ADMIN_TOKEN}
```

**Body**:
```json
{
  "reason": "Demostración del endpoint en video"
}
```

---

## REQUEST 7: HU 1.6 - CONSULTAR DETALLES (como Merchant)
**Endpoint**: GET /api/v1/merchants/{merchant_id}

**Header**:
```
Authorization: Bearer {MERCHANT_TOKEN}
```

**Body**: (vacío)

---

## REQUEST 8: HU 1.6 - CONSULTAR DETALLES (como Admin - OPCIONAL)
**Endpoint**: GET /api/v1/merchants/{merchant_id}

**Header**:
```
Authorization: Bearer {ADMIN_TOKEN}
```

**Body**: (vacío)

---

## ⚠️ IMPORTANTE: REEMPLAZAR

| Variable | Donde obtenerla |
|---|---|
| `{ADMIN_TOKEN}` | Response del Request 1, field "token" |
| `{MERCHANT_TOKEN}` | Response del Request 2, field "token" |
| `{merchant_id}` | Response del Request 2, field "merchant_id" |

---

## VALORES PREDEFINIDOS (NO CAMBIAR)

```
IBAN:              ES9121000418450200051332
Routing Number:    021000021
Admin Email:       admin@embedded-payments.com
Admin Password:    admin@123
Merchant Email:    merchant@example.com
Merchant Password: merchant@123
Contact Name:      Juan Esteban Mosquera
Contact Email:     juan.mosquera@embedded-payments.com
Account Holder:    Empresa Payments S.L.
```

---

## EXPECTED RESPONSES

| Request | Status | Message |
|---|---|---|
| Request 1 (Auth Admin) | 200 | token presente |
| Request 2 (Auth Merchant) | 200 | token presente |
| Request 3 (HU 1.2) | 200 | "Contact information updated successfully" |
| Request 4 (HU 1.3) | 200 | "Bank account registered successfully" |
| Request 5 (HU 1.4) | 200 | "Merchant activated successfully" |
| Request 6 (HU 1.5) | 200 | "Merchant deactivated successfully" |
| Request 7 (HU 1.6) | 200 | Merchant details con datos visibles |
| Request 8 (HU 1.6 Admin) | 200 | Merchant details con acceso total |

---

**Versión**: 1.0  
**Listo para pegar en Swagger UI**
