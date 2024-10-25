public class Libro {
    private String id; // ID del libro
    private String titulo; // Título del libro

    public Libro(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
