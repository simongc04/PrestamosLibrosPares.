import java.util.Random;
import java.util.concurrent.Semaphore;

public class Estudiante implements Runnable {
    private static final Random random = new Random();
    private static final int TIEMPO_MIN = 1;
    private static final int TIEMPO_MAX = 3; 
    private static final int DESCANSO_MIN = 1;
    private static final int DESCANSO_MAX = 2; 

    private String nombre;
    private Semaphore semaphore;
    private Libro[] libros;

    public Estudiante(String nombre, Semaphore semaphore, Libro[] libros) {
        this.nombre = nombre;
        this.semaphore = semaphore;
        this.libros = libros;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int libro1Index = random.nextInt(libros.length);
                int libro2Index = random.nextInt(libros.length);
                
                while (libro1Index == libro2Index) {
                    libro2Index = random.nextInt(libros.length);
                }

                // Intentar tomar prestados los libros
                long tiempoEsperaInicio = System.currentTimeMillis();
                semaphore.acquire(2); 
                long tiempoEsperaFin = System.currentTimeMillis();
                long tiempoEspera = (tiempoEsperaFin - tiempoEsperaInicio) / 1000; 

                System.out.println(nombre + " ha tomado prestados los libros: " 
                                   + libros[libro1Index].getId() + " y " 
                                   + libros[libro2Index].getId() + " (esperó " + tiempoEspera + " segundos)");

                // Usar libros por un tiempo aleatorio
                int tiempoUso = TIEMPO_MIN + random.nextInt(TIEMPO_MAX - TIEMPO_MIN + 1);
                System.out.println(nombre + " está usando los libros por " + tiempoUso + " minutos.");
                Thread.sleep(tiempoUso * 1000); 

                // Devolver los libros
                System.out.println(nombre + " ha devuelto los libros: " 
                                   + libros[libro1Index].getId() + " y " 
                                   + libros[libro2Index].getId());

                semaphore.release(2); // Liberar los libros

                // Descansar por un tiempo aleatorio
                int tiempoDescanso = DESCANSO_MIN + random.nextInt(DESCANSO_MAX - DESCANSO_MIN + 1);
                System.out.println(nombre + " descansará por " + tiempoDescanso + " minutos.");
                Thread.sleep(tiempoDescanso * 1000); // Convertir minutos a milisegundos
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
