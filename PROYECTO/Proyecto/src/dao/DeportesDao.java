package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Deporte;

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

