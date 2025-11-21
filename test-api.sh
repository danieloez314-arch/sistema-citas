#!/bin/bash

# Script de prueba automatizado para el Sistema de Citas
# Este script prueba todos los endpoints de la API REST

# Colores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Configuración
BASE_URL="http://localhost:8080"
CONTENT_TYPE="Content-Type: application/json"

# Contadores
TESTS_PASSED=0
TESTS_FAILED=0

# Función para imprimir encabezados
print_header() {
    echo -e "\n${BLUE}========================================${NC}"
    echo -e "${BLUE}$1${NC}"
    echo -e "${BLUE}========================================${NC}\n"
}

# Función para imprimir resultados
print_result() {
    if [ $1 -eq 0 ]; then
        echo -e "${GREEN}✓ PASSED${NC}: $2"
        ((TESTS_PASSED++))
    else
        echo -e "${RED}✗ FAILED${NC}: $2"
        ((TESTS_FAILED++))
    fi
}

# Función para hacer peticiones y verificar
test_endpoint() {
    local method=$1
    local endpoint=$2
    local description=$3
    local data=$4
    local expected_status=$5

    echo -e "${YELLOW}Testing:${NC} $description"
    
    if [ -z "$data" ]; then
        response=$(curl -s -w "\n%{http_code}" -X $method "$BASE_URL$endpoint")
    else
        response=$(curl -s -w "\n%{http_code}" -X $method "$BASE_URL$endpoint" \
            -H "$CONTENT_TYPE" \
            -d "$data")
    fi
    
    http_code=$(echo "$response" | tail -n1)
    body=$(echo "$response" | sed '$d')
    
    if [ "$http_code" = "$expected_status" ]; then
        print_result 0 "$description (Status: $http_code)"
        echo -e "${GREEN}Response:${NC} $body" | head -c 200
        echo "..."
    else
        print_result 1 "$description (Expected: $expected_status, Got: $http_code)"
        echo -e "${RED}Response:${NC} $body"
    fi
    echo ""
}

# Verificar que la aplicación esté ejecutándose
print_header "Verificando Conexión"
if ! curl -s "$BASE_URL" > /dev/null 2>&1; then
    echo -e "${RED}ERROR: La aplicación no está ejecutándose en $BASE_URL${NC}"
    echo "Por favor, inicia la aplicación con: mvn spring-boot:run"
    exit 1
fi
echo -e "${GREEN}✓ Aplicación ejecutándose correctamente${NC}\n"

# ==========================================
# PRUEBAS DE AUTENTICACIÓN
# ==========================================
print_header "1. PRUEBAS DE AUTENTICACIÓN"

test_endpoint "GET" "/api/auth/status" \
    "Estado de autenticación" \
    "" \
    "200"

test_endpoint "POST" "/api/auth/register" \
    "Registrar nuevo usuario" \
    '{
        "nombre": "Usuario Test",
        "email": "test_'$(date +%s)'@example.com",
        "password": "password123",
        "confirmPassword": "password123",
        "telefono": "3001234567"
    }' \
    "201"

# ==========================================
# PRUEBAS DE USUARIOS
# ==========================================
print_header "2. PRUEBAS DE USUARIOS (CRUD)"

# Nota: Estos endpoints requieren autenticación
# En un entorno real, necesitarías obtener un token de sesión primero

test_endpoint "GET" "/api/usuarios" \
    "Obtener todos los usuarios" \
    "" \
    "200"

test_endpoint "POST" "/api/usuarios" \
    "Crear usuario" \
    '{
        "nombre": "Pedro Ramírez",
        "email": "pedro_'$(date +%s)'@example.com",
        "password": "password123",
        "telefono": "3009998877"
    }' \
    "201"

# ==========================================
# PRUEBAS DE SERVICIOS
# ==========================================
print_header "3. PRUEBAS DE SERVICIOS (CRUD)"

test_endpoint "GET" "/api/servicios" \
    "Obtener todos los servicios" \
    "" \
    "200"

test_endpoint "POST" "/api/servicios" \
    "Crear servicio" \
    '{
        "nombre": "Corte Premium Test",
        "descripcion": "Corte de cabello premium con acabado de lujo",
        "duracion": "45 minutos",
        "precio": 40000.00
    }' \
    "201"

# ==========================================
# PRUEBAS DE PROFESIONALES
# ==========================================
print_header "4. PRUEBAS DE PROFESIONALES (CRUD)"

test_endpoint "GET" "/api/profesionales" \
    "Obtener todos los profesionales" \
    "" \
    "200"

# Nota: Crear profesional requiere un usuario existente
test_endpoint "POST" "/api/profesionales" \
    "Crear profesional" \
    '{
        "especialidad": "Barbero Experto Test",
        "horarioDisponible": "2025-01-20T09:00:00",
        "usuarioId": 1
    }' \
    "201"

# ==========================================
# PRUEBAS DE CITAS
# ==========================================
print_header "5. PRUEBAS DE CITAS (CRUD)"

test_endpoint "GET" "/api/citas" \
    "Obtener todas las citas" \
    "" \
    "200"

# Nota: Crear cita requiere usuario, servicio y profesional existentes
test_endpoint "POST" "/api/citas" \
    "Crear cita" \
    '{
        "fechaHora": "2025-01-25T15:00:00",
        "estado": "PENDIENTE",
        "notas": "Cliente prefiere corte moderno",
        "usuarioId": 1,
        "servicioId": 1,
        "profesionalId": 1
    }' \
    "201"

# ==========================================
# RESUMEN DE PRUEBAS
# ==========================================
print_header "RESUMEN DE PRUEBAS"

TOTAL_TESTS=$((TESTS_PASSED + TESTS_FAILED))
echo -e "Total de pruebas: ${BLUE}$TOTAL_TESTS${NC}"
echo -e "Pruebas exitosas: ${GREEN}$TESTS_PASSED${NC}"
echo -e "Pruebas fallidas: ${RED}$TESTS_FAILED${NC}"

if [ $TESTS_FAILED -eq 0 ]; then
    echo -e "\n${GREEN}¡Todas las pruebas pasaron exitosamente!${NC}\n"
    exit 0
else
    echo -e "\n${RED}Algunas pruebas fallaron. Revisa los detalles arriba.${NC}\n"
    exit 1
fi
