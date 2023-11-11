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
        // Añade al ComboBox todos los aeropuertos disponibles
        AeropuertoDao aeropuertoDao = new AeropuertoDao();
        ObservableList<Aeropuerto> listaNombresAeropuertos = aeropuertoDao.nombreAeropuertos();
        cbAeropuertos.setItems(listaNombresAeropuertos);
    }

    @FXML
    void accionElegirAeropuerto(ActionEvent event) {
        // Obtiene el aeropuerto seleccionado en el ComboBox
        Aeropuerto aeropuertoSeleccionado = cbAeropuertos.getSelectionModel().getSelectedItem();
        
        // Realiza acciones adicionales según sea necesario
        // Lista los aviones en el aeropuerto seleccionado
        AvionesDao avionDao = new AvionesDao();
        ObservableList<Avion> listaAvionesAeropuerto = avionDao.listadoAviones(aeropuertoSeleccionado.getId());
        
        // Asigna la lista de aviones al ComboBox de aviones
        cbAviones.setItems(listaAvionesAeropuerto);
    }

    @FXML
    void accionElegirAvion(ActionEvent event) {
        // Obtiene el avión seleccionado en el ComboBox
        Avion avionSeleccionado = cbAviones.getSelectionModel().getSelectedItem();
        
        // Deja el RadioButton seleccionado dependiendo de si el avión seleccionado está activado o no
        if (avionSeleccionado != null) {
            if (avionSeleccionado.getActivado() == 1) {
                rbActivado.setSelected(true);
            } else {
                rbDesactivado.setSelected(true);
            }
        }
    }

    @FXML
    void accionCancelar(ActionEvent event) {
        // Cierra la ventana actual al hacer clic en el botón Cancelar
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    
    @FXML
    void accionBorrar(ActionEvent event) {
        // Obtiene el aeropuerto y el avión seleccionados en los ComboBox
        Aeropuerto aeropuertoSeleccionado = cbAeropuertos.getSelectionModel().getSelectedItem();
        Avion avionSeleccionado = cbAviones.getSelectionModel().getSelectedItem();
        
        // Verifica si se ha seleccionado un aeropuerto
        if (aeropuertoSeleccionado != null) {
            // Verifica si se ha seleccionado un avión
            if (avionSeleccionado != null) {
                // Borra el avión utilizando AvionesDao
                AvionesDao avionDao = new AvionesDao();
                avionDao.borrarAvion(avionSeleccionado);
                
                // Muestra una alerta informativa de que se ha eliminado correctamente el avión
                alertaInformacion("Se ha eliminado correctamente el avión " + avionSeleccionado.getModelo() + " del aeropuerto " + aeropuertoSeleccionado.getNombre());
            } else {
                // Muestra una alerta de error si no se ha seleccionado un avión
                alertaError("Tiene que seleccionar un Avión");
            }
        } else {
            // Muestra una alerta de error si no se ha seleccionado un aeropuerto
            alertaError("Tienes que seleccionar un Aeropuerto");
        }
    }

    @FXML
    void accionGuardar(ActionEvent event) {
        // Obtiene el aeropuerto y el avión seleccionados en los ComboBox
        Aeropuerto aeropuertoSeleccionado = cbAeropuertos.getSelectionModel().getSelectedItem();
        Avion avionSeleccionado = cbAviones.getSelectionModel().getSelectedItem();
        
        // Verifica si se ha seleccionado un aeropuerto
        if (aeropuertoSeleccionado != null) {
            // Verifica si se ha seleccionado un avión
            if (avionSeleccionado != null) {
                // Modifica el estado del avión según el RadioButton seleccionado
                if (rbActivado.isSelected()) {
                    avionSeleccionado.setActivado(1);
                } else {
                    avionSeleccionado.setActivado(0);
                }
                
                // Modifica el avión utilizando AvionesDao
                AvionesDao avionDao = new AvionesDao();
                avionDao.activarDesactivarAvion(avionSeleccionado);
                
                // Muestra una alerta informativa de que se ha modificado correctamente el avión
                alertaInformacion("Se ha modificado correctamente el avión " + avionSeleccionado.getModelo() + " del aeropuerto " + aeropuertoSeleccionado.getNombre());
            } else {
                // Muestra una alerta de error si no se ha seleccionado un avión
                alertaError("Tiene que seleccionar un Avión");
            }
        } else {
            // Muestra una alerta de error si no se ha seleccionado un aeropuerto
            alertaError("Tienes que seleccionar un Aeropuerto");
        }
    }

    // Métodos de diferentes ventanas emergentes
    private void alertaError(String mensaje) {
        // Alerta de error con botón Aceptar
        Alert ventanaEmergente = new Alert(AlertType.ERROR);
        ventanaEmergente.setTitle("Información");
        ventanaEmergente.setContentText(mensaje);
        
        // Botón para cerrar la ventana emergente
        Button ocultarBtn = new Button("Aceptar");
        ocultarBtn.setOnAction(e -> {
            ventanaEmergente.hide();
        });
        
        // Muestra la ventana emergente
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


    

