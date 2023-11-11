package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Aeropuerto;
import model.AeropuertoPrivado;
import model.AeropuertoPublico;
import model.Avion;

public class AeropuertoDao {
    private ConexionBD conexion;
    
    public ObservableList<Aeropuerto> nombreAeropuertos() {
    	ObservableList<Aeropuerto> listalAeropuertos = FXCollections.observableArrayList();
    	try {
    		// creamos conexoion
            conexion = new ConexionBD();   
	    	// obtener todos los aeropuertos
	        String consulta = "SELECT * FROM aeropuertos";
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
	    	ResultSet rs = pstmt.executeQuery();   
	    	
			while (rs.next()) {
				Aeropuerto a = new Aeropuerto(rs.getInt("id"), rs.getInt("anio_inauguracion"), rs.getInt("capacidad"), rs.getInt("id_direccion"), rs.getString("nombre"), rs.getString("imagen"));
				listalAeropuertos.add(a);
			}
			
			rs.close();  
			conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }  
    	
    	return listalAeropuertos;
    }
    
    public void aniadirAeropuertoPrivado(AeropuertoPrivado a) {
    	try {
    		// creamos conexoion
            conexion = new ConexionBD();      
            // añadir en la tabla de aeropuertos
            
            String consulta = "insert into direcciones (pais, ciudad, calle, numero) VALUES ('"+a.getPais()+"','"+a.getCiudad()+"','"+a.getCalle()+"',"+a.getNumero()+");";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
        	pstmt.executeUpdate();
            
            consulta = "insert into aeropuertos (nombre, anio_inauguracion, capacidad, id_direccion, imagen) VALUES ('"+a.getNombre()+"',"+a.getAnioInauguracion()+","+a.getCapacidad()+", "+(ultimoId("direcciones")-1)+" ,'');";
        	pstmt = conexion.getConexion().prepareStatement(consulta);
        	pstmt.executeUpdate();
        	
        	// añadir en la tabla de aeropuertos
        	consulta = "insert into aeropuertos_privados (id_aeropuerto, numero_socios) VALUES ("+(ultimoId("aeropuertos")-1)+","+a.getNSocios()+");";
        	pstmt = conexion.getConexion().prepareStatement(consulta);
        	pstmt.executeUpdate();
        	      
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }  
    }
    
    public void borrarAeropuertoPrivado(AeropuertoPrivado a) {
    	try {
    		conexion = new ConexionBD();      
    		
    		// primero borramos los aviones del aeropuerto
    		AvionesDao avionesDao = new AvionesDao();
    		ObservableList<Avion> listadoAviones = avionesDao.listadoAviones(a.getId());
    		for(Avion avion : listadoAviones) {
    			avionesDao.borrarAvion(avion);
    		}
            
            // borrar de la tabla de aeropuertos privados
            String consulta = "DELETE FROM aeropuertos_privados WHERE id_aeropuerto = "+a.getId()+";";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
            pstmt.executeUpdate();
            
            // sacar el id de direcciones que tiene el aeropuerto
            consulta = "select id_direccion from aeropuertos where id = "+a.getId();
        	pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
        	int idDireccion = 0;
			 if (rs.next()) {
				 idDireccion = rs.getInt("id_direccion");
		            rs.close();   
			 }
            
            // borrar de la tabla de aeropuertos
        	consulta = "DELETE FROM aeropuertos WHERE id = "+a.getId()+";";
        	pstmt = conexion.getConexion().prepareStatement(consulta);      
        	pstmt.executeUpdate();
			 
			// borrar de la tabla de direcciones
        	consulta = "DELETE FROM direcciones WHERE id = "+idDireccion;
        	pstmt = conexion.getConexion().prepareStatement(consulta);      
        	pstmt.executeUpdate();
        	      
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
    }
    
    public void borrarAeropuertoPublico(AeropuertoPublico a) {
    	try {
            conexion = new ConexionBD();   
            
            // primero borramos los aviones del aeropuerto
    		AvionesDao avionesDao = new AvionesDao();
    		ObservableList<Avion> listadoAviones = avionesDao.listadoAviones(a.getId());
    		for(Avion avion : listadoAviones) {
    			avionesDao.borrarAvion(avion);
    		}
            
            // borrar de la tabla de aeropuertos privados
            String consulta = "DELETE FROM aeropuertos_publicos WHERE id_aeropuerto = "+a.getId()+";";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
            pstmt.executeUpdate();
            
            // sacar el id de direcciones que tiene el aeropuerto
            consulta = "select id_direccion from aeropuertos where id = "+a.getId();
        	pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
        	int idDireccion = 0;
			 if (rs.next()) {
				 idDireccion = rs.getInt("id_direccion");
		            rs.close();   
			 }
            
            // borrar de la tabla de aeropuertos
        	consulta = "DELETE FROM aeropuertos WHERE id = "+a.getId()+";";
        	pstmt = conexion.getConexion().prepareStatement(consulta);      
        	pstmt.executeUpdate();
			 
			// borrar de la tabla de direcciones
        	consulta = "DELETE FROM direcciones WHERE id = "+idDireccion;
        	pstmt = conexion.getConexion().prepareStatement(consulta);      
        	pstmt.executeUpdate();
			 
        	
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
        	pstmt.executeUpdate();
            
            consulta = "insert into aeropuertos (nombre, anio_inauguracion, capacidad, id_direccion, imagen) VALUES ('"+a.getNombre()+"',"+a.getAnioInauguracion()+","+a.getCapacidad()+", "+(ultimoId("direcciones")-1)+" ,'');";
        	pstmt = conexion.getConexion().prepareStatement(consulta);
        	pstmt.executeUpdate();
        	
        	// añadir en la tabla de aeropuertos
        	consulta = "insert into aeropuertos_publicos (id_aeropuerto, financiacion, num_trabajadores) VALUES ("+(ultimoId("aeropuertos")-1)+","+a.getFinanciacion()+","+a.getNTrabajadores()+");";
        	pstmt = conexion.getConexion().prepareStatement(consulta);
        	pstmt.executeUpdate();
        	      
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }  
    }
    
    public void editarAeropuertoPublico(AeropuertoPublico a) {
    	try {
    		// creamos conexoion
            conexion = new ConexionBD();      
            // editamos la tabla de aeropuertos publicos
            String consulta = "UPDATE aeropuertos_publicos "
            				+ "SET financiacion = "+a.getFinanciacion()+", num_trabajadores = "+a.getNTrabajadores()+" "
            				+ "WHERE id_aeropuerto = "+a.getId();
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
        	pstmt.executeUpdate();
            
        	// sacar el id de direcciones que tiene el aeropuerto
            consulta = "select id_direccion from aeropuertos where id = "+a.getId();
        	pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs2 = pstmt.executeQuery();   
        	int idDireccion = 0;
			 if (rs2.next()) {
				 idDireccion = rs2.getInt("id_direccion");
		            rs2.close();   
			 }
        	
        	// editamos la tabla direcciones
        	consulta = "UPDATE direcciones "
    				 + "SET pais = '"+a.getPais()+"', ciudad = '"+a.getCiudad()+"', calle = '"+a.getCalle()+"' , numero = "+a.getNumero()+" "
    				 + "WHERE id = "+idDireccion;
        	pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();
        	
        	// editamos la tabla aeropuertos
        	consulta = "UPDATE aeropuertos "
    				 + "SET nombre = '"+a.getNombre()+"', anio_inauguracion = "+a.getAnioInauguracion()+", capacidad = "+a.getCapacidad()+", id_direccion = '"+idDireccion+"' "
    				 + "WHERE id = "+a.getId();
			pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();
        	      
        	conexion.closeConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }  
    }
    
    public void editarAeropuertoPrivado(AeropuertoPrivado a) {
    	try {
    		// creamos conexoion
            conexion = new ConexionBD();      
            // editamos la tabla de aeropuertos publicos
            String consulta = "UPDATE aeropuertos_privados "
            				+ "SET numero_socios = "+a.getNSocios()+" "
            				+ "WHERE id_aeropuerto = "+a.getId();
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
        	pstmt.executeUpdate();
            
        	// sacar el id de direcciones que tiene el aeropuerto
            consulta = "select id_direccion from aeropuertos where id = "+a.getId();
        	pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs2 = pstmt.executeQuery();   
        	int idDireccion = 0;
			 if (rs2.next()) {
				 idDireccion = rs2.getInt("id_direccion");
		            rs2.close();   
			 }
        	
        	// editamos la tabla direcciones
        	consulta = "UPDATE direcciones "
    				 + "SET pais = '"+a.getPais()+"', ciudad = '"+a.getCiudad()+"', calle = '"+a.getCalle()+"' , numero = "+a.getNumero()+" "
    				 + "WHERE id = "+idDireccion;
        	pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();
        	
        	// editamos la tabla aeropuertos
        	consulta = "UPDATE aeropuertos "
    				 + "SET nombre = '"+a.getNombre()+"', anio_inauguracion = "+a.getAnioInauguracion()+", capacidad = "+a.getCapacidad()+", id_direccion = '"+idDireccion+"' "
    				 + "WHERE id = "+a.getId();
			pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();
        	      
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

