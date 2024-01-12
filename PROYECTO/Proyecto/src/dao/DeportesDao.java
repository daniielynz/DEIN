package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Deporte;
import model.Deportista;

public class DeportesDao {
    private ConexionBD conexion;
    
    public ObservableList<Deporte> cargarDeportes(String cadena)  {
		ObservableList<Deporte> listaDeportes = FXCollections.observableArrayList();
	    try {
	        conexion = new ConexionBD();        	
	    	String consulta = "select * "
			    			+ "from Deporte "
			    			+ "WHERE nombre LIKE '%"+cadena+"%'";
	    	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
	    	ResultSet rs = pstmt.executeQuery();   
				
			 while (rs.next()) {
				 	// Guardamos todos los datos
		            int id = rs.getInt("id_deporte");
		            String nombre = rs.getString("nombre");
		            
		            // Creamos el Deporte
		            Deporte a = new Deporte(id, nombre);
		            listaDeportes.add(a);
			 }     
			 rs.close();       
			 conexion.closeConexion();
	
			 return listaDeportes;
			 
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
	    return listaDeportes;    
	}
    
    public void borrarDeporte(Deporte a) {
    	try {
    		conexion = new ConexionBD();   
    		
    		// borrar de la tabla Participacion
    		String consulta = "DELETE FROM Participacion WHERE id_evento IN (SELECT id_evento FROM Evento WHERE id_deporte = "+a.getId_deporte()+")";
    		PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
            pstmt.executeUpdate();
    		
    		// borrar de la tabla Evento
    		consulta = "DELETE FROM Evento WHERE id_deporte = "+ a.getId_deporte();
    		pstmt = conexion.getConexion().prepareStatement(consulta);      
            pstmt.executeUpdate();
    		
    		// borrar de la tabla Deporte
            consulta = "DELETE FROM Deporte WHERE id_deporte = "+a.getId_deporte();
            pstmt = conexion.getConexion().prepareStatement(consulta);      
            pstmt.executeUpdate();
            
            
        	      
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
    }
    
    public int ultimoId() {
    	try {
            conexion = new ConexionBD();        	
        	String consulta = "select MAX(id_deporte) as ID from Deporte";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
				
			 if (rs.next()) {
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

