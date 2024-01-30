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
import model.Libro;

public class LibroDao {
    private ConexionBD conexion;
    
    public ObservableList<Libro> cargarLibros(String cadena)  {
		ObservableList<Libro> listaAlumnos = FXCollections.observableArrayList();
	    try {
	        conexion = new ConexionBD();        	
	    	String consulta = "select * "
			    			+ "from Libro "
			    			+ "WHERE titulo LIKE '%"+cadena+"%' AND baja=1";
	    	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
	    	ResultSet rs = pstmt.executeQuery();   
				
			 while (rs.next()) {
				 	// Guardamos todos los datos
				 	int codigo = rs.getInt("codigo");
				 	String titulo = rs.getString("titulo");
		            String autor = rs.getString("autor");
		            String editorial = rs.getString("editorial");
		            String estado = rs.getString("estado");
		            int baja = rs.getInt("baja");
		            
		            // Creamos el Alumno
		            Libro a = new Libro(codigo, titulo, autor, editorial, estado, baja);
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
    
    public void aniadirLibro(Libro libro) {
    	try {
	    	conexion = new ConexionBD();
	    	String consulta = "INSERT INTO Libro (titulo, autor, editorial, estado, baja) VALUES (?, ?, ?, ?, ?)";
	
	    	try (PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta)) {
	    	    pstmt.setString(1, libro.getTitulo());
	    	    pstmt.setString(2, libro.getAutor());
	    	    pstmt.setString(3, libro.getEditorial());
	    	    pstmt.setString(4, libro.getEstado());
	    	    pstmt.setInt(5, libro.getBaja());
	
	    	    pstmt.executeUpdate();
	    	}
	    	
    	    alertaInformacion("Se ha añadido correctamente el Libro\nActualiza la tabla para ver los cambios");
    	    conexion.closeConexion();
    	    
    	} catch (SQLException e) {
            // Manejar excepciones de SQL
            e.printStackTrace();
        } 
    }
    
    public void editarLibro(Libro libro) {
    	try{
    		conexion = new ConexionBD();
    		String consulta = "UPDATE Libro SET titulo = ?, autor = ?, editorial = ?, estado = ?, baja = ? WHERE codigo = ?";
    		
    		PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
    	    // Establecer los parámetros
    		pstmt.setString(1, libro.getTitulo());
    	    pstmt.setString(2, libro.getAutor());
    	    pstmt.setString(3, libro.getEditorial());
    	    pstmt.setString(4, libro.getEditorial());
    	    pstmt.setInt(5, libro.getBaja());
    	    pstmt.setInt(6, libro.getCodigo());

    	    // Ejecutar la actualización
    	    pstmt.executeUpdate();
    	} catch (SQLException e) {
    	    // Manejar excepciones de SQL
    	    e.printStackTrace();
    	}
    }
    
    public void borrarLibro(Libro libro) {
    	try {
    		conexion = new ConexionBD();  
    		
    		String consulta = "UPDATE Libro SET baja = ? WHERE codigo = ?";
    		
    		PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
    	    // Establecer los parámetros
    	    pstmt.setInt(1, 0);
    	    pstmt.setInt(2, libro.getCodigo());

    	    // Ejecutar la actualización
    	    pstmt.executeUpdate();
    		/*
    		// borrar de la tabla Prestamos (si existe)
    		String existePrestamos = "SELECT COUNT(*) FROM Prestamo WHERE codigo_libro = "+libro.getCodigo();
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(existePrestamos);      
        	ResultSet rs = pstmt.executeQuery();   
        	rs.next();
            int count = rs.getInt(1);
            // si hay alguna referencia a ese libro en la tabla Prestamo lo borramos
            if (count > 0) { 
            	String consulta = "DELETE FROM Historico_prestamo WHERE codigo_libro = "+libro.getCodigo();
        		pstmt = conexion.getConexion().prepareStatement(consulta);      
                pstmt.executeUpdate();
            }
            
            // borrar de la tabla Historico Prestamos (si existe)
    		String existeHistoricoPrestamos = "SELECT COUNT(*) FROM Historico_prestamo WHERE codigo_libro = "+libro.getCodigo();
        	pstmt = conexion.getConexion().prepareStatement(existeHistoricoPrestamos);      
        	rs = pstmt.executeQuery();   
        	rs.next();
            count = rs.getInt(1);
            // si hay alguna referencia a ese libro en la tabla Historico prestamo lo borramos
            if (count > 0) { 
                String consulta = "DELETE FROM Prestamo WHERE codigo_libro = "+libro.getCodigo();
                pstmt = conexion.getConexion().prepareStatement(consulta);      
                pstmt.executeUpdate();
            }
            
            // borrar de la tabla Libro
            String consulta = "DELETE FROM Libro WHERE codigo = "+libro.getCodigo();
            pstmt = conexion.getConexion().prepareStatement(consulta);      
            pstmt.executeUpdate();
            */
        	      
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
    }
    
    public int ultimoId() {
    	try {
            conexion = new ConexionBD();        	
        	String consulta = "select MAX(codigo) as ID from Libro";
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

