package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Participacion;

public class ParticipacionesDao {
private ConexionBD conexion;
    
    public ObservableList<Participacion> cargarParticipaciones(String cadena)  {
		ObservableList<Participacion> listaParticipaciones = FXCollections.observableArrayList();
	    try {
	        conexion = new ConexionBD();        	
	    	String consulta = "select Deportista.nombre, Evento.nombre, Equipo.nombre, Participacion.edad, Participacion.medalla "
			    			+ "from Evento, Deportista, Equipo, Participacion "
			    			+ "WHERE Deportista.nombre LIKE '%"+cadena+"%' "
			    			+ "AND Deportista.id_deportista = Participacion.id_deportista "
			    			+ "AND Evento.id_evento = Participacion.id_evento "
			    			+ "AND Equipo.id_equipo = Participacion.id_equipo";
	    	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
	    	ResultSet rs = pstmt.executeQuery();   
	    	
			 while (rs.next()) {
				 	// Guardamos todos los datos
				 	String nombre_deportista = rs.getString("Deportista.nombre");
		            String nombre_evento = rs.getString("Evento.nombre");
		            String nombre_equipo = rs.getString("Equipo.nombre");
		            int edad = rs.getInt("Participacion.edad");
		            String medalla = rs.getString("Participacion.medalla");
		            
		            // Creamos la Participacion
		            Participacion p = new Participacion(nombre_deportista, nombre_evento, nombre_equipo, edad, medalla);
		            listaParticipaciones.add(p);
			 }     
			 rs.close();       
			 conexion.closeConexion();
	
			 return listaParticipaciones;
			 
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
	    return listaParticipaciones;    
	}
}
