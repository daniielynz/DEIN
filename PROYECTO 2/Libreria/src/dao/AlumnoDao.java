package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Alumno;

public class AlumnoDao {
    private ConexionBD conexion;
    public ObservableList<Alumno> cargarAlumnos(String cadena)  {
		ObservableList<Alumno> listaAlumnos = FXCollections.observableArrayList();
	    try {
	        conexion = new ConexionBD();        	
	    	String consulta = "select * "
			    			+ "from Alumno "
			    			+ "WHERE nombre LIKE '%"+cadena+"%'";
	    	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
	    	ResultSet rs = pstmt.executeQuery();   
				
			 while (rs.next()) {
				 	// Guardamos todos los datos
		            int dni = rs.getInt("dni");
		            String nombre = rs.getString("nombre");
		            String apellido1 = rs.getString("apellido1");
		            String apellido2 = rs.getString("apellido2");
		            
		            // Creamos el Alumno
		            Alumno a = new Alumno(dni, nombre, apellido1, apellido2);
		            listaAlumnos.add(a);
			 }     
			 rs.close();       
			 conexion.closeConexion();
	
			 return listaAlumnos;
			 
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
	    return listaAlumnos;    
	}
    
    public void editarAlumno(Alumno alumno, int dniAntiguo) {
    	try{
    		conexion = new ConexionBD();
    		String consulta = "UPDATE Alumno SET dni=?, nombre = ?, apellido1 = ?, apellido2 = ? WHERE dni = ?";
    		
    		PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
    	    // Establecer los parámetros
    		pstmt.setInt(1, alumno.getDni());
    	    pstmt.setString(2, alumno.getNombre());
    	    pstmt.setString(3, alumno.getApellido1());
    	    pstmt.setString(4, alumno.getApellido2());
    	    pstmt.setInt(5, dniAntiguo);

    	    // Ejecutar la actualización
    	    pstmt.executeUpdate();
    	} catch (SQLException e) {
    	    // Manejar excepciones de SQL
    	    e.printStackTrace();
    	}
    }
    
    public void borrarAlumno(Alumno alumno) {
    	try {
    		conexion = new ConexionBD();   
    		
    		// borrar de la tabla Prestamos
    		String consulta = "DELETE FROM Historico_prestamo WHERE dni_alumno = "+alumno.getDni();
    		PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
            pstmt.executeUpdate();
    		
    		// borrar de la tabla Historico Prestamos
            consulta = "DELETE FROM Prestamo WHERE dni_alumno = "+alumno.getDni();
            pstmt = conexion.getConexion().prepareStatement(consulta);      
            pstmt.executeUpdate();
            
            // borrar de la tabla Alumno
            consulta = "DELETE FROM Alumno WHERE dni = "+alumno.getDni();
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
        	String consulta = "select MAX(dni) as ID from Alumnos";
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

