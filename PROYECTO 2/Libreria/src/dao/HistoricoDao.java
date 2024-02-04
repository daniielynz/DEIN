package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.HistoricoPrestamo;
/**
 * Clase que maneja las operaciones de acceso a datos relacionadas con la entidad HistoricoPrestamo.
 */
public class HistoricoDao {

    private ConexionBD conexion;

    /**
     * Carga una lista de registros de la tabla HistoricoPrestamo según los parámetros proporcionados.
     *
     * @param dni    El DNI del alumno para filtrar los registros.
     * @param codigo El código del libro para filtrar los registros.
     * @return Una lista de objetos HistoricoPrestamo que cumplen con los criterios de filtrado.
     */
    public ObservableList<HistoricoPrestamo> cargarHistoricoPrestamos(String dni, String codigo) {
        ObservableList<HistoricoPrestamo> listaHistoricoPrestamos = FXCollections.observableArrayList();
        try {
            conexion = new ConexionBD();

            String consulta;
            if (!codigo.isEmpty()) {
                consulta = "SELECT * FROM Historico_prestamo WHERE codigo_libro LIKE '%" + codigo + "%'";
            } else {
                consulta = "SELECT * FROM Historico_prestamo WHERE dni_alumno LIKE '%" + dni + "%'";
            }

            try (PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    // Guardamos todos los datos
                    int id_prestamo = rs.getInt("id_prestamo");
                    String dni_alumno = rs.getString("dni_alumno");
                    int codigo_libro = rs.getInt("codigo_libro");
                    String fecha_prestamo = rs.getString("fecha_prestamo");
                    String fecha_devolucion = rs.getString("fecha_devolucion");

                    // Creamos el HistoricoPrestamo
                    HistoricoPrestamo historicoPrestamo = new HistoricoPrestamo(id_prestamo, dni_alumno, codigo_libro, fecha_prestamo, fecha_devolucion);
                    listaHistoricoPrestamos.add(historicoPrestamo);
                }
            }
            conexion.closeConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return listaHistoricoPrestamos;
    }
    
    /**
     * Añade un registro a la tabla HistoricoPrestamo.
     *
     * @param historicoPrestamo El objeto HistoricoPrestamo a añadir.
     */
    public void aniadirHistoricoPrestamo(HistoricoPrestamo historicoPrestamo) {
        try {
            conexion = new ConexionBD();
            String consulta = "INSERT INTO Historico_prestamo (dni_alumno, codigo_libro, fecha_prestamo, fecha_devolucion) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta)) {
                // Establecer los parámetros
                pstmt.setString(1, historicoPrestamo.getDni());
                pstmt.setInt(2, historicoPrestamo.getId_libro());
                pstmt.setString(3, historicoPrestamo.getFechaPrestamo());
                pstmt.setString(4, historicoPrestamo.getFechaDevolucion());
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
     * Edita un registro en la tabla HistoricoPrestamo.
     *
     * @param historicoPrestamo El objeto HistoricoPrestamo modificado.
     */
    public void editarHistoricoPrestamo(HistoricoPrestamo historicoPrestamo) {
        try {
            conexion = new ConexionBD();
            String consulta = "UPDATE Historico_prestamo SET dni_alumno = ?, codigo_libro = ?, fecha_prestamo = ?, fecha_devolucion = ? WHERE id_prestamo = ?";

            try (PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta)) {
                // Establecer los parámetros
                pstmt.setString(1, historicoPrestamo.getDni());
                pstmt.setInt(2, historicoPrestamo.getId_libro());
                pstmt.setString(3, historicoPrestamo.getFechaPrestamo());
                pstmt.setString(4, historicoPrestamo.getFechaDevolucion());
                pstmt.setInt(5, historicoPrestamo.getId());
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
     * Borra un registro de la tabla HistoricoPrestamo.
     *
     * @param historicoPrestamo El objeto HistoricoPrestamo a borrar.
     */
    public void borrarHistoricoPrestamo(HistoricoPrestamo historicoPrestamo) {
        try {
            conexion = new ConexionBD();

            // Borrar de la tabla HistoricoPrestamo
            String consulta = "DELETE FROM Historico_prestamo WHERE id_prestamo = " + historicoPrestamo.getId();
            try (PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta)) {
                pstmt.executeUpdate();
            }

            conexion.closeConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene el último ID de la tabla HistoricoPrestamo.
     *
     * @return El último ID incrementado en 1.
     */
    public int ultimoId() {
        try {
            conexion = new ConexionBD();
            String consulta = "SELECT MAX(id_prestamo) AS ID FROM Historico_prestamo";
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
    
}

