package controllers;

import dao.DeportistasDao;
import dao.ParticipacionesDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Participacion;

public class ControllerBorrarParticipacion {
	private Participacion participacionSeleccionada;
	@FXML
	private ComboBox<Participacion> cbDeportistas;
	@FXML
    private Button btnBorrar;
	@FXML
    private Button btnCancelar;
	
	@FXML
    void initialize() {
		ParticipacionesDao participacionesDao = new ParticipacionesDao();
        ObservableList<Participacion> listaParticipaciones =  participacionesDao.cargarParticipaciones("");
        cbDeportistas.setItems(listaParticipaciones);
    }
	
	@FXML
    void accionBorrar(ActionEvent event) {
    	if(participacionSeleccionada != null) {
    		ParticipacionesDao dao = new ParticipacionesDao();
    		// hay que borrar el deportista de la participacion
        	dao.borrarParticipacion(participacionSeleccionada);
        	alertaInformacion("Se ha borrado la participacion seleccionada");
    	}
    }
	
	@FXML
    void accionCancelar(ActionEvent event) {
    	// Cierra la ventana actual al hacer clic en el botón Cancelar
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void accionElegirParticipacion(ActionEvent event) {
    	participacionSeleccionada = cbDeportistas.getSelectionModel().getSelectedItem();
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
