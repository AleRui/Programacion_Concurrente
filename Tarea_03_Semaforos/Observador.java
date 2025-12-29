package Tarea_03_Semaforos;

public class Observador extends Thread {

    public Observador() {
        start();
    }

    private void observar() {
        try {
            // Acceso exclusivo a la cuenta
            CuentaBancariaBuffer.getAccesoExclusivo().acquire();
            
            double saldo_actual = CuentaBancariaBuffer.getSaldo();
            
            System.out.println("Observador: Saldo actual: €" + String.format("%.2f", saldo_actual));
            
            // Liberar acceso exclusivo
            CuentaBancariaBuffer.getAccesoExclusivo().release();
            
            // Esperar un tiempo antes de la siguiente observación
            Thread.sleep(1000);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            observar();
        }
    }
    
}
