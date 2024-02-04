package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;

import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import model.Alumno;

/**
 * Clase que maneja las operaciones de acceso a datos para la entidad Alumno.
 */
public class AlumnoDao {
    private ConexionBD conexion;

    /**
     * Carga una lista de alumnos que coinciden con una cadena de búsqueda en el nombre.
     *
     * @param cadena La cadena de búsqueda para el nombre del alumno.
     * @return Una lista observable de alumnos que coinciden con la cadena de búsqueda.
     */
    public ObservableList<Alumno> cargarAlumnos(String cadena) {
        ObservableList<Alumno> listaAlumnos = FXCollections.observableArrayList();
        try {
            conexion = new ConexionBD();
            String consulta = "SELECT * FROM Alumno WHERE nombre LIKE '%" + cadena + "%'";

            try (PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    // Guardamos todos los datos
                    String dni = rs.getString("dni");
                    String nombre = rs.getString("nombre");
                    String apellido1 = rs.getString("apellido1");
                    String apellido2 = rs.getString("apellido2");

                    // Creamos el Alumno
                    Alumno a = new Alumno(dni, nombre, apellido1, apellido2);
                    listaAlumnos.add(a);
                }
            }
            conexion.closeConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return listaAlumnos;
    }
    
    /**
     * Añade un nuevo alumno a la base de datos.
     *
     * @param alumno El objeto Alumno a añadir.
     * @param bundle El ResourceBundle para manejar mensajes internacionales.
     */
    public void aniadirAlumno(Alumno alumno, ResourceBundle bundle) {
        try {
            conexion = new ConexionBD();

            // Añadir en la tabla de Alumno
            String consulta = "INSERT INTO Alumno VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta)) {
                // Establecer los parámetros
                pstmt.setString(1, alumno.getDni());
                pstmt.setString(2, alumno.getNombre());
                pstmt.setString(3, alumno.getApellido1());
                pstmt.setString(4, alumno.getApellido2());

                // Ejecutar la inserción
                pstmt.executeUpdate();
            }

            conexion.closeConexion();

            alertaInformacion(bundle);
        } catch (SQLIntegrityConstraintViolationException e) {
            // Manejar la excepción de clave duplicada aquí
            alertaError(bundle);
        } catch (SQLException e) {
            // Manejar otras excepciones de SQL
            e.printStackTrace();
        }
    }

    /**
     * Edita los datos de un alumno en la base de datos.
     *
     * @param alumno El objeto Alumno con los datos actualizados.
     */
    public void editarAlumno(Alumno alumno) {
        try {
            conexion = new ConexionBD();

            // Actualizar Alumno
            String consultaAlumno = "UPDATE Alumno SET nombre = ?, apellido1 = ?, apellido2 = ? WHERE dni = ?";
            try (PreparedStatement pstmtAlumno = conexion.getConexion().prepareStatement(consultaAlumno)) {
                // Establecer los parámetros
                pstmtAlumno.setString(1, alumno.getNombre());
                pstmtAlumno.setString(2, alumno.getApellido1());
                pstmtAlumno.setString(3, alumno.getApellido2());
                pstmtAlumno.setString(4, alumno.getDni());

                // Ejecutar la actualización
                pstmtAlumno.executeUpdate();
            }

            conexion.closeConexion();
        } catch (SQLException e) {
            // Manejar excepciones de SQL
            e.printStackTrace();
        }
    }

    
    /**
     * Borra un alumno de la base de datos, incluyendo referencias en las tablas relacionadas.
     *
     * @param alumno El objeto Alumno a borrar.
     */
    public void borrarAlumno(Alumno alumno) {
        try {
            conexion = new ConexionBD();

            // Verificar si existen préstamos relacionados en la tabla Prestamo
            String existePrestamosQuery = "SELECT COUNT(*) FROM Prestamo WHERE dni_alumno = ?";
            try (PreparedStatement pstmtExistePrestamos = conexion.getConexion().prepareStatement(existePrestamosQuery)) {
                pstmtExistePrestamos.setString(1, alumno.getDni());
                ResultSet rs = pstmtExistePrestamos.executeQuery();
                rs.next();
                int count = rs.getInt(1);

                // Si hay préstamos relacionados en la tabla Prestamo, borrarlos de la tabla Historico_prestamo
                if (count > 0) {
                    String borrarHistoricoPrestamosQuery = "DELETE FROM Historico_prestamo WHERE dni_alumno = ?";
                    try (PreparedStatement pstmtBorrarHistoricoPrestamos = conexion.getConexion().prepareStatement(borrarHistoricoPrestamosQuery)) {
                        pstmtBorrarHistoricoPrestamos.setString(1, alumno.getDni());
                        pstmtBorrarHistoricoPrestamos.executeUpdate();
                    }
                }
            }

            // Verificar si existen préstamos relacionados en la tabla Historico_prestamo
            String existeHistoricoPrestamosQuery = "SELECT COUNT(*) FROM Historico_prestamo WHERE dni_alumno = ?";
            try (PreparedStatement pstmtExisteHistoricoPrestamos = conexion.getConexion().prepareStatement(existeHistoricoPrestamosQuery)) {
                pstmtExisteHistoricoPrestamos.setString(1, alumno.getDni());
                ResultSet rs = pstmtExisteHistoricoPrestamos.executeQuery();
                rs.next();
                int count = rs.getInt(1);

                // Si hay préstamos relacionados en la tabla Historico_prestamo, borrarlos de la tabla Prestamo
                if (count > 0) {
                    String borrarPrestamosQuery = "DELETE FROM Prestamo WHERE dni_alumno = ?";
                    try (PreparedStatement pstmtBorrarPrestamos = conexion.getConexion().prepareStatement(borrarPrestamosQuery)) {
                        pstmtBorrarPrestamos.setString(1, alumno.getDni());
                        pstmtBorrarPrestamos.executeUpdate();
                    }
                }
            }

            // Borrar al alumno de la tabla Alumno
            String borrarAlumnoQuery = "DELETE FROM Alumno WHERE dni = ?";
            try (PreparedStatement pstmtBorrarAlumno = conexion.getConexion().prepareStatement(borrarAlumnoQuery)) {
                pstmtBorrarAlumno.setString(1, alumno.getDni());
                pstmtBorrarAlumno.executeUpdate();
            }

            conexion.closeConexion();
        } catch (SQLException e) {
            // Manejar excepciones de SQL
            e.printStackTrace();
        }
    }
    
    public int ultimoId() {
        try {
            conexion = new ConexionBD();
            String consulta = "SELECT MAX(dni) AS ID FROM Alumnos";
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
     * Muestra una ventana emergente de error.
     *
     * @param bundle El ResourceBundle para obtener los mensajes.
     */
    private void alertaError(ResourceBundle bundle) {
        Alert ventanaEmergente = new Alert(AlertType.ERROR);
        ventanaEmergente.setTitle("info");
        ventanaEmergente.setContentText(bundle.getString("mensajeErrorAlumnoAniadido"));
        Button ocultarBtn = new Button("Aceptar");
        ocultarBtn.setOnAction(e -> ventanaEmergente.hide());
        ventanaEmergente.show();
    }

    /**
     * Muestra una ventana emergente de información.
     *
     * @param bundle El ResourceBundle para obtener los mensajes.
     */
    private void alertaInformacion(ResourceBundle bundle) {
        Alert ventanaEmergente = new Alert(AlertType.INFORMATION);
        ventanaEmergente.setTitle("info");
        ventanaEmergente.setContentText(bundle.getString("mensajeAlumnoAniadido"));
        Button ocultarBtn = new Button("Aceptar");
        ocultarBtn.setOnAction(e -> ventanaEmergente.hide());
        ventanaEmergente.show();
    }
    
}

