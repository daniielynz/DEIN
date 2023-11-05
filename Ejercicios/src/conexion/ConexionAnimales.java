package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class ConexionAnimales {
    private Connection conexion;
    
    public ConexionAnimales() throws SQLException {
    	String url = Propiedades.getValor("urlAnimales") + "?serverTimezone=" + TimeZone.getDefault().getID();
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
