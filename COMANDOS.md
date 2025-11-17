# 📋 Comandos de Compilación y Ejecución - WordVenture

## 🚀 Uso Rápido

### Script Automatizado (Recomendado)

```bash
# Compilar solo el código
bash compile-and-test.sh compilar

# Compilar y ejecutar pruebas unitarias
bash compile-and-test.sh pruebas

# Compilar todo (código + pruebas) y ejecutar pruebas
bash compile-and-test.sh todo

# Ejecutar la interfaz gráfica
bash compile-and-test.sh gui

# Ejecutar la versión en consola
bash compile-and-test.sh consola

# Ver ayuda
bash compile-and-test.sh ayuda
```

---

## 🔧 Comandos Manuales

### Paso 1: Compilar el Código Principal

```bash
# Navegar al directorio del proyecto
cd WordVenture

# Crear directorio bin si no existe
mkdir -p bin

# Compilar TODO el código (módulos, modelos, niveles, validadores, GUI, app)
javac -d bin \
  src/module-info.java \
  src/modelos/*.java \
  src/niveles/*.java \
  src/validadores/Validador.java \
  src/gui/*.java \
  src/app/Main.java
```

**Explicación:**
- `-d bin`: Coloca los archivos compilados (.class) en el directorio `bin`
- `src/module-info.java`: Información del módulo (requiere java.desktop para Swing)
- `src/modelos/*.java`: Clases base (Jugador, Juego, Posicion, Celda, Pregunta, BancoPreguntas, GeneradorMapas)
- `src/niveles/*.java`: Implementaciones de niveles (Basico, Intermedio, Avanzado, Leyenda)
- `src/validadores/Validador.java`: Utilidades de validación
- `src/gui/*.java`: Interfaz gráfica (GameWindow, GamePanel)
- `src/app/Main.java`: Punto de entrada de la consola

---

### Paso 2: Ejecutar la Aplicación

#### Opción A: Interfaz Gráfica (GUI Swing)

```bash
cd WordVenture
java -cp bin gui.GameWindow
```

**Características:**
- Visualización gráfica del mapa
- Controles W/A/S/D para movimiento
- Diálogos interactivos para preguntas
- Menú de selección de nivel

#### Opción B: Versión en Consola

```bash
cd WordVenture
java -cp bin app.Main
```

**Características:**
- Interfaz basada en texto
- Entrada/salida en consola
- Compatible con cualquier terminal

---

### Paso 3: Ejecutar Pruebas Unitarias

#### 3.1: Descargar JUnit (Primera Vez)

```bash
cd WordVenture
mkdir -p lib

# Descargar JUnit 4.13.2
curl -L -o lib/junit-4.13.2.jar \
  https://github.com/junit-team/junit4/releases/download/r4.13.2/junit-4.13.2.jar

# Descargar Hamcrest 1.3
curl -L -o lib/hamcrest-core-1.3.jar \
  https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar

# Verificar descargas
ls -la lib/
```

**Salida esperada:**
```
-rw-r--r--  hamcrest-core-1.3.jar (30 KB)
-rw-r--r--  junit-4.13.2.jar (383 KB)
```

#### 3.2: Compilar las Pruebas

```bash
cd WordVenture

javac -cp "bin:lib/junit-4.13.2.jar" \
  -d bin \
  src/modelos/*Test.java \
  src/niveles/*Test.java \
  src/validadores/*Test.java
```

**Explicación:**
- `-cp "bin:lib/junit-4.13.2.jar"`: Classpath con código compilado y JUnit
- `src/**/*Test.java`: Todos los archivos de prueba

#### 3.3: Ejecutar las Pruebas

```bash
cd WordVenture

java -cp "bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" \
  org.junit.runner.JUnitCore \
  validadores.ValidadorTest \
  modelos.JuegoTest \
  modelos.PosicionTest \
  modelos.JugadorTest \
  modelos.PreguntaTest \
  modelos.BancoPreguntasTest \
  modelos.GeneradorMapasTest \
  niveles.NivelTest \
  niveles.LeyendaTest
```

**Salida esperada:**
```
OK (200+ tests)
```

---

## 🎯 Flujo Completo (One-Liner)

### Compilar + Ejecutar GUI
```bash
cd WordVenture && \
mkdir -p bin && \
javac -d bin src/module-info.java src/**/*.java && \
java -cp bin gui.GameWindow
```

### Compilar + Ejecutar Consola
```bash
cd WordVenture && \
mkdir -p bin && \
javac -d bin src/module-info.java src/**/*.java && \
java -cp bin app.Main
```

### Compilar + Pruebas (Asumiendo JUnit ya descargado)
```bash
cd WordVenture && \
mkdir -p bin && \
javac -d bin src/module-info.java src/**/*.java && \
javac -cp "bin:lib/junit-4.13.2.jar" -d bin src/**/*Test.java && \
java -cp "bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" \
  org.junit.runner.JUnitCore validadores.ValidadorTest modelos.JuegoTest modelos.PosicionTest modelos.JugadorTest modelos.PreguntaTest modelos.BancoPreguntasTest modelos.GeneradorMapasTest niveles.NivelTest niveles.LeyendaTest
```

---

## 📊 Pruebas Disponibles

| Clase de Prueba | Ubicación | Pruebas |
|---|---|---|
| **ValidadorTest** | `src/validadores/ValidadorTest.java` | 15+ pruebas de validación |
| **JuegoTest** | `src/modelos/JuegoTest.java` | 20+ pruebas de lógica |
| **PosicionTest** | `src/modelos/PosicionTest.java` | 10+ pruebas de coordenadas |
| **JugadorTest** | `src/modelos/JugadorTest.java` | 15+ pruebas de estado |
| **PreguntaTest** | `src/modelos/PreguntaTest.java` | 10+ pruebas de preguntas |
| **BancoPreguntasTest** | `src/modelos/BancoPreguntasTest.java` | 10+ pruebas de banco |
| **GeneradorMapasTest** | `src/modelos/GeneradorMapasTest.java` | 10+ pruebas de mapas |
| **NivelTest** | `src/niveles/NivelTest.java` | 20+ pruebas de niveles |
| **LeyendaTest** | `src/niveles/LeyendaTest.java` | 15+ pruebas de Leyenda |

**Total:** 200+ pruebas unitarias

---

## 🐛 Solución de Problemas

### Error: "class file version mismatch"
```bash
# Solución: Asegurar que usas Java 21
java -version

# Si tienes Java 16 instalado, recompila y ejecuta con Java 21
/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home/bin/java -cp bin gui.GameWindow
```

### Error: "no se ha encontrado la clase gui.GameWindow"
```bash
# Verifica que la compilación incluya la GUI
ls -la bin/gui/

# Debe mostrar:
# GameWindow.class
# GamePanel.class
```

### Error: "cannot find symbol @Test"
```bash
# Solución: JUnit no está en el classpath. Descarga JUnit:
bash compile-and-test.sh pruebas
```

### Error: "Connection refused" descargando JUnit
```bash
# Descarga manual desde:
# JUnit: https://github.com/junit-team/junit4/releases/download/r4.13.2/junit-4.13.2.jar
# Hamcrest: https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar

# Y coloca los archivos en lib/
```

---

## 📝 Variables de Entorno Útiles

```bash
# Guardar ruta del proyecto
export WORDVENTURE_HOME="WordVenture"

# Compilar usando variable
cd $WORDVENTURE_HOME && \
mkdir -p bin && \
javac -d bin src/**/*.java

# Ejecutar GUI
java -cp $WORDVENTURE_HOME/bin gui.GameWindow

# Ejecutar Consola
java -cp $WORDVENTURE_HOME/bin app.Main
```

---

## ✅ Checklist

- [ ] Java 21+ instalado (`java -version`)
- [ ] Proyecto clonado en `WordVenture`
- [ ] Script `compile-and-test.sh` es ejecutable (`chmod +x compile-and-test.sh`)
- [ ] Compilación exitosa (`bash compile-and-test.sh compilar`)
- [ ] GUI ejecutándose (`bash compile-and-test.sh gui`)
- [ ] Consola ejecutándose (`bash compile-and-test.sh consola`)
- [ ] JUnit descargado (`ls lib/*.jar`)
- [ ] Pruebas compiladas y ejecutadas (`bash compile-and-test.sh pruebas`)

---

## 📚 Documentación Relacionada

- **README.md**: Documentación técnica completa
- **TESTING.md**: Guía detallada de pruebas
- **INICIO_RAPIDO.md**: Inicio rápido en 2 minutos
- **ENTREGA_COMPLETA.md**: Resumen de entrega

---

**Última actualización:** 16 de noviembre de 2025
