# API Foro

API REST para un foro de discusión desarrollada con Spring Boot.

## 🚀 Tecnologías Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
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

### Tópicos

- `POST /topico` - Crear un nuevo tópico
- `GET /topico` - Listar todos los tópicos activos (paginado)
- `GET /topico/{id}` - Obtener un tópico específico
- `PUT /topico` - Actualizar un tópico existente
- `DELETE /topico/{id}` - Eliminar un tópico (borrado lógico)

## 🔧 Configuración

Para ejecutar el proyecto, necesitarás:

1. Java 17 instalado
2. MySQL configurado
3. Configurar las credenciales de la base de datos en `application.properties`

## 📝 Validaciones

El sistema incluye validaciones para:

- Tópicos duplicados
- Existencia de usuarios y cursos
- Campos obligatorios en los DTOs