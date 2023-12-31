package model;

import java.util.Objects;

public class Olimpiada {
	int id_olimpiada, anio;
	String nombre, ciudad, temporada;
	
	public Olimpiada(int id_olimpiada, int anio, String nombre, String ciudad, String temporada) {
		this.id_olimpiada = id_olimpiada;
		this.anio = anio;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.temporada = temporada;
	}

	public int getId_olimpiada() {
		return id_olimpiada;
	}

	public int getAnio() {
		return anio;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getTemporada() {
		return temporada;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anio, ciudad, id_olimpiada, nombre, temporada);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Olimpiada other = (Olimpiada) obj;
		return anio == other.anio && Objects.equals(ciudad, other.ciudad) && id_olimpiada == other.id_olimpiada
				&& Objects.equals(nombre, other.nombre) && Objects.equals(temporada, other.temporada);
	}

	@Override
	public String toString() {
		return "Olimpiada [id_olimpiada=" + id_olimpiada + ", anio=" + anio + ", nombre=" + nombre + ", ciudad="
				+ ciudad + ", temporada=" + temporada + "]";
	}
	
	
}
