package controllers;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import conexion.Propiedades;
import dao.AlumnoDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Alumno;


/**
 * Controlador para la interfaz de edición de alumno.
 */
public class ControllerEditarAlumno implements Initializable {

    // Alumno que se va a editar
    private static Alumno alumno;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfApellido1;

    @FXML
    private TextField tfApellido2;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    // ResourceBundle para manejar recursos específicos del idioma
    private ResourceBundle bundle;

    /**
     * Inicializa la interfaz de edición con los datos del alumno.
     *
     * @param arg0 La URL de inicialización.
     * @param arg1 El ResourceBundle que contiene los recursos específicos del idioma.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Inicializar el ResourceBundle para manejar recursos específicos del idioma
        bundle = arg1;

        // Configurar los campos de texto con los datos del alumno
        tfNombre.setText(alumno.getNombre());
        tfApellido1.setText(alumno.getApellido1());
        tfApellido2.setText(alumno.getApellido2());
    }

    /**
     * Establece el alumno que se va a editar y abre la ventana de edición.
     *
     * @param alumno El alumno que se va a editar.
     */
    public void editarAlumno(Alumno alumno) {
        // Asignar el alumno proporcionado al atributo estático de la clase
        this.alumno = alumno;

        try {
            // Obtener configuración de idioma y región desde un archivo de propiedades
            String idioma = Propiedades.getValor("idioma");
            String region = Propiedades.getValor("region");

            // Establecer la configuración regional por defecto
            Locale.setDefault(new Locale(idioma, region));

            // Cargar los recursos de idioma
            ResourceBundle bundle = ResourceBundle.getBundle("idiomas/messages");

            // Abrir la ventana para editar el alumno
            Stage primaryStage = new Stage();
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("/fxml/editarAlumno.fxml"), bundle);
            Scene scene = new Scene(root);
            primaryStage.setTitle(bundle.getString("tituloModificarAlumno"));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir al abrir la ventana
            e.printStackTrace();
        }
    }
	
    /**
     * Valida los campos de entrada y retorna los errores como una cadena.
     *
     * @return Cadena que contiene los mensajes de error.
     */
    private String validarCampos() {
        String errores = "";

        // Validar el campo de nombre
        if (tfNombre.getText().isEmpty()) {
            errores += bundle.getString("errorNombre") + "\n";
        }

        // Validar el campo de apellido1
        if (tfApellido1.getText().isEmpty()) {
            errores += bundle.getString("errorApellido1") + "\n";
        }

        // Validar el campo de apellido2
        if (tfApellido2.getText().isEmpty()) {
            errores += bundle.getString("errorApellido2") + "\n";
        }

        return errores;
    }

    /**
     * Acción realizada al hacer clic en el botón Guardar.
     *
     * Antes de modificar, valida que los campos de entrada no contengan errores.
     *
     * @param event El evento de acción.
     */
    @FXML
    void accionGuardar(ActionEvent event) {
        // Antes de modificar, validamos que los campos de entrada no contengan errores
        String errores = validarCampos();

        if (errores.isEmpty()) {
            try {
                AlumnoDao alumnoDao = new AlumnoDao();

                // Le ponemos los datos nuevos al alumno
                alumno.setNombre(tfNombre.getText().toString());
                alumno.setApellido1(tfApellido1.getText().toString());
                alumno.setApellido2(tfApellido2.getText().toString());

                // Se realiza la edición del alumno
                alumnoDao.editarAlumno(alumno);

                // Mostrar una alerta de información
                alertaInformacion();
            } catch (Exception e) {
                // Manejar cualquier excepción que pueda ocurrir, aunque no se realiza ninguna acción específica en caso de error
            }
        } else {
            // Mostrar una alerta de error con los mensajes de error
            alertaError(errores);
        }
    }
	
    /**
     * Acción realizada al hacer clic en el botón Cancelar.
     * Cierra la ventana actual al hacer clic en el botón Cancelar.
     *
     * @param event El evento de acción.
     */
    @FXML
    void accionCancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    /**
     * Muestra una ventana de alerta de error con un mensaje específico.
     *
     * @param mensaje El mensaje de error a mostrar.
     */
    private void alertaError(String mensaje) {
        Alert ventanaEmergente = new Alert(AlertType.ERROR);
        ventanaEmergente.setTitle("info");
        ventanaEmergente.setContentText(mensaje);

        // Botón para ocultar la ventana de alerta
        Button ocultarBtn = new Button("Aceptar");
        ocultarBtn.setOnAction(e -> {
            ventanaEmergente.hide();
        });

        // Mostrar la ventana de alerta
        ventanaEmergente.show();
    }

    /**
     * Muestra una ventana de alerta de información con un mensaje específico.
     */
    private void alertaInformacion() {
        Alert ventanaEmergente = new Alert(AlertType.INFORMATION);
        ventanaEmergente.setTitle("info");
        ventanaEmergente.setContentText(bundle.getString("mensajeAlumnoModificado"));

        // Botón para ocultar la ventana de alerta
        Button ocultarBtn = new Button("Aceptar");
        ocultarBtn.setOnAction(e -> {
            ventanaEmergente.hide();
        });

        // Mostrar la ventana de alerta
        ventanaEmergente.show();
    }
	
}
