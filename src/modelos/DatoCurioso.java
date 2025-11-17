package modelos;

/**
 * Representa un dato curioso o información educativa.
 * 
 * Se muestra al jugador cuando pisa una celda de tipo DATO.
 */
public class DatoCurioso {
    private String titulo;
    private String descripcion;
    private String categoria;

    /**
     * Constructor de DatoCurioso.
     * 
     * @param titulo título del dato
     * @param descripcion descripción detallada
     * @param categoria categoría (ej: "Historia", "Ciencia", "Idiomas")
     */
    public DatoCurioso(String titulo, String descripcion, String categoria) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s\n%s", categoria, titulo, descripcion);
    }
}
