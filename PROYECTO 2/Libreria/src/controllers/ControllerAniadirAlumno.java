package controllers;

import dao.AlumnoDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Alumno;

public class ControllerAniadirAlumno {

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

    @FXML
    void accionCancelar(ActionEvent event) {
    	// Cierra la ventana actual al hacer clic en el botón Cancelar
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void accionGuardar(ActionEvent event) {
    	
    	String errores = validar();
    	
    	if(errores.isEmpty()) {
    		String dni = tfDni.getText();
    		String nombre = tfNombre.getText();
    		String apellido1 = tfApellido1.getText();
    		String apellido2 = tfApellido2.getText();
    		
    		Alumno alumno = new Alumno(dni, nombre, apellido1, apellido2);
	    	// Añadir deportistas
    		AlumnoDao dao = new AlumnoDao();
	    	dao.aniadirAlumno(alumno);
	    	
	    	vaciarCampos();
    	}else {
    		alertaError(errores);
    	}
    }
    
    private String validar() {
    	String errores = "";
    	
    	if(tfDni.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo dni\n";
        }
    	
    	if(tfNombre.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo nombre\n";
        }
    	
    	if(tfApellido1.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo del Primer apellido\n";
        }
    	
    	if(tfApellido2.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo del Segundo apellido\n";
        }
    	
    	return errores;
    }
    
    private void vaciarCampos() {
    	tfDni.setText("");
    	tfNombre.setText("");
    	tfApellido1.setText("");
    	tfApellido2.setText("");
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

}


