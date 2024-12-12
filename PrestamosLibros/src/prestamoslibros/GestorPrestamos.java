import java.util.concurrent.Semaphore;

public class GestorPrestamos {
    private Semaphore prestamos;

    public GestorPrestamos(int numeroLibros) {
        this.prestamos = new Semaphore(2);
    }

    public Semaphore getSemaphore() {
        return prestamos;
    }
}
