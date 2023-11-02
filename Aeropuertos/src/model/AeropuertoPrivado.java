package model;

public class AeropuertoPrivado {
	private int id, numero, NSocios;
	private String nombre, pais, ciudad, calle, anio, capacidad;
	
	public AeropuertoPrivado(int id, int numero, int nSocios, String nombre, String pais, String ciudad, String calle,
			String anio, String capacidad) {
		this.id = id;
		this.numero = numero;
		NSocios = nSocios;
		this.nombre = nombre;
		this.pais = pais;
		this.ciudad = ciudad;
		this.calle = calle;
		this.anio = anio;
		this.capacidad = capacidad;
	}

	@Override
	public String toString() {
		return "AeropuertoPrivado [id=" + id + ", numero=" + numero + ", NSocios=" + NSocios + ", nombre=" + nombre
				+ ", pais=" + pais + ", ciudad=" + ciudad + ", calle=" + calle + ", anio=" + anio + ", capacidad="
				+ capacidad + "]";
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
	
}
