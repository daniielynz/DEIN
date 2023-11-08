package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

public class ControllerAniadirAeropuerto {

    @FXML
    private VBox boxPrivados;

    @FXML
    private VBox boxPublicos;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ToggleGroup rbGrupo;

    @FXML
    private RadioButton rbPrivado;

    @FXML
    private RadioButton rbPublico;

    @FXML
    private TextField tfAnioInauguracion;

    @FXML
    private TextField tfCalle;

    @FXML
    private TextField tfCapacidad;

    @FXML
    private TextField tfCiudad;

    @FXML
    private TextField tfFinanciacion;

    @FXML
    private TextField tfNSocios;

    @FXML
    private TextField tfNTrabajadores;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfNumero;

    @FXML
    private TextField tfPais;
    
    private String tipoAeropuerto;

    @FXML
    void initialize() {
    	this.tipoAeropuerto = "";
    }
    
    @FXML
    void accionCancelar(ActionEvent event) {
    	
    }

    @FXML
    void accionGuardar(ActionEvent event) {
    	String errores = validar();
    	if(errores.isEmpty()) {
    		alertaInformacion("Se ha añadido correctamente el Aeropuerto");
    	}else {
    		alertaError(errores);
    	}
    }
    
    private String validar() {
    	String errores = "";
    	
    	if(tfNombre.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo Nombre\n";
        }
    	if(tfPais.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo Pais\n";
        }
    	if(tfCiudad.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo Ciudad\n";
        }
    	if(tfCalle.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo Calle\n";
        }
    	if(tfNumero.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo Numero\n";
        }
    	if(tfAnioInauguracion.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo Año de inauguracion\n";
        }
    	if(tfCapacidad.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo Capacidad\n";
        }
    	// validamos si se ha seleccionado algun radioButton
    	if(tipoAeropuerto.isEmpty()) {
    		errores += "Tienes que elegir algún RadioButton\n";
    	}else {
    		if(tipoAeropuerto.equals("privado")) {
    			// Se ha seleccionado el RadioButton "privado"
    			// validamos el campo de Nº Socios
    	    	if(tfNSocios.getText().isEmpty()) {
    	            errores += "Tienes que rellenar el campo Nº Socios\n";
    	        }
    		}else if(tipoAeropuerto.equals("publico")) {
    			// Se ha seleccionado el RadioButton "publico"
    			// validamos los campos de financiacion y de numero de trabajadores
    	    	if(tfFinanciacion.getText().isEmpty()) {
    	            errores += "Tienes que rellenar el campo Financiacion\n";
    	        }
    	    	if(tfNTrabajadores.getText().isEmpty()) {
    	            errores += "Tienes que rellenar el campo Numero de trabajadores\n";
    	        }
    		}
    	}
    	return errores;
    }

    @FXML
    void accionPrivado(ActionEvent event) {
    	boxPublicos.setVisible(false);
    	boxPrivados.setVisible(true);
    	this.tipoAeropuerto = "privado";
    }

    @FXML
    void accionPublico(ActionEvent event) {
    	boxPublicos.setVisible(true);
    	boxPrivados.setVisible(false);
    	this.tipoAeropuerto = "publico";
    }
    
 // Este método muestra una ventana emergente de tipo "Error" con un mensaje y un botón "Aceptar
    private void alertaError(String mensaje) {
        // Creamos una nueva ventana emergente de tipo "Error"
        Alert ventanaEmergente = new Alert(AlertType.ERROR);
        // Establecemos el título de la ventana emergente
        ventanaEmergente.setTitle("Información");
        // Establecemos el contenido de la ventana emergente como el mensaje proporcionado
        ventanaEmergente.setContentText(mensaje);
        // Creamos un botón "Aceptar" para cerrar la ventana emergente
        Button ocultarBtn = new Button("Aceptar");
        // Configuramos el evento del botón para ocultar la ventana emergente al hacer clic en "Aceptar"
        ocultarBtn.setOnAction(e -> {
            ventanaEmergente.hide();
        });
        // Mostramos la ventana emergente
        ventanaEmergente.show();
    }

    // Este método muestra una ventana emergente de tipo "Información" con un mensaje y un botón "Aceptar"
    private void alertaInformacion(String mensaje) {
        // Creamos una nueva ventana emergente de tipo "Información"
        Alert ventanaEmergente = new Alert(AlertType.INFORMATION);
        // Establecemos el título de la ventana emergente
        ventanaEmergente.setTitle("Información");
        // Establecemos el contenido de la ventana emergente como el mensaje proporcionado
        ventanaEmergente.setContentText(mensaje);
        // Creamos un botón "Aceptar" para cerrar la ventana emergente
        Button ocultarBtn = new Button("Aceptar");
        // Configuramos el evento del botón para ocultar la ventana emergente al hacer clic en "Aceptar"
        ocultarBtn.setOnAction(e -> {
            ventanaEmergente.hide();
        });
        // Mostramos la ventana emergente
        ventanaEmergente.show();
    }


}
