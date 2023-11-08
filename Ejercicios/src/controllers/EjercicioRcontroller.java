package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class EjercicioRcontroller {

	// Atributos para los dos contenedores con preguntas
    @FXML
    private VBox box1;

    @FXML
    private VBox box2;

    // Atributos para los botones
    @FXML
    private Button btnCuestionario1;

    @FXML
    private Button btnCuestionario2;
    
    @FXML
    private Button btnEnviar;
    
    // Atributos para los labels
    @FXML
    private Label lbPregunta;

    @FXML
    private Label lbPregunta1;

    @FXML
    private Label lbPregunta10;

    @FXML
    private Label lbPregunta101;

    @FXML
    private Label lbPregunta1011;

    @FXML
    private Label lbPregunta102;

    @FXML
    private Label lbPregunta103;

    @FXML
    private Label lbPregunta104;

    @FXML
    private Label lbPregunta105;

    @FXML
    private Label lbPregunta106;

    @FXML
    private Label lbPregunta107;

    @FXML
    private Label lbPregunta108;

    @FXML
    private Label lbPregunta2;

    @FXML
    private Label lbPregunta3;

    @FXML
    private Label lbPregunta4;

    @FXML
    private Label lbPregunta5;

    @FXML
    private Label lbPregunta6;

    @FXML
    private Label lbPregunta7;

    @FXML
    private Label lbPregunta8;

    @FXML
    private Label lbPregunta9;

    @FXML
    private TextField tfRespuesta;

    @FXML
    private TextField tfRespuesta1;

    @FXML
    private TextField tfRespuesta10;

    @FXML
    private TextField tfRespuesta11;

    @FXML
    private TextField tfRespuesta12;

    @FXML
    private TextField tfRespuesta13;

    @FXML
    private TextField tfRespuesta14;

    @FXML
    private TextField tfRespuesta15;

    @FXML
    private TextField tfRespuesta16;

    @FXML
    private TextField tfRespuesta17;

    @FXML
    private TextField tfRespuesta18;

    @FXML
    private TextField tfRespuesta19;

    @FXML
    private TextField tfRespuesta2;

    @FXML
    private TextField tfRespuesta3;

    @FXML
    private TextField tfRespuesta4;

    @FXML
    private TextField tfRespuesta5;

    @FXML
    private TextField tfRespuesta6;

    @FXML
    private TextField tfRespuesta7;

    @FXML
    private TextField tfRespuesta8;

    @FXML
    private TextField tfRespuesta9;

    @FXML
	void initialize() {
    	
    }
    
    @FXML
    void accionEnviar(ActionEvent event) {

    }
    
    @FXML
    void accionCuestionario1(ActionEvent event) {
    	this.box1.setVisible(true);
    	this.box2.setVisible(false);
    }
    
    @FXML
    void accionCuestionario2(ActionEvent event) {
    	this.box1.setVisible(false);
    	this.box2.setVisible(true);
    }

}
