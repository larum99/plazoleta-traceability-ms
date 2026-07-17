# Plazoleta - Traceability Microservice

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-6DB33F?style=flat-square&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=flat-square&logo=mongodb&logoColor=white)](https://www.mongodb.com/)
[![Gradle](https://img.shields.io/badge/Gradle-8.14-02303A?style=flat-square&logo=gradle&logoColor=white)](https://gradle.org/)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Descripcion

Microservicio de trazabilidad y metricas de eficiencia para la plataforma **Plazoleta**. Registra logs de auditoria de cada cambio de estado de los pedidos y genera metricas de rendimiento de pedidos y ranking de empleados.

**Puerto:** `8093`

## Stack Tecnologico

| Componente | Tecnologia |
|---|---|
| Lenguaje | Java 17 |
| Framework | Spring Boot 3.2.5 |
| Base de datos | MongoDB (Spring Data MongoDB) |
| Seguridad | Spring Security 6.2.4 + JWT (jjwt 0.11.5) |
| Build Tool | Gradle |
| Mapeo | MapStruct 1.6.3 |
| Documentacion API | SpringDoc OpenAPI 2.5.0 |
| Comunicacion | Spring Cloud OpenFeign |
| Testing | JUnit 5 + Mockito 5.11.0 |
| Coverage | JaCoCo 0.8.8 |

## Arquitectura

Arquitectura Hexagonal (Puertos y Adaptadores):

```
com.traceability.traceability
├── commons/configurations/     # Configuracion compartida (Swagger, Beans)
└── traceability/
    ├── domain/                 # Logica de negocio pura
    │   ├── models/             # Modelos de dominio
    │   ├── ports/in/           # Puertos entrantes
    │   ├── ports/out/          # Puertos salientes
    │   ├── usecase/            # Casos de uso
    │   ├── exceptions/         # Excepciones de dominio
    │   └── utils/              # Constantes de dominio
    ├── application/            # Capa de orquestacion
    │   ├── services/           # Servicios de aplicacion
    │   ├── dto/                # DTOs de request/response
    │   └── mappers/            # Mappers DTO <-> Modelo
    └── infrastructure/         # Adaptadores
        ├── endpoints/rest/     # Controladores REST
        ├── adapters/           # Adaptadores de persistencia, seguridad
        ├── entities/           # Entidades MongoDB
        ├── repositories/       # Repositorios Spring Data MongoDB
        ├── security/           # Filtros JWT, configuracion de seguridad
        ├── clients/feign/      # Cliente Feign para plazoleta-ms
        ├── mappers/            # Mappers Entity <-> Modelo
        └── exceptionshandlers/ # Manejador global de excepciones
```

## Endpoints

### Trazabilidad

| Metodo | Ruta | Auth | Rol | Descripcion |
|---|---|---|---|---|
| `POST` | `/api/v1/traceability/logs` | JWT | CLIENTE, EMPLEADO | Guardar log de trazabilidad |
| `GET` | `/api/v1/traceability/list-logs?orderId={id}` | JWT | CLIENTE | Obtener logs de trazabilidad por pedido |

### Metricas de Eficiencia

| Metodo | Ruta | Auth | Rol | Descripcion |
|---|---|---|---|---|
| `GET` | `/api/v1/traceability/orders?restaurantId={id}` | JWT | PROPIETARIO, ADMINISTRADOR | Eficiencia de pedidos (tiempo PENDIENTE -> ENTREGADO) |
| `GET` | `/api/v1/traceability/employees?restaurantId={id}` | JWT | PROPIETARIO, ADMINISTRADOR | Ranking de empleados por tiempo promedio de preparacion |

### Request - Save Log

```json
{
  "orderId": 1,
  "status": "PENDIENTE",
  "changedAt": "2024-01-15T10:30:00",
  "changedBy": 100,
  "clientId": 50,
  "description": "Pedido creado"
}
```

## Modelo de Datos

### Coleccion: `traceability_logs`

```json
{
  "_id": "ObjectId",
  "orderId": 1,
  "status": "PENDIENTE | EN_PREPARACION | LISTO | ENTREGADO",
  "changedAt": "2024-01-15T10:30:00",
  "changedBy": 100,
  "clientId": 50,
  "description": "Pedido creado"
}
```

### Modelos de Metricas

**OrderEfficiencyModel:**
```json
{
  "orderId": 1,
  "startTime": "2024-01-15T10:30:00",
  "endTime": "2024-01-15T11:00:00",
  "durationSeconds": 1800,
  "durationMinutes": 30.0
}
```

**EmployeeRankingModel:**
```json
{
  "employeeId": 15,
  "averageTimeSeconds": 600,
  "averageTimeMinutes": 10.0
}
```

## Variables de Entorno

| Variable | Descripcion | Ejemplo |
|---|---|---|
| `JWT_SECRET_KEY` | Clave secreta JWT (HS256) | `clave-secreta` |
| `JWT_EXPIRATION_MILLIS` | Expiracion del token en ms | `300000` |

**MongoDB** se configura directamente en `application.yml`:
```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/plazoleta_traceability
```

## Integraciones

- **plazoleta-plazoleta-ms** (puerto 8091): Verificar existencia de restaurante y obtener IDs de pedidos via Feign Client

## Metricas Disponibles

### Eficiencia de Pedidos
Calcula el tiempo transcurrido entre el estado **PENDIENTE** y **ENTREGADO** para cada pedido del restaurante.

### Ranking de Empleados
Calcula el tiempo promedio de preparacion (**EN_PREPARACION** -> **ENTREGADO**) por empleado, ordenado de mayor a menor eficiencia.

## Ejecutar el Proyecto

```bash
cd plazoleta-traceability-ms
./gradlew bootRun
```

La aplicacion estara disponible en `http://localhost:8093`

> **Requisito:** MongoDB debe estar ejecutandose en `localhost:27017`

## Documentacion API (Swagger)

```
http://localhost:8093/swagger-ui/index.html
http://localhost:8093/v3/api-docs
```

## Ejecutar Tests

```bash
./gradlew test
```

## Estructura de Seguridad

- Autenticacion JWT Stateless
- Autorizacion por rol en cada endpoint via `@PreAuthorize`
- Propagacion de token JWT en llamadas Feign a plazoleta-ms
- Validacion de roles a nivel de dominio (defensa en profundidad)
