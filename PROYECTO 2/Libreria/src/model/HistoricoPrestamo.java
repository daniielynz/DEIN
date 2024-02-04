package model;
/**
 * Clase que representa el historial de un préstamo, con un identificador,
 * información del libro, del alumno y las fechas de préstamo y devolución.
 */
public class HistoricoPrestamo {
    private int id;
    private int id_libro;
    private String dni;
    private String fechaPrestamo;
    private String fechaDevolucion;

    /**
     * Constructor que recibe todos los detalles del historial de préstamo, incluyendo el identificador.
     *
     * @param id              Identificador del historial de préstamo.
     * @param dni             DNI del alumno asociado al préstamo.
     * @param id_libro        Identificador del libro asociado al préstamo.
     * @param fechaPrestamo   Fecha de préstamo del libro.
     * @param fechaDevolucion Fecha de devolución del libro.
     */
    public HistoricoPrestamo(int id, String dni, int id_libro, String fechaPrestamo, String fechaDevolucion) {
        this.id = id;
        this.dni = dni;
        this.id_libro = id_libro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    /**
     * Constructor que recibe detalles del historial de préstamo sin el identificador.
     *
     * @param dni             DNI del alumno asociado al préstamo.
     * @param id_libro        Identificador del libro asociado al préstamo.
     * @param fechaPrestamo   Fecha de préstamo del libro.
     * @param fechaDevolucion Fecha de devolución del libro.
     */
    public HistoricoPrestamo(String dni, int id_libro, String fechaPrestamo, String fechaDevolucion) {
        this.dni = dni;
        this.id_libro = id_libro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    /**
     * Método para obtener el identificador del historial de préstamo.
     *
     * @return Identificador del historial de préstamo.
     */
    public int getId() {
        return id;
    }

    /**
     * Método para establecer el identificador del historial de préstamo.
     *
     * @param id Identificador del historial de préstamo.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método para obtener el DNI del alumno asociado al préstamo.
     *
     * @return DNI del alumno.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Método para establecer el DNI del alumno asociado al préstamo.
     *
     * @param dni DNI del alumno.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Método para obtener el identificador del libro asociado al préstamo.
     *
     * @return Identificador del libro.
     */
    public int getId_libro() {
        return id_libro;
    }

    /**
     * Método para establecer el identificador del libro asociado al préstamo.
     *
     * @param id_libro Identificador del libro.
     */
    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    /**
     * Método para obtener la fecha de préstamo del libro.
     *
     * @return Fecha de préstamo del libro.
     */
    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    /**
     * Método para establecer la fecha de préstamo del libro.
     *
     * @param fechaPrestamo Fecha de préstamo del libro.
     */
    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    /**
     * Método para obtener la fecha de devolución del libro.
     *
     * @return Fecha de devolución del libro.
     */
    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    /**
     * Método para establecer la fecha de devolución del libro.
     *
     * @param fechaDevolucion Fecha de devolución del libro.
     */
    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    /**
     * Método para obtener una representación en cadena del historial de préstamo.
     *
     * @return Representación en cadena del historial de préstamo.
     */
    @Override
    public String toString() {
        return "HistoricoPrestamo [id=" + id + ", dni=" + dni + ", id_libro=" + id_libro + ", fechaPrestamo="
                + fechaPrestamo + ", fechaDevolucion=" + fechaDevolucion + "]";
    }
}

