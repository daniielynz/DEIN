package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import model.Libro;
import model.Prestamo;

/**
 * Clase que maneja las operaciones de acceso a datos relacionadas con la entidad Prestamo.
 */
public class PrestamoDao {
    
    private ConexionBD conexion;
    
    /**
     * Carga una lista de préstamos que coinciden con la cadena de búsqueda.
     *
     * @param cadena La cadena de búsqueda para el DNI del alumno.
     * @return Una lista observable de préstamos.
     */
    public ObservableList<Prestamo> cargarPrestamos(String cadena) {
        ObservableList<Prestamo> listaPrestamos = FXCollections.observableArrayList();
        try {
            conexion = new ConexionBD();
            String consulta = "SELECT * FROM Prestamo WHERE dni_alumno LIKE '%" + cadena + "%'";

            try (PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta)) {
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    // Guardamos todos los datos
                    int id = rs.getInt("id_prestamo");
                    String dni = rs.getString("dni_alumno");
                    int codigo_libro = rs.getInt("codigo_libro");
                    String fecha_prestamo = rs.getString("fecha_prestamo");

                    // Creamos el Prestamo
                    Prestamo prestamo = new Prestamo(id, dni, codigo_libro, fecha_prestamo);
                    listaPrestamos.add(prestamo);
                }

                rs.close();
            }
            conexion.closeConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return listaPrestamos;
    }
    
    /**
     * Añade un nuevo préstamo a la base de datos.
     *
     * @param prestamo El préstamo a añadir.
     * @param bundle   ResourceBundle para obtener mensajes localizados.
     */
    public void aniadirPrestamo(Prestamo prestamo, ResourceBundle bundle) {
        try {
            conexion = new ConexionBD();
            String consulta = "INSERT INTO Prestamo (dni_alumno, codigo_libro, fecha_prestamo) VALUES (?, ?, ?)";

            try (PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta)) {
                pstmt.setString(1, prestamo.getDni());
                pstmt.setInt(2, prestamo.getCodigoLibro());
                pstmt.setString(3, prestamo.getFechaPrestamo());

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
     * Edita un préstamo existente en la base de datos.
     *
     * @param prestamo El préstamo actualizado.
     */
    public void editarPrestamo(Prestamo prestamo) {
        try {
            conexion = new ConexionBD();
            String consulta = "UPDATE Prestamo SET dni_alumno = ?, codigo_libro = ?, fecha_prestamo = ? WHERE id_prestamo = ?";

            try (PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta)) {
                // Establecer los parámetros
                pstmt.setString(1, prestamo.getDni());
                pstmt.setInt(2, prestamo.getCodigoLibro());
                pstmt.setString(3, prestamo.getFechaPrestamo());
                pstmt.setInt(4, prestamo.getId());

                // Ejecutar la actualización
                pstmt.executeUpdate();
            }
            conexion.closeConexion();
        } catch (SQLException e) {
            // Manejar excepciones de SQL
            e.printStackTrace();
        } 
    }
    
    /**
     * Método para borrar un préstamo de la tabla Prestamo.
     *
     * @param prestamo Objeto de tipo Prestamo que se eliminará de la base de datos.
     * @throws SQLException Si ocurre un error al ejecutar la operación SQL.
     */
    public void borrarPrestamo(Prestamo prestamo) {
        try {
            conexion = new ConexionBD();  

            // Borrar de la tabla Prestamo
            String consulta = "DELETE FROM Prestamo WHERE id_prestamo = " + prestamo.getId();
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
            pstmt.executeUpdate();

            conexion.closeConexion();
        } catch (SQLException e) {        
            e.printStackTrace();
        } 
    }

    /**
     * Método para obtener el último ID de la tabla Prestamo y retornar el siguiente ID disponible.
     *
     * @return Entero que representa el último ID incrementado en uno.
     * @throws SQLException Si ocurre un error al ejecutar la operación SQL.
     */
    public int ultimoId() {
        try {
            conexion = new ConexionBD();        
            String consulta = "select MAX(id_prestamo) as ID from Prestamo";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
            ResultSet rs = pstmt.executeQuery();   

            if (rs.next()) {
                int ultimoId = rs.getInt("ID");

                return ultimoId + 1;
            }             
            rs.close();       
            conexion.closeConexion();
        } catch (SQLException e) {        
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Método para mostrar una alerta de información con un botón de aceptar.
     *
     * @param bundle Objeto ResourceBundle que contiene los mensajes locales.
     */
    private void alertaInformacion(ResourceBundle bundle) {
        // Alerta de información con botón
        Alert ventanaEmergente = new Alert(AlertType.INFORMATION);
        ventanaEmergente.setTitle("info");
        ventanaEmergente.setContentText(bundle.getString("mensajePrestamoAniadido"));
        Button ocultarBtn = new Button("Aceptar");
        ocultarBtn.setOnAction(e -> {
            ventanaEmergente.hide();
        });
        ventanaEmergente.show();
    }

    
}

