# Sistema de Citas para Barbería

Sistema profesional de gestión de citas para barbería desarrollado con Spring Boot 3.5.7, que permite administrar usuarios, profesionales, servicios y citas de manera eficiente.

## Características

- **Arquitectura en capas**: Separación clara entre entidades, DTOs, servicios y controladores
- **Spring Security**: Autenticación y autorización con rol SuperAdmin
- **API REST**: Endpoints profesionales con validaciones y manejo de excepciones
- **Thymeleaf**: Interfaz web moderna, minimalista y cinematográfica
- **JPA/Hibernate**: Persistencia de datos con MySQL
- **Logging profesional**: Trazabilidad completa de operaciones

## Tecnologías

- Java 21
- Spring Boot 3.5.7
- Spring Data JPA
- Spring Security
- Thymeleaf
- MySQL 8.0+
- Maven
- Lombok

## Requisitos

- JDK 21 o superior
- Maven 3.6+
- MySQL 8.0+
- Puerto 8080 disponible

## Configuración de Base de Datos

El sistema se conecta a una base de datos MySQL con las siguientes credenciales por defecto:

```properties
URL: jdbc:mysql://localhost:3306/Barberia_Neita
Usuario: root
Contraseña: root
```

La base de datos se crea automáticamente si no existe.

## Instalación y Ejecución

1. Clonar el repositorio:
```bash
git clone https://github.com/danieloez314-arch/sistema-citas.git
cd sistema-citas
```

2. Compilar el proyecto:
```bash
mvn clean install
```

3. Ejecutar la aplicación:
```bash
mvn spring-boot:run
```

4. Acceder a la aplicación:
```
http://localhost:8080
```

## Estructura del Proyecto

```
src/main/java/com/neita/sistemacitas/
├── config/          # Configuraciones de Spring Security y otros
├── controller/      # Controladores REST y MVC
├── dto/            # Data Transfer Objects
├── entity/         # Entidades JPA
├── exception/      # Manejo de excepciones personalizado
├── repository/     # Repositorios JPA
├── service/        # Lógica de negocio
└── util/           # Utilidades y helpers

src/main/resources/
├── static/         # Recursos estáticos (CSS, JS, imágenes)
└── templates/      # Plantillas Thymeleaf
```

## Modelo de Datos

El sistema gestiona cuatro entidades principales:

- **Usuario**: Gestión de usuarios del sistema
- **Profesional**: Profesionales vinculados a usuarios con especialidades
- **Servicio**: Servicios ofrecidos por la barbería
- **Cita**: Reservas que relacionan usuarios, profesionales y servicios

## Autor

Desarrollado por Neita

## Licencia

Este proyecto es privado y confidencial.
