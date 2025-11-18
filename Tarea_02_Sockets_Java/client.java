import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class client {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Client is running...");
        // Aquí iría la lógica para conectarse al servidor y enviar/recibir datos

        // Obetener dirección del servidor
        String textoConsolaCogerIPServidor = "Introducir dirección IP del servidor (por defecto es localhost, pulsar intro): ";
        String serverAddress = System.console().readLine(textoConsolaCogerIPServidor);
        if (serverAddress.isEmpty()) {
            serverAddress = "127.0.0.1";
        }

        System.out.println("Conectando al servidor en " + serverAddress + "...\n");

        // Bucle que pide números al server.
        while (true) {
            String textoConsola = """
            Introducir un número entre para participar en el sorteo(o 'exit' para salir)\n
                * Debe ser un número entero.\n
                * El numero debe estar entre 1 y 6.\n
            Introducir número:
            """;
            String console_input = System.console().readLine(textoConsola);
            if (console_input.equalsIgnoreCase("exit")) {
                System.out.println("\nSaliendo del cliente...\n");
                break;
            }
            System.out.println("El número introducido es: " + console_input + "\n");

            // Creamos un socket para enviar el número al servidor.
            Socket socket =  new Socket(serverAddress, 9999);

            // Enviar número al servidor.
            PrintWriter output_client = new PrintWriter(socket.getOutputStream(), true);
            output_client.println(console_input);

            // Recibir respuesta del servidor.
            BufferedReader input_server = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response_server = input_server.readLine();

            // Imprimir respuesta del servidor.
            System.out.println("Respuesta del servidor:\n" + response_server + "\n");

            // Cerrar socket.
            socket.close();
        }
    }
}