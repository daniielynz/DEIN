package model;

import java.util.Objects;

public class Evento {
	String nombre, nombre_deporte, nombre_olimpiada;
	int id_evento, id_olimpiada, id_deporte;
	
	public Evento(String nombre, int id_evento, int id_olimpiada, int id_deporte) {
		this.nombre = nombre;
		this.id_evento = id_evento;
		this.id_olimpiada = id_olimpiada;
		this.id_deporte = id_deporte;
	}
	
	public Evento(String nombre, int id_evento, String nombre_olimpiada, String nombre_deporte) {
		this.nombre = nombre;
		this.id_evento = id_evento;
		this.nombre_olimpiada = nombre_olimpiada;
		this.nombre_deporte = nombre_deporte;
	}
	
	

	@Override
	public String toString() {
		return "Evento [nombre=" + nombre + ", nombre_deporte=" + nombre_deporte + ", nombre_olimpiada="
				+ nombre_olimpiada + ", id_evento=" + id_evento + "]";
	}

	public String getNombre() {
		return nombre;
	}

	public int getId_evento() {
		return id_evento;
	}

	public int getId_olimpiada() {
		return id_olimpiada;
	}

	public int getId_deporte() {
		return id_deporte;
	}
	
	

	public String getNombre_deporte() {
		return nombre_deporte;
	}

	public String getNombre_olimpiada() {
		return nombre_olimpiada;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_deporte, id_evento, id_olimpiada, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return id_deporte == other.id_deporte && id_evento == other.id_evento && id_olimpiada == other.id_olimpiada
				&& Objects.equals(nombre, other.nombre);
	}
	
	
}
