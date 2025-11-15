# Programa de Operaciones Matemáticas con Hilos en Java

## 📋 Resumen

Este programa implementa un sistema de procesamiento concurrente que ejecuta tres operaciones matemáticas (suma, resta y multiplicación) en hilos independientes. El usuario introduce dos números por terminal y el programa calcula las tres operaciones simultáneamente, midiendo el tiempo de ejecución de cada hilo y del proceso completo.

### Características principales:
- ✅ Ejecución concurrente de 3 hilos
- ✅ Entrada de datos por terminal
- ✅ Cronometraje individual de cada hilo
- ✅ Cronometraje del proceso completo
- ✅ Código siguiendo principios SOLID y filosofía KISS
- ✅ Variables y comentarios en español

---

## 🔧 Compilación

Para compilar el programa, abre una terminal en el directorio donde se encuentra el archivo y ejecuta:

```bash
javac ProgramaPrincipal.java
```

Este comando compilará todas las clases contenidas en el archivo, generando los archivos `.class` correspondientes.

---

## ▶️ Ejecución

Una vez compilado, ejecuta el programa con:

```bash
java ProgramaPrincipal
```

El programa te solicitará:
1. El primer número
2. El segundo número

Luego ejecutará las operaciones y mostrará los resultados con los tiempos de ejecución.

### Ejemplo de ejecución:

```
=== PROGRAMA DE OPERACIONES MATEMÁTICAS CON HILOS ===

Introduce el primer número: 15
Introduce el segundo número: 5

Números a operar: 15.0 y 5.0
Iniciando hilos...

Thread 1 iniciado...
Thread 2 iniciado...
Thread 3 iniciado...
Thread 1 completado: Suma: 15.00 + 5.00 = 20.00
Thread 1 - Tiempo de ejecución: 0.123 ms
Thread 2 completado: Resta: 15.00 - 5.00 = 10.00
Thread 2 - Tiempo de ejecución: 0.098 ms
Thread 3 completado: Multiplicación: 15.00 × 5.00 = 75.00
Thread 3 - Tiempo de ejecución: 0.115 ms

=== TODOS LOS HILOS HAN FINALIZADO ===

--- RESUMEN DE TIEMPOS ---
Thread 1 (Suma): 0.123 ms
Thread 2 (Resta): 0.098 ms
Thread 3 (Multiplicación): 0.115 ms

Tiempo total del proceso: 1.456 ms
```

---

## 📚 Estructura del Código

### 1. **Interfaz `OperacionMatematica`**
```java
interface OperacionMatematica
```
- Define el contrato para todas las operaciones matemáticas
- Métodos: `ejecutar()` y `obtenerResultado()`
- Permite aplicar el principio de **Inversión de Dependencias** (SOLID)

---

### 2. **Clases de Operaciones**

#### `OperacionSuma`
- Implementa la operación de suma
- Almacena dos números y calcula su suma
- Responsabilidad única: **sumar dos números**

#### `OperacionResta`
- Implementa la operación de resta
- Almacena dos números y calcula su resta
- Responsabilidad única: **restar dos números**

#### `OperacionMultiplicacion`
- Implementa la operación de multiplicación
- Almacena dos números y calcula su multiplicación
- Responsabilidad única: **multiplicar dos números**

**Características comunes:**
- Todas implementan `OperacionMatematica`
- Usan variables `final` para los números (inmutabilidad)
- Formato de salida consistente con dos decimales

---

### 3. **Clase `HiloOperacion`**
```java
class HiloOperacion extends Thread
```
- Extiende la clase `Thread` para crear hilos de ejecución
- Encapsula una operación matemática
- **Funcionalidad principal:**
  - Ejecuta la operación en un hilo independiente
  - Cronometra el tiempo de ejecución usando `System.nanoTime()`
  - Muestra el resultado y el tiempo transcurrido
  - Permite recuperar el tiempo de ejecución con `obtenerTiempoEjecucion()`

**Flujo del método `run()`:**
1. Anuncia el inicio del hilo
2. Captura el tiempo de inicio
3. Ejecuta la operación matemática
4. Captura el tiempo de fin
5. Calcula y almacena el tiempo de ejecución
6. Muestra el resultado y el tiempo

---

### 4. **Clase `ProgramaPrincipal`**
```java
public class ProgramaPrincipal
```
Es el punto de entrada del programa y orquesta todo el proceso.

**Flujo de ejecución:**

1. **Entrada de datos:**
   - Crea un `Scanner` para leer desde la terminal
   - Solicita y lee el primer número
   - Solicita y lee el segundo número

2. **Cronometraje global:**
   - Inicia el cronómetro del proceso completo con `System.nanoTime()`

3. **Creación de operaciones:**
   - Instancia las tres operaciones con los números introducidos

4. **Creación de hilos:**
   - Crea tres objetos `HiloOperacion`, uno para cada operación

5. **Ejecución paralela:**
   - Inicia los tres hilos con `start()` (se ejecutan concurrentemente)

6. **Sincronización:**
   - Usa `join()` en cada hilo para esperar su finalización
   - Maneja posibles interrupciones con try-catch

7. **Resultados finales:**
   - Detiene el cronómetro global
   - Calcula el tiempo total del proceso
   - Muestra un resumen con todos los tiempos
   - Cierra el `Scanner`

---

## 🎯 Principios de Diseño Aplicados

### SOLID
- **S** - Single Responsibility: Cada clase tiene una única responsabilidad
- **O** - Open/Closed: Abierto a extensión (nuevas operaciones), cerrado a modificación
- **L** - Liskov Substitution: Todas las operaciones son intercambiables
- **I** - Interface Segregation: Interfaz pequeña y específica
- **D** - Dependency Inversion: `HiloOperacion` depende de la abstracción, no de implementaciones concretas

### KISS (Keep It Simple, Stupid)
- Código directo y fácil de entender
- Sin complejidad innecesaria
- Cada método realiza una tarea específica
- Nombres descriptivos en español

---

## ⏱️ Notas sobre el Cronometraje

- **`System.nanoTime()`**: Proporciona precisión de nanosegundos
- **Conversión a milisegundos**: Se divide entre `1_000_000.0` para mejor legibilidad
- **Tiempo del proceso**: Incluye la creación, inicio, ejecución y sincronización de los hilos
- **Tiempo individual**: Solo mide la ejecución de la operación matemática dentro de cada hilo

El tiempo total del proceso suele ser mayor que cualquier tiempo individual porque incluye la sobrecarga de gestión de hilos, pero es menor que la suma de todos los tiempos (gracias a la ejecución paralela).

---

## 🚀 Posibles Extensiones

- Agregar más operaciones (división, potencia, raíz cuadrada)
- Implementar un pool de hilos para mayor eficiencia
- Añadir validación de entrada de datos
- Guardar resultados en un archivo
- Crear una interfaz gráfica (GUI)