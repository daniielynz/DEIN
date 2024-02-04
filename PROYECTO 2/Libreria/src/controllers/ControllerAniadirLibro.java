package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dao.LibroDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Libro;

/**
 * Controlador para la interfaz de añadir libro.
 */
public class ControllerAniadirLibro implements Initializable {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField tfTitulo;

    @FXML
    private TextField tfAutor;

    @FXML
    private TextField tfEditorial;

    @FXML
    private TextField tfEstado;

    @FXML
    private TextField tfBaja;

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
     * Maneja la acción de guardar un libro.
     * Valida la entrada, añade un nuevo libro si la entrada es válida,
     * y muestra una alerta de error en caso contrario.
     *
     * @param event El evento de acción asociado al botón Guardar.
     */
    @FXML
    private void accionGuardar(ActionEvent event) {
        // Validar la entrada y realizar acciones correspondientes
        String errores = validarCampos();

        if (errores.isEmpty()) {
            // Obtener datos del formulario
            String titulo = tfTitulo.getText();
            String autor = tfAutor.getText();
            String editorial = tfEditorial.getText();
            String estado = tfEstado.getText();
            int baja = Integer.parseInt(tfBaja.getText());

            // Crear un nuevo objeto Libro
            Libro libro = new Libro(titulo, autor, editorial, estado, baja);

            // Añadir el libro utilizando el DAO
            LibroDao dao = new LibroDao();
            dao.aniadirLibro(libro, bundle);

            // Limpiar campos después de añadir el libro
            vaciarCampos();
        } else {
            // Mostrar alerta de error si la validación falla
            alertaError(errores);
        }
    }
    
    /**
     * Método que realiza la validación de los campos del formulario para añadir un nuevo libro.
     * Devuelve una cadena que contiene mensajes de error encontrados durante la validación.
     *
     * @return Una cadena que contiene mensajes de error, o una cadena vacía si la validación es exitosa.
     */
    private String validarCampos() {
        // Inicializar la cadena de errores
        String errores = "";

        // Validar el campo de título
        if (tfTitulo.getText().isEmpty()) {
            errores += bundle.getString("errorTituloLibro") + "\n";
        }

        // Validar el campo de autor
        if (tfAutor.getText().isEmpty()) {
            errores += bundle.getString("errorAutorLibro") + "\n";
        }

        // Validar el campo de editorial
        if (tfEditorial.getText().isEmpty()) {
            errores += bundle.getString("errorEditorialLibro") + "\n";
        }

        // Validar el campo de estado
        if (tfEstado.getText().isEmpty()) {
            errores += bundle.getString("errorEstadoLibro") + "\n";
        } else {
            // Verificar si el estado es uno de los tipos válidos
            if (!tfEstado.getText().equalsIgnoreCase("nuevo") && !tfEstado.getText().equalsIgnoreCase("usado nuevo")
                    && !tfEstado.getText().equalsIgnoreCase("usado seminuevo") && !tfEstado.getText().equalsIgnoreCase("usado estropeado")
                    && !tfEstado.getText().equalsIgnoreCase("restaurado")) {
                errores += bundle.getString("errorTiposEstadoLibro") + "\n";
            }
        }

        // Validar el campo de baja
        if (tfBaja.getText().isEmpty()) {
            errores += bundle.getString("errorBajaLibro") + "\n";
        } else {
            try {
                // Intentar convertir el valor a un entero
                Integer.parseInt(tfBaja.getText());

                // Verificar si el valor es 0 o 1
                if (!tfBaja.getText().equals("1") && !tfBaja.getText().equals("0")) {
                    errores += bundle.getString("error0o1Libro") + "\n";
                }

            } catch (NumberFormatException e) {
                // Capturar excepción si no se puede convertir a un entero
                errores += bundle.getString("errorIntegerLibro") + "\n";
            }
        }

        // Devolver la cadena de errores
        return errores;
    }

    
    /**
     * Limpia los campos de entrada en un formulario.
     */
    private void vaciarCampos() {
        // Limpiar los campos de texto
        tfTitulo.setText("");
        tfAutor.setText("");
        tfEditorial.setText("");
        tfEstado.setText("");
        tfBaja.setText("");
    }

    /**
     * Muestra una ventana emergente de error con un mensaje personalizado.
     *
     * @param mensaje El mensaje de error a mostrar en la ventana emergente.
     */
    private void alertaError(String mensaje) {
        // Crear una nueva alerta de error con botón
        Alert ventanaEmergente = new Alert(AlertType.ERROR);
        ventanaEmergente.setTitle("Información");
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


