# API REST - Backend de Productos con Spring Boot

Backend RESTful API para gestión de productos, desarrollado con Spring Boot y PostgreSQL.

## 🚀 Características

- **CRUD completo** de productos
- **Validación de datos** con Bean Validation
- **Eliminación lógica** y física
- **Búsqueda** por nombre y categoría
- **PostgreSQL** como base de datos
- **CORS configurado** para integración con Flutter
- **Manejo global de excepciones**
- **Documentación interactiva con Swagger/OpenAPI**

## 📋 Requisitos Previos

- Java 17 o superior
- Maven 3.6+
- PostgreSQL 12 o superior

## 🗄️ Configuración de la Base de Datos

1. Instala PostgreSQL
2. Crea una base de datos:

```sql
CREATE DATABASE productos_db;
```

3. Configura las credenciales en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/productos_db
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

### Productos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/productos` | Obtener todos los productos |
| GET | `/api/productos/activos` | Obtener productos activos |
| GET | `/api/productos/{id}` | Obtener producto por ID |
| POST | `/api/productos` | Crear nuevo producto |
| PUT | `/api/productos/{id}` | Actualizar producto |
| DELETE | `/api/productos/{id}` | Eliminar producto (lógico) |
| DELETE | `/api/productos/{id}/permanente` | Eliminar producto (físico) |
| GET | `/api/productos/buscar?nombre=xxx` | Buscar por nombre |
| GET | `/api/productos/categoria/{cat}` | Buscar por categoría |

## 📝 Modelo de Datos

### Producto

```json
{
  "id": 1,
  "nombre": "Laptop HP",
  "descripcion": "Laptop HP 15.6 pulgadas",
  "precio": 2500.00,
  "stock": 10,
  "categoria": "Tecnología",
  "imagenUrl": "https://example.com/imagen.jpg",
  "activo": true,
  "fechaCreacion": "2024-01-15T10:30:00",
  "fechaActualizacion": "2024-01-15T10:30:00"
}
```

## 📦 Ejemplos de Uso

### Crear un producto (POST):

```bash
curl -X POST http://localhost:8080/api/productos \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Mouse Logitech",
    "descripcion": "Mouse inalámbrico",
    "precio": 45.99,
    "stock": 50,
    "categoria": "Periféricos"
  }'
```

### Obtener todos los productos (GET):

```bash
curl http://localhost:8080/api/productos
```

### Actualizar un producto (PUT):

```bash
curl -X PUT http://localhost:8080/api/productos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Mouse Logitech MX",
    "descripcion": "Mouse inalámbrico premium",
    "precio": 89.99,
    "stock": 30,
    "categoria": "Periféricos",
    "activo": true
  }'
```

### Eliminar un producto (DELETE):

```bash
curl -X DELETE http://localhost:8080/api/productos/1
```

## 🔐 Validaciones

Los siguientes campos son obligatorios:
- **nombre**: No puede estar vacío
- **precio**: Debe ser mayor a 0
- **stock**: No puede ser nulo

## 🌐 Integración con Flutter

El backend está configurado con CORS para aceptar peticiones desde cualquier origen. Para conectar desde Flutter:

```dart
final response = await http.get(
  Uri.parse('http://localhost:8080/api/productos'),
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
│   └── ProductoController.java
├── entity/
│   └── Producto.java
├── exception/
│   └── GlobalExceptionHandler.java
├── repository/
│   └── ProductoRepository.java
├── service/
│   ├── ProductoService.java
│   └── impl/
│       └── ProductoServiceImpl.java
└── ProductoApplication.java
```

## 🛠️ Tecnologías Utilizadas

- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Bean Validation**
- **SpringDoc OpenAPI 2.3.0** (Swagger)
- **Maven**

## 📊 Base de Datos

La tabla `productos` se crea automáticamente con el siguiente esquema:

```sql
CREATE TABLE productos (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    precio DECIMAL(10,2) NOT NULL,
    stock INTEGER NOT NULL,
    categoria VARCHAR(50),
    imagen_url VARCHAR(255),
    activo BOOLEAN NOT NULL DEFAULT true,
    fecha_creacion TIMESTAMP NOT NULL,
    fecha_actualizacion TIMESTAMP
);
```

## 📝 Notas Adicionales

- El campo `activo` permite eliminación lógica
- Las fechas se gestionan automáticamente
- El servidor corre por defecto en el puerto 8080
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
"# backend-historialmedico" 
