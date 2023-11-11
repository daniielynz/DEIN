package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Avion;

public class AvionesDao {
    private ConexionBD conexion;
    
    public void borrarAvion(Avion a) {
    	try {
    		conexion = new ConexionBD();        	
            
            // borrar de la tabla de aeropuertos privados
            String consulta =  "DELETE FROM aviones "
            				 + "WHERE id = "+a.getIdAvion()+" AND id_aeropuerto = "+a.getId_aeropuerto();
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
            pstmt.executeUpdate();   
        	      
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
    }
    
    public void activarDesactivarAvion(Avion a) {
    	try {
    		// creamos conexion
            conexion = new ConexionBD();      
        	// editamos la tabla avion
        	String consulta =  "UPDATE aviones "
		    				 + "SET activado = "+a.getActivado()+" "
		    				 + "WHERE id = "+a.getIdAvion()+" AND id_aeropuerto = "+a.getId_aeropuerto();
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();
        	      
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
    }

    public ObservableList<Avion> listadoAviones(int idAeropuerto)  {
    	ObservableList<Avion> listaAviones = FXCollections.observableArrayList();
	    try {
	        conexion = new ConexionBD();      
	        // sacamos un listado de aviones con el id de aeropuerto introducido
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
		            Avion a = new Avion(id, numAsientos, velocidadMaxima, activado, id_aeropuerto, modelo);
		            listaAviones.add(a);
			 }     
			 rs.close();       
			 conexion.closeConexion();
	
			 return listaAviones;
			 
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
	    return listaAviones;    
	}
    
    public boolean existe(Avion a) {
    	boolean existe = true;
    	try {
    		// comprobamos que existe el avion
            conexion = new ConexionBD();        	
        	String consulta = "select id from aviones where modelo = '"+a.getModelo()+"' AND id_aeropuerto = "+a.getId_aeropuerto();
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
        	 // si no existe
			 if (!rs.next()) {
				 	existe = false;
			 }             
			 rs.close();       
			 conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }
    	return existe;
    }
    
    public void aniadirAvion(Avion a) {
    	try {
    		// creamos conexoion
            conexion = new ConexionBD();      
            
            // añadir en la tabla de aviones
            String consulta = "insert into aviones (modelo, numero_asientos, velocidad_maxima, activado, id_aeropuerto) VALUES ('"+a.getModelo()+"',"+a.getNumero_asientos()+","+a.getVelocidad_maxima()+","+a.getActivado()+","+a.getId_aeropuerto()+");";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
        	pstmt.executeUpdate();
        	      
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }  
    }
    
    public int ultimoId(String tabla) {
    	try {
            conexion = new ConexionBD();        
            // sacamos el ultimo id
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

