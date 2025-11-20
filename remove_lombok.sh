#!/bin/bash

# Lista de archivos a procesar
FILES=(
    "src/main/java/com/neita/sistemacitas/service/ProfesionalService.java"
    "src/main/java/com/neita/sistemacitas/service/ServicioService.java"
    "src/main/java/com/neita/sistemacitas/service/CitaService.java"
    "src/main/java/com/neita/sistemacitas/service/CustomUserDetailsService.java"
    "src/main/java/com/neita/sistemacitas/controller/UsuarioController.java"
    "src/main/java/com/neita/sistemacitas/controller/UsuarioRestController.java"
    "src/main/java/com/neita/sistemacitas/controller/ProfesionalController.java"
    "src/main/java/com/neita/sistemacitas/controller/ProfesionalRestController.java"
    "src/main/java/com/neita/sistemacitas/controller/ServicioController.java"
    "src/main/java/com/neita/sistemacitas/controller/ServicioRestController.java"
    "src/main/java/com/neita/sistemacitas/controller/CitaController.java"
    "src/main/java/com/neita/sistemacitas/controller/CitaRestController.java"
    "src/main/java/com/neita/sistemacitas/controller/HomeController.java"
    "src/main/java/com/neita/sistemacitas/config/DataInitializer.java"
    "src/main/java/com/neita/sistemacitas/config/SecurityConfig.java"
)

echo "Archivos a procesar:"
for file in "${FILES[@]}"; do
    echo "  - $file"
done
