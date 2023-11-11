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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Aeropuerto;
import model.Avion;

public class ControllerEditarEliminarAvion {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;
    
    @FXML
    private Button btnBorrar;

    @FXML
    private ComboBox<Aeropuerto> cbAeropuertos;

    @FXML
    private ComboBox<Avion> cbAviones;

    @FXML
    private HBox hboxRb;

    @FXML
    private RadioButton rbActivado;

    @FXML
    private RadioButton rbDesactivado;

    @FXML
    private ToggleGroup rbGroup;

    @FXML
    void initialize() {
    	// añadimos al combobox todos los aeropuertos
    	AeropuertoDao aeropuertoDao = new AeropuertoDao();
    	ObservableList<Aeropuerto> listaNombresAeropuertos = aeropuertoDao.nombreAeropuertos();
    	cbAeropuertos.setItems(listaNombresAeropuertos);
    }
    
    @FXML
    void accionElegirAeropuerto(ActionEvent event) {
	    Aeropuerto aeropuertoSeleccionado = cbAeropuertos.getSelectionModel().getSelectedItem();
	    // Puedes realizar acciones adicionales aquí
	    // listado de los aviones en el aeropuerto
    	AvionesDao avionDao = new AvionesDao();
    	ObservableList<Avion> listaAvionesAeropuerto = avionDao.listadoAviones(aeropuertoSeleccionado.getId());
    	cbAviones.setItems(listaAvionesAeropuerto);
    }
    
    @FXML
    void accionElegirAvion(ActionEvent event) {
    	Avion avionSeleccionado = cbAviones.getSelectionModel().getSelectedItem();
    	// dejamos el radiobuton seleccioando dependiendo de si el avion seleccionado esta activado o no
    	if(avionSeleccionado!=null) {
    		if(avionSeleccionado.getActivado() == 1) {
        		rbActivado.setSelected(true);
        	}else {
        		rbDesactivado.setSelected(true);
        	}
    	}
    }
    
    @FXML
    void accionCancelar(ActionEvent event) {
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void accionBorrar(ActionEvent event) {
    	// obtenemos el avion seleccionado y el radiobutton 
    	Aeropuerto aeropuertoSeleccionado = cbAeropuertos.getSelectionModel().getSelectedItem();;
    	Avion avionSeleccionado = cbAviones.getSelectionModel().getSelectedItem();
    	if(aeropuertoSeleccionado != null) {
    		if(avionSeleccionado!=null) {
            	AvionesDao avionDao = new AvionesDao();
            	avionDao.borrarAvion(avionSeleccionado);
            	alertaInformacion("Se ha eliminado correctamente el avion "+avionSeleccionado.getModelo()+" del aeropuerto "+aeropuertoSeleccionado.getNombre());
        	}else {
        		alertaError("Tiene que seleccionar un Avion");
        	}
    	}else {
    		alertaError("Tienes que seleccionar un Aeropuerto");
    	}
    }

    @FXML
    void accionGuardar(ActionEvent event) {
    	// obtenemos el avion seleccionado y el radiobutton 
    	Aeropuerto aeropuertoSeleccionado = cbAeropuertos.getSelectionModel().getSelectedItem();;
    	Avion avionSeleccionado = cbAviones.getSelectionModel().getSelectedItem();
    	if(aeropuertoSeleccionado != null) {
    		if(avionSeleccionado!=null) {
        		if(rbActivado.isSelected()) {
            		avionSeleccionado.setActivado(1);
            	}else {
            		avionSeleccionado.setActivado(0);
            	}
            	// modificamos el avion
            	AvionesDao avionDao = new AvionesDao();
            	avionDao.activarDesactivarAvion(avionSeleccionado);
            	alertaInformacion("Se ha modificado correctamente el avion "+avionSeleccionado.getModelo()+" del aeropuerto "+aeropuertoSeleccionado.getNombre());
        	}else {
        		alertaError("Tiene que seleccionar un Avion");
        	}
    	}else {
    		alertaError("Tienes que seleccionar un Aeropuerto");
    	}
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


    

