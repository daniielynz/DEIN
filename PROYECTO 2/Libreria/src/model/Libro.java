package model;

/**
 * Clase que representa un libro con su código, detalles, estado y baja.
 */
public class Libro {
    private int codigo;
    private int baja;
    private String titulo;
    private String autor;
    private String editorial;
    private String estado;

    /**
     * Constructor que recibe todos los detalles del libro, incluyendo el código y baja.
     *
     * @param codigo    Código del libro.
     * @param titulo    Título del libro.
     * @param autor     Autor del libro.
     * @param editorial Editorial del libro.
     * @param estado    Estado del libro.
     * @param baja      Indica si el libro está dado de baja o no.
     */
    public Libro(int codigo, String titulo, String autor, String editorial, String estado, int baja) {
        this.codigo = codigo;
        this.baja = baja;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.estado = estado;
    }

    /**
     * Constructor que recibe detalles del libro sin el código.
     *
     * @param titulo    Título del libro.
     * @param autor     Autor del libro.
     * @param editorial Editorial del libro.
     * @param estado    Estado del libro.
     * @param baja      Indica si el libro está dado de baja o no.
     */
    public Libro(String titulo, String autor, String editorial, String estado, int baja) {
        this.baja = baja;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.estado = estado;
    }

    /**
     * Método para obtener el código del libro.
     *
     * @return Código del libro.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Método para establecer el código del libro.
     *
     * @param codigo Código del libro.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Método para obtener el estado de baja del libro.
     *
     * @return Estado de baja del libro.
     */
    public int getBaja() {
        return baja;
    }

    /**
     * Método para establecer el estado de baja del libro.
     *
     * @param baja Estado de baja del libro.
     */
    public void setBaja(int baja) {
        this.baja = baja;
    }

    /**
     * Método para obtener el título del libro.
     *
     * @return Título del libro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Método para establecer el título del libro.
     *
     * @param titulo Título del libro.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Método para obtener el autor del libro.
     *
     * @return Autor del libro.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Método para establecer el autor del libro.
     *
     * @param autor Autor del libro.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Método para obtener la editorial del libro.
     *
     * @return Editorial del libro.
     */
    public String getEditorial() {
        return editorial;
    }

    /**
     * Método para establecer la editorial del libro.
     *
     * @param editorial Editorial del libro.
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /**
     * Método para obtener el estado del libro.
     *
     * @return Estado del libro.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Método para establecer el estado del libro.
     *
     * @param estado Estado del libro.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Método para obtener una representación en cadena del libro.
     *
     * @return Representación en cadena del libro.
     */
    @Override
    public String toString() {
        return this.codigo + " - " + this.titulo;
    }
}

