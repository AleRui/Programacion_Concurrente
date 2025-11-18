import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class server {

    public static void main(String[] args) throws IOException {
        
        // Inicio del ServerSocket. Los puertos válidos son del 0 al 65535.
        ServerSocket server_socket_listener = new ServerSocket(9999);
        System.out.println("Server is running...\n");

        // Bucle de escucha.
        while (true) {
            
            Socket socket = server_socket_listener.accept();

            // Leer la entrada enviada por el cliente.
            BufferedReader input_client = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            int input_client_number = 0;

            String inputString = input_client.readLine();
            System.out.println("Número recibido del cliente: " + inputString + "\n");

            // Crear el string de salida.
            PrintWriter output_server = new PrintWriter(socket.getOutputStream(), true);

            // Comprobar que es número.
            if (!isNumeric(inputString)) {
                String response_invalid = "Error: Entrada no válida. Por favor, introduce un número entero entre 1 y 6.";
                System.out.println("Respuesta a enviar:" + response_invalid + "\n");
                output_server.println(response_invalid);
                continue;
            }
            input_client_number = Integer.parseInt(inputString);

            // Comprovar que el número introducido esta entre 1 y 6.
            if (input_client_number < 1 || input_client_number > 6) {
                String response_out_of_bounds = "Error: Número fuera de rango. Por favor, introduce un número entero entre 1 y 6.";
                System.out.println("Respuesta a enviar:"+ response_out_of_bounds + "\n");
                output_server.println(response_out_of_bounds);
                continue;
            }

            // Generar un numero aleatorio entre 1 y 6.
            Random randomGenerator = new Random();
            int random_number = randomGenerator.nextInt(6) + 1;
            System.out.println("Número aleatorio generado por el servidor: " + random_number + "\n");

            if (input_client_number == random_number) {
                String response_win = "¡Felicidades! Has ganado el sorteo. El número era: " + random_number;
                System.out.println("Respuesta a enviar:"+ response_win + "\n");
                output_server.println(response_win);
            } else {
                String response_lose = "Lo siento, has perdido. El número era: " + random_number;
                System.out.println("Respuesta a enviar:"+ response_lose + "\n");
                output_server.println(response_lose);
            }

            // Cerrar el socket.
            socket.close();
        }

    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}