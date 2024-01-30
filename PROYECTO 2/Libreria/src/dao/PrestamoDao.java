package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import model.Libro;
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
		            Prestamo prestamo = new Prestamo(id, dni, codigo_libro, fecha_prestamo);
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
    
    public void aniadirPrestamo(Prestamo prestamo) {
    	try {
	    	conexion = new ConexionBD();
	    	String consulta = "INSERT INTO Prestamo (dni_alumno, codigo_libro, fecha_prestamo) VALUES (?, ?, ?)";
	
	    	try (PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta)) {
	    	    pstmt.setString(1, prestamo.getDni());
	    	    pstmt.setInt(2, prestamo.getCodigoLibro());
	    	    pstmt.setString(3, prestamo.getFechaPrestamo());
	
	    	    pstmt.executeUpdate();
	    	}
	    	
    	    alertaInformacion("Se ha añadido correctamente el Prestamo\nActualiza la tabla para ver los cambios");
    	    conexion.closeConexion();
    	    
    	} catch (SQLException e) {
            // Manejar excepciones de SQL
            e.printStackTrace();
        } 
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

