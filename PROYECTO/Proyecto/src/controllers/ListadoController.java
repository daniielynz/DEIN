package controllers;

import dao.DeportesDao;
import dao.DeportistasDao;
import dao.EquiposDao;
import dao.EventosDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Deporte;
import model.Deportista;
import model.Equipo;
import model.Evento;

public class ListadoController {

	//
    @FXML
    private ToggleGroup groupRb;
    @FXML
    private TextField tfBuscarPorNombre;
    @FXML
    private Label lblListado;
    @FXML
    private RadioButton rbDeportistas, rbEventos, rbEquipos, rbParticipaciones, rbDeportes, rbOlimpiadas;
    
    @FXML
    void initialize() {
		   // ponemos evento al radioButon de Deportistas
		   rbDeportistas.setOnAction(new EventHandler<ActionEvent>() {
		        @Override
		        public void handle(ActionEvent event) {
		        	ocultarTablas();
		        	tablaDeportistas.setVisible(true);
		        	
		        	// cargamos los datos de la tabla
		        	colIdDeportista.setCellValueFactory(new PropertyValueFactory<Deportista,Integer>("id_deportista") );
		        	colNombreDeportista.setCellValueFactory(new PropertyValueFactory<Deportista,String>("nombre") );
		        	colSexoDeportista.setCellValueFactory(new PropertyValueFactory<Deportista,String>("sexo") );
		        	colPesoDeportista.setCellValueFactory(new PropertyValueFactory<Deportista,Integer>("peso") );
		        	colAlturaDeportista.setCellValueFactory(new PropertyValueFactory<Deportista,Integer>("altura") );
		        	colFotoDeportista.setCellValueFactory(new PropertyValueFactory<Deportista,String>("foto") );
		        	
		    		// en caso de que existan personas en la base de datos, las cargamos en la tabla
		    		cargarTablaDeportistas("");
		    		
		    		// ponemos evento al TextField del filtrado por nombre
		            tfBuscarPorNombre.setOnAction(new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(ActionEvent event) {
		                    // Este código se ejecutará cuando se presione "Enter" en el TextField.
		                    String cadena = tfBuscarPorNombre.getText();
		                    cargarTablaDeportistas(cadena);
		                }
		            });
		        }
		    });
		   
		   // ponemos evento al radioButon de Deporte
		   rbDeportes.setOnAction(new EventHandler<ActionEvent>() {
		        @Override
		        public void handle(ActionEvent event) {
		        	ocultarTablas();
		        	tablaDeporte.setVisible(true);
		        	
		        	// cargamos los datos de la tabla
		        	colIdDeporte.setCellValueFactory(new PropertyValueFactory<Deporte,Integer>("id_deporte") );
		        	colNombreDeporte.setCellValueFactory(new PropertyValueFactory<Deporte,String>("nombre") );
		        	
		    		// en caso de que existan personas en la base de datos, las cargamos en la tabla
		    		cargarTablaDeportes("");
		    		
		    		// ponemos evento al TextField del filtrado por nombre
		            tfBuscarPorNombre.setOnAction(new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(ActionEvent event) {
		                    // Este código se ejecutará cuando se presione "Enter" en el TextField.
		                    String cadena = tfBuscarPorNombre.getText();
		                    cargarTablaDeportes(cadena);
		                }
		            });
		        }
		    });
		   
		   // ponemos evento al radioButon de Equipo
		   rbEquipos.setOnAction(new EventHandler<ActionEvent>() {
		        @Override
		        public void handle(ActionEvent event) {
		        	ocultarTablas();
		        	tablaEquipos.setVisible(true);
		        	
		        	// cargamos los datos de la tabla Equipo
		        	colIdEquipo.setCellValueFactory(new PropertyValueFactory<Equipo,Integer>("id_equipo") );
		        	colNombreEquipo.setCellValueFactory(new PropertyValueFactory<Equipo,String>("nombre") );
		        	colInicialesEquipo.setCellValueFactory(new PropertyValueFactory<Equipo,String>("iniciales") );
		        	
		    		// en caso de que existan personas en la base de datos, las cargamos en la tabla
		    		cargarTablaEquipos("");
		    		
		    		// ponemos evento al TextField del filtrado por nombre
		            tfBuscarPorNombre.setOnAction(new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(ActionEvent event) {
		                    // Este código se ejecutará cuando se presione "Enter" en el TextField.
		                    String cadena = tfBuscarPorNombre.getText();
		                    cargarTablaEquipos(cadena);
		                }
		            });
		        }
		    });
		   
		   // ponemos evento al radioButon de Evento
		   rbEventos.setOnAction(new EventHandler<ActionEvent>() {
		        @Override
		        public void handle(ActionEvent event) {
		        	ocultarTablas();
		        	tablaEventos.setVisible(true);
		        	
		        	// cargamos los datos de la tabla Evento
		            colNombreEvento.setCellValueFactory(new PropertyValueFactory<Evento, String>("nombre") );
		            colIdEvento.setCellValueFactory(new PropertyValueFactory<Evento, Integer>("id_evento") );
		            colDeporteEvento.setCellValueFactory(new PropertyValueFactory<Evento, String>("nombre_deporte") );
		            colOlimpiadaEvento.setCellValueFactory(new PropertyValueFactory<Evento, String>("nombre_olimpiada") );	        	
		    		// en caso de que existan personas en la base de datos, las cargamos en la tabla
		    		cargarTablaEventos("");
		    		
		    		// ponemos evento al TextField del filtrado por nombre
		            tfBuscarPorNombre.setOnAction(new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(ActionEvent event) {
		                    // Este código se ejecutará cuando se presione "Enter" en el TextField.
		                    String cadena = tfBuscarPorNombre.getText();
		                    cargarTablaEventos(cadena);
		                }
		            });
		        }
		    });
    }
    
    private void ocultarTablas(){
    	tablaDeportistas.setVisible(false);
    	tablaDeporte.setVisible(false);
    	tablaEquipos.setVisible(false);
    	tablaEventos.setVisible(false);
    	tablaOlimpiadas.setVisible(false);
    	tablaParticipaciones.setVisible(false);
    }
		    
    
    /* 	**************************************************************************************************************************************************
     	**************************************************************************************************************************************************
     	*								DEPORTISTAS
     	************************************************************************************************************************************************** 
     	**************************************************************************************************************************************************
     */
    
    @FXML
    private TableColumn<Deportista,Integer> colAlturaDeportista;
    @FXML
    private TableColumn<Deportista,String> colNombreDeportista;
    @FXML
    private TableColumn<Deportista,Integer> colIdDeportista;
    @FXML
    private TableColumn<Deportista,Integer> colPesoDeportista;
    @FXML
    private TableColumn<Deportista,String> colSexoDeportista;
    @FXML
    private TableColumn<Deportista,String> colFotoDeportista;
    @FXML
    private TableView<Deportista> tablaDeportistas;
    
    @FXML
    void accionAniadirDeportista(ActionEvent event) {
    	try {
	        // Abre la ventana para añadir un nuevo aeropuerto
	        Stage primaryStage = new Stage();
	        GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/aniadirDeportista.fxml"));
	        Scene scene = new Scene(root);
	        primaryStage.setTitle("AÑADIR DEPORTISTA");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    } catch(Exception e) {
	        // Maneja cualquier excepción que pueda ocurrir al abrir la ventana
	        e.printStackTrace();
	    }
    }
    
    @FXML
    void accionEditarDeportista(ActionEvent event) {

    }
    @FXML
    void accionBorrarDeportista(ActionEvent event) {
    	try {
	        // Abre la ventana para añadir un nuevo aeropuerto
	        Stage primaryStage = new Stage();
	        GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/borrarDeportista.fxml"));
	        Scene scene = new Scene(root);
	        primaryStage.setTitle("BORRAR DEPORTISTA");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    } catch(Exception e) {
	        // Maneja cualquier excepción que pueda ocurrir al abrir la ventana
	        e.printStackTrace();
	    }
    }
    
    void cargarTablaDeportistas(String cadena) {
	    try {
	        // Carga la tabla de aeropuertos públicos con la cadena de búsqueda
	        DeportistasDao deportistaDao = new DeportistasDao();
	        ObservableList<Deportista> listaDeportista =  deportistaDao.cargarDeportista(cadena);
	        tablaDeportistas.setItems(listaDeportista);
	        tablaDeportistas.refresh();
	    } catch(Exception e) {
	        // Maneja cualquier excepción que pueda ocurrir durante la carga
	        e.printStackTrace();
	    }
	}
    
    /* 	**************************************************************************************************************************************************
	 	**************************************************************************************************************************************************
	 	*								DEPORTES
	 	************************************************************************************************************************************************** 
	 	**************************************************************************************************************************************************
 	*/
    @FXML
    private TableView<Deporte> tablaDeporte;
    @FXML
    private TableColumn<Deporte, String> colNombreDeporte;
    @FXML
    private TableColumn<Deporte, Integer> colIdDeporte;
    @FXML
    void accionAniadirDeporte(ActionEvent event) {

    }
    
    @FXML
    void accionEditarDeporte(ActionEvent event) {

    }
    
    @FXML
    void accionBorrarDeporte(ActionEvent event) {

    }
    
    void cargarTablaDeportes(String cadena) {
	    try {
	        // Carga la tabla de aeropuertos públicos con la cadena de búsqueda
	        DeportesDao deportesDao = new DeportesDao();
	        ObservableList<Deporte> listaDeportes =  deportesDao.cargarDeportes(cadena);
	        tablaDeporte.setItems(listaDeportes);
	        tablaDeporte.refresh();
	    } catch(Exception e) {
	        // Maneja cualquier excepción que pueda ocurrir durante la carga
	        e.printStackTrace();
	    }
	}
    
    /* 	**************************************************************************************************************************************************
	 	**************************************************************************************************************************************************
	 	*								EVENTOS
	 	************************************************************************************************************************************************** 
	 	**************************************************************************************************************************************************
	*/
    @FXML
    private TableColumn<Evento, String> colNombreEvento;
    @FXML
    private TableColumn<Evento, String> colDeporteEvento;
    @FXML
    private TableColumn<Evento, Integer> colIdEvento;
    @FXML
    private TableView<Evento> tablaEventos;
    @FXML
    private TableColumn<Evento, String> colOlimpiadaEvento;
    
    @FXML
    void accionAniadirEvento(ActionEvent event) {

    }
    
    @FXML
    void accionBorrarEvento(ActionEvent event) {

    }
    
    @FXML
    void accionEditarEvento(ActionEvent event) {

    }
    
    void cargarTablaEventos(String cadena) {
	    try {
	        // Carga la tabla de aeropuertos públicos con la cadena de búsqueda
	        EventosDao eventosDao = new EventosDao();
	        ObservableList<Evento> listaEventos =  eventosDao.cargarEventos(cadena);
	        tablaEventos.setItems(listaEventos);
	        tablaEventos.refresh();
	    } catch(Exception e) {
	        // Maneja cualquier excepción que pueda ocurrir durante la carga
	        e.printStackTrace();
	    }
	}
    
    /* 	**************************************************************************************************************************************************
	 	**************************************************************************************************************************************************
	 	*								EQUIPOS
	 	************************************************************************************************************************************************** 
	 	**************************************************************************************************************************************************
	*/
    @FXML
    private TableView<Equipo> tablaEquipos;
    @FXML
    private TableColumn<Equipo, Integer> colIdEquipo;
    @FXML
    private TableColumn<Equipo, String> colInicialesEquipo;
    @FXML
    private TableColumn<Equipo, String> colNombreEquipo;
    
    @FXML
    void accionAniadirEquipo(ActionEvent event) {

    }
    
    @FXML
    void accionEditarEquipo(ActionEvent event) {

    }
    
    @FXML
    void accionBorrarEquipo(ActionEvent event) {

    }
    
    void cargarTablaEquipos(String cadena) {
	    try {
	        // Carga la tabla de aeropuertos públicos con la cadena de búsqueda
	        EquiposDao equiposDao = new EquiposDao();
	        ObservableList<Equipo> listaEquipos =  equiposDao.cargarEquipos(cadena);
	        tablaEquipos.setItems(listaEquipos);
	        tablaDeportistas.refresh();
	    } catch(Exception e) {
	        // Maneja cualquier excepción que pueda ocurrir durante la carga
	        e.printStackTrace();
	    }
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
