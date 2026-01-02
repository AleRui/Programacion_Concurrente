package Tarea_03_Semaforos;

public class Programa {

    public static final int NUMERO_INGRESADORES = 3;
    public static final int NUMERO_RETIRADORES = 3;
    public static final int NUMERO_OBSERVADORES = 3;

    public static void main(String[] args) {

        for (int i = 0; i < NUMERO_INGRESADORES - 1; i++) {
            new Ingresador();
        }

        for (int i = 0; i < NUMERO_RETIRADORES - 1; i++) {
            new Retirador();
        }

        for (int i = 0; i < NUMERO_OBSERVADORES - 1; i++) {
            new Observador();
        }
        
    }
}
