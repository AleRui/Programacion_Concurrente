package Tarea_03_Semaforos;

import java.util.concurrent.Semaphore;

public class CuentaBancariaBuffer {

    // Saldo de la cuenta bancaria
    private static double saldo = 0.0;
    
    // Semáforo para acceso exclusivo a la cuenta
    private static Semaphore acceso_exclusivo = new Semaphore(1, true);
    
    // Semáforo para controlar que haya saldo disponible
    private static Semaphore saldo_disponible = new Semaphore(0, true);
    
    // Métodos
    public static double getSaldo() {
        return saldo;
    }
    
    public static void setSaldo(double nuevoSaldo) {
        saldo = nuevoSaldo;
    }

    public static Semaphore getAccesoExclusivo() {
        return acceso_exclusivo;
    }

    public static Semaphore getSaldoDisponible() {
        return saldo_disponible;
    }
    
}