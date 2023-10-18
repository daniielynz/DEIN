package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Persona;

public class PersonaDao {
    private ConexionBD conexion;

    public ObservableList<Persona> cargarPersonas()  {
    	
    	ObservableList<Persona> personas = FXCollections.observableArrayList();
        try {
            conexion = new ConexionBD();        	
        	String consulta = "select * from Persona";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
				
			 while (rs.next()) {
		            int idPersona = rs.getInt("id");
		            String nombre = rs.getString("nombre");
		            String apellidos = rs.getString("apellidos");
		            int edad = rs.getInt("edad");
		            
		            Persona a = new Persona(idPersona, nombre, apellidos, edad);
			 }             
		rs.close();       
        conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
        return personas;    
    }
    
    public int ultimoId() {
    	try {
            conexion = new ConexionBD();        	
        	String consulta = "select MAX(id) as ID from Persona";
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
    
    public void aniadirPersona(Persona p)  {
    	System.out.println("hola");
        try {
            conexion = new ConexionBD();        	
        	String consulta = "insert into Persona (id,nombre,apellidos,edad) VALUES ("+ultimoId()+",'"+p.getNombre()+"','"+p.getApellidos()+"',"+p.getEdad()+")";
        	System.out.println(consulta);
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	int rs = pstmt.executeUpdate();
        	      
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }   
    }
}
