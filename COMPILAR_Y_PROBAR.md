# 🧪 Guía de Compilación y Pruebas - WordVenture

## Resumen Rápido

Para compilar y ejecutar pruebas unitarias en WordVenture, sigue estos pasos:

---

## ⚡ Opción 1: Script Automático (Recomendado)

El script `setup-and-test.sh` hace todo automáticamente:

```bash
bash setup-and-test.sh
```

Esto:
1. ✅ Descarga JUnit 4.13.2 y Hamcrest 1.3
2. ✅ Compila el código principal
3. ✅ Compila las pruebas unitarias
4. ✅ Ejecuta todas las pruebas

---

## 📋 Opción 2: Comandos Manuales

### Paso 1: Descargar JUnit (primera vez)

```bash
mkdir -p lib

# Descargar JUnit 4.13.2
curl -L -o lib/junit-4.13.2.jar \
  https://github.com/junit-team/junit4/releases/download/r4.13.2/junit-4.13.2.jar

# Descargar Hamcrest 1.3
curl -L -o lib/hamcrest-core-1.3.jar \
  https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar
```

### Paso 2: Compilar Código Principal

```bash
mkdir -p bin

javac -d bin \
  src/module-info.java \
  src/modelos/*.java \
  src/niveles/*.java \
  src/validadores/Validador.java \
  src/gui/*.java \
  src/app/Main.java
```

### Paso 3: Compilar Pruebas Unitarias

```bash
javac -cp bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar \
  -d bin \
  src/modelos/*Test.java \
  src/validadores/*Test.java \
  src/niveles/*Test.java
```

### Paso 4: Ejecutar Pruebas

```bash
java -cp bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar \
  org.junit.runner.JUnitCore \
  validadores.ValidadorTest \
  modelos.PosicionTest \
  modelos.DireccionTest \
  modelos.CeldaTest \
  modelos.PreguntaTest \
  modelos.JugadorTest \
  modelos.BancoPreguntasTest \
  modelos.GeneradorMapasTest \
  modelos.JuegoTest \
  niveles.BasicoTest \
  niveles.IntermedioTest \
  niveles.AvanzadoTest \
  niveles.LeyendaTest
```

---

## 🎮 Ejecutar el Juego

Después de compilar, puedes jugar:

### Versión Gráfica (Recomendado)

```bash
java -cp bin gui.GameWindow
```

### Versión Consola

```bash
java -cp bin app.Main
```

---

## 📊 Pruebas Disponibles

| Clase de Prueba | Descripción |
|-----------------|-----------|
| `ValidadorTest` | Validaciones de entrada y nombres |
| `PosicionTest` | Coordenadas y movimientos |
| `DireccionTest` | Enumeración de direcciones |
| `CeldaTest` | Tipos de celdas del mapa |
| `PreguntaTest` | Estructura de preguntas |
| `JugadorTest` | Estado y movimiento del jugador |
| `BancoPreguntasTest` | Selección aleatoria de preguntas |
| `GeneradorMapasTest` | Generación dinámica de mapas |
| `JuegoTest` | Lógica principal del juego |
| `BasicoTest` | Nivel Básico (8×8) |
| `IntermedioTest` | Nivel Intermedio (16×16) |
| `AvanzadoTest` | Nivel Avanzado (32×32) |
| `LeyendaTest` | Nivel Leyenda (64×64) |

**Total: 13 clases de prueba con ~200+ test cases**

---

## ❌ Solución de Problemas

### Error: `cannot find symbol class Test`

**Causa:** JUnit no está en el classpath

**Solución:**
```bash
# Ejecuta el Paso 1 para descargar JUnit
curl -L -o lib/junit-4.13.2.jar \
  https://github.com/junit-team/junit4/releases/download/r4.13.2/junit-4.13.2.jar
```

### Error: `No such file or directory`

**Causa:** Estás en el directorio incorrecto

**Solución:**
```bash
cd WordVenture
```

### Error: `module not found: java.desktop`

**Causa:** Java 21 requiere módulos explícitos para Swing

**Solución:** Asegúrate de que `module-info.java` incluye:
```java
requires java.desktop;
```

---

## 📝 Notas Importantes

- 🔴 **IMPORTANTE**: Siempre compila el código principal ANTES de las pruebas
- 🔴 **IMPORTANTE**: JUnit debe estar en `lib/` antes de compilar pruebas
- 🟢 Los resultados de pruebas se muestran en la consola
- 🟢 El archivo COMANDOS.sh contiene una referencia completa de todos los comandos

---

## 📚 Referencias

- [README.md](README.md) - Documentación completa del proyecto
- [INICIO_RAPIDO.md](INICIO_RAPIDO.md) - Guía rápida de inicio
- [TESTING.md](TESTING.md) - Documentación detallada de pruebas
- [ENTREGA_COMPLETA.md](ENTREGA_COMPLETA.md) - Resumen de entrega
