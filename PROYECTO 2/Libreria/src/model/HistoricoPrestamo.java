package model;

public class HistoricoPrestamo {
	int id, id_libro;
	String dni, fechaPrestamo, fechaDevolucion;
	
	public HistoricoPrestamo(int id, String dni, int id_libro, String fechaPrestamo, String fechaDevolucion) {
		this.id = id;
		this.dni = dni;
		this.id_libro = id_libro;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
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

	public int getId_libro() {
		return id_libro;
	}

	public void setId_libro(int id_libro) {
		this.id_libro = id_libro;
	}

	public String getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public String getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	@Override
	public String toString() {
		return "HistoricoPrestamo [id=" + id + ", dni=" + dni + ", id_libro=" + id_libro + ", fechaPrestamo="
				+ fechaPrestamo + ", fechaDevolucion=" + fechaDevolucion + "]";
	}
	
	
	
}
