package model;

/**
 * Clase que representa a un alumno.
 */
public class Alumno {
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;

    /**
     * Constructor que recibe todos los atributos de un alumno.
     *
     * @param dni       DNI del alumno.
     * @param nombre    Nombre del alumno.
     * @param apellido1 Primer apellido del alumno.
     * @param apellido2 Segundo apellido del alumno.
     */
    public Alumno(String dni, String nombre, String apellido1, String apellido2) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    /**
     * Constructor que recibe el nombre y los apellidos de un alumno.
     *
     * @param nombre    Nombre del alumno.
     * @param apellido1 Primer apellido del alumno.
     * @param apellido2 Segundo apellido del alumno.
     */
    public Alumno(String nombre, String apellido1, String apellido2) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
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
     * @param dni Nuevo DNI del alumno.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Método para obtener el nombre del alumno.
     *
     * @return Nombre del alumno.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para establecer el nombre del alumno.
     *
     * @param nombre Nuevo nombre del alumno.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método para obtener el primer apellido del alumno.
     *
     * @return Primer apellido del alumno.
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Método para establecer el primer apellido del alumno.
     *
     * @param apellido1 Nuevo primer apellido del alumno.
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * Método para obtener el segundo apellido del alumno.
     *
     * @return Segundo apellido del alumno.
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * Método para establecer el segundo apellido del alumno.
     *
     * @param apellido2 Nuevo segundo apellido del alumno.
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * Método para obtener una representación en cadena del alumno.
     *
     * @return Cadena que representa al alumno en el formato "DNI - Nombre".
     */
    @Override
    public String toString() {
        return this.dni + " - " + this.nombre;
    }
}

