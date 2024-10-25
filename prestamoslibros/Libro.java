public class Libro {
    private String id; 
    private String titulo; 

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
