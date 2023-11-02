package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AeropuertoPrivado;
import model.AeropuertoPublico;

public class AeropuertoDao {
    private ConexionBD conexion;

public ObservableList<AeropuertoPublico> cargarAeropuertosPublicos()  {
    	
		ObservableList<AeropuertoPublico> aeropuertosPublicos = FXCollections.observableArrayList();
	    try {
	        conexion = new ConexionBD();        	
	    	String consulta = "select a.id, a.nombre, d.pais, d.ciudad, d.calle, d.numero, a.anio_inauguracion, a.capacidad, ap.num_trabajadores, ap.financiacion "
	    			+ "from aeropuertos a, aeropuertos_publicos ap, direcciones d "
	    			+ "WHERE a.id = ap.id_aeropuerto AND d.id = a.id_direccion";
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
	    	ResultSet rs = pstmt.executeQuery();   
				
			 while (rs.next()) {
				 	// Guardamos todos los datos
		            int id = rs.getInt("id");
		            String nombre = rs.getString("nombre");
		            String pais = rs.getString("pais");
		            String ciudad = rs.getString("ciudad");
		            String calle = rs.getString("calle");
		            int numero = rs.getInt("numero");
		            String anio = rs.getString("anio_inauguracion");
		            String capacidad = rs.getString("capacidad");
		            int numTrabajadores = rs.getInt("num_trabajadores");
		            String financiacion = rs.getString("financiacion");
		            // Creamos la persona
		            AeropuertoPublico a = new AeropuertoPublico(id, numero, numTrabajadores, nombre, pais, ciudad, calle, anio, capacidad, financiacion);
		            aeropuertosPublicos.add(a);
			 }     
			 rs.close();       
			 conexion.closeConexion();
	
			 return aeropuertosPublicos;
			 
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
	    return aeropuertosPublicos;    
	}
    
    public ObservableList<AeropuertoPrivado> cargarAeropuertosPrivados()  {
    	
		ObservableList<AeropuertoPrivado> aeropuertosPrivados = FXCollections.observableArrayList();
	    try {
	        conexion = new ConexionBD();        	
	    	String consulta = "select a.id, a.nombre, d.pais, d.ciudad, d.calle, d.numero, a.anio_inauguracion, a.capacidad, ap.numero_socios "
	    			+ "from aeropuertos a, aeropuertos_privados ap, direcciones d "
	    			+ "WHERE a.id = ap.id_aeropuerto AND d.id = a.id_direccion";
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
	    	ResultSet rs = pstmt.executeQuery();   
				
			 while (rs.next()) {
				 	// Guardamos todos los datos
		            int id = rs.getInt("id");
		            String nombre = rs.getString("nombre");
		            String pais = rs.getString("pais");
		            String ciudad = rs.getString("ciudad");
		            String calle = rs.getString("calle");
		            int numero = rs.getInt("numero");
		            String anio = rs.getString("anio_inauguracion");
		            String capacidad = rs.getString("capacidad");
		            int NSocios = rs.getInt("numero_socios");
		            // Creamos la persona
		            AeropuertoPrivado a = new AeropuertoPrivado(id, numero, NSocios, nombre, pais, ciudad, calle, anio, capacidad);
		            aeropuertosPrivados.add(a);
			 }     
			 rs.close();       
			 conexion.closeConexion();
	
			 return aeropuertosPrivados;
			 
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
	    return aeropuertosPrivados;    
	}
    
    public int ultimoId() {
    	try {
            conexion = new ConexionBD();        	
        	String consulta = "select MAX(id) as ID from usuarios";
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
    
}

