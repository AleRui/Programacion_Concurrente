public class client {
    public static void main(String[] args) {
        System.out.println("Client is running...");
        // Aquí iría la lógica para conectarse al servidor y enviar/recibir datos

        // Obetener dirección del servidor
        String textoConsolaCogerIPServidor = "Introducir dirección IP del servidor (por defecto es localhost, pulsar intro): ";
        String serverAddress = System.console().readLine(textoConsolaCogerIPServidor);
        if (serverAddress.isEmpty()) {
            serverAddress = "127.0.0.1";
        }

        System.out.println("Conectando al servidor en " + serverAddress + "...\n");

        while (true) {
            String textoConsola = """
            Introducir un número entre para participar en el sorteo(o 'exit' para salir)\n
                * Debe ser un número entero.\n
                * El numero debe estar entre 1 y 6.\n
            Introducir número:
            """;
            String message = System.console().readLine(textoConsola);
            if (message.equalsIgnoreCase("exit")) {
                System.out.println("Saliendo del cliente...");
                break;
            }
            System.out.println("EL número introducir es: " + message + "\n");
        }
    }
}