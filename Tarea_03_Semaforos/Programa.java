package Tarea_03_Semaforos;

public class Programa {

    // public static final int NUMERO_TOTAL_TRANSACCIONES = 9;

    public static void main(String[] args) {

        // for (int i = 0; i < NUMERO_TOTAL_TRANSACCIONES - 1; i++) {
            // System.out.println("Iniciando transacción número: " + (i + 1));
            new Ingresador();
            new Retirador();
            new Observador();
        // }
        // System.out.println("Programa finalizado.");

    }
}
