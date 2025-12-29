# Sistema de Cuenta Bancaria con Semáforos

## 📋 Descripción

Este programa simula una cuenta bancaria donde múltiples hilos (threads) realizan operaciones concurrentes de forma segura utilizando **semáforos** para la sincronización.

## 🎯 Objetivo

Demostrar el uso de semáforos en Java para resolver problemas de concurrencia, evitando condiciones de carrera cuando múltiples threads acceden a un recurso compartido (el saldo de la cuenta bancaria).

## 🔧 Componentes del Sistema

### 1. **CuentaBancariaBuffer**
- Almacena el saldo de la cuenta bancaria
- Gestiona dos semáforos:
  - `acceso_exclusivo`: Garantiza que solo un thread modifique o lea el saldo a la vez
  - `saldo_disponible`: Controla que haya dinero antes de permitir retiros

### 2. **Ingresador** (Thread)
- Añade cantidades aleatorias de dinero a la cuenta
- Espera tiempos aleatorios entre operaciones
- Muestra el saldo antes y después de cada ingreso

### 3. **Retirador** (Thread)
- Retira cantidades aleatorias de la cuenta
- Solo puede retirar si hay saldo disponible
- Si intenta retirar más de lo disponible, retira todo el saldo
- Muestra el saldo antes y después de cada retiro

### 4. **Observador** (Thread)
- Consulta y muestra el saldo actual cada segundo
- Lee el saldo de forma segura sin interferir con otras operaciones

### 5. **Programa**
- Punto de entrada que inicia los tres threads simultáneamente

## 🚀 Compilación y Ejecución

Desde el directorio padre del paquete:

```bash
# Compilar
javac Tarea_03_Semaforos/*.java

# Ejecutar
java Tarea_03_Semaforos.Programa
```

## 📊 Ejemplo de Salida

```
Sistema de cuenta bancaria iniciado...
Ingresador: Ingresando €523.45 | Saldo anterior: €0.00 | Saldo nuevo: €523.45
Observador: Saldo actual: €523.45
Retirador: Retirando €234.12 | Saldo anterior: €523.45 | Saldo nuevo: €289.33
Observador: Saldo actual: €289.33
Ingresador: Ingresando €678.90 | Saldo anterior: €289.33 | Saldo nuevo: €968.23
```

## 🔐 Sincronización

El programa utiliza semáforos para:

- **Exclusión mutua**: Solo un thread puede acceder al saldo a la vez
- **Coordinación**: El retirador espera hasta que haya saldo disponible
- **Seguridad**: Evita condiciones de carrera y lecturas/escrituras inconsistentes

## 💡 Conceptos Clave

- **Semáforos**: Mecanismo de sincronización que controla el acceso a recursos compartidos
- **Threads**: Hilos de ejecución que corren simultáneamente
- **Sección crítica**: Código que accede al recurso compartido (el saldo)
- **acquire()**: Solicita permiso del semáforo (espera si no hay permisos disponibles)
- **release()**: Libera el semáforo para que otros threads puedan acceder