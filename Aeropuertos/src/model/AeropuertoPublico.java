package model;

public class AeropuertoPublico {
	private int id, numero, numTrabajadores;
	private String nombre, pais, ciudad, calle, anio, capacidad, financiacion;
	public AeropuertoPublico(int id, int numero, int numTrabajadores, String nombre, String pais, String ciudad,
			String calle, String anio, String capacidad, String financiacion) {
		super();
		this.id = id;
		this.numero = numero;
		this.numTrabajadores = numTrabajadores;
		this.nombre = nombre;
		this.pais = pais;
		this.ciudad = ciudad;
		this.calle = calle;
		this.anio = anio;
		this.capacidad = capacidad;
		this.financiacion = financiacion;
	}
	@Override
	public String toString() {
		return "AeropuertoPublico [id=" + id + ", numero=" + numero + ", numTrabajadores=" + numTrabajadores
				+ ", nombre=" + nombre + ", pais=" + pais + ", ciudad=" + ciudad + ", calle=" + calle + ", anio=" + anio
				+ ", capacidad=" + capacidad + ", financiacion=" + financiacion + "]";
	}
	public int getId() {
		return id;
	}
	public int getNumero() {
		return numero;
	}
	public int getNumTrabajadores() {
		return numTrabajadores;
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
	public String getAnio() {
		return anio;
	}
	public String getCapacidad() {
		return capacidad;
	}
	public String getFinanciacion() {
		return financiacion;
	}
	
	
	
}
