# API Foro

API REST para un foro de discusión desarrollada con Spring Boot.

## 🚀 Tecnologías Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT (Auth0)
- Flyway (Migraciones)
- Jakarta EE
- MySQL
- Lombok

## 📋 Descripción

Esta API permite la gestión de tópicos de discusión en un foro, incluyendo las siguientes funcionalidades:

- Crear nuevos tópicos
- Listar tópicos existentes (con paginación)
- Obtener detalles de un tópico específico
- Actualizar tópicos
- Eliminar tópicos (borrado lógico)

## 🗄️ Estructura de la Base de Datos

El sistema utiliza las siguientes tablas:

- `Usuario`: Almacena información de los usuarios
- `Perfil`: Gestiona los perfiles de usuario
- `Curso`: Información sobre los cursos
- `Topico`: Almacena los tópicos de discusión
- `Respuesta`: Gestiona las respuestas a los tópicos

## 🔀 Endpoints

### Autenticación

- `POST /login` - Iniciar sesión y obtener token JWT
  - Request: `{ "correoElectronico": "email@example.com", "contrasena": "password" }`
  - Response: `{ "token": "jwt-token-value" }`

### Tópicos

- `POST /topico` - Crear un nuevo tópico (requiere autenticación)
  - Request: `{ "titulo": "Título", "mensaje": "Contenido", "autor_id": 1, "curso_id": 1 }`
  
- `GET /topico` - Listar todos los tópicos activos (paginado, requiere autenticación)
  - Parámetros opcionales: `?page=0&size=10&sort=fechaCreacion,desc`
  
- `GET /topico/{id}` - Obtener un tópico específico por ID (requiere autenticación)
  
- `PUT /topico` - Actualizar un tópico existente (requiere autenticación)
  - Request: `{ "id": 1, "titulo": "Nuevo título", "mensaje": "Nuevo contenido" }`
  
- `DELETE /topico/{id}` - Eliminar un tópico (borrado lógico, requiere autenticación)

## 📂 Estructura del Proyecto

El proyecto sigue una arquitectura en capas:

- `controller`: Controladores REST que manejan las peticiones HTTP
- `domain`: Entidades y repositorios organizados por dominio (usuario, topico, curso)
- `infra`: Configuraciones de infraestructura
  - `security`: Implementación de seguridad con JWT
  - `exceptions`: Manejo centralizado de errores

## 🔧 Configuración

Para ejecutar el proyecto, necesitarás:

1. Java 17 instalado
2. MySQL configurado
3. Configurar las credenciales de la base de datos en `application.properties`
4. Configurar la clave secreta para JWT en `application.properties` con la propiedad `api.security.token.secret`

## 🚀 Ejecución del Proyecto

Para ejecutar el proyecto, sigue estos pasos:

1. Clona el repositorio:
   ```
   git clone https://github.com/tu-usuario/foro-alura.git
   cd foro-alura
   ```

2. Configura la base de datos en `application.properties`:
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/foro_alura
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   ```

3. Ejecuta la aplicación:
   ```
   ./mvnw spring-boot:run
   ```

4. La API estará disponible en `http://localhost:8080`

## 📝 Validaciones

El sistema incluye validaciones para:

- Tópicos duplicados
- Existencia de usuarios y cursos
- Campos obligatorios en los DTOs

## 🔒 Seguridad y Autenticación

El sistema implementa seguridad mediante Spring Security y JWT:

- Autenticación basada en tokens JWT
- Tokens con expiración de 2 horas
- Contraseñas encriptadas con BCrypt
- Endpoints protegidos que requieren autenticación
- Filtro de seguridad personalizado para validación de tokens

### Uso de Tokens

Para acceder a los endpoints protegidos:

1. Obtén un token mediante el endpoint `/login`
2. Incluye el token en el header de tus peticiones:
   ```
   Authorization: Bearer tu-token-jwt
   ```

## 📅 Fecha de Actualización

Última actualización: 6 de agosto de 2025

## 📬 Contacto

Para cualquier consulta o sugerencia sobre este proyecto, puedes contactar al desarrollador:

- Email: ejemplo@correo.com
- GitHub: [tu-usuario](https://github.com/tu-usuario)