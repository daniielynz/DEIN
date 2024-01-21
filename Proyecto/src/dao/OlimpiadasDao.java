package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Evento;
import model.Olimpiada;

public class OlimpiadasDao {
private ConexionBD conexion;
    
    public ObservableList<Olimpiada> cargarOlimpiadas(String cadena)  {
		ObservableList<Olimpiada> listaOlimpiadas = FXCollections.observableArrayList();
	    try {
	        conexion = new ConexionBD();        	
	    	String consulta = "select * "
			    			+ "from Olimpiada "
			    			+ "WHERE nombre LIKE '%"+cadena+"%' ";
	    	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
	    	ResultSet rs = pstmt.executeQuery();   
	    	
			 while (rs.next()) {
				 	// Guardamos todos los datos
		            int id_olimpiada = rs.getInt("id_olimpiada");
		            int anio = rs.getInt("anio");
		            String nombre = rs.getString("nombre");
		            String ciudad = rs.getString("ciudad");
		            String temporada = rs.getString("temporada");
		            
		            // Creamos la Olimpiada
		            Olimpiada o = new Olimpiada(id_olimpiada, anio, nombre, ciudad, temporada);
		            listaOlimpiadas.add(o);
			 }     
			 rs.close();       
			 conexion.closeConexion();
	
			 return listaOlimpiadas;
			 
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
	    return listaOlimpiadas;    
	}
    
    public void borrarOlimpiada(Olimpiada a) {
    	try {
    		conexion = new ConexionBD();   
    		
    		// borrar de la tabla Participacion
    		String consulta = "DELETE FROM Participacion WHERE id_evento IN (SELECT id_evento FROM Evento WHERE id_olimpiada = "+a.getId_olimpiada()+")";
    		PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
            pstmt.executeUpdate();
    		
    		// borrar de la tabla Evento
    		consulta = "DELETE FROM Evento WHERE id_olimpiada = "+ a.getId_olimpiada();
    		pstmt = conexion.getConexion().prepareStatement(consulta);      
            pstmt.executeUpdate();
    		
    		// borrar de la tabla Olimpiada
            consulta = "DELETE FROM Olimpiada WHERE id_olimpiada = "+a.getId_olimpiada();
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
        	String consulta = "select MAX(id_olimpiada) as ID from Olimpiada";
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
