package controllers;

import dao.DeportesDao;
import dao.DeportistasDao;
import dao.EquiposDao;
import dao.EventosDao;
import dao.OlimpiadasDao;
import dao.ParticipacionesDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Deporte;
import model.Deportista;
import model.Equipo;
import model.Evento;
import model.Olimpiada;
import model.Participacion;

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
		    		cargarTablaDeportistas("");
		    		
		    		//// Creamos un menú contextual para la tabla ////
                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem modifyItem = new MenuItem("Modificar");
                    MenuItem deleteItem = new MenuItem("Eliminar");

                    modifyItem.setOnAction(e -> {
                        alertaInformacion("Se esta intentado modificar un deportista");
                    });

                    deleteItem.setOnAction(e -> {
                    	Deportista deportistaSeleccionado = tablaDeportistas.getSelectionModel().getSelectedItem();
                        if(deportistaSeleccionado!=null) {
                        	DeportistasDao dao = new DeportistasDao();
	                		// hay que borrar el deportista de la participacion
	                    	dao.borrarDeportista(deportistaSeleccionado);
	                    	alertaInformacion("Se ha borrado el deportista seleccionado");
	                    	cargarTablaDeportistas("");
                        }
                    });
                    contextMenu.getItems().addAll(modifyItem, deleteItem);
                    tablaDeportistas.setContextMenu(contextMenu);
		    		
                    //// Filtrado por nombre de Deportista ////
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
		   
		   // ponemos evento al radioButon de Participacion
		   rbOlimpiadas.setOnAction(new EventHandler<ActionEvent>() {
		        @Override
		        public void handle(ActionEvent event) {
		        	ocultarTablas();
		        	tablaOlimpiadas.setVisible(true);
		        	// cargamos los datos de la tabla Evento
		        	colNombreOlimpiada.setCellValueFactory(new PropertyValueFactory<Olimpiada, String>("nombre") );
		        	colAnioOlimpiada.setCellValueFactory(new PropertyValueFactory<Olimpiada, Integer>("anio") );
		        	colIdOlimpiada.setCellValueFactory(new PropertyValueFactory<Olimpiada, Integer>("id_olimpiada") );
		        	colCiudadOlimpiada.setCellValueFactory(new PropertyValueFactory<Olimpiada, String>("ciudad") );
		            colTemporada.setCellValueFactory(new PropertyValueFactory<Olimpiada, String>("temporada") );
		    		// en caso de que existan personas en la base de datos, las cargamos en la tabla
		    		cargarTablaOlimpiadas("");
		    		
		    		// ponemos evento al TextField del filtrado por nombre
		            tfBuscarPorNombre.setOnAction(new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(ActionEvent event) {
		                    // Este código se ejecutará cuando se presione "Enter" en el TextField.
		                    String cadena = tfBuscarPorNombre.getText();
		                    cargarTablaOlimpiadas(cadena);
		                }
		            });
		        }
		    });
		   
		   // ponemos evento al radioButon de Participacion
		   rbParticipaciones.setOnAction(new EventHandler<ActionEvent>() {
		        @Override
		        public void handle(ActionEvent event) {
		        	ocultarTablas();
		        	tablaParticipaciones.setVisible(true);
		        	// cargamos los datos de la tabla Participacion
		        	colDeportistaParticipacion.setCellValueFactory(new PropertyValueFactory<Participacion, String>("deportista") );
		        	colEdadParticipacion.setCellValueFactory(new PropertyValueFactory<Participacion, Integer>("edad") );
		        	colEquipoParticipacion.setCellValueFactory(new PropertyValueFactory<Participacion, String>("equipo") );
		        	colEventoParticipacion.setCellValueFactory(new PropertyValueFactory<Participacion, String>("evento") );
		        	colMedallaParticipacion.setCellValueFactory(new PropertyValueFactory<Participacion, String>("medalla") );
		    		// en caso de que existan personas en la base de datos, las cargamos en la tabla
		        	cargarTablaParticipaciones("");
		    		
		    		// ponemos evento al TextField del filtrado por nombre
		            tfBuscarPorNombre.setOnAction(new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(ActionEvent event) {
		                    // Este código se ejecutará cuando se presione "Enter" en el TextField.
		                    String cadena = tfBuscarPorNombre.getText();
		                    cargarTablaParticipaciones(cadena);
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
    private TableColumn<Olimpiada, String> colNombreOlimpiada;
    @FXML
    private TableColumn<Olimpiada, Integer> colAnioOlimpiada;
    @FXML
    private TableColumn<Olimpiada, Integer> colIdOlimpiada;
    @FXML
    private TableColumn<Olimpiada, String> colTemporada;
    @FXML
    private TableView<Olimpiada> tablaOlimpiadas;
    @FXML
    private TableColumn<Olimpiada, String> colCiudadOlimpiada;
    
    void cargarTablaOlimpiadas(String cadena) {
	    try {
	        // Carga la tabla de aeropuertos públicos con la cadena de búsqueda
	    	OlimpiadasDao olimpiadasDao = new OlimpiadasDao();
	        ObservableList<Olimpiada> listaOlimpiadas =  olimpiadasDao.cargarOlimpiadas(cadena);
	        tablaOlimpiadas.setItems(listaOlimpiadas);
	        tablaOlimpiadas.refresh();
	    } catch(Exception e) {
	        // Maneja cualquier excepción que pueda ocurrir durante la carga
	        e.printStackTrace();
	    }
	}
    
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
    private TableView<Participacion> tablaParticipaciones;
    @FXML
    private TableColumn<Participacion, String> colDeportistaParticipacion;
    @FXML
    private TableColumn<Participacion, Integer> colEdadParticipacion;
    @FXML
    private TableColumn<Participacion, String> colEquipoParticipacion;
    @FXML
    private TableColumn<Participacion, String> colEventoParticipacion;
    @FXML
    private TableColumn<Participacion, String> colMedallaParticipacion;
    
    void cargarTablaParticipaciones(String cadena) {
	    try {
	        // Carga la tabla de aeropuertos públicos con la cadena de búsqueda
	    	ParticipacionesDao participacionesDao = new ParticipacionesDao();
	        ObservableList<Participacion> listaParticipaciones =  participacionesDao.cargarParticipaciones(cadena);
	        tablaParticipaciones.setItems(listaParticipaciones);
	        tablaParticipaciones.refresh();
	    } catch(Exception e) {
	        // Maneja cualquier excepción que pueda ocurrir durante la carga
	        e.printStackTrace();
	    }
	}
    
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
