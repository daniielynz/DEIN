package controllers;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import conexion.Propiedades;
import dao.LibroDao;
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
import model.Libro;

/**
 * Clase controladora para la interfaz de edición de un libro.
 */
public class ControllerEditarLibro implements Initializable {

    // Atributo estático para almacenar el libro a editar
    private static Libro libro;

    // Campos de texto de la interfaz gráfica
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

    // Botones de la interfaz gráfica
    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    // ResourceBundle utilizado para la internacionalización
    private ResourceBundle bundle;
    
    /**
     * Inicializa la interfaz de edición de libro con los datos del libro y establece los valores iniciales de los campos.
     *
     * @param arg0  La URL de inicialización.
     * @param arg1  El ResourceBundle utilizado para la internacionalización.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        bundle = arg1;
        tfTitulo.setText(libro.getTitulo());
        tfAutor.setText(libro.getAutor());
        tfEditorial.setText(libro.getEditorial());
        tfEstado.setText(libro.getEstado());
        tfBaja.setText(libro.getBaja() + "");
    }

	
    /**
     * Inicializa la interfaz de edición de libro con los datos del libro proporcionado.
     *
     * @param libro El libro a editar.
     */
    public void editarLibro(Libro libro) {
        this.libro = libro;

        try {
            String idioma = Propiedades.getValor("idioma");
            String region = Propiedades.getValor("region");

            // Establecer la configuración regional por defecto
            Locale.setDefault(new Locale(idioma, region));

            // Cargar los recursos de idioma
            ResourceBundle bundle = ResourceBundle.getBundle("idiomas/messages");

            // Abre la ventana para editar un libro
            Stage primaryStage = new Stage();
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("/fxml/editarLibro.fxml"), bundle);
            Scene scene = new Scene(root);
            primaryStage.setTitle(bundle.getString("tituloModificarLibro"));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            // Maneja cualquier excepción que pueda ocurrir al abrir la ventana
            e.printStackTrace();
        }
    }
	
    /**
     * Método invocado al hacer clic en el botón "Guardar" para editar un libro.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void accionGuardar(ActionEvent event) {
        // Antes de modificar, validamos que los campos de entrada no contengan errores
        String errores = validarCampos();

        if (errores.isEmpty()) {
            try {
                LibroDao libroDao = new LibroDao();
                // Le ponemos los datos nuevos al Libro
                libro.setTitulo(this.tfTitulo.getText().toString());
                libro.setAutor(this.tfAutor.getText().toString());
                libro.setEditorial(this.tfEditorial.getText().toString());
                libro.setEstado(this.tfEstado.getText().toString());
                libro.setBaja(Integer.parseInt(this.tfBaja.getText().toString()));

                // Llamamos al método del DAO para editar el libro en la base de datos
                libroDao.editarLibro(libro);

                // Mostramos una alerta informativa indicando que el libro se ha modificado correctamente
                alertaInformacion(bundle.getString("mensajeLibroModificado"));
            } catch (Exception e) {
                // Manejamos cualquier excepción que pueda ocurrir, aunque no se realiza ninguna acción específica en caso de error
                e.printStackTrace();
            }
        } else {
            // Mostramos una alerta de error con los mensajes de error
            alertaError(errores);
        }
    }

	
    /**
     * Método privado utilizado para validar los campos de entrada al editar un libro.
     *
     * @return Una cadena que contiene mensajes de error si existen, o una cadena vacía si no hay errores.
     */
    private String validarCampos() {
        String errores = "";

        // Validar el campo del título del libro
        if(tfTitulo.getText().isEmpty()) {
            errores += bundle.getString("errorTituloLibro") + "\n";
        }

        // Validar el campo del autor del libro
        if(tfAutor.getText().isEmpty()) {
            errores += bundle.getString("errorAutorLibro") + "\n";
        }

        // Validar el campo de la editorial del libro
        if(tfEditorial.getText().isEmpty()) {
            errores += bundle.getString("errorEditorialLibro") + "\n";
        }

        // Validar el campo del estado del libro
        if(tfEstado.getText().isEmpty()) {
            errores += bundle.getString("errorEstadoLibro") + "\n";
        } else {
            // Validar que el estado del libro sea uno de los valores permitidos
            if(!tfEstado.getText().equalsIgnoreCase("nuevo") && !tfEstado.getText().equalsIgnoreCase("usado nuevo") && !tfEstado.getText().equalsIgnoreCase("usado seminuevo") && !tfEstado.getText().equalsIgnoreCase("usado estropeado") && !tfEstado.getText().equalsIgnoreCase("restaurado")) {
                errores += bundle.getString("errorTiposEstadoLibro") + "\n";
            }
        }

        // Validar el campo de la baja del libro
        if(tfBaja.getText().isEmpty()) {
            errores += bundle.getString("errorBajaLibro") + "\n";
        } else {
            try {
                // Validar que la baja del libro sea un valor numérico entero
                Integer.parseInt(tfBaja.getText());

                // Validar que la baja del libro sea 0 o 1
                if(!tfBaja.getText().equals("1") && !tfBaja.getText().equals("0")) {
                    errores += bundle.getString("error0o1Libro") + "\n";
                }

            } catch (NumberFormatException e) {
                // Manejar la excepción si el valor no es un número entero
                errores += bundle.getString("errorIntegerLibro") + "\n";
            }
        }

        return errores;
    }

	
    /**
     * Método que se activa cuando se presiona el botón Cancelar.
     *
     * @param event Evento de acción generado por la interfaz gráfica.
     */
    @FXML
    void accionCancelar(ActionEvent event) {
        // Cierra la ventana actual al hacer clic en el botón Cancelar
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    /**
     * Muestra una alerta de error con un mensaje especificado.
     *
     * @param mensaje Mensaje de error a mostrar en la alerta.
     */
    private void alertaError(String mensaje) {
        // Alerta de error con botón
        Alert ventanaEmergente = new Alert(AlertType.ERROR);
        ventanaEmergente.setTitle("info");
        ventanaEmergente.setContentText(mensaje);
        Button ocultarBtn = new Button("Aceptar");
        ocultarBtn.setOnAction(e -> {
            ventanaEmergente.hide();
        });
        ventanaEmergente.show();
    }

    /**
     * Muestra una alerta de información con un mensaje especificado.
     *
     * @param mensaje Mensaje de información a mostrar en la alerta.
     */
    private void alertaInformacion(String mensaje) {
        // Alerta de información con botón
        Alert ventanaEmergente = new Alert(AlertType.INFORMATION);
        ventanaEmergente.setTitle("info");
        ventanaEmergente.setContentText(mensaje);
        Button ocultarBtn = new Button("Aceptar");
        ocultarBtn.setOnAction(e -> {
            ventanaEmergente.hide();
        });
        ventanaEmergente.show();
    }
	
}
