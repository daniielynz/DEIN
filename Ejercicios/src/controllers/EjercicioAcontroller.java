package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class EjercicioAcontroller {

    @FXML
    private CheckBox cbDeporte;

    @FXML
    private ComboBox<String> cbEdad;
    
    @FXML
    private ListView<String> lista;

    @FXML
    private ToggleGroup rbGroup;

    @FXML
    private RadioButton rbHombre;

    @FXML
    private RadioButton rbMujer;

    @FXML
    private RadioButton rbOtro;

    @FXML
    private Slider sldCine;

    @FXML
    private Slider sldCompras;

    @FXML
    private Slider sldTelevision;

    @FXML
    private TextField tfHermanos;

    @FXML
    private TextField tfProfesion;

    @FXML
    void initialize() {
    	ObservableList<String> listaCombo = FXCollections.observableArrayList("Menores de 18","Entre 18 y 30","Entre 31 y 50","Entre 51 y 70","Mayores de 70");
    	cbEdad.setItems(listaCombo);
    	cbEdad.setValue(listaCombo.get(0));
    	
    	ObservableList<String> listaList = FXCollections.observableArrayList("Tenis","Fútbol","Baloncesto","Natación","Ciclismo","Otro");
    	lista.setItems(listaList);
    	
    	//cbDeporte.setOnAction(event -> habilitarLista());
    	cbDeporte.setSelected(false);
    	lista.setDisable(true);
    }
    
    @FXML
    void accionCheck(ActionEvent event) {
    	lista.setDisable(!cbDeporte.isSelected());
    }
    
    @FXML
    void accionAceptar(ActionEvent event) {
    	String profesion = tfProfesion.getText();
    	String NHermanos = tfHermanos.getText();
    	String edad = cbEdad.getSelectionModel().getSelectedItem();
    	String sexo;
    	if(rbHombre.isSelected()) {
    		sexo = rbHombre.getText();
    	}else if(rbMujer.isSelected()) {
    		sexo = rbMujer.getText();
    	}else if(rbOtro.isSelected()) {
    		sexo = rbOtro.getText();
    	}
    }

    @FXML
    void accionCancelar(ActionEvent event) {

    }
}

