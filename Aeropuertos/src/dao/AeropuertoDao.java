package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AeropuertoPrivado;
import model.AeropuertoPublico;

public class AeropuertoDao {
    private ConexionBD conexion;
    
    public void aniadirAeropuertoPrivado(AeropuertoPrivado a) {
    	try {
    		// creamos conexoion
            conexion = new ConexionBD();      
            // añadir en la tabla de aeropuertos
            
            String consulta = "insert into direcciones (pais, ciudad, calle, numero) VALUES ('"+a.getPais()+"','"+a.getCiudad()+"','"+a.getCalle()+"',"+a.getNumero()+");";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
        	int rs = pstmt.executeUpdate();
            
            consulta = "insert into aeropuertos (nombre, anio_inauguracion, capacidad, id_direccion, imagen) VALUES ('"+a.getNombre()+"',"+a.getAnioInauguracion()+","+a.getCapacidad()+", "+(ultimoId("direcciones")-1)+" ,'');";
        	pstmt = conexion.getConexion().prepareStatement(consulta);
        	rs = pstmt.executeUpdate();
        	
        	// añadir en la tabla de aeropuertos
        	consulta = "insert into aeropuertos_privados (id_aeropuerto, numero_socios) VALUES ("+(ultimoId("aeropuertos")-1)+","+a.getNSocios()+");";
        	pstmt = conexion.getConexion().prepareStatement(consulta);
        	rs = pstmt.executeUpdate();
        	      
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }  
    }
    
    public void borrarAeropuertoPrivado(AeropuertoPrivado a) {
    	try {
            conexion = new ConexionBD();        	
            
            // borrar de la tabla de aeropuertos privados
            String consulta = "DELETE FROM aeropuertos_privados WHERE id_aeropuerto = "+a.getId()+";";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
            int rs2 = pstmt.executeUpdate();
            
            // borrar de la tabla de aeropuertos
        	consulta = "DELETE FROM aeropuertos WHERE id = "+a.getId()+";";
        	pstmt = conexion.getConexion().prepareStatement(consulta);      
        	rs2 = pstmt.executeUpdate();
            
            // sacar el id de direcciones que tiene el aeropuerto
        	consulta = "select id_direccion from aeropuertos WHERE id = "+a.getId();
        	pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
        	int idDirecciones = rs.getInt("id_direccion");
            
        	// borrar de la tabla de direcciones
        	consulta = "DELETE FROM direcciones WHERE id = "+idDirecciones+";";
        	pstmt = conexion.getConexion().prepareStatement(consulta);      
        	rs2 = pstmt.executeUpdate();
        	      
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
    }
    
    public void borrarAeropuertoPublico(AeropuertoPublico a) {
    	try {
            conexion = new ConexionBD();        	
            
            // borrar de la tabla de aeropuertos privados
            String consulta = "DELETE FROM aeropuertos_publicos WHERE id_aeropuerto = "+a.getId()+";";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
            int rs2 = pstmt.executeUpdate();
            
            // sacar el id de direcciones que tiene el aeropuerto
            consulta = "select id_direccion from aeropuertos where id = "+a.getId();
        	pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
        	int idDirecciones = 0;
			 if (rs.next()) {
		            idDirecciones = rs.getInt("id_direccion");
		            System.out.println(idDirecciones);
		            rs.close();   
			 }
            
            // borrar de la tabla de aeropuertos
        	consulta = "DELETE FROM aeropuertos WHERE id = "+a.getId()+";";
        	pstmt = conexion.getConexion().prepareStatement(consulta);      
        	rs2 = pstmt.executeUpdate();
			 
			// borrar de la tabla de direcciones
        	consulta = "DELETE FROM direcciones WHERE id = "+idDirecciones;
        	pstmt = conexion.getConexion().prepareStatement(consulta);      
        	rs2 = pstmt.executeUpdate();
			 
        	
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
    }
    
    public void aniadirAeropuertoPublico(AeropuertoPublico a) {
    	try {
    		// creamos conexoion
            conexion = new ConexionBD();      
            // añadir en la tabla de aeropuertos
            
            String consulta = "insert into direcciones (pais, ciudad, calle, numero) VALUES ('"+a.getPais()+"','"+a.getCiudad()+"','"+a.getCalle()+"',"+a.getNumero()+");";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
        	int rs = pstmt.executeUpdate();
            
            consulta = "insert into aeropuertos (nombre, anio_inauguracion, capacidad, id_direccion, imagen) VALUES ('"+a.getNombre()+"',"+a.getAnioInauguracion()+","+a.getCapacidad()+", "+(ultimoId("direcciones")-1)+" ,'');";
        	pstmt = conexion.getConexion().prepareStatement(consulta);
        	rs = pstmt.executeUpdate();
        	
        	// añadir en la tabla de aeropuertos
        	consulta = "insert into aeropuertos_publicos (id_aeropuerto, financiacion, num_trabajadores) VALUES ("+(ultimoId("aeropuertos")-1)+","+a.getFinanciacion()+","+a.getNTrabajadores()+");";
        	pstmt = conexion.getConexion().prepareStatement(consulta);
        	rs = pstmt.executeUpdate();
        	      
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }  
    }

    public ObservableList<AeropuertoPublico> cargarAeropuertosPublicos(String cadena)  {
		ObservableList<AeropuertoPublico> aeropuertosPublicos = FXCollections.observableArrayList();
	    try {
	        conexion = new ConexionBD();        	
	    	String consulta = "select a.id, a.nombre, d.pais, d.ciudad, d.calle, d.numero, a.anio_inauguracion, a.capacidad, ap.num_trabajadores, ap.financiacion "
	    			+ "from aeropuertos a, aeropuertos_publicos ap, direcciones d "
	    			+ "WHERE a.id = ap.id_aeropuerto AND d.id = a.id_direccion AND a.nombre LIKE '%"+cadena+"%'";
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
	    	ResultSet rs = pstmt.executeQuery();   
				
			 while (rs.next()) {
				 	// Guardamos todos los datos
		            int id = rs.getInt("id");
		            String nombre = rs.getString("nombre");
		            String pais = rs.getString("pais");
		            String ciudad = rs.getString("ciudad");
		            String calle = rs.getString("calle");
		            int numero = rs.getInt("numero");
		            int anio = rs.getInt("anio_inauguracion");
		            int capacidad = rs.getInt("capacidad");
		            int numTrabajadores = rs.getInt("num_trabajadores");
		            Float financiacion = rs.getFloat("financiacion");
		            // Creamos la persona
		            AeropuertoPublico a = new AeropuertoPublico(id, numero, financiacion, numTrabajadores, anio, capacidad, nombre, pais, ciudad, calle);
		            aeropuertosPublicos.add(a);
			 }     
			 rs.close();       
			 conexion.closeConexion();
	
			 return aeropuertosPublicos;
			 
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
	    return aeropuertosPublicos;    
	}
    
    public ObservableList<AeropuertoPrivado> cargarAeropuertosPrivados(String cadena)  {
		ObservableList<AeropuertoPrivado> aeropuertosPrivados = FXCollections.observableArrayList();
	    try {
	        conexion = new ConexionBD();        	
	    	String consulta = "select a.id, a.nombre, d.pais, d.ciudad, d.calle, d.numero, a.anio_inauguracion, a.capacidad, ap.numero_socios "
	    			+ "from aeropuertos a, aeropuertos_privados ap, direcciones d "
	    			+ "WHERE a.id = ap.id_aeropuerto AND d.id = a.id_direccion AND a.nombre LIKE '%"+cadena+"%'";
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
	    	ResultSet rs = pstmt.executeQuery();   
				
			 while (rs.next()) {
				 	// Guardamos todos los datos
		            int id = rs.getInt("id");
		            String nombre = rs.getString("nombre");
		            String pais = rs.getString("pais");
		            String ciudad = rs.getString("ciudad");
		            String calle = rs.getString("calle");
		            int numero = rs.getInt("numero");
		            int anio = rs.getInt("anio_inauguracion");
		            int capacidad = rs.getInt("capacidad");
		            int NSocios = rs.getInt("numero_socios");
		            // Creamos la persona
		            AeropuertoPrivado a = new AeropuertoPrivado(id, numero, NSocios, anio, capacidad, nombre, pais, ciudad, calle);
		            aeropuertosPrivados.add(a);
			 }     
			 rs.close();       
			 conexion.closeConexion();
	
			 return aeropuertosPrivados;
			 
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
	    return aeropuertosPrivados;    
	}
    
    public int ultimoId(String tabla) {
    	try {
            conexion = new ConexionBD();        	
        	String consulta = "select MAX(id) as ID from "+tabla;
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

