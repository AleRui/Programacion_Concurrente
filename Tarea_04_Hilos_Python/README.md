# Tarea_04_Hilos_Python

Sistema de procesamiento concurrente utilizando hilos en Python para realizar operaciones matemáticas sobre tuplas.

## 📋 Descripción

Este proyecto implementa un sistema de 4 hilos que trabajan concurrentemente:
- Un hilo productor distribuye tuplas a los demás hilos
- Tres hilos consumidores realizan operaciones matemáticas (suma, resta, multiplicación)

El sistema utiliza colas (`Queue`) para la comunicación segura entre hilos y simula concurrencia con retrasos temporales.

## 🏗️ Arquitectura del Proyecto

### Clases Principales

#### `ProducerHilo`
Hilo productor que distribuye las tuplas a los hilos consumidores.

**Responsabilidad:** Enviar cada tupla de la lista a todas las colas de destino, una por una.

**Atributos:**
- `lista_tuplas`: Lista de tuplas a procesar
- `colas_destino`: Lista de colas donde se enviarán las tuplas

**Métodos:**
- `run()`: Distribuye las tuplas y envía señal de finalización (`None`)

#### `OperadorHilo`
Hilo consumidor que realiza operaciones matemáticas sobre las tuplas recibidas.

**Responsabilidad:** Procesar tuplas aplicando una operación matemática específica.

**Atributos:**
- `nombre_operacion`: Nombre descriptivo de la operación (SUMA, RESTA, MULTIPLICACIÓN)
- `cola_entrada`: Cola desde donde recibe las tuplas
- `funcion_operacion`: Función matemática a aplicar

**Métodos:**
- `run()`: Procesa tuplas de la cola hasta recibir señal de fin

### Funciones Principales

#### `sumar(tupla: Tuple[int, int]) -> int`
Realiza la suma de los dos elementos de la tupla.

**Ejemplo:**
```python
sumar((3, 4))  # Retorna: 7
```

#### `restar(tupla: Tuple[int, int]) -> int`
Realiza la resta del primer elemento menos el segundo.

**Ejemplo:**
```python
restar((5, 3))  # Retorna: 2
```

#### `multiplicar(tupla: Tuple[int, int]) -> int`
Realiza la multiplicación de los dos elementos de la tupla.

**Ejemplo:**
```python
multiplicar((4, 5))  # Retorna: 20
```

#### `main()`
Función principal que coordina la creación e inicialización de todos los hilos.

**Flujo de ejecución:**
1. Define la lista de tuplas a procesar
2. Crea las colas de comunicación
3. Instancia los 4 hilos (1 productor + 3 operadores)
4. Inicia todos los hilos
5. Espera a que todos terminen (`join()`)

## 🚀 Uso

### Requisitos
- Python 3.6 o superior
- Módulos estándar: `threading`, `time`, `queue`, `typing`

### Ejecución

```bash
python main.py
```

### Ejemplo de Salida

```
=== Iniciando sistema de procesamiento concurrente ===

[Hilo ID: 123145483264000] SUMA: 1 y 2 = 3
[Hilo ID: 123145488519168] RESTA: 1 y 2 = -1
[Hilo ID: 123145493774336] MULTIPLICACIÓN: 1 y 2 = 2
[Hilo ID: 123145483264000] SUMA: 2 y 3 = 5
[Hilo ID: 123145488519168] RESTA: 2 y 3 = -1
[Hilo ID: 123145493774336] MULTIPLICACIÓN: 2 y 3 = 6
[Hilo ID: 123145483264000] SUMA: 3 y 4 = 7
[Hilo ID: 123145488519168] RESTA: 3 y 4 = -1
[Hilo ID: 123145493774336] MULTIPLICACIÓN: 3 y 4 = 12
...

=== Procesamiento completado ===
```

## 🎯 Características Técnicas

### Concurrencia
- Utiliza `threading.Thread` para crear hilos
- Implementa `Queue` para comunicación thread-safe
- Delay de 0.5 segundos entre operaciones para simular trabajo

### Principios de Diseño

#### SOLID
- **Single Responsibility:** Cada clase tiene una única responsabilidad bien definida
- **Open/Closed:** Fácil de extender con nuevas operaciones sin modificar código existente
- **Liskov Substitution:** Correcta herencia de `threading.Thread`
- **Interface Segregation:** Interfaces simples y específicas
- **Dependency Inversion:** Las operaciones se inyectan como funciones

#### KISS (Keep It Simple, Stupid)
- Código minimalista y directo
- Sin abstracciones innecesarias
- Fácil de leer y mantener

### Convenciones de Código
- Variables en español con formato `snake_case`
- Docstrings en todas las funciones y clases
- Type hints para mayor claridad

## 📊 Datos de Entrada

Lista de tuplas procesada por defecto:

```python
[
    (1, 2),
    (2, 3),
    (3, 4),
    (4, 5),
    (5, 6),
    (6, 7),
    (7, 8),
    (8, 9),
    (9, 10)
]
```

Cada tupla se procesa mediante las tres operaciones (suma, resta, multiplicación).

## 🔧 Personalización

Para añadir una nueva operación matemática:

1. Crea la función de operación:
```python
def dividir(tupla: Tuple[int, int]) -> float:
    return tupla[0] / tupla[1]
```

2. Crea una nueva cola y hilo en `main()`:
```python
cola_division = Queue()
hilo_division = OperadorHilo("DIVISIÓN", cola_division, dividir)
```

3. Añade la cola a `colas_destino` e inicia el hilo.

## 👨‍💻 Autor

Proyecto desarrollado como parte del Grado en Diseño de Software - España

## 📝 Licencia

Proyecto educativo - Uso libre para fines académicos