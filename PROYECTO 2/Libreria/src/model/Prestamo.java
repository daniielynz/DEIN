package model;

public class Prestamo {
	int id, codigoLibro;
	String dni, fechaPrestamo;
	
	public Prestamo(int id, String dni, int codigo_libro, String fechaPrestamo) {
		this.id = id;
		this.dni = dni;
		this.fechaPrestamo = fechaPrestamo;
		this.codigoLibro = codigo_libro;
	}
	
	public Prestamo(String dni, int codigo_libro, String fechaPrestamo) {
		this.dni = dni;
		this.fechaPrestamo = fechaPrestamo;
		this.codigoLibro = codigo_libro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public int getCodigoLibro() {
		return codigoLibro;
	}

	public void setCodigoLibro(int codigo_libro) {
		this.codigoLibro = codigo_libro;
	}

	@Override
	public String toString() {
		return "Prestamo [id=" + id + ", dni=" + dni + ", fechaPrestamo=" + fechaPrestamo
				+ ", codigo Libro=" + codigoLibro + "]";
	}
	
	
	
	
}
