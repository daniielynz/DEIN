package model;

import java.util.Objects;

public class AeropuertoPrivado {
	private int id, numero, NSocios, anioInauguracion, capacidad;
	private String nombre, pais, ciudad, calle;
	
	public AeropuertoPrivado(int id, int numero, int nSocios, int anioInauguracion, int capacidad, String nombre,
			String pais, String ciudad, String calle) {
		this.id = id;
		this.numero = numero;
		NSocios = nSocios;
		this.anioInauguracion = anioInauguracion;
		this.capacidad = capacidad;
		this.nombre = nombre;
		this.pais = pais;
		this.ciudad = ciudad;
		this.calle = calle;
	}
	
	public AeropuertoPrivado(int numero, int nSocios, int anioInauguracion, int capacidad, String nombre,
			String pais, String ciudad, String calle) {
		this.numero = numero;
		NSocios = nSocios;
		this.anioInauguracion = anioInauguracion;
		this.capacidad = capacidad;
		this.nombre = nombre;
		this.pais = pais;
		this.ciudad = ciudad;
		this.calle = calle;
	}

	public int getId() {
		return id;
	}

	public int getNumero() {
		return numero;
	}

	public int getNSocios() {
		return NSocios;
	}

	public int getAnioInauguracion() {
		return anioInauguracion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPais() {
		return pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getCalle() {
		return calle;
	}

	@Override
	public String toString() {
		return "AeropuertoPrivado [id=" + id + ", numero=" + numero + ", NSocios=" + NSocios + ", anioInauguracion="
				+ anioInauguracion + ", capacidad=" + capacidad + ", nombre=" + nombre + ", pais=" + pais + ", ciudad="
				+ ciudad + ", calle=" + calle + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(NSocios, anioInauguracion, calle, capacidad, ciudad, id, nombre, numero, pais);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AeropuertoPrivado other = (AeropuertoPrivado) obj;
		return NSocios == other.NSocios && anioInauguracion == other.anioInauguracion
				&& Objects.equals(calle, other.calle) && capacidad == other.capacidad
				&& Objects.equals(ciudad, other.ciudad) && id == other.id && Objects.equals(nombre, other.nombre)
				&& numero == other.numero && Objects.equals(pais, other.pais);
	}
	
}
