"""
Sistema de procesamiento concurrente con hilos en Python.
Proyecto: Tarea_04_Hilos_Python
"""

import threading
import time
from queue import Queue
from typing import Tuple


class ProducerHilo(threading.Thread):
    """Hilo productor que distribuye tuplas a los hilos consumidores."""
    
    def __init__(self, lista_tuplas: list, colas_destino: list):
        super().__init__()
        self.lista_tuplas = lista_tuplas
        self.colas_destino = colas_destino
        
    def run(self):
        """Distribuye las tuplas una por una a cada cola."""
        for tupla in self.lista_tuplas:
            for cola in self.colas_destino:
                cola.put(tupla)
            time.sleep(0.5)
        
        # Enviar señal de fin a todas las colas
        for cola in self.colas_destino:
            cola.put(None)


class OperadorHilo(threading.Thread):
    """Hilo que realiza operaciones matemáticas sobre tuplas."""
    
    def __init__(self, nombre_operacion: str, cola_entrada: Queue, funcion_operacion):
        super().__init__()
        self.nombre_operacion = nombre_operacion
        self.cola_entrada = cola_entrada
        self.funcion_operacion = funcion_operacion
        
    def run(self):
        """Procesa las tuplas de la cola aplicando la operación."""
        while True:
            tupla = self.cola_entrada.get()
            
            if tupla is None:
                break
            
            resultado = self.funcion_operacion(tupla)
            id_hilo = threading.current_thread().ident
            
            print(f"[Hilo ID: {id_hilo}] {self.nombre_operacion}: "
                  f"{tupla[0]} y {tupla[1]} = {resultado}")
            
            time.sleep(0.5)


def sumar(tupla: Tuple[int, int]) -> int:
    """Realiza la suma de los elementos de la tupla."""
    return tupla[0] + tupla[1]


def restar(tupla: Tuple[int, int]) -> int:
    """Realiza la resta de los elementos de la tupla."""
    return tupla[0] - tupla[1]


def multiplicar(tupla: Tuple[int, int]) -> int:
    """Realiza la multiplicación de los elementos de la tupla."""
    return tupla[0] * tupla[1]

# --------------------------------------------------------------

def main():
    """Función principal que coordina la ejecución de los hilos."""
    
    # Lista de tuplas a procesar
    lista_tuplas = [
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
    
    # Crear colas de comunicación
    cola_suma = Queue()
    cola_resta = Queue()
    cola_multiplicacion = Queue()
    
    colas_destino = [cola_suma, cola_resta, cola_multiplicacion]
    
    # Crear hilos
    hilo_productor = ProducerHilo(lista_tuplas, colas_destino)
    hilo_suma = OperadorHilo("SUMA", cola_suma, sumar)
    hilo_resta = OperadorHilo("RESTA", cola_resta, restar)
    hilo_multiplicacion = OperadorHilo("MULTIPLICACIÓN", cola_multiplicacion, multiplicar)
    
    # Iniciar hilos
    print("=== Iniciando sistema de procesamiento concurrente ===\n")
    
    hilo_productor.start()
    hilo_suma.start()
    hilo_resta.start()
    hilo_multiplicacion.start()
    
    # Esperar a que todos los hilos terminen
    hilo_productor.join()
    hilo_suma.join()
    hilo_resta.join()
    hilo_multiplicacion.join()
    
    print("\n=== Procesamiento completado ===")


if __name__ == "__main__":
    main()
