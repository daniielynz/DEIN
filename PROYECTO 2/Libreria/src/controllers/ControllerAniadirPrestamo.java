package controllers;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import conexion.ConexionBD;
import dao.AlumnoDao;
import dao.LibroDao;
import dao.PrestamoDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Alumno;
import model.Libro;
import model.Prestamo;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Controlador para la interfaz de añadir préstamo.
 */
public class ControllerAniadirPrestamo implements Initializable {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<Alumno> cbAlumnos;

    @FXML
    private ComboBox<Libro> cbLibros;

    @FXML
    private DatePicker dpFechaPrestamo;

    private ResourceBundle bundle;

    /**
     * Método de inicialización llamado automáticamente al cargar la interfaz.
     *
     * @param arg0 La URL de inicialización.
     * @param arg1 El ResourceBundle que contiene los recursos específicos del idioma.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Inicializar el ResourceBundle para manejar recursos específicos del idioma
        bundle = arg1;

        // Cargar la lista de alumnos desde el DAO de Alumnos
        AlumnoDao alumnosDao = new AlumnoDao();
        ObservableList<Alumno> listaAlumnos = alumnosDao.cargarAlumnos("");
        cbAlumnos.setItems(listaAlumnos);

        // Cargar la lista de libros desde el DAO de Libros
        LibroDao librosDao = new LibroDao();
        ObservableList<Libro> listaLibros = librosDao.cargarLibros("");
        cbLibros.setItems(listaLibros);
    }

    /**
     * Maneja la acción del botón Cancelar.
     * Cierra la ventana actual al hacer clic en el botón Cancelar.
     *
     * @param event El evento de acción asociado al botón Cancelar.
     */
    @FXML
    void accionCancelar(ActionEvent event) {
        // Obtener la ventana actual y cerrarla al hacer clic en el botón Cancelar
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Maneja la acción del botón Guardar.
     * Valida la entrada, añade un nuevo préstamo si la entrada es válida,
     * y muestra una alerta de error en caso contrario.
     *
     * @param event El evento de acción asociado al botón Guardar.
     */
    @FXML
    private void accionGuardar(ActionEvent event) {
        // Validar la entrada y realizar acciones correspondientes
        String errores = validar();

        if (errores.isEmpty()) {
            // Dar formato a la fecha
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fechaFormateada = dpFechaPrestamo.getValue().atStartOfDay().format(formatter);

            // Crear el objeto dao
            PrestamoDao dao = new PrestamoDao();
            
            // Crear un nuevo objeto Prestamo
            Prestamo prestamo = new Prestamo(dao.ultimoId(),
                    this.cbAlumnos.getSelectionModel().getSelectedItem().getDni(),
                    this.cbLibros.getSelectionModel().getSelectedItem().getCodigo(),
                    fechaFormateada
            );
            
            // Añadir el préstamo utilizando el DAO
            dao.aniadirPrestamo(prestamo, bundle);
            
            generarInforme(prestamo);

            // Limpiar campos después de añadir el préstamo
            vaciarCampos();
        } else {
            // Mostrar alerta de error si la validación falla
            alertaError(errores);
        }
    }
    
    /**
     * Método para generar un informe utilizando JasperReports y mostrarlo en un visor JasperViewer.
     * Se espera que el informe esté definido en un archivo .jasper y se llenará con datos de una base de datos.
     */
    private void generarInforme(Prestamo prestamo) {
        try {
            // Establecer conexión a la base de datos usando la clase ConexionBD
            ConexionBD con = new ConexionBD();

            // Cargar un mapa con los parametros que queremos pasarle (El id del prestamo que acabamos de crear)
            Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id_prestamo_creado", prestamo.getId());
			
			// Cargar el informe Jasper desde el archivo .jasper
            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/jasper/informe1/informe1.jasper"));

            // Llenar el informe con datos desde la base de datos utilizando la conexión
            JasperPrint jprint = JasperFillManager.fillReport(report, parameters, con.getConexion());

            // Crear un visor de informes Jasper y mostrarlo en pantalla
            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setVisible(true);
        } catch (Exception e) {
            // Manejar cualquier excepción

            // Mostrar un cuadro de diálogo de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Ha ocurrido un error");
            alert.showAndWait();

            // Imprimir la traza de la excepción para el registro
            e.printStackTrace();
        }
    }


    /**
     * Valida la entrada antes de añadir un préstamo.
     *
     * @return Una cadena que contiene mensajes de error, o una cadena vacía si la validación es exitosa.
     */
    private String validar() {
        // Inicializar la cadena de errores
        String errores = "";

        // Validar la fecha de préstamo
        if (dpFechaPrestamo.getValue() == null) {
            errores += bundle.getString("errorFechaPrestamo") + "\n";
        }

        // Validar la selección del alumno
        if (this.cbAlumnos.getSelectionModel().getSelectedItem() == null) {
            errores += bundle.getString("errorAlumnoPrestamo") + "\n";
        }

        // Validar la selección del libro
        if (this.cbLibros.getSelectionModel().getSelectedItem() == null) {
            errores += bundle.getString("errorLibroPrestamo") + "\n";
        }

        // Devolver la cadena de errores
        return errores;
    }
    
    /**
     * Limpia los campos de la interfaz.
     */
    private void vaciarCampos() {
        // Establecer la fecha de préstamo a nulo y seleccionar el primer elemento en los ComboBox
        dpFechaPrestamo.setValue(null);
        cbAlumnos.getSelectionModel().select(0);
        cbLibros.getSelectionModel().select(0);
    }

    /**
     * Muestra una alerta de error con un mensaje específico.
     *
     * @param mensaje El mensaje de error a mostrar.
     */
    private void alertaError(String mensaje) {
        // Crear una ventana de alerta de tipo ERROR
        Alert ventanaEmergente = new Alert(AlertType.ERROR);
        ventanaEmergente.setTitle("info");
        ventanaEmergente.setContentText(mensaje);

        // Crear un botón "Aceptar" y asignar un evento para ocultar la ventana al hacer clic
        Button ocultarBtn = new Button("Aceptar");
        ocultarBtn.setOnAction(e -> {
            ventanaEmergente.hide();
        });

        // Mostrar la ventana de alerta
        ventanaEmergente.show();
    }

}


