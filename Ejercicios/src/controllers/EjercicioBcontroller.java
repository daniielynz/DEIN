package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EjercicioBcontroller {

    @FXML
    private Button btnAgregar;

    @FXML
    private TableColumn<?, ?> colApellidos;

    @FXML
    private TableColumn<?, ?> colEdad;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private TableView<?> tableInfo;

    @FXML
    private TextField tfApellidos;

    @FXML
    private TextField tfEdad;

    @FXML
    private TextField tfNombre;

    @FXML
    void accionAceptar(ActionEvent event) {
    	String errores = "";
    	
    	// Obtenemos el dato del campo Nombre y validamos que no este vacio
    	String nombre = tfNombre.getText();
    	if(nombre.isEmpty()) {
    		errores+= "Tienes que rellenar el campo Nombre\n";
    	}
    	// Obtenemos el dato del campo Apellidos y validamos que no este vacio
    	String apellidos = tfApellidos.getText();
    	if(apellidos.isEmpty()) {
    		errores+= "Tienes que rellenar el campo Apellidos\n";
    	}
    	// Obtenemos el dato del campo Edad y validamos que sea numerico y no este vacio
    	int edad = 0;
    	try {
    		edad = Integer.parseInt(tfEdad.getText());
		}catch(NumberFormatException e) {
			errores+= "El numero de hermanos tiene que ser numerico\n";
		}
    	
    	if(errores.isEmpty()) {
    		
    	}else {
    		Alert ventanaEmergente = new Alert(AlertType.ERROR);
        	ventanaEmergente.setTitle("ERROR");
        	ventanaEmergente.setContentText(errores);
        	// Creamos el boton de Aceptar y su accion
        	Button ocultarBtn = new Button("Aceptar");
            ocultarBtn.setOnAction(e -> {
            	ventanaEmergente.hide();
            });
        	ventanaEmergente.show();
    	}
    }
}


