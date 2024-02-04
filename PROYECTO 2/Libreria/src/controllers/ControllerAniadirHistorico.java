package controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import dao.HistoricoDao;
import dao.LibroDao;
import dao.PrestamoDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.HistoricoPrestamo;
import model.Prestamo;

/**
 * Controlador para la interfaz de añadir historial de préstamo.
 */
public class ControllerAniadirHistorico implements Initializable {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<Prestamo> cbPrestamos;

    private ResourceBundle bundle;

    /**
     * Método de inicialización llamado automáticamente al cargar la interfaz.
     *
     * @param arg0 La URL de inicialización.
     * @param arg1 El ResourceBundle que contiene los recursos específicos del idioma.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        bundle = arg1;

        // Cargar la lista de préstamos en el ComboBox al iniciar la interfaz
        PrestamoDao prestamoDao = new PrestamoDao();
        ObservableList<Prestamo> listaPrestamos = prestamoDao.cargarPrestamos("");
        cbPrestamos.setItems(listaPrestamos);
    }

    /**
     * Maneja la acción del botón Cancelar.
     * Cierra la ventana actual al hacer clic en el botón Cancelar.
     *
     * @param event El evento de acción asociado al botón Cancelar.
     */
    @FXML
    void accionCancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Maneja la acción del botón Guardar.
     * Valida la entrada, guarda el historial de préstamo, cambia el estado del libro,
     * borra el préstamo y muestra una alerta de información si la entrada es válida,
     * o muestra una alerta de error en caso contrario.
     *
     * @param event El evento de acción asociado al botón Guardar.
     */
    @FXML
    private void accionGuardar(ActionEvent event) {
        // Validar la entrada
        String errores = validar();

        if (errores.isEmpty()) {
            // Guardar el préstamo seleccionado
            Prestamo prestamo = this.cbPrestamos.getSelectionModel().getSelectedItem();

            // Obtener la fecha y hora actual
            LocalDateTime fechaHoraActual = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fechaHoy = fechaHoraActual.format(formatter);

            // Crear el historial de préstamos
            HistoricoPrestamo historico = new HistoricoPrestamo(prestamo.getDni(), prestamo.getCodigoLibro(), prestamo.getFechaPrestamo(), fechaHoy);
            HistoricoDao dao = new HistoricoDao();
            dao.aniadirHistoricoPrestamo(historico);

            // Cambiar el estado del libro
            LibroDao daoLibros = new LibroDao();
            daoLibros.cambiarEstado(prestamo.getCodigoLibro());

            // Borrar el préstamo
            PrestamoDao daoPrestamos = new PrestamoDao();
            daoPrestamos.borrarPrestamo(prestamo);

            // Limpiar campos después de realizar las acciones
            vaciarCampos();
            alertaInformacion();
        } else {
            // Mostrar alerta de error si la validación falla
            alertaError(errores);
        }
    }

    /**
     * Realiza la validación de los campos del formulario.
     *
     * @return Una cadena que contiene los mensajes de error encontrados.
     */
    private String validar() {
        String errores = "";

        if (cbPrestamos.getValue() == null) {
            errores += bundle.getString("errorPrestamoHistorico") + "\n";
        }

        return errores;
    }

    /**
     * Limpia los campos del formulario después de realizar las acciones.
     */
    private void vaciarCampos() {
        cbPrestamos.getSelectionModel().select(0);
    }
    
    /**
     * Muestra una ventana emergente de error con un mensaje personalizado.
     *
     * @param mensaje El mensaje de error a mostrar en la ventana emergente.
     */
    private void alertaError(String mensaje) {
        // Crear una nueva alerta de error con botón
        Alert ventanaEmergente = new Alert(AlertType.ERROR);
        ventanaEmergente.setTitle("Error");
        ventanaEmergente.setContentText(mensaje);

        // Configurar el botón de aceptar
        Button ocultarBtn = new Button("Aceptar");
        ocultarBtn.setOnAction(e -> {
            ventanaEmergente.hide();
        });

        // Mostrar la ventana emergente
        ventanaEmergente.show();
    }

    /**
     * Muestra una ventana emergente de información con un mensaje específico.
     */
    private void alertaInformacion() {
        // Crear una nueva alerta de información con botón
        Alert ventanaEmergente = new Alert(AlertType.INFORMATION);
        ventanaEmergente.setTitle("Información");
        ventanaEmergente.setContentText(bundle.getString("aniadidoHistorico"));

        // Configurar el botón de aceptar
        Button ocultarBtn = new Button("Aceptar");
        ocultarBtn.setOnAction(e -> {
            ventanaEmergente.hide();
        });

        // Mostrar la ventana emergente
        ventanaEmergente.show();
    }

}


