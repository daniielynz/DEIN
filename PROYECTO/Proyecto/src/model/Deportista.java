package model;

import java.util.Objects;

public class Deportista {
	int id_deportista, peso, altura;
	String nombre, foto, sexo;
	
	public Deportista(int id_deportista, int peso, int altura, String nombre, String foto, String sexo) {
		this.id_deportista = id_deportista;
		this.peso = peso;
		this.altura = altura;
		this.nombre = nombre;
		this.foto = foto;
		this.sexo = sexo;
	}
	
	public Deportista(int peso, int altura, String nombre, String foto, String sexo) {
		this.peso = peso;
		this.altura = altura;
		this.nombre = nombre;
		this.foto = foto;
		this.sexo = sexo;
	}

	public int getId_deportista() {
		return id_deportista;
	}

	public int getPeso() {
		return peso;
	}

	public int getAltura() {
		return altura;
	}

	public String getNombre() {
		return nombre;
	}

	public String getFoto() {
		return foto;
	}

	public String getSexo() {
		return sexo;
	}

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(altura, foto, id_deportista, nombre, peso, sexo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deportista other = (Deportista) obj;
		return altura == other.altura && Objects.equals(foto, other.foto) && id_deportista == other.id_deportista
				&& Objects.equals(nombre, other.nombre) && peso == other.peso && sexo == other.sexo;
	}
	
	
}
