package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import conexion.ConexionBD;
import model.AeropuertoPublico;
import model.Avion;

public class AvionesDao {
    private ConexionBD conexion;

    public ArrayList<Avion> listadoAviones(int idAeropuerto)  {
    	ArrayList<Avion> arrlAviones = new ArrayList<Avion>();
	    try {
	        conexion = new ConexionBD();        	
	    	String consulta = "SELECT * "
			    			+ "FROM aviones "
			    			+ "WHERE id_aeropuerto = "+idAeropuerto;
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
	    	ResultSet rs = pstmt.executeQuery(); 
				
			 while (rs.next()) {
				 	// Guardamos todos los datos
		            int id = rs.getInt("id");
		            String modelo = rs.getString("modelo");
		            int numAsientos = rs.getInt("numero_asientos");
		            int velocidadMaxima = rs.getInt("velocidad_maxima");
		            int activado = rs.getInt("activado");
		            int id_aeropuerto = rs.getInt("id_aeropuerto");
		            // Creamos la persona
		            Avion a = new Avion(numAsientos, velocidadMaxima, activado, id_aeropuerto, modelo);
		            arrlAviones.add(a);
			 }     
			 rs.close();       
			 conexion.closeConexion();
	
			 return arrlAviones;
			 
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
	    return arrlAviones;    
	}
    
    public int ultimoId(String tabla) {
    	try {
            conexion = new ConexionBD();        	
        	String consulta = "select MAX(id) as ID from "+tabla;
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

