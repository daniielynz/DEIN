package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Prestamo;

public class PrestamoDao {
    private ConexionBD conexion;
    public ObservableList<Prestamo> cargarPrestamos(String cadena)  {
		ObservableList<Prestamo> listaPrestamos = FXCollections.observableArrayList();
	    try {
	        conexion = new ConexionBD();        	
	    	String consulta = "select * "
			    			+ "from Prestamo "
			    			+ "WHERE dni_alumno LIKE '%"+cadena+"%'";
	    	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
	    	ResultSet rs = pstmt.executeQuery();   
				
			 while (rs.next()) {
				 	// Guardamos todos los datos
				 	int id = rs.getInt("id_prestamo");
		            String dni = rs.getString("dni_alumno");
		            int codigo_libro = rs.getInt("codigo_libro");
		            String fecha_prestamo = rs.getString("fecha_prestamo");
		            
		            // Creamos el Prestamo
		            Prestamo prestamo = new Prestamo(id, dni, fecha_prestamo, codigo_libro);
		            listaPrestamos.add(prestamo);
			 }     
			 rs.close();       
			 conexion.closeConexion();
	
			 return listaPrestamos;
			 
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
	    return listaPrestamos;    
	}
    
    public void editarPrestamo(Prestamo Prestamo) {
    	try{
    		conexion = new ConexionBD();
    		String consulta = "UPDATE Prestamo SET dni_alumno = ?, codigo_libro = ?, fecha_prestamo = ? WHERE id_prestamo = ?";
    		
    		PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
    	    // Establecer los parámetros
    		pstmt.setString(1, Prestamo.getDni());
    	    pstmt.setInt(2, Prestamo.getCodigoLibro());
    	    pstmt.setString(3, Prestamo.getFechaPrestamo());
    	    pstmt.setInt(4, Prestamo.getId());

    	    // Ejecutar la actualización
    	    pstmt.executeUpdate();
    	} catch (SQLException e) {
    	    // Manejar excepciones de SQL
    	    e.printStackTrace();
    	}
    }
    
    public void borrarPrestamo(Prestamo Prestamo) {
    	try {
    		conexion = new ConexionBD();  
    		
    		// borrar de la tabla Prestamo
            String consulta = "DELETE FROM Prestamo WHERE id_prestamo = "+Prestamo.getId();
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
            pstmt.executeUpdate();
            
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
    }
    
    public int ultimoId() {
    	try {
            conexion = new ConexionBD();        	
        	String consulta = "select MAX(id_prestamo) as ID from Prestamo";
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

