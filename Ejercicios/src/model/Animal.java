package model;

import java.util.Objects;

public class Animal {
	private String nombre, especie, raza, sexo, observaciones, fechaPrimeraCita, foto;
	private int codigo, edad, peso;
	
	public Animal(String nombre, String especie, String raza, String sexo, String observaciones,
			String fechaPrimeraCita, String foto, int codigo, int edad, int peso) {
		this.nombre = nombre;
		this.especie = especie;
		this.raza = raza;
		this.sexo = sexo;
		this.observaciones = observaciones;
		this.fechaPrimeraCita = fechaPrimeraCita;
		this.foto = foto;
		this.codigo = codigo;
		this.edad = edad;
		this.peso = peso;
	}
	public Animal(String nombre, String especie, String raza, String sexo, String observaciones,
			String fechaPrimeraCita, String foto, int edad, int peso) {
		this.nombre = nombre;
		this.especie = especie;
		this.raza = raza;
		this.sexo = sexo;
		this.observaciones = observaciones;
		this.fechaPrimeraCita = fechaPrimeraCita;
		this.foto = foto;
		this.codigo = codigo;
		this.edad = edad;
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "Animal [nombre=" + nombre + ", especie=" + especie + ", raza=" + raza + ", sexo=" + sexo
				+ ", observaciones=" + observaciones + ", fechaPrimeraCita=" + fechaPrimeraCita + ", foto=" + foto
				+ ", codigo=" + codigo + ", edad=" + edad + ", peso=" + peso + "]";
	}
	public String getNombre() {
		return nombre;
	}
	public String getEspecie() {
		return especie;
	}
	public String getRaza() {
		return raza;
	}
	public String getSexo() {
		return sexo;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public String getFechaPrimeraCita() {
		return fechaPrimeraCita;
	}
	public String getFoto() {
		return foto;
	}
	public int getCodigo() {
		return codigo;
	}
	public int getEdad() {
		return edad;
	}
	public int getPeso() {
		return peso;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codigo, edad, especie, fechaPrimeraCita, foto, nombre, observaciones, peso, raza, sexo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		return codigo == other.codigo && edad == other.edad && Objects.equals(especie, other.especie)
				&& Objects.equals(fechaPrimeraCita, other.fechaPrimeraCita) && Objects.equals(foto, other.foto)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(observaciones, other.observaciones)
				&& peso == other.peso && Objects.equals(raza, other.raza) && Objects.equals(sexo, other.sexo);
	}
	
}
