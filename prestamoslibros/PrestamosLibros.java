public class PrestamosLibros {
    public static int NUMERO_LIBROS = 9;
    public static int NUMERO_ESTUDIANTES = 4;

    public static void main(String[] args) {
        // Crear los libros
        Libro[] libros = new Libro[NUMERO_LIBROS];
        libros[0] = new Libro("977-9-452-000", "El Quijote");
        libros[1] = new Libro("978-3-16-148410-0", "Cien años de soledad");
        libros[2] = new Libro("978-0-7432-7356-5", "El túnel");
        libros[3] = new Libro("978-0-452-28423-4", "1984");
        libros[4] = new Libro("978-0-15-100031-8", "Moby Dick");
        libros[5] = new Libro("978-0-14-044913-6", "Crimen y castigo");
        libros[6] = new Libro("978-0-19-953556-9", "Orgullo y prejuicio");
        libros[7] = new Libro("978-0-06-112008-4", "To Kill a Mockingbird");
        libros[8] = new Libro("978-0-452-28423-4", "La casa de los espíritus");

        // Crear el gestor de préstamos
        GestorPrestamos gestor = new GestorPrestamos(NUMERO_LIBROS);

        // Crear estudiantes
        Thread estudiantes[] = new Thread[NUMERO_ESTUDIANTES];
        estudiantes[0] = new Thread(new Estudiante("Simon", gestor.getSemaphore(), libros));
        estudiantes[1] = new Thread(new Estudiante("Mimi", gestor.getSemaphore(), libros));
        estudiantes[2] = new Thread(new Estudiante("Ramon", gestor.getSemaphore(), libros));
        estudiantes[3] = new Thread(new Estudiante("Juan", gestor.getSemaphore(), libros));

        // Lanzar los estudiantes
        for (Thread estudiante : estudiantes) {
            estudiante.start();
        }

        // Esperar a que terminen todos los estudiantes
        for (Thread estudiante : estudiantes) {
            try {
                estudiante.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

