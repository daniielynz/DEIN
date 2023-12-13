package controllers;

import dao.DeportistasDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.Deportista;
	

public class ControllerBorrarDeportista {
	
	private Deportista deportistaSeleccionado;
	@FXML
	private ComboBox<Deportista> cbDeportistas;
	@FXML
    private Button btnBorrar;
	@FXML
    private Button btnCancelar;
	
	@FXML
    void initialize() {
		DeportistasDao deportistaDao = new DeportistasDao();
        ObservableList<Deportista> listaDeportista =  deportistaDao.cargarDeportista("");
        cbDeportistas.setItems(listaDeportista);
    }
	
	@FXML
    void accionBorrar(ActionEvent event) {
    	if(deportistaSeleccionado != null) {
    		DeportistasDao dao = new DeportistasDao();
    		// hay que borrar el deportista de la participacion
        	dao.borrarDeportista(deportistaSeleccionado);
    	}
    }
	
	@FXML
    void accionCancelar(ActionEvent event) {
    	// Cierra la ventana actual al hacer clic en el botón Cancelar
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void accionElegirDeportista(ActionEvent event) {
    	deportistaSeleccionado = cbDeportistas.getSelectionModel().getSelectedItem();
    }
}
