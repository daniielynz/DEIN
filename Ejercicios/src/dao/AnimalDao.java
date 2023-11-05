package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexion.ConexionAnimales;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Animal;

public class AnimalDao {
    private ConexionAnimales conexion;

    public ObservableList<Animal> cargarAnimales()  {
    	
    	ObservableList<Animal> animales = FXCollections.observableArrayList();
        try {
            conexion = new ConexionAnimales();        	
        	String consulta = "select * from Animales";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
				
			 while (rs.next()) {
				 	// Guardamos todos los datos
		            int codigo = rs.getInt("id");
		            String nombre = rs.getString("nombre");
		            String especie = rs.getString("especie");
		            String raza = rs.getString("raza");
		            String sexo = rs.getString("sexo");
		            String observaciones = rs.getString("observaciones");
		            String fechaPrimeraCita = rs.getString("fechaPrimeraCita");
		            String foto = rs.getString("foto");
		            int edad = rs.getInt("edad");
		            int peso = rs.getInt("peso");
		            // Creamos la persona
		            // Persona a = new Persona(idPersona, nombre, apellidos, edad);
		            Animal a = new Animal(nombre, especie, raza, sexo,  observaciones, fechaPrimeraCita,  foto,  codigo,  edad,  peso);
		            animales.add(a);
			 }     
			 rs.close();       
			 conexion.closeConexion();

			 return animales;
			 
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
        return animales;    
    }
    
    public int ultimoId() {
    	try {
            conexion = new ConexionAnimales();        	
        	String consulta = "select MAX(id) as ID from Animales";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
				
			 while (rs.next()) {
		            int ultimoId = rs.getInt("ID");
		            
		            return ultimoId+1;
			 }             
		rs.close();       
        conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }
    	return 0;
    }
    
    public void eliminarAnimal(Animal a)  {
        try {
            conexion = new ConexionAnimales();        	
        	String consulta = "DELETE FROM Animales WHERE id = "+a.getCodigo()+";";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	int rs = pstmt.executeUpdate();
        	      
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }   
    }
    
    public void aniadirAnimal(Animal a)  {
        try {
            conexion = new ConexionAnimales();        	
        	String consulta = "insert into Animales (codigo, nombre, especie, raza, sexo, edad, peso, observaciones, fecha_primera_cita, foto) VALUES ("+ultimoId()+",'"+a.getNombre()+"','"+a.getEspecie()+"','"+a.getRaza()+"','"+a.getSexo()+"',"+a.getEdad()+","+a.getPeso()+",'"+a.getObservaciones()+"',"+a.getFechaPrimeraCita()+",'"+a.getFoto()+"');";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
        	int rs = pstmt.executeUpdate();
        	      
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }   
    }
    /*
    public void modificarAnimal(Animal p)  {
        try {
            conexion = new ConexionAnimales();        	
        	String consulta = "UPDATE Persona "
					        			+ "SET nombre = '"+p.getNombre()+"',"
					        			+ "apellidos = '"+p.getApellidos()+"',"
					        			+ "edad = "+p.getEdad()+" "
					        			+ "WHERE id = "+p.getIdPersona();
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
        	int rs = pstmt.executeUpdate();
        	      
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }   
    }*/
}
