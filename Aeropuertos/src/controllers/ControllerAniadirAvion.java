package controllers;

import dao.AeropuertoDao;
import dao.AvionesDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Aeropuerto;
import model.Avion;

public class ControllerAniadirAvion {

    @FXML
    private VBox boxPrivados;

    @FXML
    private VBox boxPublicos;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<Aeropuerto> cbAeropuertos;

    @FXML
    private RadioButton rbActivado;

    @FXML
    private RadioButton rbDesactivado;

    @FXML
    private ToggleGroup rbGroup;

    @FXML
    private TextField tfAsientos;

    @FXML
    private TextField tfFinanciacion;

    @FXML
    private TextField tfModelo;

    @FXML
    private TextField tfNSocios;

    @FXML
    private TextField tfNTrabajadores;

    @FXML
    private TextField tfVelMax;

    @FXML
    void initialize() {
    	AeropuertoDao a = new AeropuertoDao();
    	ObservableList<Aeropuerto> listaAeropuertos = a.nombreAeropuertos();
    	// añadimos al combobox todos los aeropuertos
    	cbAeropuertos.setItems(listaAeropuertos);
    }
    
    @FXML
    void accionCancelar(ActionEvent event) {
        // Cierra la ventana actual al hacer clic en el botón Cancelar
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void accionGuardar(ActionEvent event) {
        // Valida los campos del formulario y obtiene mensajes de error si los hay
        String errores = validar();

        // Si no hay errores, procesa la información ingresada
        if (errores.isEmpty()) {
            // Recupera los datos del formulario
            String modelo = tfModelo.getText().toString();
            int numero_asientos = Integer.parseInt(tfAsientos.getText().toString());
            int velocidad_maxima = Integer.parseInt(tfVelMax.getText().toString());
            int activado = 0;

            // Comprueba si el RadioButton "Activado" está seleccionado y actualiza el valor de activado
            if (rbActivado.isSelected()) {
                activado = 1;
            }

            // Obtiene el ID del aeropuerto seleccionado en el ComboBox
            int id_aeropuerto = cbAeropuertos.getSelectionModel().getSelectedItem().getId();

            // Crea un objeto Avion con los datos obtenidos
            Avion a = new Avion(numero_asientos, velocidad_maxima, activado, id_aeropuerto, modelo);

            // Valida si el avión ya existe en el aeropuerto seleccionado
            AvionesDao avionesDao = new AvionesDao();
            if (avionesDao.existe(a)) {
                errores = "Ese avión ya existe en ese aeropuerto";
                alertaError(errores);
            } else {
                // Añade el avión utilizando el objeto AvionesDao
                avionesDao.aniadirAvion(a);

                // Muestra una alerta informativa de que se ha añadido correctamente el avión
                alertaInformacion("Se ha añadido correctamente el Avión");

                // Limpia los campos del formulario
                vaciarCampos();
            }
        } else {
            // Si hay errores, muestra una alerta de error con los mensajes de error
            alertaError(errores);
        }
    }

    
    private void vaciarCampos() {
    	tfModelo.setText("");
    	tfAsientos.setText("");
    	tfVelMax.setText("");
    	rbActivado.setSelected(false);
    	rbDesactivado.setSelected(false);
    	cbAeropuertos = new ComboBox<Aeropuerto>();
    }
    
    private String validar() {
    	String errores = "";
    	
    	if(tfModelo.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo Modelo\n";
        }
    	
    	if(tfAsientos.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo Asientos\n";
        }else {
        	try {
    			// Intenta convertir el String a un número
                Integer.parseInt(tfAsientos.getText());
            } catch (NumberFormatException e) {
                // Si hay una excepción, no es numérico
                errores += "El campo Asientos tiene que ser numerico\n";
            }
        }
    	if(tfVelMax.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo de Velocidad Maxima\n";
        }else {
        	if (!tfVelMax.getText().matches("^-?[0-9]+([\\.,][0-9]+)?$")) {
        		errores += "El campo Velocidad Maxima tiene que ser decimal\n";
        	}
        }
    	// validamos si se ha seleccionado algun radioButton
    	if(!rbActivado.isSelected() && !rbDesactivado.isSelected()) {
    		errores += "Tienes que elegir algún RadioButton\n";
    	}
    	// validamos si se ha seleccionado algun Aeropuerto
    	if(cbAeropuertos.getSelectionModel().getSelectedItem() == null){
    		errores += "Tienes que elegir algún Aeropuerto\n";
    	}
    	
    	return errores;
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
    
    private void alertaInformacion(String mensaje) {
    	// Alerta de informacion con boton
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

