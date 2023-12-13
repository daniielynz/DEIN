package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Evento;

public class EventosDao {
private ConexionBD conexion;
    
    public ObservableList<Evento> cargarEventos(String cadena)  {
		ObservableList<Evento> listaEquipos = FXCollections.observableArrayList();
	    try {
	        conexion = new ConexionBD();        	
	    	String consulta = "select Evento.id_evento as id_evento, Evento.nombre as nombre, Olimpiada.nombre as olimpiada, Deporte.nombre as deporte "
			    			+ "from Evento, Olimpiada, Deporte "
			    			+ "WHERE Evento.nombre LIKE '%"+cadena+"%' "
	    					+ "AND Deporte.id_deporte = Evento.id_deporte and Evento.id_olimpiada = Olimpiada.id_olimpiada";
	    	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
	    	ResultSet rs = pstmt.executeQuery();   
	    	
			 while (rs.next()) {
				 	// Guardamos todos los datos
		            String nombre = rs.getString("nombre");
		            int id_evento = rs.getInt("id_evento");
		            String olimpiada = rs.getString("olimpiada");
		            String deporte = rs.getString("deporte");
		            
		            // Creamos el Deporte
		            Evento e = new Evento(nombre, id_evento, olimpiada, deporte);
		            System.out.println(e);
		            listaEquipos.add(e);
			 }     
			 rs.close();       
			 conexion.closeConexion();
	
			 return listaEquipos;
			 
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
	    return listaEquipos;    
	}
    
    public int ultimoId() {
    	try {
            conexion = new ConexionBD();        	
        	String consulta = "select MAX(id_evento) as ID from Evento";
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
