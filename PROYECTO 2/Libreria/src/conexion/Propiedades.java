package conexion;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Clase abstracta que proporciona un acceso estático a las propiedades cargadas desde un archivo de configuración.
 * Las propiedades se cargan al inicio de la aplicación desde el archivo "configuration.properties".
 */
public abstract class Propiedades {

    /**
     * Propiedades cargadas desde el archivo de configuración.
     */
    private static Properties props = new Properties();

    /**
     * Bloque estático para cargar la configuración desde un archivo de propiedades al iniciar la clase.
     * Se utiliza un bloque estático para asegurar que las propiedades se carguen solo una vez al inicio.
     */
    static {
        try (FileInputStream input = new FileInputStream("configuration.properties")) {
            // Cargamos las propiedades desde el archivo
            props.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método estático para obtener el valor asociado a una clave en la configuración.
     *
     * @param clave Clave cuyo valor se desea obtener.
     * @return Valor asociado a la clave proporcionada.
     * @throws RuntimeException Si la clave no existe en las propiedades.
     */
    public static String getValor(String clave) {
        // Obtenemos el valor correspondiente a la clave proporcionada
        String valor = props.getProperty(clave);

        // Si la clave existe y tiene un valor asociado, lo retornamos
        if (valor != null) {
            return valor;
        }

        // En caso de que la clave no exista, lanzamos una excepción RuntimeException
        throw new RuntimeException("La clave solicitada en configuration.properties no está disponible");
    }
}

