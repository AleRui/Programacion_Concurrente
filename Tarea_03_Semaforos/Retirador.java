package Tarea_03_Semaforos;

import java.util.Random;

public class Retirador extends Thread {

    private static int contador_retiradores = 0;
    private int id_retirador;
    
    public Retirador() {
        id_retirador = ++contador_retiradores;
        start();
    }

    private void retirar() {
        Random numero_random = new Random();

        double cantidad_a_retirar = numero_random.nextDouble() * 999 + 1;

        int tiempo_de_retiro = numero_random.nextInt(500 - 25 + 1) + 25;

        try {
            Thread.sleep(tiempo_de_retiro);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            // Esperar a que haya saldo disponible
            CuentaBancariaBuffer.getSaldoDisponible().acquire();
            
            // Acceso exclusivo a la cuenta
            CuentaBancariaBuffer.getAccesoExclusivo().acquire();
            
            double saldo_anterior = CuentaBancariaBuffer.getSaldo();
            
            // Si no hay suficiente saldo, solo retirar lo que hay
            if (cantidad_a_retirar > saldo_anterior) {
                cantidad_a_retirar = saldo_anterior;
            }
            
            double saldo_nuevo = saldo_anterior - cantidad_a_retirar;
            CuentaBancariaBuffer.setSaldo(saldo_nuevo);
            
            System.out.println("Retirador " + id_retirador + ": Retirando " + String.format("%.2f", cantidad_a_retirar) + "€" + 
                             " | Saldo anterior: " + String.format("%.2f", saldo_anterior) + "€" +
                             " | Saldo nuevo: " + String.format("%.2f", saldo_nuevo) + "€");
            
            // Liberar acceso exclusivo
            CuentaBancariaBuffer.getAccesoExclusivo().release();
            
            // Si todavía hay saldo, devolver el permiso
            if (saldo_nuevo > 0) {
                CuentaBancariaBuffer.getSaldoDisponible().release();
            }
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            retirar();
        }
    }
}