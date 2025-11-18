🚀 GUÍA COMPLETA DE COMPILACIÓN - WordVenture
==============================================

## 📌 OPCIÓN 1: Compilación Rápida (SIN Pruebas)

```bash
bash compilar.sh
```

✅ Compila todo el código principal en ~2-3 segundos
✅ NO descarga JUnit
✅ Genera bin/ con todas las clases compiladas
✅ Ideal para probar rápidamente la GUI o consola

---

## 📌 OPCIÓN 2: Compilar + Ejecutar Pruebas Unitarias

```bash
bash compile-and-test.sh pruebas
```

✅ Descarga JUnit automáticamente (primera vez)
✅ Compila código principal
✅ Compila pruebas unitarias
✅ Ejecuta todas las pruebas (~200+ tests)

---

## 📌 OPCIÓN 3: Compilación Paso a Paso (Manual)

### Paso 1: Compilar código principal
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

### Paso 2: Ejecutar GUI
```bash
java -cp bin gui.GameWindow
```

### Paso 3: O ejecutar consola
```bash
java -cp bin app.Main
```

---

## 🧪 OPCIÓN 4: Solo Pruebas Unitarias (JUnit requerido)

### Descargar JUnit (primera vez)
```bash
mkdir -p lib
curl -L -o lib/junit-4.13.2.jar \
  https://github.com/junit-team/junit4/releases/download/r4.13.2/junit-4.13.2.jar
curl -L -o lib/hamcrest-core-1.3.jar \
  https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar
```

### Compilar pruebas
```bash
javac -cp bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar \
  -d bin \
  src/modelos/*Test.java \
  src/validadores/*Test.java \
  src/niveles/*Test.java
```

### Ejecutar pruebas
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

## 📋 SCRIPTS DISPONIBLES

| Script | Comando | Descripción |
|--------|---------|-----------|
| compilar.sh | `bash compilar.sh` | ⚡ Compila SIN pruebas (rápido) |
| compile-and-test.sh compilar | `bash compile-and-test.sh compilar` | Compila solo código principal |
| compile-and-test.sh pruebas | `bash compile-and-test.sh pruebas` | Compila + ejecuta pruebas |
| compile-and-test.sh todo | `bash compile-and-test.sh todo` | Compila todo + pruebas |
| compile-and-test.sh gui | `bash compile-and-test.sh gui` | Ejecuta GUI |
| compile-and-test.sh consola | `bash compile-and-test.sh consola` | Ejecuta consola |

---

## ⚡ RECOMENDACIÓN POR CASO DE USO

### 🎮 Quiero probar la GUI rápidamente
```bash
bash compilar.sh
java -cp bin gui.GameWindow
```

### 🎮 Quiero probar la consola rápidamente
```bash
bash compilar.sh
java -cp bin app.Main
```

### 🧪 Quiero ejecutar todas las pruebas
```bash
bash compile-and-test.sh pruebas
```

### 📦 Quiero compilación completa (código + pruebas)
```bash
bash compile-and-test.sh todo
```

---

## ⚠️ PROBLEMAS COMUNES

### ❌ Error: "cannot find symbol class Test"
**Causa:** JUnit no está en el classpath
**Solución:** 
```bash
bash compile-and-test.sh pruebas
```
Esto descargará JUnit automáticamente

### ❌ Error: "module not found"
**Causa:** Faltan archivos de módulo
**Solución:** Asegúrate de estar en el directorio correcto
```bash
cd WordVenture
bash compilar.sh
```

### ❌ Error: "No such file or directory"
**Causa:** Ruta incorrecta
**Solución:** Verifica que estés en la raíz del proyecto WordVenture
```bash
pwd  # Debe mostrar: WordVenture
```

---

## 📊 RESUMEN RÁPIDO

```
┌─────────────────────────────────────────────────────────────┐
│ PARA DESARROLLAR RÁPIDAMENTE:                               │
│                                                              │
│  bash compilar.sh                                            │
│  java -cp bin gui.GameWindow                                │
└─────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│ PARA VALIDAR CON PRUEBAS:                                   │
│                                                              │
│  bash compile-and-test.sh pruebas                           │
└─────────────────────────────────────────────────────────────┘
```

---

Para más información, ver:
- README.md - Documentación completa
- COMPILAR_Y_PROBAR.md - Guía detallada
- QUICK_COMMANDS.txt - Referencia rápida
