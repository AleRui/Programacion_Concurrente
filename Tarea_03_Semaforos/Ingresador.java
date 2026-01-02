package Tarea_03_Semaforos;

import java.util.Random;

public class Ingresador extends Thread {

    private static int contador_ingresadores = 0;
    private int id_ingresador;

    public Ingresador() {
        id_ingresador = ++contador_ingresadores;
        start();
    }

    private void ingresar() {
        Random numero_random = new Random();

        double cantidad_a_ingresar = numero_random.nextDouble() * 999 + 1;

        int tiempo_de_ingreso = numero_random.nextInt(500 - 25 + 1) + 25;

        try {
            Thread.sleep(tiempo_de_ingreso);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            // Acceso exclusivo a la cuenta
            CuentaBancariaBuffer.getAccesoExclusivo().acquire();
            
            double saldo_anterior = CuentaBancariaBuffer.getSaldo();
            double saldo_nuevo = saldo_anterior + cantidad_a_ingresar;
            CuentaBancariaBuffer.setSaldo(saldo_nuevo);
            
            System.out.println("Ingresador " + id_ingresador + ": Ingresando " + String.format("%.2f", cantidad_a_ingresar) + "€" + 
                             " | Saldo anterior: " + String.format("%.2f", saldo_anterior) + "€" +
                             " | Saldo nuevo: " + String.format("%.2f", saldo_nuevo) + "€");
            
            // Liberar acceso exclusivo
            CuentaBancariaBuffer.getAccesoExclusivo().release();
            
            // Indicar que hay saldo disponible
            CuentaBancariaBuffer.getSaldoDisponible().release();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // while (true) {
        //     ingresar();
        // }
        for (int i = 0; i < 3; i++) {  // Cambiado de while(true) a for con 3 iteraciones
            ingresar();
        }
    }
    
}