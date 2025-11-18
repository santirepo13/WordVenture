# worldVenture 🎮

Una aplicación educativa interactiva en Java que combina un juego de navegación en laberinto con preguntas trivia para aprender vocabulario en inglés.

---

## 📋 Tabla de Contenidos

1. [Descripción General](#descripción-general)
2. [Características](#características)
3. [Arquitectura del Sistema](#arquitectura-del-sistema)
4. [Requisitos del Sistema](#requisitos-del-sistema)
5. [Instalación y Configuración](#instalación-y-configuración)
6. [Guía de Usuario](#guía-de-usuario)
7. [Documentación Técnica](#documentación-técnica)
8. [Pruebas Unitarias](#pruebas-unitarias)
9. [Estructura de Archivos](#estructura-de-archivos)
10. [FAQ](#faq)

---

## Descripción General

**worldVenture** es una aplicación educativa que gamifica el aprendizaje de vocabulario en inglés mediante:

- 🗺️ **Navegación en Laberinto**: El jugador se mueve por mapas de diferentes dificultades
- ❓ **Preguntas Trivia**: Se encuentran preguntas al alcanzar celdas específicas
- 💯 **Sistema de Puntuación**: Gana puntos por respuestas correctas
- ❤️ **Sistema de Vidas**: 3 vidas iniciales, pierde una por respuesta incorrecta
- 🎯 **Múltiples Niveles**: Básico, Intermedio y Avanzado con complejidad progresiva

### Objetivo del Juego

Navegar desde la posición de inicio (esquina superior izquierda) hasta la meta (esquina inferior derecha), respondiendo correctamente las preguntas que encuentres en el camino, sin perder todas tus vidas.

---

## Características

### ✨ Características Principales

1. **Cuatro Niveles de Dificultad con Mapas Dinámicos**
   - **Básico** (8×8): Introducción al juego, preguntas y salida aleatoria
   - **Intermedio** (16×16): Mapas más grandes con paredes y obstáculos aleatorios
   - **Avanzado** (32×32): Mayor complejidad con múltiples obstáculos aleatorios
   - **Leyenda** (64×64): Nivel épico con máxima complejidad y desafío

2. **Sistema de Validación Robusto**
   - Validación de nombres de jugadores
   - Validación de selección de niveles
   - Validación de movimientos en el mapa
   - Verificación de condiciones de inicio del juego
   - Validación case-insensitive de respuestas

3. **Mecánica de Juego Dinámica**
   - Movimiento con teclas **W/A/S/D o Flechas del teclado** ↑↓←→
   - **Laberinto generado mediante algoritmo DFS** (Depth-First Search) - único en cada partida
   - **Preguntas y datos curiosos ubicados aleatoriamente** (20% del mapa preguntas, 10% datos)
   - **Respuesta correcta mostrada al fallar** para retroalimentación inmediata
   - Deducción de 1 vida por respuesta incorrecta
   - Incremento de 10 puntos por respuesta correcta
   - Sistema de validación que ignora mayúsculas/minúsculas

4. **Interfaz Gráfica Moderna (GUI)**
   - Interfaz gráfica Swing que visualiza el mapa en tiempo real
   - Soporte para teclado (W/A/S/D y Flechas ↑↓←→)
   - Dialogs interactivos para preguntas de opción múltiple y texto libre
   - Visualización clara del estado del jugador (posición, vidas, puntaje)
   - Menú de selección de nivel integrado
   - Celdas codificadas por color: Inicio 🟢 | Meta 🔴 | Preguntas 🟡 | Datos 🟠

5. **Sistema de Preguntas y Datos Educativos**
   - **Basico**: 40+ preguntas (inglés básico, matemáticas, curiosidades)
   - **Intermedio**: 50+ preguntas (verbos, sustantivos, matemáticas avanzada)
   - **Avanzado**: 50+ preguntas (phrasal verbs, trigonometría, física, historia)
   - **Leyenda**: 40+ preguntas (vocabulario experto, matemáticas especializadas)
   - Las preguntas se seleccionan **aleatoriamente sincronizadas con sus respuestas**
   - Datos curiosos educativos sobre ciencia, historia, geografía y cultura

---

## Arquitectura del Sistema

### Diagrama de Componentes

```
worldVenture/
├── Módulo Principal (app)
│   └── Main.java                 [Punto de entrada - versión consola]
│
├── Interfaz Gráfica (gui)
│   ├── GameWindow.java           [Ventana principal de la GUI]
│   └── GamePanel.java            [Panel de renderizado del mapa]
│
├── Modelos de Datos (modelos)
│   ├── Jugador.java              [Estado del jugador]
│   ├── Juego.java                [Lógica central del juego]
│   ├── Posicion.java             [Coordenadas en el mapa]
│   ├── Direccion.java            [Enumeración de movimientos]
│   ├── Celda.java                [Tipos de celdas del mapa]
│   ├── Pregunta.java             [Estructura de preguntas]
│   ├── BancoPreguntas.java       [Gestor de preguntas aleatorias sincronizadas]
│   ├── GeneradorLaberinto.java   [Generador de laberintos DFS]
│   ├── GeneradorMapas.java       [Generador de mapas aleatorios]
│   ├── DatoCurioso.java          [Datos educativos interactivos]
│   └── Nivel.java                [Interfaz para niveles]
│
├── Implementaciones de Niveles (niveles)
│   ├── Basico.java               [Nivel 1: 8×8, 12 preguntas + 6 datos]
│   ├── Intermedio.java           [Nivel 2: 16×16, 51 preguntas + 25 datos]
│   ├── Avanzado.java             [Nivel 3: 32×32, 204 preguntas + 102 datos]
│   └── Leyenda.java              [Nivel 4: 64×64, 819 preguntas + 409 datos]
│
└── Utilidades (validadores)
    └── Validador.java            [Validaciones del sistema]
```

### Patrones de Diseño Utilizados

1. **Pattern Strategy**: La interfaz `Nivel` permite diferentes implementaciones de niveles
2. **Pattern Singleton Implícito**: `Validador` con métodos estáticos
3. **Pattern Pair**: `BancoPreguntas` mantiene pares sincronizados de pregunta-respuesta
4. **Encapsulation**: Clases bien encapsuladas con getters/setters
5. **Composition**: `Juego` compone `Jugador` y `Nivel`

### Algoritmos Clave

1. **Depth-First Search (DFS)** - Generación de Laberintos
   - Implementado en `GeneradorLaberinto.java`
   - Crea caminos conectados sin ciclos
   - Garantiza soluciones únicas

2. **Sincronización Pregunta-Respuesta**
   - Implementado en `BancoPreguntas.java` con clase interna `ParPreguntaRespuesta`
   - Asegura que cada pregunta siempre tenga su respuesta correcta asociada
   - Usa mismos índices aleatorios para ambas colecciones

---

## Requisitos del Sistema

### Software Requerido

- **Java Development Kit (JDK)** versión 21 o superior (requerido para Swing)
- **Terminal/Consola** con soporte UTF-8
- **Soporte gráfico**: X11, Wayland, Quartz (macOS) o Windows Graphics
- Espacio en disco: ~5 MB

### Compatibilidad

- ✅ macOS (Big Sur y posteriores)
- ✅ Linux (Ubuntu, Debian, CentOS)
- ✅ Windows (10/11 con PowerShell o CMD)

### Dependencias

- **JUnit 4** (opcional, solo para ejecutar pruebas unitarias)
- Ningunas otras dependencias externas

---

## Instalación y Configuración

### Paso 1: Obtener el Código

```bash
# Clonar el repositorio
git clone https://github.com/santirepo13/worldVenture.git
cd worldVenture

# Guardar ruta del proyecto en variable de entorno
export worldVenture_HOME="$(pwd)"
```

### Paso 2: Compilar el Proyecto

```bash
# Navegar al directorio del proyecto
cd "$worldVenture_HOME"

# Crear carpeta de compilación si no existe
mkdir -p bin

# Compilar todos los archivos
javac -d bin \
  src/module-info.java \
  src/modelos/*.java \
  src/niveles/*.java \
  src/validadores/Validador.java \
  src/gui/*.java \
  src/app/Main.java

# Si la compilación es exitosa, verás: ✓ Compilación exitosa
echo "✓ Compilación completada"
```

### Paso 3: Ejecutar la Aplicación

Tienes dos opciones para ejecutar el juego:

**Opción A: Interfaz Gráfica (Recomendado)**
```bash
# Navegar al directorio del proyecto
cd "$worldVenture_HOME"

# Ejecutar el juego con GUI
java -cp bin gui.GameWindow
```

**Opción B: Versión en Consola**
```bash
# Navegar al directorio del proyecto
cd "$worldVenture_HOME"

# Ejecutar el juego en consola
java -cp bin app.Main
```

### Comando Rápido (One-liner)

```bash
# Si ya estás en el directorio worldVenture
mkdir -p bin && javac -d bin src/**/*.java && java -cp bin app.Main
```

### Instalación de JUnit (Opcional)

Si deseas ejecutar las pruebas unitarias:

```bash
# Descargar JUnit 4
# Opción 1: Usando Maven
mvn dependency:copy-dependencies

# Opción 2: Descargar manualmente desde
# https://github.com/junit-team/junit4/releases
```

---

## Guía de Usuario

### 🎮 Inicio del Juego

1. **Ejecuta la aplicación**:
   ```bash
   java -cp bin app.Main
   ```

2. **Ingresa tu nombre** (máximo 20 caracteres, solo letras, números y espacios):
   ```
   worldVenture
   Ingrese nombre: Juan 123
   ```

3. **Selecciona el nivel** (1-4 o nombre completo):
   ```
   Seleccione nivel:
     1) Basico
     2) Intermedio
     3) Avanzado
     4) Leyenda
   Ingrese 1/2/3/4 o escriba el nombre del nivel: 1
   ```

### 🎯 Controles del Juego

**Versión Gráfica (GUI):**
| Control | Acción |
|---------|--------|
| **W** o **↑** | Mover arriba |
| **A** o **←** | Mover izquierda |
| **S** o **↓** | Mover abajo |
| **D** o **→** | Mover derecha |
| **Mouse** | Interactuar con diálogos de preguntas |

**Versión en Consola:**
| Tecla | Acción |
|-------|--------|
| **W** | Mover arriba |
| **A** | Mover izquierda |
| **S** | Mover abajo |
| **D** | Mover derecha |

### 📊 Estados del Juego

**Durante el Juego:**
```
Posición: (0,0) Vidas: 3 Puntaje: 0
Mover (W/A/S/D o Flechas): w
```

**Al Encontrar una Pregunta:**
```
✔️ Respuesta Correcta
¡Correcto! +10 puntos

❌ Respuesta Incorrecta
Incorrecto. Pierdes una vida.
Respuesta correcta: cat
```

**Victoria:**
```
¡Ganaste! Llegaste a la meta.
Puntaje Final: 50
Vidas Restantes: 2
```

**Derrota:**
```
¡Perdiste! No te quedan vidas.
Puntaje Final: 20
```

### 💡 Estrategias de Juego

1. **Nivel Básico (8×8)**: 12 preguntas + 6 datos curiosos, ideal para aprender
2. **Nivel Intermedio (16×16)**: 51 preguntas + 25 datos, requiere planificación
3. **Nivel Avanzado (32×32)**: 204 preguntas + 102 datos, necesita estrategia
4. **Nivel Leyenda (64×64)**: 819 preguntas + 409 datos, máximo desafío

**Consejos:** 
- Las preguntas están distribuidas dinámicamente (20% del mapa)
- Los datos curiosos ocupan el 10% del mapa y son solo para aprender
- Las respuestas son insensibles a mayúsculas/minúsculas
- El laberinto es único en cada partida (generado con algoritmo DFS)

### ⚠️ Validaciones de Entrada

El sistema rechazará:
- Nombres vacíos o solo espacios
- Nombres con más de 20 caracteres
- Nombres con caracteres especiales (!@#$%^&*)
- Selecciones de nivel inválidas
- Comandos de movimiento no reconocidos (solo W/A/S/D)

---

## Documentación Técnica

### 📦 Descripción de Clases

#### `modelos.Jugador`

Representa el estado del jugador en la partida.

**Atributos:**
- `nombre: String` - Nombre del jugador (1-20 caracteres)
- `vidas: int` - Número de vidas (iniciales: 3)
- `puntaje: int` - Puntuación acumulada (inicial: 0)
- `nivel: String` - Nombre del nivel (Basico/Intermedio/Avanzado)
- `posicion: Posicion` - Ubicación actual en el mapa

**Métodos Principales:**
```java
// Movimiento
void mover(Direccion d)              // Mueve al jugador en dirección d

// Puntuación
void sumarPuntos(int pts)            // Suma puntos (solo positivos)
void perderVida()                    // Reduce vidas en 1

// Consultas
boolean estaVivo()                   // Retorna si vidas > 0
String getNombre()                   // Obtiene nombre
int getVidas()                       // Obtiene vidas
int getPuntaje()                     // Obtiene puntaje
Posicion getPosicion()               // Obtiene posición
```

**Ejemplo de Uso:**
```java
Jugador j = new Jugador("Alice", "Basico");
j.setPosicion(new Posicion(0, 0));
j.sumarPuntos(10);
j.mover(Direccion.ARRIBA);
if (j.estaVivo()) {
    System.out.println("Puntaje: " + j.getPuntaje());
}
```

#### `modelos.Posicion`

Representa una coordenada (fila, columna) en el mapa.

**Atributos:**
- `fila: int` - Índice de fila (base 0)
- `columna: int` - Índice de columna (base 0)

**Métodos Principales:**
```java
// Movimiento
Posicion mover(Direccion d)          // Retorna nueva Posicion desplazada

// Consultas
int getFila()                        // Obtiene fila
int getColumna()                     // Obtiene columna

// Utilidades
boolean equals(Object o)             // Compara por coordenadas
String toString()                    // Retorna "(fila,columna)"
```

**Ejemplo de Uso:**
```java
Posicion p = new Posicion(0, 0);
Posicion p2 = p.mover(Direccion.DERECHA);  // (0, 1)
Posicion p3 = p2.mover(Direccion.ABAJO);   // (1, 1)
System.out.println(p3);  // "(1,1)"
```

#### `modelos.Juego`

Encapsula la lógica principal del juego.

**Atributos:**
- `jugador: Jugador` - Jugador actual
- `nivel: Nivel` - Nivel en ejecución
- `mapa: Celda[][]` - Matriz del mapa
- `preguntas: Pregunta[]` - Preguntas del nivel
- `enEjecucion: boolean` - Estado del juego
- `indicePreguntaActual: int` - Índice de pregunta actual

**Métodos Principales:**
```java
// Ciclo de vida
boolean puedeIniciar()               // Verifica condiciones iniciales
void iniciar()                       // Inicia la partida
void terminar()                      // Finaliza la partida
boolean estaEnEjecucion()            // Retorna estado

// Mecánica
boolean moverJugador(Direccion d)    // Mueve jugador (valida límites)
boolean procesarRespuesta(int indice) // Procesa respuesta a pregunta
Pregunta obtenerPreguntaActual()     // Obtiene pregunta activa

// Consultas
Celda[][] obtenerMapa()             // Retorna mapa
Jugador getJugador()                 // Retorna jugador
Nivel getNivel()                     // Retorna nivel
boolean jugadorEnMeta()              // Verifica si está en meta
```

**Ejemplo de Uso:**
```java
Juego juego = new Juego(jugador, new Basico());
if (juego.puedeIniciar()) {
    juego.iniciar();
    while (juego.estaEnEjecucion()) {
        juego.moverJugador(Direccion.DERECHA);
        if (juego.jugadorEnMeta()) {
            juego.terminar();
        }
    }
}
```

#### `modelos.Pregunta`

Representa una pregunta con opciones y puntos.

**Atributos:**
- `enunciado: String` - Texto de la pregunta
- `opciones: String[]` - Opciones de respuesta
- `indiceCorrecto: int` - Índice de respuesta correcta
- `puntos: int` - Puntos por responder correctamente

**Métodos Principales:**
```java
// Validación
boolean verificarRespuesta(int indice) // Verifica si es correcta

// Consultas
String getEnunciado()                // Obtiene enunciado
String[] getOpciones()               // Obtiene opciones
int getIndiceCorrecto()              // Obtiene índice correcto
int getPuntos()                      // Obtiene puntos
```

**Ejemplo de Uso:**
```java
Pregunta q = new Pregunta(
    "¿Cuál es la capital de Francia?",
    new String[]{"París", "Londres", "Berlín"},
    0,  // Índice correcto
    10  // Puntos
);
boolean correcto = q.verificarRespuesta(0);  // true
```

#### `modelos.BancoPreguntas`

Gestor centralizado de preguntas con selección aleatoria.

**Atributos:**
- `preguntas: List<Pregunta>` - Lista de todas las preguntas del nivel
- `random: Random` - Generador de números aleatorios

**Métodos Principales:**
```java
// Administración
void agregarPregunta(String enunciado, String[] opciones, 
                     int indiceCorrecto, int puntos, String respuestaEsperada)

// Selección aleatoria
Pregunta[] obtenerPreguntasAleatorias(int cantidad)  // Retorna preguntas random
String[] obtenerRespuestasAleatorias(int cantidad)   // Retorna respuestas esperadas
```

**Características:**
- Utiliza `Collections.shuffle()` para garantizar aleatoriedad
- Las preguntas se seleccionan sin reemplazo
- Cada instancia de nivel tiene su propio banco

#### `modelos.GeneradorMapas`

Genera mapas dinámicos con colocación aleatoria de preguntas y salida.

**Métodos Estáticos:**

```java
// Generación de mapas
static Celda[][] generarMapaAleatorio(int tamaño, int numParedes, 
                                       int numPreguntas, Random random)
    // Parámetros:
    //   - tamaño: dimensiones del mapa (e.g., 8, 16, 32, 64)
    //   - numParedes: cantidad de paredes a colocar
    //   - numPreguntas: cantidad de preguntas a colocar
    //   - random: generador de números aleatorios
    // Retorna: mapa con inicio (0,0), salida aleatoria, preguntas aleatorias
```

**Características:**
- Inicio siempre en (0,0)
- Salida colocada aleatoriamente en el mapa
- Preguntas distribuidas aleatoriamente
- Paredes distribuidas aleatoriamente (en niveles superiores)
- Garantiza que no haya colisiones de elementos

#### `validadores.Validador`

Utilidades estáticas para validación.

**Métodos Estáticos:**

```java
// Validación de entrada
static boolean nombreJugadorValido(String nombre)
    // Reglas: No null, 1-20 chars, [A-Za-z0-9 ]+
    // Ejemplo: "Alice 123" ✓, "Bob@" ✗

static boolean seleccionNivelValida(String nivel)
    // Acepta: "Basico", "Intermedio", "Avanzado", "Leyenda"
    //         "beginner", "intermediate", "advanced", "legend"
    // Ejemplo: "intermedio" ✓, "expert" ✗

static boolean puedeMover(Celda[][] mapa, Posicion desde, Direccion d)
    // Verifica limites y obstáculos
    // Retorna false si hay pared o fuera de límites

static boolean puedeIniciarJuego(Jugador j, String[] preguntas)
    // Verifica: jugador válido, nombre válido, preguntas > 0
```

**Ejemplo de Uso:**
```java
if (Validador.nombreJugadorValido("Juan")) {
    System.out.println("Nombre válido");
}

if (Validador.puedeMover(mapa, posicion, Direccion.DERECHA)) {
    jugador.mover(Direccion.DERECHA);
}
```

#### `modelos.Nivel` (Interfaz)

Define contrato para implementaciones de niveles.

**Métodos:**
```java
String nombreNivel()              // "Basico", "Intermedio", "Avanzado"
Celda[][] crearMapa()            // Retorna matriz del mapa
Pregunta[] obtenerPreguntas()     // Retorna preguntas del nivel
String[] obtenerRespuestasEsperadas() // Retorna respuestas en minúsculas
```

#### `niveles.Basico` / `niveles.Intermedio` / `niveles.Avanzado`

Implementaciones concretas de `Nivel`.

**Características:**

| Aspecto | Basico | Intermedio | Avanzado | Leyenda |
|---------|--------|-----------|----------|---------|
| Tamaño | 8×8 | 16×16 | 32×32 | 64×64 |
| Preguntas | 3 aleatorias | 3 aleatorias | 3 aleatorias | 5 aleatorias |
| Banco de Preguntas | 19 | 17 | 16 | 25+ |
| Paredes | 0 | Aleatorias | Aleatorias | Aleatorias |
| Puntos por Pregunta | 10 | 12 | 15 | 20 |
| Dificultad Trivia | Básica | Media | Alta | Experto |
| Posición Salida | Aleatoria | Aleatoria | Aleatoria | Aleatoria |

### 🔄 Flujo de Ejecución

```
INICIO
  │
  ├─→ Validar nombre del jugador
  │
  ├─→ Seleccionar nivel
  │
  ├─→ Crear instancias de Juego
  │
  ├─→ Verificar si puede iniciar
  │
  ├─→ BUCLE PRINCIPAL
  │    │
  │    ├─→ Mostrar estado (posición, vidas, puntaje)
  │    │
  │    ├─→ Leer comando (W/A/S/D)
  │    │
  │    ├─→ Validar movimiento
  │    │
  │    ├─→ Ejecutar movimiento
  │    │
  │    ├─→ Verificar si en pregunta
  │    │    ├─→ SI: Mostrar pregunta, leer respuesta
  │    │    │    ├─→ Correcta: Sumar puntos
  │    │    │    └─→ Incorrecta: Perder vida, terminar si vidas = 0
  │    │    │
  │    │    └─→ NO: Continuar
  │    │
  │    ├─→ Verificar si en meta o sin vidas
  │    │    ├─→ En meta: Victoria
  │    │    └─→ Sin vidas: Derrota
  │    │
  │    └─→ ¿Continuar? → SI: Volver a BUCLE PRINCIPAL
  │
  └─→ FIN
```

### 🏗️ Dependencias Entre Clases

```
Main
  ├─→ Jugador
  ├─→ Juego (contiene Jugador + Nivel)
  ├─→ Validador (validaciones)
  ├─→ Nivel (interfaz)
  │    ├─→ Basico (usa BancoPreguntas + GeneradorMapas)
  │    ├─→ Intermedio (usa BancoPreguntas + GeneradorMapas)
  │    ├─→ Avanzado (usa BancoPreguntas + GeneradorMapas)
  │    └─→ Leyenda (usa BancoPreguntas + GeneradorMapas)
  ├─→ Posicion
  ├─→ Direccion
  ├─→ Celda
  ├─→ Pregunta
  ├─→ BancoPreguntas
  └─→ GeneradorMapas
```

---

## Pruebas Unitarias

### 📝 Framework de Pruebas

El proyecto utiliza **JUnit 4** para pruebas unitarias.

### 🧪 Ejecutar Pruebas

```bash
# Navegar al directorio del proyecto
cd "$worldVenture_HOME"

# Descargar JUnit 4 (primera vez)
mkdir -p lib
curl -L -o lib/junit-4.13.2.jar \
  https://github.com/junit-team/junit4/releases/download/r4.13.2/junit-4.13.2.jar
curl -L -o lib/hamcrest-core-1.3.jar \
  https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar

# Compilar pruebas
javac -cp "bin:lib/junit-4.13.2.jar" \
  -d bin \
  src/modelos/*Test.java \
  src/validadores/*Test.java \
  src/niveles/*Test.java

# Ejecutar todas las pruebas
java -cp "bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" \
  org.junit.runner.JUnitCore \
  modelos.JuegoTest \
  modelos.PosicionTest \
  modelos.JugadorTest \
  modelos.PreguntaTest \
  validadores.ValidadorTest \
  niveles.NivelTest
```

**Resultado esperado:**
```
OK (178 tests)
```

### 📊 Cobertura de Pruebas

Cada clase tiene pruebas para:

1. **Validador**
   - ✓ Validación de nombres
   - ✓ Validación de niveles
   - ✓ Validación de movimientos
   - ✓ Condiciones de inicio

2. **Posicion**
   - ✓ Creación de posiciones
   - ✓ Movimientos en 4 direcciones
   - ✓ Comparación (equals)
   - ✓ Representación en string

3. **Jugador**
   - ✓ Creación y estado inicial
   - ✓ Movimiento
   - ✓ Suma de puntos
   - ✓ Pérdida de vidas
   - ✓ Verificación de vida

4. **Pregunta**
   - ✓ Creación de preguntas
   - ✓ Verificación de respuestas
   - ✓ Normalización de puntos

5. **Juego**
   - ✓ Inicialización
   - ✓ Estado de ejecución
   - ✓ Movimientos del jugador
   - ✓ Procesamiento de respuestas
   - ✓ Verificación de meta

### ✅ Ejemplo de Prueba

```java
@Test
public void testValidarNombreValido() {
    assertTrue(Validador.nombreJugadorValido("Alice 123"));
    assertTrue(Validador.nombreJugadorValido("Bob"));
}

@Test
public void testValidarNombreInvalido() {
    assertFalse(Validador.nombreJugadorValido(null));
    assertFalse(Validador.nombreJugadorValido(""));
    assertFalse(Validador.nombreJugadorValido("Bob@123"));
}
```

---

## Estructura de Archivos

```
worldVenture/
├── README.md                          # Este archivo
├── src/
│   ├── module-info.java               # Declaración de módulo
│   ├── app/
│   │   └── Main.java                  # Punto de entrada
│   ├── modelos/
│   │   ├── Celda.java                 # Enum de tipos de celda
│   │   ├── Direccion.java             # Enum de direcciones
│   │   ├── Jugador.java               # Clase de jugador
│   │   ├── Juego.java                 # Clase principal de lógica
│   │   ├── JuegoTest.java             # Pruebas unitarias de Juego
│   │   ├── Nivel.java                 # Interfaz de niveles
│   │   ├── Posicion.java              # Clase de coordenadas
│   │   └── Pregunta.java              # Clase de preguntas
│   ├── niveles/
│   │   ├── Basico.java                # Implementación nivel básico
│   │   ├── Intermedio.java            # Implementación nivel intermedio
│   │   └── Avanzado.java              # Implementación nivel avanzado
│   └── validadores/
│       ├── Validador.java             # Utilidades de validación
│       └── ValidadorTest.java         # Pruebas de Validador
├── bin/
│   ├── module-info.class
│   ├── app/
│   │   └── Main.class
│   ├── modelos/
│   │   └── *.class
│   ├── niveles/
│   │   └── *.class
│   └── validadores/
│       └── *.class
└── doc/                                # Documentación generada con JavaDoc
    └── [archivos HTML de javadoc]
```

---

## FAQ

### ❓ Preguntas Frecuentes

**P: ¿Puedo cambiar el nombre del jugador durante el juego?**
R: No, el nombre se establece al inicio. Para cambiar, debes reiniciar la aplicación.

**P: ¿Qué pasa si pierdo todas mis vidas?**
R: El juego termina y se muestra tu puntaje final. Puedes jugar nuevamente iniciando la aplicación.

**P: ¿Las preguntas se repiten en múltiples jugadas?**
R: Sí, las preguntas del nivel son las mismas. Esto permite aprender consistentemente.

**P: ¿Puedo jugar sin responder preguntas?**
R: No es posible evitar preguntas; debes responder cuando las encuentres.

**P: ¿Cuál es el puntaje máximo?**
R: Depende del nivel:
- Basico: 30 puntos (3 preguntas × 10)
- Intermedio: 36 puntos (3 preguntas × 12)
- Avanzado: 45 puntos (3 preguntas × 15)

**P: ¿Se necesita conexión a Internet?**
R: No, la aplicación es completamente offline.

**P: ¿Puedo modificar el código?**
R: Sí, el proyecto es open-source bajo licencia [especificar]. Siéntete libre de hacer fork y contribuir.

**P: ¿Hay un ranking de puntuaciones?**
R: Actualmente no, pero podría añadirse como futura mejora.

**P: ¿Cómo agregar nuevos niveles?**
R: Crea una nueva clase que implemente la interfaz `Nivel` y síguele el patrón de las clases existentes.

**P: ¿Por qué algunas preguntas usan texto libre?**
R: Permite validar respuestas con mayor flexibilidad y refuerza la escritura correcta.

---

## 🔧 Desarrollo y Contribuciones

### Compilar desde Cero

```bash
./clean_and_build.sh  # Script de compilación (si existe)
# O manualmente:
rm -rf bin/
mkdir bin/
javac -d bin src/**/*.java
```

### Generar Documentación JavaDoc

```bash
javadoc -d doc -sourcepath src -subpackages modelos,niveles,validadores,app
```

### Mejorar el Proyecto

Posibles mejoras futuras:
- 🎨 Interfaz gráfica (Swing/JavaFX)
- 💾 Persistencia de puntuaciones
- 🌐 Más idiomas de preguntas
- 🎵 Efectos de sonido
- 👥 Multijugador local
- 🏆 Sistema de logros
- 📊 Estadísticas avanzadas

---

## 📄 Licencia

Especificar licencia del proyecto (MIT, GPL, Apache, etc.)

---

## 👥 Autores y Contacto

- **Desarrollador Principal**: [Nombre]
- **Repositorio**: [https://github.com/santirepo13/worldVenture](https://github.com/santirepo13/worldVenture)
- **Email**: [correo de contacto]

---

## 📞 Soporte

Para reportar bugs o sugerencias:
1. Abre un issue en GitHub
2. Proporciona detalles del problema
3. Incluye versión de Java si es relevante

---

**Última actualización**: 17 de noviembre de 2025

**Versión**: 1.1.0 - Mejoras de Mecánica de Juego

---

