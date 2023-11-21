package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import conexion.ConexionBD;
import model.Participacion;

public class ParicipacionDao {
	private ConexionBD conexion;
	
	public void crearParticipacion(Participacion p) {
		try {
    		// creamos conexion
            conexion = new ConexionBD();  
            
            // añadir en la tabla de productos
            String consulta = "insert into Participacion (id_deportista, id_evento, id_equipo, edad, medalla) VALUES ("+p.getId_deportista()+","+p.getId_evento()+","+p.getId_equipo()+","+p.getEdad()+",'"+p.getMedalla()+"')";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
        	pstmt.executeUpdate();
        	      
        	// cerramos
        	pstmt.close();
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
	}

	public void editarParticipacion(Participacion p) {
		try {
            conexion = new ConexionBD();  
			 
			// Editar de la tabla de productos
        	String consulta = "UPDATE Participacion SET id_deportista = "+ p.getId_deportista() +", id_evento = "+ p.getId_evento() +", id_equipo = "+ p.getId_equipo() +", edad = "+p.getEdad()+", medalla = '"+p.getMedalla()+"'  WHERE id_deportista = "+ p.getId_deportista();
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	pstmt.executeUpdate();
        	
        	pstmt.close();
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
	}

	public void borrarParticipacion(Participacion p) throws Exception {
		try {
            conexion = new ConexionBD();   
            
			// borrar de la tabla de productos
        	String consulta = "DELETE FROM Participacion WHERE id_deportista = "+p.getId_deportista();
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	pstmt.executeUpdate();
        	
        	pstmt.close();
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
	}
}
