package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ListadoController {

	//
    @FXML
    private ToggleGroup groupRb;
    @FXML
    private TextField tfBuscarPorNombre;
    @FXML
    private Label lblListado;
    
    
    /* 	**************************************************************************************************************************************************
     	**************************************************************************************************************************************************
     	*								DEPORTISTAS
     	************************************************************************************************************************************************** 
     	**************************************************************************************************************************************************
     */
    @FXML
    private RadioButton rbDeportistas;
    @FXML
    private TableColumn<?, ?> colAlturaDeportista;
    @FXML
    private TableColumn<?, ?> colNombreDeportista;
    @FXML
    private TableColumn<?, ?> colIdDeportista;
    @FXML
    private TableColumn<?, ?> colPesoDeportista;
    @FXML
    private TableColumn<?, ?> colSexoDeportista;
    @FXML
    private TableColumn<?, ?> colFotoDeportista;
    @FXML
    private TableView<?> tablaDeportista;
    
    @FXML
    void accionAniadirDeportista(ActionEvent event) {

    }
    
    @FXML
    void accionEditarDeportista(ActionEvent event) {

    }
    @FXML
    void accionBorrarDeportista(ActionEvent event) {

    }
    
    /* 	**************************************************************************************************************************************************
	 	**************************************************************************************************************************************************
	 	*								DEPORTES
	 	************************************************************************************************************************************************** 
	 	**************************************************************************************************************************************************
 	*/
    @FXML
    private TableView<?> tablaDeporte;
    @FXML
    private RadioButton rbDeportes;
    @FXML
    private TableColumn<?, ?> colNombreDeporte;
    @FXML
    private TableColumn<?, ?> colIdDeporte;
    @FXML
    void accionAniadirDeporte(ActionEvent event) {

    }
    
    @FXML
    void accionEditarDeporte(ActionEvent event) {

    }
    
    @FXML
    void accionBorrarDeporte(ActionEvent event) {

    }
    /* 	**************************************************************************************************************************************************
	 	**************************************************************************************************************************************************
	 	*								EVENTOS
	 	************************************************************************************************************************************************** 
	 	**************************************************************************************************************************************************
	*/
    @FXML
    private RadioButton rbEventos;
    @FXML
    private TableColumn<?, ?> colNombreEvento;
    @FXML
    private TableColumn<?, ?> colDeporteEvento;
    @FXML
    private TableColumn<?, ?> colIdEvento;
    @FXML
    private TableView<?> tablaEventos;
    @FXML
    private TableColumn<?, ?> colOlimpiadaEvento;
    
    @FXML
    void accionAniadirEvento(ActionEvent event) {

    }
    
    @FXML
    void accionBorrarEvento(ActionEvent event) {

    }
    
    @FXML
    void accionEditarEvento(ActionEvent event) {

    }
    
    /* 	**************************************************************************************************************************************************
	 	**************************************************************************************************************************************************
	 	*								EQUIPOS
	 	************************************************************************************************************************************************** 
	 	**************************************************************************************************************************************************
	*/
    @FXML
    private RadioButton rbEquipos;
    @FXML
    private TableColumn<?, ?> colIdEquipo;
    @FXML
    private TableColumn<?, ?> colInicialesEquipo;
    @FXML
    private TableView<?> tablaEquipos;
    @FXML
    private TableColumn<?, ?> colNombreEquipo;
    
    @FXML
    void accionAniadirEquipo(ActionEvent event) {

    }
    
    @FXML
    void accionEditarEquipo(ActionEvent event) {

    }
    
    @FXML
    void accionBorrarEquipo(ActionEvent event) {

    }
    
    /* 	**************************************************************************************************************************************************
	 	**************************************************************************************************************************************************
	 	*								OLIMPIADAS
	 	************************************************************************************************************************************************** 
	 	**************************************************************************************************************************************************
	*/
    @FXML
    private TableColumn<?, ?> colNombreOlimpiada;
    @FXML
    private TableColumn<?, ?> colAnioOlimpiada;
    @FXML
    private TableColumn<?, ?> colIdOlimpiada;
    @FXML
    private TableView<?> tablaOlimpiadas;
    @FXML
    private RadioButton rbOlimpiadas;
    @FXML
    private TableColumn<?, ?> colCiudadOlimpiada;
    
    @FXML
    void accionAniadirOlimpiada(ActionEvent event) {

    }
    
    @FXML
    void accionEditarOlimpiada(ActionEvent event) {

    }
    
    @FXML
    void accionBorrarOlimpiada(ActionEvent event) {

    }
    
    /* 	**************************************************************************************************************************************************
	 	**************************************************************************************************************************************************
	 	*								PARTICIPACION
	 	************************************************************************************************************************************************** 
	 	**************************************************************************************************************************************************
	*/
    @FXML
    private TableView<?> tablaParticipaciones;
    @FXML
    private RadioButton rbParticipaciones;
    @FXML
    private TableColumn<?, ?> colDeportistaParticipacion;
    @FXML
    private TableColumn<?, ?> colEdadParticipacion;
    @FXML
    private TableColumn<?, ?> colEquipoParticipacion;
    @FXML
    private TableColumn<?, ?> colEventoParticipacion;
    @FXML
    private TableColumn<?, ?> colMedallaParticipacion;
    
    
    @FXML
    void accionAniadirParticipacion(ActionEvent event) {

    }
    
    @FXML
    void accionEditarParticipacion(ActionEvent event) {

    }
    @FXML
    void accionBorrarParticipacion(ActionEvent event) {

    }
    

    

    

    

}
