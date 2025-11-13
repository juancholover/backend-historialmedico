
# API REST - Backend de Historias Clínicas

Backend RESTful para gestión de pacientes, médicos y sus historias clínicas, desarrollado con Spring Boot y PostgreSQL.

## 🚀 Características

- **CRUD completo** de Pacientes, Médicos y Historiales Clínicos
- **Validación de datos** con Bean Validation
- **Relaciones entre entidades** (Paciente, Medico, HistorialClinica)
- **PostgreSQL** como base de datos
- **CORS configurado** para integración con Flutter
- **Manejo global de excepciones**
- **Documentación interactiva con Swagger/OpenAPI**

## 📋 Requisitos Previos

- Java 17 o superior
- Maven 3.6+
- PostgreSQL 18 o superior

## 🗄️ Configuración de la Base de Datos

1. Instala PostgreSQL
2. Crea la base de datos:

```sql
CREATE DATABASE hclinico_db;
```

3. Configura las credenciales en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/hclinico_db
spring.datasource.username=postgres
spring.datasource.password=tu_contraseña
```

## 🔧 Instalación y Ejecución

### Usando Maven:

```bash
# Compilar el proyecto
mvnw clean install

# Ejecutar la aplicación
mvnw spring-boot:run
```

El servidor estará disponible en: `http://localhost:8080`

## 📚 Documentación de la API (Swagger)

Una vez que la aplicación esté ejecutándose, accede a la documentación interactiva:

- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **OpenAPI JSON**: [http://localhost:8080/api-docs](http://localhost:8080/api-docs)

Swagger UI permite:
- ✅ Ver todos los endpoints disponibles
- ✅ Probar las APIs directamente desde el navegador
- ✅ Ver modelos de datos y validaciones
- ✅ Descargar la especificación OpenAPI

## 📡 Endpoints de la API

### Pacientes
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET    | `/api/pacientes` | Obtener todos los pacientes |
| GET    | `/api/pacientes/{dni}` | Obtener paciente por DNI |
| POST   | `/api/pacientes` | Crear nuevo paciente |
| PUT    | `/api/pacientes/{dni}` | Actualizar paciente |
| DELETE | `/api/pacientes/{dni}` | Eliminar paciente |

### Médicos
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET    | `/api/medicos` | Obtener todos los médicos |
| GET    | `/api/medicos/{cmp}` | Obtener médico por CMP |
| POST   | `/api/medicos` | Crear nuevo médico |
| PUT    | `/api/medicos/{cmp}` | Actualizar médico |
| DELETE | `/api/medicos/{cmp}` | Eliminar médico |

### Historiales Clínicos
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET    | `/api/historiales` | Obtener todos los historiales |
| GET    | `/api/historiales/{id}` | Obtener historial por ID |
| POST   | `/api/historiales` | Crear nuevo historial |
| PUT    | `/api/historiales/{id}` | Actualizar historial |
| DELETE | `/api/historiales/{id}` | Eliminar historial |
| GET    | `/api/historiales/paciente/{dni}` | Buscar historiales por paciente |
| GET    | `/api/historiales/medico/{cmp}` | Buscar historiales por médico |

## 📝 Modelo de Datos

### Paciente
```json
{
  "dni": "12345678",
  "nombre": "Juan Carlos",
  "apellidoPaterno": "García",
  "apellidoMaterno": "López",
  "direccion": "Av. Los Pinos 123, Lima",
  "telefono": "987654321"
}
```

### Médico
```json
{
  "cmp": "CMP-12345",
  "nombre": "Roberto",
  "apellidos": "Hernández Salazar",
  "especialidad": "Cardiología"
}
```

### Historial Clínico
```json
{
  "id": 1,
  "paciente": { ... },
  "medico": { ... },
  "fechaAtencion": "2024-01-15",
  "diagnostico": "Hipertensión arterial",
  "analisis": "Paciente presenta presión arterial elevada",
  "tratamiento": "Enalapril 10mg cada 12 horas"
}
```

## 📦 Ejemplos de Uso

### Crear un paciente (POST):
```bash
curl -X POST http://localhost:8080/api/pacientes \
  -H "Content-Type: application/json" \
  -d '{
    "dni": "12345678",
    "nombre": "Juan Carlos",
    "apellidoPaterno": "García",
    "apellidoMaterno": "López",
    "direccion": "Av. Los Pinos 123, Lima",
    "telefono": "987654321"
  }'
```

### Crear un historial clínico (POST):
```bash
curl -X POST http://localhost:8080/api/historiales \
  -H "Content-Type: application/json" \
  -d '{
    "pacienteDni": "12345678",
    "medicoCmp": "CMP-12345",
    "fechaAtencion": "2024-01-15",
    "diagnostico": "Hipertensión arterial",
    "analisis": "Paciente presenta presión arterial elevada",
    "tratamiento": "Enalapril 10mg cada 12 horas"
  }'
```

### Obtener todos los historiales (GET):
```bash
curl http://localhost:8080/api/historiales
```

## 🔐 Validaciones

Los siguientes campos son obligatorios:
- **dni**: Paciente debe tener 8 dígitos
- **cmp**: Médico debe ser único y no vacío
- **fechaAtencion**: No puede ser nula
- **diagnostico**: No puede estar vacío

## 🌐 Integración con Flutter

El backend está configurado con CORS para aceptar peticiones desde cualquier origen. Para conectar desde Flutter:

```dart
final response = await http.get(
  Uri.parse('http://localhost:8080/api/historiales'),
);
```

**Nota:** En un dispositivo Android, usa `http://10.0.2.2:8080` en lugar de `localhost`.

## 📁 Estructura del Proyecto

```
src/main/java/upeu/edu/pe/Producto/
├── config/
│   ├── CorsConfig.java
│   └── SwaggerConfig.java
├── controller/
│   ├── PacienteController.java
│   ├── MedicoController.java
│   └── HistorialClinicaController.java
├── entity/
│   ├── Paciente.java
│   ├── Medico.java
│   └── HistorialClinica.java
├── exception/
│   └── GlobalExceptionHandler.java
├── repository/
│   ├── PacienteRepository.java
│   ├── MedicoRepository.java
│   └── HistorialClinicaRepository.java
├── service/
│   ├── PacienteService.java
│   ├── MedicoService.java
│   ├── HistorialClinicaService.java
│   └── impl/
│       ├── PacienteServiceImpl.java
│       ├── MedicoServiceImpl.java
│       └── HistorialClinicaServiceImpl.java
└── ProductoApplication.java
```

## 🛠️ Tecnologías Utilizadas

- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **PostgreSQL 18.0**
- **Lombok**
- **Bean Validation**
- **SpringDoc OpenAPI 2.7.0** (Swagger)
- **Maven**

## 📊 Base de Datos

Las tablas se crean automáticamente por Hibernate/JPA. Ejemplo de tabla `historial_clinica`:

```sql
CREATE TABLE historial_clinica (
    id BIGSERIAL PRIMARY KEY,
    paciente_dni VARCHAR(8) NOT NULL REFERENCES pacientes(dni),
    medico_cmp VARCHAR(20) NOT NULL REFERENCES medicos(cmp),
    fecha_atencion DATE NOT NULL,
    diagnostico VARCHAR(500) NOT NULL,
    analisis VARCHAR(1000),
    tratamiento VARCHAR(1000),
    fecha_registro TIMESTAMP NOT NULL
);
```

## 📝 Notas Adicionales

- El servidor corre por defecto en el puerto 8080
- Las fechas se gestionan automáticamente
- Los logs SQL están habilitados para debugging

## 🤝 Contribución

Para contribuir al proyecto:
1. Haz un fork del repositorio
2. Crea una rama para tu feature
3. Commit tus cambios
4. Push a la rama
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT.
