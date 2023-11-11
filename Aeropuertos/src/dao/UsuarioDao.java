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
            // comprobamos que el usuario existe en la base de datos
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
            // sacamos el ultimo id
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
    
    
}

