package model;

public class Libro {
	int codigo, baja;
	String titulo, autor, editorial, estado;
	
	public Libro(int codigo, String titulo, String autor, String editorial, String estado, int baja) {
		this.codigo = codigo;
		this.baja = baja;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.estado = estado;
	}
	
	public Libro(String titulo, String autor, String editorial, String estado, int baja) {
		this.baja = baja;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.estado = estado;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getBaja() {
		return baja;
	}

	public void setBaja(int baja) {
		this.baja = baja;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Libro [codigo=" + codigo + ", baja=" + baja + ", titulo=" + titulo + ", autor=" + autor + ", editorial="
				+ editorial + ", estado=" + estado + "]";
	}
	
	
	
	
}
