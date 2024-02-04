package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import model.Libro;

/**
 * Clase que maneja las operaciones de acceso a datos relacionadas con la entidad Libro.
 */
public class LibroDao {
    
    private ConexionBD conexion;
    
    /**
     * Carga una lista de libros que coinciden con la cadena de búsqueda y no están dados de baja.
     *
     * @param cadena La cadena de búsqueda para el título de los libros.
     * @return Una lista observable de libros.
     */
    public ObservableList<Libro> cargarLibros(String cadena) {
        ObservableList<Libro> listaLibros = FXCollections.observableArrayList();
        try {
            conexion = new ConexionBD();
            String consulta = "SELECT * FROM Libro WHERE titulo LIKE '%" + cadena + "%' AND baja = 1";

            try (PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta)) {
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    // Guardamos todos los datos
                    int codigo = rs.getInt("codigo");
                    String titulo = rs.getString("titulo");
                    String autor = rs.getString("autor");
                    String editorial = rs.getString("editorial");
                    String estado = rs.getString("estado");
                    int baja = rs.getInt("baja");

                    // Creamos el Libro
                    Libro libro = new Libro(codigo, titulo, autor, editorial, estado, baja);
                    listaLibros.add(libro);
                }

                rs.close();
            }
            conexion.closeConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return listaLibros;
    }
    
    public void cambiarEstado(int id) {
    	// generamos un estado aleatorio
        String[] palabras = {"Nuevo", "Usado nuevo", "Usado seminuevo", "Usado estropeado", "Restaurado"};

        // Generar un índice aleatorio
        Random random = new Random();
        int indiceAleatorio = random.nextInt(palabras.length);

        // Obtener la palabra aleatoria
        String palabraAleatoria = palabras[indiceAleatorio];
    	try{
    		conexion = new ConexionBD();
    		String consulta = "UPDATE Libro SET estado = ? WHERE codigo = ?";
    		
    		PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
    	    // Establecer los parámetros
    		pstmt.setString(1, palabraAleatoria);
    	    pstmt.setInt(2, id);

    	    // Ejecutar la actualización
    	    pstmt.executeUpdate();
    	} catch (SQLException e) {
    	    // Manejar excepciones de SQL
    	    e.printStackTrace();
    	}
    }
    
    /**
     * Agrega un nuevo libro a la base de datos.
     *
     * @param libro   El libro a ser agregado.
     * @param bundle  El ResourceBundle para la localización del mensaje.
     */
    public void aniadirLibro(Libro libro, ResourceBundle bundle) {
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

            alertaInformacion(bundle);
            conexion.closeConexion();
        } catch (SQLException e) {
            // Manejar excepciones de SQL
            e.printStackTrace();
        } 
    }

    /**
     * Actualiza la información de un libro existente en la base de datos.
     *
     * @param libro El libro con la información actualizada.
     */
    public void editarLibro(Libro libro) {
        try {
            conexion = new ConexionBD();
            String consulta = "UPDATE Libro SET titulo = ?, autor = ?, editorial = ?, estado = ?, baja = ? WHERE codigo = ?";

            try (PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta)) {
                // Establecer los parámetros
                pstmt.setString(1, libro.getTitulo());
                pstmt.setString(2, libro.getAutor());
                pstmt.setString(3, libro.getEditorial());
                pstmt.setString(4, libro.getEstado());
                pstmt.setInt(5, libro.getBaja());
                pstmt.setInt(6, libro.getCodigo());

                // Ejecutar la actualización
                pstmt.executeUpdate();
                conexion.closeConexion();
            }
        } catch (SQLException e) {
            // Manejar excepciones de SQL
            e.printStackTrace();
        } 
    }
    
    public void borrarLibro(Libro libro) {
        try {
            conexion = new ConexionBD();

            String consulta = "UPDATE Libro SET baja = ? WHERE codigo = ?";
            try (PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta)) {
                // Establecer los parámetros
                pstmt.setInt(1, 0);
                pstmt.setInt(2, libro.getCodigo());

                // Ejecutar la actualización
                pstmt.executeUpdate();
            }
            conexion.closeConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    /**
     * Obtiene el último ID de libro almacenado en la base de datos.
     *
     * @return El último ID de libro más uno.
     */
    public int ultimoId() {
        try {
            conexion = new ConexionBD();
            String consulta = "SELECT MAX(codigo) AS ID FROM Libro";
            try (PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
                 ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    int ultimoId = rs.getInt("ID");
                    return ultimoId + 1;
                }
            }
            conexion.closeConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return 0;
    }

    /**
     * Muestra una ventana emergente de información después de agregar un libro exitosamente.
     *
     * @param bundle El ResourceBundle para la localización del mensaje.
     */
    private void alertaInformacion(ResourceBundle bundle) {
        // Alerta de información con botón
        Alert ventanaEmergente = new Alert(AlertType.INFORMATION);
        ventanaEmergente.setTitle("info");
        ventanaEmergente.setContentText(bundle.getString("mensajeLibroAniadido"));
        Button ocultarBtn = new Button("Aceptar");
        ocultarBtn.setOnAction(e -> {
            ventanaEmergente.hide();
        });
        ventanaEmergente.show();
    }
    
}

