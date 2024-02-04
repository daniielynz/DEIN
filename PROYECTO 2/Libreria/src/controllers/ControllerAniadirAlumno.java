package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import dao.AlumnoDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Alumno;

/**
 * Controlador para la interfaz de añadir alumno.
 * Implementa la interfaz Initializable de JavaFX.
 */
public class ControllerAniadirAlumno implements Initializable {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField tfDni;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfApellido1;

    @FXML
    private TextField tfApellido2;

    private ResourceBundle bundle;

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
     * Método de inicialización llamado automáticamente al cargar la interfaz.
     *
     * @param arg0 La URL de inicialización.
     * @param arg1 El ResourceBundle que contiene los recursos específicos del idioma.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        bundle = arg1;
    }
    
    /**
     * Maneja la acción del botón Guardar.
     * Valida la entrada, añade un nuevo alumno si la entrada es válida,
     * y muestra una alerta de error en caso contrario.
     *
     * @param event El evento de acción asociado al botón Guardar.
     */
    @FXML
    private void accionGuardar(ActionEvent event) {
        // Validar la entrada
        String errores = validar();

        if (errores.isEmpty()) {
            // Obtener datos del formulario
            String dni = tfDni.getText();
            String nombre = tfNombre.getText();
            String apellido1 = tfApellido1.getText();
            String apellido2 = tfApellido2.getText();

            // Crear un nuevo objeto Alumno
            Alumno alumno = new Alumno(dni, nombre, apellido1, apellido2);

            // Añadir el alumno utilizando el DAO
            AlumnoDao dao = new AlumnoDao();
            dao.aniadirAlumno(alumno, bundle);

            // Limpiar campos después de añadir el alumno
            vaciarCampos();
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

        if (tfDni.getText().isEmpty()) {
            errores += bundle.getString("errorDni") + "\n";
        } else {
            String dni = tfDni.getText();
            if (!dni.matches("\\d{8}[a-zA-Z]")) {
                errores += bundle.getString("errorDni2") + "\n";
            }
        }

        if (tfNombre.getText().isEmpty()) {
            errores += bundle.getString("errorNombre") + "\n";
        }

        if (tfApellido1.getText().isEmpty()) {
            errores += bundle.getString("errorApellido1") + "\n";
        }

        if (tfApellido2.getText().isEmpty()) {
            errores += bundle.getString("errorApellido2") + "\n";
        }

        return errores;
    }
    
    /**
     * Vacía los campos de texto en la interfaz del formulario.
     */
    private void vaciarCampos() {
        tfDni.setText("");
        tfNombre.setText("");
        tfApellido1.setText("");
        tfApellido2.setText("");
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

}


