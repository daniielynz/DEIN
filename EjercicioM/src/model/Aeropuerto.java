package model;

import java.util.Objects;

public class Aeropuerto {
	 int id, anio_inuguracion, capacidad, id_direccion;
	 private String nombre, imagen;
	public Aeropuerto(int id, int anio_inuguracion, int capacidad, int id_direccion, String nombre, String imagen) {
		super();
		this.id = id;
		this.anio_inuguracion = anio_inuguracion;
		this.capacidad = capacidad;
		this.id_direccion = id_direccion;
		this.nombre = nombre;
		this.imagen = imagen;
	}
	@Override
	public String toString() {
		return nombre;
	}
	public int getId() {
		return id;
	}
	public int getAnio_inuguracion() {
		return anio_inuguracion;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public int getId_direccion() {
		return id_direccion;
	}
	public String getNombre() {
		return nombre;
	}
	public String getImagen() {
		return imagen;
	}
	@Override
	public int hashCode() {
		return Objects.hash(anio_inuguracion, capacidad, id, id_direccion, imagen, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aeropuerto other = (Aeropuerto) obj;
		return anio_inuguracion == other.anio_inuguracion && capacidad == other.capacidad && id == other.id
				&& id_direccion == other.id_direccion && Objects.equals(imagen, other.imagen)
				&& Objects.equals(nombre, other.nombre);
	}
	 
}
