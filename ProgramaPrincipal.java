// Interfaz para las operaciones matemáticas (Principio de Segregación de Interfaces - SOLID)
interface OperacionMatematica {
    void ejecutar();
    String obtenerResultado();
}

// Clase para la operación de suma (Principio de Responsabilidad Única - SOLID)
class OperacionSuma implements OperacionMatematica {
    private final double primerNumero;
    private final double segundoNumero;
    private double resultado;
    
    public OperacionSuma(double primerNumero, double segundoNumero) {
        this.primerNumero = primerNumero;
        this.segundoNumero = segundoNumero;
    }
    
    @Override
    public void ejecutar() {
        resultado = primerNumero + segundoNumero;
    }
    
    @Override
    public String obtenerResultado() {
        return String.format("Suma: %.2f + %.2f = %.2f", primerNumero, segundoNumero, resultado);
    }
}

// Clase para la operación de resta
class OperacionResta implements OperacionMatematica {
    private final double primerNumero;
    private final double segundoNumero;
    private double resultado;
    
    public OperacionResta(double primerNumero, double segundoNumero) {
        this.primerNumero = primerNumero;
        this.segundoNumero = segundoNumero;
    }
    
    @Override
    public void ejecutar() {
        resultado = primerNumero - segundoNumero;
    }
    
    @Override
    public String obtenerResultado() {
        return String.format("Resta: %.2f - %.2f = %.2f", primerNumero, segundoNumero, resultado);
    }
}

// Clase para la operación de multiplicación
class OperacionMultiplicacion implements OperacionMatematica {
    private final double primerNumero;
    private final double segundoNumero;
    private double resultado;
    
    public OperacionMultiplicacion(double primerNumero, double segundoNumero) {
        this.primerNumero = primerNumero;
        this.segundoNumero = segundoNumero;
    }
    
    @Override
    public void ejecutar() {
        resultado = primerNumero * segundoNumero;
    }
    
    @Override
    public String obtenerResultado() {
        return String.format("Multiplicación: %.2f × %.2f = %.2f", primerNumero, segundoNumero, resultado);
    }
}

// Clase que representa un hilo de ejecución (KISS - mantiene la lógica simple)
class HiloOperacion extends Thread {
    private final OperacionMatematica operacion;
    private final String nombreHilo;
    private long tiempoEjecucion;
    
    public HiloOperacion(String nombreHilo, OperacionMatematica operacion) {
        super(nombreHilo);
        this.nombreHilo = nombreHilo;
        this.operacion = operacion;
    }
    
    @Override
    public void run() {
        System.out.println(nombreHilo + " iniciado...");
        
        // Iniciar cronómetro del hilo
        long tiempoInicio = System.nanoTime();
        
        operacion.ejecutar();
        
        // Detener cronómetro del hilo
        long tiempoFin = System.nanoTime();
        tiempoEjecucion = tiempoFin - tiempoInicio;
        
        System.out.println(nombreHilo + " completado: " + operacion.obtenerResultado());
        System.out.println(nombreHilo + " - Tiempo de ejecución: " + (tiempoEjecucion / 1_000_000.0) + " ms");
    }
    
    public long obtenerTiempoEjecucion() {
        return tiempoEjecucion;
    }
}

// Programa principal
public class ProgramaPrincipal {
    public static void main(String[] args) {
        java.util.Scanner escaner = new java.util.Scanner(System.in);
        
        System.out.println("=== PROGRAMA DE OPERACIONES MATEMÁTICAS CON HILOS ===\n");
        
        // Solicitar números al usuario
        System.out.print("Introduce el primer número: ");
        double primerNumero = escaner.nextDouble();
        
        System.out.print("Introduce el segundo número: ");
        double segundoNumero = escaner.nextDouble();
        
        System.out.println("\nNúmeros a operar: " + primerNumero + " y " + segundoNumero);
        System.out.println("Iniciando hilos...\n");
        
        // Iniciar cronómetro del proceso completo
        long tiempoInicioProceso = System.nanoTime();
        
        // Crear las operaciones
        OperacionMatematica suma = new OperacionSuma(primerNumero, segundoNumero);
        OperacionMatematica resta = new OperacionResta(primerNumero, segundoNumero);
        OperacionMatematica multiplicacion = new OperacionMultiplicacion(primerNumero, segundoNumero);
        
        // Crear los hilos
        HiloOperacion hilo1 = new HiloOperacion("Thread 1", suma);
        HiloOperacion hilo2 = new HiloOperacion("Thread 2", resta);
        HiloOperacion hilo3 = new HiloOperacion("Thread 3", multiplicacion);
        
        // Iniciar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        
        // Esperar a que todos los hilos terminen
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            System.err.println("Error: Los hilos fueron interrumpidos");
            e.printStackTrace();
        }
        
        // Detener cronómetro del proceso completo
        long tiempoFinProceso = System.nanoTime();
        long tiempoTotalProceso = tiempoFinProceso - tiempoInicioProceso;
        
        System.out.println("\n=== TODOS LOS HILOS HAN FINALIZADO ===");
        System.out.println("\n--- RESUMEN DE TIEMPOS ---");
        System.out.println("Thread 1 (Suma): " + (hilo1.obtenerTiempoEjecucion() / 1_000_000.0) + " ms");
        System.out.println("Thread 2 (Resta): " + (hilo2.obtenerTiempoEjecucion() / 1_000_000.0) + " ms");
        System.out.println("Thread 3 (Multiplicación): " + (hilo3.obtenerTiempoEjecucion() / 1_000_000.0) + " ms");
        System.out.println("\nTiempo total del proceso: " + (tiempoTotalProceso / 1_000_000.0) + " ms");
        
        escaner.close();
    }
}