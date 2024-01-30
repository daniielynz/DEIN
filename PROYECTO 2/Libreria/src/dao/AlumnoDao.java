package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
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
				 	String dni = rs.getString("dni");
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
    
    public void aniadirAlumno(Alumno alumno) {
    	try {
            conexion = new ConexionBD();  
            
            // añadir en la tabla de Alumno
            String consulta = "insert into Alumno VALUES ('"+alumno.getDni()+"','"+alumno.getNombre()+"','"+alumno.getApellido1()+"','"+alumno.getApellido2()+"')";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
        	pstmt.executeUpdate();
        	      
        	conexion.closeConexion();
        	
        	alertaInformacion("Se ha añadido correctamente el Alumno\nActualiza la tabla para ver los cambios");
    	} catch (SQLIntegrityConstraintViolationException e) {
    	    // Manejar la excepción de clave duplicada aquí
    	    alertaError("Ya existe un alumno con ese Dni");
    	} catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }  
    }
    
    public void editarAlumno(Alumno alumno, String dniAntiguo) {
    	try{
    		conexion = new ConexionBD();
    		String consulta = "UPDATE Alumno SET dni=?, nombre = ?, apellido1 = ?, apellido2 = ? WHERE dni = ?";
    		
    		PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
    	    // Establecer los parámetros
    		pstmt.setString(1, alumno.getDni());
    	    pstmt.setString(2, alumno.getNombre());
    	    pstmt.setString(3, alumno.getApellido1());
    	    pstmt.setString(4, alumno.getApellido2());
    	    pstmt.setString(5, dniAntiguo);

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
    		// borrar de la tabla Prestamos (si existe)
    		String existePrestamos = "SELECT COUNT(*) FROM Prestamo WHERE dni_alumno = '"+alumno.getDni()+"'";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(existePrestamos);      
        	ResultSet rs = pstmt.executeQuery();   
        	rs.next();
            int count = rs.getInt(1);
            // si hay alguna referencia a ese alumno en la tabla Prestamo lo borramos
            if (count > 0) { 
            	String consulta = "DELETE FROM Historico_prestamo WHERE dni_alumno = '"+alumno.getDni()+"'";
        		pstmt = conexion.getConexion().prepareStatement(consulta);      
                pstmt.executeUpdate();
            }
            
            // borrar de la tabla Historico Prestamos (si existe)
    		String existeHistoricoPrestamos = "SELECT COUNT(*) FROM Historico_prestamo WHERE dni_alumno = '"+alumno.getDni()+"'";
        	pstmt = conexion.getConexion().prepareStatement(existeHistoricoPrestamos);      
        	rs = pstmt.executeQuery();   
        	rs.next();
            count = rs.getInt(1);
            // si hay alguna referencia a ese alumno en la tabla Historico prestamo lo borramos
            if (count > 0) { 
                String consulta = "DELETE FROM Prestamo WHERE dni_alumno = '"+alumno.getDni()+"'";
                pstmt = conexion.getConexion().prepareStatement(consulta);      
                pstmt.executeUpdate();
            }
            
            // borrar de la tabla Alumno
            String consulta = "DELETE FROM Alumno WHERE dni = '"+alumno.getDni()+"'";
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
    
    // Metodos de diferentes ventanas emergentes
    private void alertaError(String mensaje) {
    	// Alerta de error con boton
    	Alert ventanaEmergente = new Alert(AlertType.ERROR);
    	ventanaEmergente.setTitle("info");
    	ventanaEmergente.setContentText(mensaje);
    	Button ocultarBtn = new Button("Aceptar");
        ocultarBtn.setOnAction(e -> {
        	ventanaEmergente.hide();
        });
        ventanaEmergente.show();
    }
    
    private void alertaInformacion(String mensaje) {
    	// Alerta de informacion con boton
    	Alert ventanaEmergente = new Alert(AlertType.INFORMATION);
    	ventanaEmergente.setTitle("info");
    	ventanaEmergente.setContentText(mensaje);
    	Button ocultarBtn = new Button("Aceptar");
        ocultarBtn.setOnAction(e -> {
        	ventanaEmergente.hide();
        });
        ventanaEmergente.show();
    }
    
}

