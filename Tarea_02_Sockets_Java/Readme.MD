# Resumen de server.java y client.java

Estos dos archivos implementan una comunicación cliente-servidor utilizando Sockets en Java.

## server.java
Este archivo implementa el servidor. Sus principales funciones son:
- Crear un `ServerSocket` en un puerto específico y esperar conexiones de clientes.
- Recibir un número enviado por el cliente.
- Validar que el número esté entre 1 y 6.
- Generar un número aleatorio entre 1 y 6.
- Comparar ambos números y enviar al cliente un mensaje indicando si ganó o perdió.
- Manejar errores de entrada y cerrar la conexión tras cada intento.

## client.java
Este archivo implementa el cliente. Sus principales funciones son:
- Solicitar al usuario la dirección IP del servidor (por defecto, localhost).
- Pedir al usuario un número entre 1 y 6 para participar en el sorteo.
- Enviar el número al servidor usando un `Socket`.
- Recibir y mostrar la respuesta del servidor (si ganó, perdió o si hubo un error).
- Permitir al usuario salir escribiendo 'exit'.

Ambos archivos muestran cómo establecer una comunicación básica entre procesos usando Sockets en Java, validando datos y gestionando la interacción entre cliente y servidor.
