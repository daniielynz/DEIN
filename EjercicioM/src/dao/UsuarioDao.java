package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexion.ConexionBD;
import model.Usuario;

public class UsuarioDao {
    private ConexionBD conexion;

    
    public boolean existeUsuario(Usuario usu)  {
    	boolean existe = false;
    	
        try {
            conexion = new ConexionBD();   
        	String consulta = "select * from usuarios WHERE usuario = '"+usu.getNombre()+"' AND password = '"+usu.getPassword()+"'";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
        	
			 if (rs.next()) {
				 	existe = true;
			 }     
			 rs.close();       
			 conexion.closeConexion();
	
			 return existe;
			 
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
        return existe;    
    }
    
    public int ultimoId() {
    	try {
            conexion = new ConexionBD();        	
        	String consulta = "select MAX(id) as ID from usuarios";
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
    
    /*public ObservableList<Persona> cargarPersonas()  {
	
		ObservableList<Persona> personas = FXCollections.observableArrayList();
	    try {
	        conexion = new ConexionBD();        	
	    	String consulta = "select * from Persona";
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
	    	ResultSet rs = pstmt.executeQuery();   
				
			 while (rs.next()) {
				 	// Guardamos todos los datos
		            int idPersona = rs.getInt("id");
		            String nombre = rs.getString("nombre");
		            String apellidos = rs.getString("apellidos");
		            int edad = rs.getInt("edad");
		            // Creamos la persona
		            Persona a = new Persona(idPersona, nombre, apellidos, edad);
		            personas.add(a);
			 }     
			 rs.close();       
			 conexion.closeConexion();
	
			 return personas;
			 
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
	    return personas;    
	}
    
    public void eliminarPersona(Persona p)  {
        try {
            conexion = new ConexionBD();        	
        	String consulta = "DELETE FROM Persona WHERE id = "+p.getIdPersona()+";";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	int rs = pstmt.executeUpdate();
        	      
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }   
    }
    
    public void aniadirPersona(Persona p)  {
        try {
            conexion = new ConexionBD();        	
        	String consulta = "insert into Persona (id,nombre,apellidos,edad) VALUES ("+ultimoId()+",'"+p.getNombre()+"','"+p.getApellidos()+"',"+p.getEdad()+");";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
        	int rs = pstmt.executeUpdate();
        	      
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }   
    }
    
    public void modificarPersona(Persona p)  {
        try {
            conexion = new ConexionBD();        	
        	String consulta = "UPDATE Persona "
					        			+ "SET nombre = '"+p.getNombre()+"',"
					        			+ "apellidos = '"+p.getApellidos()+"',"
					        			+ "edad = "+p.getEdad()+" "
					        			+ "WHERE id = "+p.getIdPersona();
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
        	int rs = pstmt.executeUpdate();
        	      
        	conexion.PersonacloseConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }   
    }
*/
    
}

