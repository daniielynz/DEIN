package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.HistoricoPrestamo;

public class HistoricoDao {
    private ConexionBD conexion;
    public ObservableList<HistoricoPrestamo> cargarHistoricoPrestamos(String cadena)  {
		ObservableList<HistoricoPrestamo> listaHistoricoPrestamos = FXCollections.observableArrayList();
	    try {
	        conexion = new ConexionBD();        	
	    	String consulta = "select * "
			    			+ "from Historico_prestamo "
			    			+ "WHERE dni_alumno LIKE '%"+cadena+"%'";
	    	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
	    	ResultSet rs = pstmt.executeQuery();   
				
			 while (rs.next()) {
				 	// Guardamos todos los datos
				 	int idid_prestamo = rs.getInt("id_prestamo");
		            String dni_alumno = rs.getString("dni_alumno");
		            int codigo_libro = rs.getInt("codigo_libro");
		            String fecha_prestamo = rs.getString("fecha_prestamo");
		            String fecha_devolucion = rs.getString("fecha_devolucion");
		            
		            // Creamos el HistoricoPrestamo
		            HistoricoPrestamo HistoricoPrestamo = new HistoricoPrestamo(idid_prestamo, dni_alumno, codigo_libro, fecha_prestamo, fecha_devolucion);
		            listaHistoricoPrestamos.add(HistoricoPrestamo);
			 }     
			 rs.close();       
			 conexion.closeConexion();
	
			 return listaHistoricoPrestamos;
			 
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
	    return listaHistoricoPrestamos;    
	}
    
    public void editarHistoricoPrestamo(HistoricoPrestamo HistoricoPrestamo) {
    	try{
    		conexion = new ConexionBD();
    		String consulta = "UPDATE Historico_prestamo SET dni_alumno = ?, codigo_libro = ?, fecha_prestamo = ?, fecha_devolucion = ? WHERE id_prestamo = ?";
    		
    		PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
    	    // Establecer los parámetros
    		pstmt.setString(1, HistoricoPrestamo.getDni());
    	    pstmt.setInt(2, HistoricoPrestamo.getId_libro());
    	    pstmt.setString(3, HistoricoPrestamo.getFechaPrestamo());
    	    pstmt.setString(4, HistoricoPrestamo.getFechaDevolucion());
    	    pstmt.setInt(5, HistoricoPrestamo.getId());
    	    // Ejecutar la actualización
    	    pstmt.executeUpdate();
    	} catch (SQLException e) {
    	    // Manejar excepciones de SQL
    	    e.printStackTrace();
    	}
    }
    
    public void borrarHistoricoPrestamo(HistoricoPrestamo HistoricoPrestamo) {
    	try {
    		conexion = new ConexionBD();  
    		
    		// borrar de la tabla HistoricoPrestamo
            String consulta = "DELETE FROM Historico_prestamo WHERE id_prestamo = "+HistoricoPrestamo.getId();
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
        	String consulta = "select MAX(id_prestamo) as ID from Historico_prestamo";
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

