package model;

import java.util.Objects;

public class AeropuertoPublico {
	private int id, numero, anioInauguracion, capacidad, NTrabajadores;
	private float financiacion;
	private String nombre, pais, ciudad, calle;
	
	public AeropuertoPublico(int id, int numero, float financiacion, int NTrabajadores, int anioInauguracion, int capacidad,String nombre, String pais, String ciudad, String calle) {
		this.id = id;
		this.numero = numero;
		this.financiacion = financiacion;
		this.NTrabajadores = NTrabajadores;
		this.anioInauguracion = anioInauguracion;
		this.capacidad = capacidad;
		this.nombre = nombre;
		this.pais = pais;
		this.ciudad = ciudad;
		this.calle = calle;
	}
	

	
	public AeropuertoPublico(int numero, Float financiacion, int NTrabajadores, int anioInauguracion, int capacidad,String nombre, String pais, String ciudad, String calle) {
		this.numero = numero;
		this.financiacion = financiacion;
		this.NTrabajadores = NTrabajadores;
		this.anioInauguracion = anioInauguracion;
		this.capacidad = capacidad;
		this.nombre = nombre;
		this.pais = pais;
		this.ciudad = ciudad;
		this.calle = calle;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(NTrabajadores, anioInauguracion, calle, capacidad, ciudad, financiacion, id, nombre, numero,
				pais);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AeropuertoPublico other = (AeropuertoPublico) obj;
		return NTrabajadores == other.NTrabajadores && anioInauguracion == other.anioInauguracion
				&& Objects.equals(calle, other.calle) && capacidad == other.capacidad
				&& Objects.equals(ciudad, other.ciudad) && financiacion == other.financiacion && id == other.id
				&& Objects.equals(nombre, other.nombre) && numero == other.numero && Objects.equals(pais, other.pais);
	}

	public int getId() {
		return id;
	}

	public int getNumero() {
		return numero;
	}

	public int getAnioInauguracion() {
		return anioInauguracion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public float getFinanciacion() {
		return financiacion;
	}

	public int getNTrabajadores() {
		return NTrabajadores;
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
		return "AeropuertoPublico [id=" + id + ", numero=" + numero + ", anioInauguracion=" + anioInauguracion
				+ ", capacidad=" + capacidad + ", financiacion=" + financiacion + ", NTrabajadores=" + NTrabajadores
				+ ", nombre=" + nombre + ", pais=" + pais + ", ciudad=" + ciudad + ", calle=" + calle + "]";
	}

	
	
}
