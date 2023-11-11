package model;

import java.util.Objects;

public class Avion {
	int idAvion, numero_asientos, velocidad_maxima, activado, id_aeropuerto;
	String modelo, nombreAeropuerto;
	
	public Avion(int idAvion, int numero_asientos, int velocidad_maxima, int activado, int id_aeropuerto,
			String modelo) {
		this.idAvion = idAvion;
		this.numero_asientos = numero_asientos;
		this.velocidad_maxima = velocidad_maxima;
		this.activado = activado;
		this.id_aeropuerto = id_aeropuerto;
		this.modelo = modelo;
	}
	
	public Avion(int idAvion, int numero_asientos, int velocidad_maxima, int activado, int id_aeropuerto, String nombreAeropuerto,
			String modelo) {
		this.nombreAeropuerto = nombreAeropuerto;
		this.idAvion = idAvion;
		this.numero_asientos = numero_asientos;
		this.velocidad_maxima = velocidad_maxima;
		this.activado = activado;
		this.id_aeropuerto = id_aeropuerto;
		this.modelo = modelo;
	}
	
	public Avion(int numero_asientos, int velocidad_maxima, int activado, int id_aeropuerto,
			String modelo) {
		this.numero_asientos = numero_asientos;
		this.velocidad_maxima = velocidad_maxima;
		this.activado = activado;
		this.id_aeropuerto = id_aeropuerto;
		this.modelo = modelo;
	}

	public int getIdAvion() {
		return idAvion;
	}

	public int getNumero_asientos() {
		return numero_asientos;
	}

	public int getVelocidad_maxima() {
		return velocidad_maxima;
	}

	public int getActivado() {
		return activado;
	}

	public int getId_aeropuerto() {
		return id_aeropuerto;
	}

	public String getModelo() {
		return modelo;
	}

	@Override
	public String toString() {
		return modelo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(activado, idAvion, id_aeropuerto, modelo, numero_asientos, velocidad_maxima);
	}
	
	

	public void setActivado(int activado) {
		this.activado = activado;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avion other = (Avion) obj;
		return activado == other.activado && idAvion == other.idAvion && id_aeropuerto == other.id_aeropuerto
				&& Objects.equals(modelo, other.modelo) && numero_asientos == other.numero_asientos
				&& velocidad_maxima == other.velocidad_maxima;
	}
	
	
}
