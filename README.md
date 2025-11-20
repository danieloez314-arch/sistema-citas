# Sistema de Citas - Barbería Neita

Sistema profesional de gestión de citas para barbería desarrollado con Spring Boot 3.5.7 y MySQL. Incluye API REST completa, interfaz web con Thymeleaf y sistema de autenticación robusto.

## 🚀 Características Principales

- **Arquitectura en capas**: Separación clara entre entidades, DTOs, servicios y controladores
- **Spring Security**: Autenticación y autorización con rol SuperAdmin
- **API REST completa**: 4 CRUDs totalmente funcionales (Usuario, Profesional, Servicio, Cita)
- **Registro de usuarios**: Endpoint público para registro con validaciones
- **Interfaz web Thymeleaf**: Diseño moderno, minimalista y cinematográfico
- **JPA/Hibernate**: Persistencia de datos con MySQL
- **Sin Lombok**: Código explícito y fácil de mantener
- **Logging profesional**: Trazabilidad completa con SLF4J
- **Validaciones**: Bean Validation en todos los endpoints
- **Manejo de excepciones**: Sistema centralizado de errores

## 📋 Requisitos del Sistema

- **Java**: OpenJDK 21 o superior
- **Maven**: 3.6.3 o superior
- **MySQL**: 8.0 o superior
- **Git**: Para clonar el repositorio
- **Puerto 8080**: Disponible para la aplicación

## 🛠️ Tecnologías Utilizadas

- **Spring Boot 3.5.7**
  - Spring Data JPA
  - Spring Security
  - Spring Web
  - Spring Validation
- **Thymeleaf** con Thymeleaf Spring Security
- **MySQL 8.0+** con conector JDBC
- **Maven 4.0**
- **BCrypt** para encriptación de contraseñas
- **SLF4J/Logback** para logging

## 📊 Modelo de Datos

### Usuario
- `id` (PK, AUTO_INCREMENT)
- `nombre` VARCHAR(255) NOT NULL
- `email` VARCHAR(255) NOT NULL UNIQUE
- `password` VARCHAR(255) NOT NULL (encriptado con BCrypt)
- `telefono` VARCHAR(255)
- `fecha_registro` DATETIME(6) NOT NULL
- `rol` VARCHAR(50) NOT NULL DEFAULT 'SUPER_ADMIN'
- `activo` BOOLEAN NOT NULL DEFAULT TRUE

### Profesional
- `id` (PK, AUTO_INCREMENT)
- `especialidad` VARCHAR(255) NOT NULL
- `horario_disponible` DATETIME(6)
- `activo` BOOLEAN NOT NULL DEFAULT TRUE
- `usuario_id` (FK → Usuario, UNIQUE)

### Servicio
- `id` (PK, AUTO_INCREMENT)
- `nombre` VARCHAR(255) NOT NULL
- `descripcion` TEXT
- `duracion` VARCHAR(255) NOT NULL
- `precio` DOUBLE NOT NULL
- `activo` BOOLEAN NOT NULL DEFAULT TRUE

### Cita
- `id` (PK, AUTO_INCREMENT)
- `fecha_hora` DATETIME(6) NOT NULL
- `estado` VARCHAR(50) NOT NULL DEFAULT 'PENDIENTE'
- `notas` TEXT
- `fecha_creacion` DATETIME(6) NOT NULL
- `usuario_id` (FK → Usuario)
- `servicio_id` (FK → Servicio)
- `profesional_id` (FK → Profesional)

## 🚀 Instalación y Configuración

### 1. Clonar el Repositorio

```bash
git clone https://github.com/danieloez314-arch/sistema-citas.git
cd sistema-citas
```

### 2. Configurar la Base de Datos

#### Opción A: Creación Automática (Recomendado)

La aplicación creará automáticamente la base de datos si no existe. Solo necesitas:

1. Asegurarte de que MySQL esté ejecutándose
2. Tener un usuario con permisos para crear bases de datos

#### Opción B: Creación Manual

```bash
mysql -u root -p < src/main/resources/schema.sql
```

### 3. Configurar Variables de Entorno

Copia el archivo de ejemplo y ajusta los valores:

```bash
cp .env.example .env
```

Edita `.env` con tus credenciales:

```properties
SERVER_PORT=8080
DB_URL=jdbc:mysql://localhost:3306/Barberia_Neita?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=America/Bogota&allowPublicKeyRetrieval=true
DB_USERNAME=root
DB_PASSWORD=tu_password
DDL_AUTO=update
SHOW_SQL=true
```

**Formas de usar variables de entorno**:

```bash
# Opción 1: Exportar en terminal
export DB_PASSWORD=tu_password
mvn spring-boot:run

# Opción 2: Pasar como argumentos
mvn spring-boot:run -Dspring-boot.run.arguments="--DB_PASSWORD=tu_password"

# Opción 3: Usar direnv (instalar primero)
direnv allow
mvn spring-boot:run
```

### 4. Compilar el Proyecto

```bash
mvn clean install
```

### 5. Ejecutar la Aplicación

```bash
mvn spring-boot:run
```

O ejecutar el JAR generado:

```bash
java -jar target/sistema-citas-0.0.1-SNAPSHOT.jar
```

La aplicación estará disponible en: **http://localhost:8080**

## 👤 Credenciales por Defecto

Al iniciar la aplicación por primera vez, se crea automáticamente un usuario SuperAdmin:

- **Email**: `admin@barberia.com`
- **Contraseña**: `admin123`

**⚠️ IMPORTANTE**: Cambia esta contraseña después del primer inicio de sesión.

## 📦 Datos de Prueba

### Cargar Datos de Prueba

Para insertar datos de prueba adicionales:

```bash
mysql -u root -p Barberia_Neita < src/main/resources/data-seed.sql
```

Esto creará:
- 4 usuarios de ejemplo (contraseña: `password123`)
- 5 servicios (corte, afeitado, tinte, etc.)
- 2 profesionales
- 4 citas de ejemplo

## 🧪 Pruebas con Postman

### Importar la Colección

1. Abre Postman
2. Click en **"Import"**
3. Selecciona el archivo `postman_collection.json`
4. Importa también el environment `postman_environment.json`

### Configurar el Environment

1. Selecciona el environment **"Sistema Citas - Local"**
2. Verifica que `baseUrl` apunte a `http://localhost:8080`

### Orden de Pruebas Recomendado

1. **Autenticación**
   - ✅ Registrar Usuario
   - ✅ Estado de Autenticación

2. **Usuarios** (CRUD Completo)
   - ✅ Obtener Todos los Usuarios
   - ✅ Crear Usuario
   - ✅ Obtener Usuario por ID
   - ✅ Actualizar Usuario
   - ✅ Eliminar Usuario

3. **Servicios** (CRUD Completo)
   - ✅ Obtener Todos los Servicios
   - ✅ Crear Servicio
   - ✅ Actualizar Servicio
   - ✅ Eliminar Servicio

4. **Profesionales** (CRUD Completo)
   - ✅ Crear Profesional (requiere un usuario existente)
   - ✅ Obtener Todos los Profesionales
   - ✅ Actualizar Profesional
   - ✅ Eliminar Profesional

5. **Citas** (CRUD Completo)
   - ✅ Crear Cita (requiere usuario, servicio y profesional existentes)
   - ✅ Obtener Todas las Citas
   - ✅ Actualizar Cita
   - ✅ Eliminar Cita

## 📡 Endpoints de la API

### Autenticación (Público)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/auth/register` | Registrar nuevo usuario |
| GET | `/api/auth/status` | Estado del sistema de autenticación |

### Usuarios (Requiere autenticación)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/usuarios` | Listar todos los usuarios |
| GET | `/api/usuarios/{id}` | Obtener usuario por ID |
| GET | `/api/usuarios/activos` | Listar usuarios activos |
| GET | `/api/usuarios/buscar?nombre={nombre}` | Buscar usuarios por nombre |
| POST | `/api/usuarios` | Crear usuario |
| PUT | `/api/usuarios/{id}` | Actualizar usuario |
| DELETE | `/api/usuarios/{id}` | Eliminar usuario (lógico) |

### Servicios (Requiere autenticación)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/servicios` | Listar todos los servicios |
| GET | `/api/servicios/{id}` | Obtener servicio por ID |
| GET | `/api/servicios/activos` | Listar servicios activos |
| GET | `/api/servicios/buscar?nombre={nombre}` | Buscar servicios por nombre |
| POST | `/api/servicios` | Crear servicio |
| PUT | `/api/servicios/{id}` | Actualizar servicio |
| DELETE | `/api/servicios/{id}` | Eliminar servicio (lógico) |

### Profesionales (Requiere autenticación)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/profesionales` | Listar todos los profesionales |
| GET | `/api/profesionales/{id}` | Obtener profesional por ID |
| GET | `/api/profesionales/activos` | Listar profesionales activos |
| POST | `/api/profesionales` | Crear profesional |
| PUT | `/api/profesionales/{id}` | Actualizar profesional |
| DELETE | `/api/profesionales/{id}` | Eliminar profesional (lógico) |

### Citas (Requiere autenticación)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/citas` | Listar todas las citas |
| GET | `/api/citas/{id}` | Obtener cita por ID |
| GET | `/api/citas/usuario/{usuarioId}` | Listar citas de un usuario |
| GET | `/api/citas/profesional/{profesionalId}` | Listar citas de un profesional |
| GET | `/api/citas/estado/{estado}` | Listar citas por estado |
| POST | `/api/citas` | Crear cita |
| PUT | `/api/citas/{id}` | Actualizar cita |
| PATCH | `/api/citas/{id}/estado?estado={estado}` | Cambiar estado de cita |
| DELETE | `/api/citas/{id}` | Eliminar cita |

## 🌐 Rutas Web (Interfaz Thymeleaf)

| Ruta | Descripción |
|------|-------------|
| `/` | Página de inicio con estadísticas |
| `/login` | Página de inicio de sesión |
| `/logout` | Cerrar sesión |
| `/usuarios` | Gestión de usuarios |
| `/profesionales` | Gestión de profesionales |
| `/servicios` | Gestión de servicios |
| `/citas` | Gestión de citas |

## 📁 Estructura del Proyecto

```
sistema-citas/
├── src/
│   ├── main/
│   │   ├── java/com/neita/sistemacitas/
│   │   │   ├── config/              # Configuración
│   │   │   │   ├── DataInitializer.java
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── controller/          # Controladores REST y Web
│   │   │   │   ├── AuthRestController.java
│   │   │   │   ├── UsuarioRestController.java
│   │   │   │   ├── ProfesionalRestController.java
│   │   │   │   ├── ServicioRestController.java
│   │   │   │   ├── CitaRestController.java
│   │   │   │   ├── HomeController.java
│   │   │   │   └── ...
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   │   ├── RegistroDTO.java
│   │   │   │   ├── UsuarioDTO.java
│   │   │   │   ├── ProfesionalDTO.java
│   │   │   │   ├── ServicioDTO.java
│   │   │   │   ├── CitaDTO.java
│   │   │   │   ├── ApiResponse.java
│   │   │   │   └── ErrorDetails.java
│   │   │   ├── entity/              # Entidades JPA
│   │   │   │   ├── Usuario.java
│   │   │   │   ├── Profesional.java
│   │   │   │   ├── Servicio.java
│   │   │   │   └── Cita.java
│   │   │   ├── exception/           # Manejo de excepciones
│   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   ├── DuplicateResourceException.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── repository/          # Repositorios JPA
│   │   │   │   ├── UsuarioRepository.java
│   │   │   │   ├── ProfesionalRepository.java
│   │   │   │   ├── ServicioRepository.java
│   │   │   │   └── CitaRepository.java
│   │   │   ├── service/             # Lógica de negocio
│   │   │   │   ├── UsuarioService.java
│   │   │   │   ├── ProfesionalService.java
│   │   │   │   ├── ServicioService.java
│   │   │   │   ├── CitaService.java
│   │   │   │   └── CustomUserDetailsService.java
│   │   │   └── SistemaCitasApplication.java
│   │   └── resources/
│   │       ├── static/              # Recursos estáticos
│   │       │   ├── css/style.css
│   │       │   └── js/main.js
│   │       ├── templates/           # Plantillas Thymeleaf
│   │       │   ├── fragments/layout.html
│   │       │   ├── auth/login.html
│   │       │   ├── usuario/
│   │       │   ├── profesional/
│   │       │   ├── servicio/
│   │       │   ├── cita/
│   │       │   └── index.html
│   │       ├── application.properties
│   │       ├── schema.sql           # Script de creación de BD
│   │       └── data-seed.sql        # Datos de prueba
│   └── test/                        # Tests
├── logs/                            # Logs de build y análisis
├── postman_collection.json          # Colección de Postman
├── postman_environment.json         # Environment de Postman
├── .env.example                     # Ejemplo de variables de entorno
├── .gitignore
├── pom.xml                          # Configuración de Maven
└── README.md                        # Este archivo
```

## 🔒 Seguridad

- **Autenticación basada en formularios** con Spring Security
- **Contraseñas encriptadas** con BCrypt (factor 10)
- **Sesiones gestionadas** con invalidación al cerrar sesión
- **Protección CSRF** habilitada
- **Rol único SuperAdmin** con acceso completo
- **Endpoints públicos**: Solo `/api/auth/register` y `/api/auth/status`

## 📝 Logging

El sistema implementa logging profesional con SLF4J:

- **DEBUG**: Operaciones detalladas de servicios y SQL
- **INFO**: Operaciones importantes y exitosas
- **WARN**: Advertencias y situaciones inusuales
- **ERROR**: Errores y excepciones

Configuración en `application.properties`:

```properties
logging.level.com.neita.sistemacitas=DEBUG
logging.level.org.springframework.security=DEBUG
logging.level.org.hibernate.SQL=DEBUG
```

## ✅ Validaciones

Todas las entradas de datos son validadas con:

- **Bean Validation** (Jakarta Validation)
- **@NotBlank**, **@Email**, **@Size**, **@NotNull**
- Validaciones personalizadas en servicios
- Mensajes de error descriptivos
- Manejo centralizado con `GlobalExceptionHandler`

## 🎨 Diseño y Estilo

El sistema cuenta con un diseño minimalista y cinematográfico:

- **Paleta de colores oscura**: Fondo negro con acentos dorados
- **Tipografías**: Anton (títulos), Montserrat (cuerpo), Open Sans (texto)
- **Animaciones suaves**: Transiciones y efectos de entrada
- **Diseño responsivo**: Adaptable a diferentes tamaños de pantalla
- **Cards con sombras**: Efecto de profundidad y elevación
- **Botones con gradientes**: Efectos visuales atractivos

## 🔧 Solución de Problemas

### Error de Conexión a MySQL

```
Communications link failure
```

**Solución**: Verifica que MySQL esté ejecutándose:

```bash
# Linux/Mac
sudo systemctl status mysql

# Windows
net start MySQL80
```

### Error de Puerto en Uso

```
Port 8080 is already in use
```

**Solución**: Cambia el puerto en `.env` o detén la aplicación que está usando el puerto:

```bash
# Linux/Mac
lsof -i :8080
kill -9 <PID>

# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```

### Error de Compilación

```
BUILD FAILURE
```

**Solución**: Verifica la versión de Java:

```bash
java -version
# Debe ser Java 21 o superior
```

## 📌 Cambios Recientes

### Versión Actual (2025-11-20)

- ✅ **Eliminación completa de Lombok**: Código explícito y mantenible
- ✅ **Endpoint de registro**: `/api/auth/register` público
- ✅ **Variables de entorno**: Configuración portable
- ✅ **Scripts SQL**: `schema.sql` y `data-seed.sql`
- ✅ **Colección de Postman**: Completa con todos los endpoints
- ✅ **Documentación exhaustiva**: README, comentarios en código
- ✅ **4 CRUDs funcionales**: Usuario, Profesional, Servicio, Cita
- ✅ **Build exitoso**: Sin errores ni warnings críticos

## 👨‍💻 Autor

Desarrollado por **Neita** para Barbería Neita

## 📦 Repositorio

[https://github.com/danieloez314-arch/sistema-citas](https://github.com/danieloez314-arch/sistema-citas)

## 📄 Licencia

Este proyecto es privado y confidencial.

## 📞 Soporte

Para reportar problemas o solicitar nuevas funcionalidades, contacta al equipo de desarrollo.

---

**Última actualización**: 2025-11-20
**Versión**: 0.0.1-SNAPSHOT
