package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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
    	
    	// Poner TollTips a elementos
    	Tooltip tp = new Tooltip();
    	
    	tp.setText("Elige los deportes que practiques");
    	lista.setTooltip(tp);
    	
    	tp.setText("Valora del 1 al 10 tu aficcion a ir de compras");
    	sldCompras.setTooltip(tp);
    	
    	tp.setText("Valora del 1 al 10 tu aficcion a ver la television");
    	sldTelevision.setTooltip(tp);
    	
    	tp.setText("Valora del 1 al 10 tu aficcion a ir al cine");
    	sldCine.setTooltip(tp);
    }
    
    @FXML
    void accionCheck(ActionEvent event) {
    	lista.setDisable(!cbDeporte.isSelected());
    }
    
    @FXML
    void accionAceptar(ActionEvent event) {
    	String profesion = tfProfesion.getText();
    	int NHermanosNumerico = 0;
    	String edad = cbEdad.getSelectionModel().getSelectedItem();
    	String sexo = "";
    	if(rbHombre.isSelected()) {
    		sexo = rbHombre.getText();
    	}else if(rbMujer.isSelected()) {
    		sexo = rbMujer.getText();
    	}else if(rbOtro.isSelected()) {
    		sexo = rbOtro.getText();
    	}
    	// Guardamos los deportes seleccionados de la lista
    	ObservableList<String> deportesElegidos = lista.getSelectionModel().getSelectedItems();
    	// Guardamos el valor de las aficciones
    	double aficcionTelevision = sldTelevision.getValue();
    	double aficcionCine = sldCine.getValue();
    	double aficcionCompras = sldCompras.getValue();
    	
    	// Creamos variable donde iremos guardando sus errores
    	String errores = "";
    	// Validamos que el campo profesion no este vacio
    	if(profesion.isEmpty()) {
    		errores+= "Hay que rellenar el campo profesión\n";
    	}
    	// Validamos que el campo de Nº Hermanos no este vacio
    	if(tfHermanos.getText().isEmpty()) {
    		errores+= "Hay que rellenar el campo número de hermanos\n";
    	}else {
    		// Si no esta vacio, validamos si es numerico o no
    		try {
    			NHermanosNumerico = Integer.parseInt(tfHermanos.getText());
    		}catch(NumberFormatException e) {
    			errores+= "El numero de hermanos tiene que ser numerico\n";
    		}
    	}
    	// Validamos que se haya elegido un deporte de la lista
    	if(cbDeporte.isSelected() && deportesElegidos.isEmpty()){
    		errores+= "Tienes que indicar los deportes que practicas";
    	}
    	// Creamos la ventana Emergente
    	Alert ventanaEmergente = new Alert(null);
    	ventanaEmergente.setTitle("TUS DATOS");
    	
    	if(errores.isEmpty()) {
    		// Ponemos que la alerta sera de tipo Informacion
    		ventanaEmergente.setAlertType(AlertType.INFORMATION);
    		// Guardamos todos los datos del formulario
    		String datos =  "Profesion:"+profesion+"\n"+
		    				"Nº de hermanos:"+NHermanosNumerico+"\n";
    		if(!edad.isEmpty()) {
    			datos+= "Edad:"+edad+"\n";
    		}
    		if(!sexo.isEmpty()) {
    			datos+= "Sexo:"+sexo+"\n";
    		}
    		if(cbDeporte.isSelected()) {
    			datos+="Deportes que practicas:\n";
    			for (String elemento : deportesElegidos) {
                    datos+="\t"+elemento+"\n";
                }
    		}
    		if(aficcionCompras != 0) {
    			datos+= "Grado de aficción a las compras:"+(int)aficcionCompras+"\n";
    		}
    		if(aficcionTelevision != 0) {
    			datos+= "Grado de aficción a ver la televisión:"+(int)aficcionTelevision+"\n";
    		}
    		if(aficcionCine != 0) {
    			datos+= "Grado de aficción a ir al cine:"+(int)aficcionCine+"\n";
    		}
    		
    		ventanaEmergente.setContentText(datos);
    	}else {
    		// Ponemos que la alerta sera de tipo Error
    		ventanaEmergente.setAlertType(AlertType.ERROR);
    		// Mostramos mensaje de error
    		ventanaEmergente.setContentText(errores);
    	}
    	// Creamos el boton de Aceptar y su accion
    	Button ocultarBtn = new Button("Aceptar");
        ocultarBtn.setOnAction(e -> {
        	ventanaEmergente.hide();
        });
    	ventanaEmergente.show();
    }
    
    public void mostrarDialogo() {
        Stage ventanaEmergente = new Stage();
        Button aceptarBtn = new Button("Aceptar");
        aceptarBtn.setOnAction(e -> {
            ventanaEmergente.close();
        });
        HBox contenedorRaiz = new HBox();
        contenedorRaiz.setPadding(new Insets(20));
        contenedorRaiz.setSpacing(20);
        contenedorRaiz.getChildren().addAll(aceptarBtn);
        Scene escena = new Scene(contenedorRaiz);
        ventanaEmergente.setScene(escena);
        ventanaEmergente.setTitle("TUS DATOS");
        ventanaEmergente.show();
    }
   

    @FXML
    void accionCancelar(ActionEvent event) {

    }
}

