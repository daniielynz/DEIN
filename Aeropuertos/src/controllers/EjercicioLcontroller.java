package controllers;

import dao.UsuarioDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Usuario;

public class EjercicioLcontroller {

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField tfUsuario;

    @FXML
    void accionLogin(MouseEvent event) {
        // Recupera el nombre de usuario y la contraseña del formulario
        Usuario usu = new Usuario(tfUsuario.getText().toString(), passwordField.getText().toString());
        // Llama a la función para validar el usuario
        validarUsuario(usu);
    }

    public void validarUsuario(Usuario usu){
        try {
            // Verifica si el usuario existe en la base de datos
            UsuarioDao usuarioDao = new UsuarioDao();
            boolean existe = usuarioDao.existeUsuario(usu);
            if(existe) {
                // Si el usuario es válido, muestra el listado de aeropuertos
                listadoAeropuertos();
            } else {
                // Si el usuario no es válido, muestra un mensaje de error
                alertaError("Usuario o Contraseña incorrectos");
            }
        } catch(Exception e) {
            // Maneja cualquier excepción que pueda ocurrir durante la validación del usuario
            e.printStackTrace();
        }
    }

    // Métodos de diferentes ventanas emergentes

    private void alertaError(String mensaje) {
        // Muestra una ventana emergente de error con un botón de aceptar
        Alert ventanaEmergente = new Alert(AlertType.ERROR);
        ventanaEmergente.setTitle("info");
        ventanaEmergente.setContentText(mensaje);
        Button ocultarBtn = new Button("Aceptar");
        ocultarBtn.setOnAction(e -> {
            ventanaEmergente.hide();
        });
        ventanaEmergente.show();
    }

    public void listadoAeropuertos(){
        try {
            // Abre la ventana que muestra el listado de aeropuertos
            Stage primaryStage = new Stage();
            GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/EjercicioLfxmlListado.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("AVIONES-AEROPUERTOS");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            // Maneja cualquier excepción que pueda ocurrir al abrir la ventana de listado de aeropuertos
            e.printStackTrace();
        }
    }


}
