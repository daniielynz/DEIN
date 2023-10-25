package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class ConexionBD {
    private Connection conexion;
    
    public ConexionBD() throws SQLException {
       /* String host = "localhost";
        String baseDatos = "personas";
        String usuario = "admin";
        String password = "dm2";
        String cadenaConexion = "jdbc:mysql://" + host + "/" + baseDatos+ "?serverTimezone=" + TimeZone.getDefault().getID();*/
    	String url = Propiedades.getValor("url") + "?serverTimezone=" + TimeZone.getDefault().getID();
        String user = Propiedades.getValor("user");
        String password = Propiedades.getValor("password");
        conexion = DriverManager.getConnection(url, user, password);
        //conexion = DriverManager.getConnection(cadenaConexion, usuario, password);
        conexion.setAutoCommit(true);
    }
    public Connection getConexion() {
        return conexion;
    }
    public void closeConexion() throws SQLException {
    	conexion.close();
    }
}
