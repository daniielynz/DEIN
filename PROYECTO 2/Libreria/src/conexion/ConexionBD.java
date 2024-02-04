package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

/**
 * Clase que proporciona la conexión a la base de datos utilizando las propiedades definidas en "configuration.properties".
 * La conexión se establece al instanciar la clase y se cierra al llamar al método closeConexion().
 */
public class ConexionBD {

    /**
     * Conexión a la base de datos.
     */
    private Connection conexion;

    /**
     * Constructor de la clase. Establece la conexión a la base de datos utilizando las propiedades definidas.
     *
     * @throws SQLException Si ocurre un error al establecer la conexión.
     */
    public ConexionBD() throws SQLException {
        String url = Propiedades.getValor("url") + "?serverTimezone=" + TimeZone.getDefault().getID();
        String user = Propiedades.getValor("user");
        String password = Propiedades.getValor("password");

        // Creamos la conexión
        conexion = DriverManager.getConnection(url, user, password);
        conexion.setAutoCommit(true);
    }

    /**
     * Obtiene la conexión a la base de datos.
     *
     * @return Conexión a la base de datos.
     */
    public Connection getConexion() {
        return conexion;
    }

    /**
     * Cierra la conexión a la base de datos.
     *
     * @throws SQLException Si ocurre un error al cerrar la conexión.
     */
    public void closeConexion() throws SQLException {
        conexion.close();
    }
}

