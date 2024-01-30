package controllers;

import dao.LibroDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Libro;

public class ControllerAniadirLibro {

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
    		String titulo = tfTitulo.getText();
    		String autor = tfAutor.getText();
    		String editorial = tfEditorial.getText();
    		String estado = tfEstado.getText();
    		int baja = Integer.parseInt(tfBaja.getText());
    		
    		Libro libro = new Libro(titulo, autor, editorial, estado, baja);
	    	// Añadir deportistas
    		LibroDao dao = new LibroDao();
	    	dao.aniadirLibro(libro);
	    	
	    	vaciarCampos();
    	}else {
    		alertaError(errores);
    	}
    }
    
    private String validar() {
    	String errores = "";
    	
    	if(tfTitulo.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo titulo\n";
        }
    	
    	if(tfAutor.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo autor\n";
        }
    	
    	if(tfEditorial.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo editorial\n";
        }
    	
    	if(tfEstado.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo estado\n";
        }
    	
    	if(tfBaja.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo baja\n";
        }else {
        	try {
                Integer.parseInt(tfBaja.getText());
                
                if(!tfBaja.getText().equals("1") && !tfBaja.getText().equals("0")) {
                	 errores += "El campo de baja tiene que ser 0 o 1\n";
                }
                	
            } catch (NumberFormatException e) {
                errores += "El campo de baja tiene que ser numerico\n";
            }
        }
    	
    	return errores;
    }
    
    private void vaciarCampos() {
    	tfTitulo.setText("");
    	tfAutor.setText("");
    	tfEditorial.setText("");
    	tfEstado.setText("");
    	tfBaja.setText("");
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


