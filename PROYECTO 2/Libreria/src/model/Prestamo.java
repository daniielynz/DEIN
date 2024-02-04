package model;

/**
 * Clase que representa un préstamo con su identificador, DNI del alumno, código del libro y fecha de préstamo.
 */
public class Prestamo {
    private int id;
    private String dni;
    private String fechaPrestamo;
    private int codigoLibro;

    /**
     * Constructor que recibe todos los detalles del préstamo, incluyendo el identificador.
     *
     * @param id            Identificador del préstamo.
     * @param dni           DNI del alumno.
     * @param codigo_libro  Código del libro prestado.
     * @param fechaPrestamo Fecha en la que se realizó el préstamo.
     */
    public Prestamo(int id, String dni, int codigo_libro, String fechaPrestamo) {
        this.id = id;
        this.dni = dni;
        this.fechaPrestamo = fechaPrestamo;
        this.codigoLibro = codigo_libro;
    }

    /**
     * Constructor que recibe detalles del préstamo sin el identificador.
     *
     * @param dni           DNI del alumno.
     * @param codigo_libro  Código del libro prestado.
     * @param fechaPrestamo Fecha en la que se realizó el préstamo.
     */
    public Prestamo(String dni, int codigo_libro, String fechaPrestamo) {
        this.dni = dni;
        this.fechaPrestamo = fechaPrestamo;
        this.codigoLibro = codigo_libro;
    }

    /**
     * Método para obtener el identificador del préstamo.
     *
     * @return Identificador del préstamo.
     */
    public int getId() {
        return id;
    }

    /**
     * Método para establecer el identificador del préstamo.
     *
     * @param id Identificador del préstamo.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método para obtener el DNI del alumno.
     *
     * @return DNI del alumno.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Método para establecer el DNI del alumno.
     *
     * @param dni DNI del alumno.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Método para obtener la fecha de préstamo.
     *
     * @return Fecha de préstamo.
     */
    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    /**
     * Método para establecer la fecha de préstamo.
     *
     * @param fechaPrestamo Fecha de préstamo.
     */
    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    /**
     * Método para obtener el código del libro prestado.
     *
     * @return Código del libro prestado.
     */
    public int getCodigoLibro() {
        return codigoLibro;
    }

    /**
     * Método para establecer el código del libro prestado.
     *
     * @param codigo_libro Código del libro prestado.
     */
    public void setCodigoLibro(int codigo_libro) {
        this.codigoLibro = codigo_libro;
    }

    /**
     * Método para obtener una representación en cadena del préstamo.
     *
     * @return Representación en cadena del préstamo.
     */
    @Override
    public String toString() {
        return "Prestamo " + id;
    }
}

