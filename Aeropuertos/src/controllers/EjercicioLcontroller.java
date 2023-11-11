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
    	Usuario usu = new Usuario(tfUsuario.getText().toString(), passwordField.getText().toString());
    	validarUsuario(usu);
    }
    
    public void validarUsuario(Usuario usu){
    	try {
            UsuarioDao usuarioDao = new UsuarioDao();
            boolean existe = usuarioDao.existeUsuario(usu);
            if(existe) {
            	listadoAeropuertos();
            }else {
            	alertaError("Usuario o Contraseña incorrectos");
            }
        } catch(Exception e) {}
    }
    
 // Metodos de diferentes ventanas emergentes
    private void alertaError(String mensaje) {
    	// Alerta de error con boton
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
    		Stage primaryStage = new Stage();
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/EjercicioLfxmlListado.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("AVIONES-AEROPUERTOS");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

}
