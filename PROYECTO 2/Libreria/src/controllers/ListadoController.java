package controllers;

import dao.AlumnoDao;
import dao.HistoricoDao;
import dao.LibroDao;
import dao.PrestamoDao;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Alumno;
import model.HistoricoPrestamo;
import model.Libro;
import model.Prestamo;

public class ListadoController {

	//
    @FXML
    private ToggleGroup groupRb;
    @FXML
    private TextField tfBuscarPorNombre;
    @FXML
    private Label lblListado;
    @FXML
    private RadioButton rbAlumnos, rbLibros, rbHistoricoPrestamos, rbPrestamos;
    
    @FXML
    void initialize() {
		   // ponemos evento al radioButon de Alumno
		   rbAlumnos.setOnAction(new EventHandler<ActionEvent>() {
		        @Override
		        public void handle(ActionEvent event) {
		        	ocultarTablas();
		        	tablaAlumnos.setVisible(true);
		        	
		        	// cargamos los datos de la tabla
		        	colDni.setCellValueFactory(new PropertyValueFactory<Alumno,String>("dni") );
		        	colNombreAlumno.setCellValueFactory(new PropertyValueFactory<Alumno,String>("nombre") );
		        	colApellido1.setCellValueFactory(new PropertyValueFactory<Alumno,String>("apellido1") );
		        	colApellido2.setCellValueFactory(new PropertyValueFactory<Alumno,String>("apellido2") );
		        	cargarTablaAlumnos("");
		    		
		    		// Creamos un menú contextual para la tabla
		            ContextMenu contextMenu = new ContextMenu();
		            MenuItem itemModificar = new MenuItem("Modificar");
		            MenuItem itemBorrar = new MenuItem("Eliminar");

		            // Manejamos eventos de clic para las opciones del menú contextual
		            itemModificar.setOnAction(e -> {
		            	Alumno alumnoSeleccionado = tablaAlumnos.getSelectionModel().getSelectedItem();
		                if(alumnoSeleccionado != null) {
		                	ControllerEditarAlumno contr = new ControllerEditarAlumno();
		                	// Modificamos el Alumno
		                	contr.editarAlumno(alumnoSeleccionado);
		                	// mensaje una vez de haya modificado
		                	alertaInformacion("Se ha modificado el alumno seleccionado");
		                	cargarTablaAlumnos("");
		                }
		            });

		            itemBorrar.setOnAction(e -> {
		            	Alumno alumnoSeleccionado = tablaAlumnos.getSelectionModel().getSelectedItem();
		                if(alumnoSeleccionado!=null) {
		                	AlumnoDao dao = new AlumnoDao();
		                	// borramos el Alumno
		                	dao.borrarAlumno(alumnoSeleccionado);
		                	// mensaje una vez de haya modificado
		                	alertaInformacion("Se ha borrado el alumno seleccionado");
		                	cargarTablaAlumnos("");
		                }
		            });
		            // agregamos los items al menu
		            contextMenu.getItems().addAll(itemModificar, itemBorrar);
		            tablaAlumnos.setContextMenu(contextMenu);
		    	
		    		// ponemos evento al TextField del filtrado por nombre
		            tfBuscarPorNombre.setOnAction(new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(ActionEvent event) {
		                    // Este código se ejecutará cuando se presione "Enter" en el TextField.
		                    String cadena = tfBuscarPorNombre.getText();
		                    cargarTablaAlumnos(cadena);
		                }
		            });
		        }
		    });
		   
		   // ponemos evento al radioButon de Libro
		   rbLibros.setOnAction(new EventHandler<ActionEvent>() {
		        @Override
		        public void handle(ActionEvent event) {
		        	ocultarTablas();
		        	tablaLibros.setVisible(true);
		        	
		        	// cargamos los datos de la tabla
		        	colCodigoLibro.setCellValueFactory(new PropertyValueFactory<Libro,Integer>("codigo") );
		        	colBajaLibro.setCellValueFactory(new PropertyValueFactory<Libro,Integer>("baja") );
		        	colTituloLibro.setCellValueFactory(new PropertyValueFactory<Libro,String>("titulo") );
		        	colAutorLibro.setCellValueFactory(new PropertyValueFactory<Libro,String>("autor") );
		        	colEditorialLibro.setCellValueFactory(new PropertyValueFactory<Libro,String>("editorial") );
		        	colEstadoLibro.setCellValueFactory(new PropertyValueFactory<Libro,String>("estado") );
		        	cargarTablaLibros("");
		    		
		    		// Creamos un menú contextual para la tabla
		            ContextMenu contextMenu = new ContextMenu();
		            MenuItem itemModificar = new MenuItem("Modificar");
		            MenuItem itemBorrar = new MenuItem("Eliminar");

		            // Manejamos eventos de clic para las opciones del menú contextual
		            itemModificar.setOnAction(e -> {
		            	Libro libroSeleccionado = tablaLibros.getSelectionModel().getSelectedItem();
		                if(libroSeleccionado != null) {
		                	//ControllerEditarAlumno contr = new ControllerEditarAlumno();
		                	// Modificamos el Libro
		                	//contr.editarAlumno(libroSeleccionado);
		                	// mensaje una vez de haya modificado
		                	alertaInformacion("Se ha modificado el libro seleccionado");
		                	cargarTablaLibros("");
		                }
		            });

		            itemBorrar.setOnAction(e -> {
		            	Libro libroSeleccionado = tablaLibros.getSelectionModel().getSelectedItem();
		                if(libroSeleccionado!=null) {
		                	LibroDao dao = new LibroDao();
		                	// borramos el Libro
		                	dao.borrarLibro(libroSeleccionado);
		                	// mensaje una vez de haya modificado
		                	alertaInformacion("Se ha borrado el libro seleccionado");
		                	cargarTablaLibros("");
		                }
		            });
		            // agregamos los items al menu
		            contextMenu.getItems().addAll(itemModificar, itemBorrar);
		            tablaLibros.setContextMenu(contextMenu);
		    	
		    		// ponemos evento al TextField del filtrado por nombre
		            tfBuscarPorNombre.setOnAction(new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(ActionEvent event) {
		                    // Este código se ejecutará cuando se presione "Enter" en el TextField.
		                    String cadena = tfBuscarPorNombre.getText();
		                    cargarTablaLibros(cadena);
		                }
		            });
		        }
		    });
		   
		   // ponemos evento al radioButon de Prestamo
		   rbPrestamos.setOnAction(new EventHandler<ActionEvent>() {
		        @Override
		        public void handle(ActionEvent event) {
		        	ocultarTablas();
		        	tablaPrestamos.setVisible(true);
		        	
		        	// cargamos los datos de la tabla
		        	colIdPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo,Integer>("id") );
		        	colDniPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo,String>("dni") );
		        	colFechaPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo,String>("fechaPrestamo") );
		        	colCodigoLibroPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo,Integer>("codigoLibro") );
		        	cargarTablaLibros("");
		    		
		    		// Creamos un menú contextual para la tabla
		            ContextMenu contextMenu = new ContextMenu();
		            MenuItem itemModificar = new MenuItem("Modificar");
		            MenuItem itemBorrar = new MenuItem("Eliminar");

		            // Manejamos eventos de clic para las opciones del menú contextual
		            itemModificar.setOnAction(e -> {
		            	Prestamo prestamoSeleccionado = tablaPrestamos.getSelectionModel().getSelectedItem();
		                if(prestamoSeleccionado != null) {
		                	//ControllerEditarAlumno contr = new ControllerEditarAlumno();
		                	// Modificamos el Prestamo
		                	//contr.editarPrestamo(prestamoSeleccionado);
		                	// mensaje una vez de haya modificado
		                	alertaInformacion("Se ha modificado el Prestamo seleccionado");
		                	cargarTablaPrestamo("");
		                }
		            });

		            itemBorrar.setOnAction(e -> {
		            	Prestamo prestamoSeleccionado = tablaPrestamos.getSelectionModel().getSelectedItem();
		                if(prestamoSeleccionado!=null) {
		                	PrestamoDao dao = new PrestamoDao();
		                	// borramos el Prestamo
		                	dao.borrarPrestamo(prestamoSeleccionado);
		                	// mensaje una vez de haya modificado
		                	alertaInformacion("Se ha borrado el Prestamo seleccionado");
		                	cargarTablaPrestamo("");
		                }
		            });
		            // agregamos los items al menu
		            contextMenu.getItems().addAll(itemModificar, itemBorrar);
		            tablaPrestamos.setContextMenu(contextMenu);
		    	
		    		// ponemos evento al TextField del filtrado por nombre
		            tfBuscarPorNombre.setOnAction(new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(ActionEvent event) {
		                    // Este código se ejecutará cuando se presione "Enter" en el TextField.
		                    String cadena = tfBuscarPorNombre.getText();
		                    cargarTablaPrestamo(cadena);
		                }
		            });
		        }
		    });
		   
		   // ponemos evento al radioButon de Historico Prestamos
		   rbHistoricoPrestamos.setOnAction(new EventHandler<ActionEvent>() {
		        @Override
		        public void handle(ActionEvent event) {
		        	ocultarTablas();
		        	tablaHistoricoPrestamos.setVisible(true);
		        	
		        	// cargamos los datos de la tabla
		        	colIdHistorico.setCellValueFactory(new PropertyValueFactory<HistoricoPrestamo,Integer>("id") );
		        	colDniHistorico.setCellValueFactory(new PropertyValueFactory<HistoricoPrestamo,String>("dni") );
		        	colIdLibroHistorico.setCellValueFactory(new PropertyValueFactory<HistoricoPrestamo,Integer>("id_libro") );
		        	colFechaPrestamoHistorico.setCellValueFactory(new PropertyValueFactory<HistoricoPrestamo,String>("fechaPrestamo") );
		        	colFechaDevolucionHistorico.setCellValueFactory(new PropertyValueFactory<HistoricoPrestamo,String>("codigoLibro") );
		        	cargarTablaLibros("");
		    		
		    		// Creamos un menú contextual para la tabla
		            ContextMenu contextMenu = new ContextMenu();
		            MenuItem itemModificar = new MenuItem("Modificar");
		            MenuItem itemBorrar = new MenuItem("Eliminar");

		            // Manejamos eventos de clic para las opciones del menú contextual
		            itemModificar.setOnAction(e -> {
		            	HistoricoPrestamo historicoSeleccionado = tablaHistoricoPrestamos.getSelectionModel().getSelectedItem();
		                if(historicoSeleccionado != null) {
		                	HistoricoDao contr = new HistoricoDao();
		                	// Modificamos el Historico
		                	//contr.editarPrestamo(prestamoSeleccionado);
		                	// mensaje una vez de haya modificado
		                	alertaInformacion("Se ha modificado el Historico seleccionado");
		                	cargarTablaPrestamo("");
		                }
		            });

		            itemBorrar.setOnAction(e -> {
		            	HistoricoPrestamo historicoSeleccionado = tablaHistoricoPrestamos.getSelectionModel().getSelectedItem();
		                if(historicoSeleccionado!=null) {
		                	HistoricoDao dao = new HistoricoDao();
		                	// borramos el Historico
		                	dao.borrarHistoricoPrestamo(historicoSeleccionado);
		                	// mensaje una vez de haya modificado
		                	alertaInformacion("Se ha borrado el Historico seleccionado");
		                	cargarTablaPrestamo("");
		                }
		            });
		            // agregamos los items al menu
		            contextMenu.getItems().addAll(itemModificar, itemBorrar);
		            tablaHistoricoPrestamos.setContextMenu(contextMenu);
		    	
		    		// ponemos evento al TextField del filtrado por nombre
		            tfBuscarPorNombre.setOnAction(new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(ActionEvent event) {
		                    // Este código se ejecutará cuando se presione "Enter" en el TextField.
		                    String cadena = tfBuscarPorNombre.getText();
		                    cargarTablaHistorico(cadena);
		                }
		            });
		        }
		    });
    }
    
    private void ocultarTablas(){
    	tablaAlumnos.setVisible(false);
    	tablaHistoricoPrestamos.setVisible(false);
    	tablaLibros.setVisible(false);
    	tablaPrestamos.setVisible(false);
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
     	*								ALUMNOS
     	************************************************************************************************************************************************** 
     	**************************************************************************************************************************************************
     */
    
    @FXML
    private TableColumn<Alumno,String> colDni;
    @FXML
    private TableColumn<Alumno,String> colNombreAlumno;
    @FXML
    private TableColumn<Alumno,String> colApellido1;
    @FXML
    private TableColumn<Alumno,String> colApellido2;
    @FXML
    private TableView<Alumno> tablaAlumnos;
    
    @FXML
    void accionAniadirAlumno(ActionEvent event) {
    	try {
	        // Abre la ventana para añadir un nuevo Deportista
	        Stage primaryStage = new Stage();
	        GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/aniadirAlumno.fxml"));
	        Scene scene = new Scene(root);
	        primaryStage.setTitle("AÑADIR ALUMNO");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    } catch(Exception e) {
	        // Maneja cualquier excepción que pueda ocurrir al abrir la ventana
	        e.printStackTrace();
	    }
    }
    
    private void cargarTablaAlumnos(String cadena) {
	    try {
	        // Carga la tabla de Deportes con la cadena de búsqueda
	        AlumnoDao alumnoDao = new AlumnoDao();
	        ObservableList<Alumno> listaAlumnos =  alumnoDao.cargarAlumnos(cadena);
	        tablaAlumnos.setItems(listaAlumnos);
	        tablaAlumnos.refresh();
	    } catch(Exception e) {
	        // Maneja cualquier excepción que pueda ocurrir durante la carga
	        e.printStackTrace();
	    }
	}
    
    /* 	**************************************************************************************************************************************************
 	**************************************************************************************************************************************************
 	*								LIBRO
 	************************************************************************************************************************************************** 
 	**************************************************************************************************************************************************
 	*/

	@FXML
	private TableColumn<Libro,Integer> colCodigoLibro;
	@FXML
	private TableColumn<Libro,String> colTituloLibro;
	@FXML
	private TableColumn<Libro,String> colAutorLibro;
	@FXML
	private TableColumn<Libro,String> colEditorialLibro;
	@FXML
	private TableColumn<Libro,String> colEstadoLibro;
	@FXML
	private TableColumn<Libro,Integer> colBajaLibro;
	@FXML
	private TableView<Libro> tablaLibros;
	
	@FXML
	void accionAniadirLibro(ActionEvent event) {
		try {
	        // Abre la ventana para añadir un nuevo Deportista
	        Stage primaryStage = new Stage();
	        GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/aniadirLibro.fxml"));
	        Scene scene = new Scene(root);
	        primaryStage.setTitle("AÑADIR LIBRO");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    } catch(Exception e) {
	        // Maneja cualquier excepción que pueda ocurrir al abrir la ventana
	        e.printStackTrace();
	    }
	}
	
	private void cargarTablaLibros(String cadena) {
	    try {
	        // Carga la tabla de Deportes con la cadena de búsqueda
	        LibroDao libroDao = new LibroDao();
	        ObservableList<Libro> listaLibros =  libroDao.cargarLibros(cadena);
	        tablaLibros.setItems(listaLibros);
	        tablaLibros.refresh();
	    } catch(Exception e) {
	        // Maneja cualquier excepción que pueda ocurrir durante la carga
	        e.printStackTrace();
	    }
	}
	
	/* 	**************************************************************************************************************************************************
 	**************************************************************************************************************************************************
 	*								PRESTAMO
 	************************************************************************************************************************************************** 
 	**************************************************************************************************************************************************
 	*/

	@FXML
	private TableColumn<Prestamo,Integer> colIdPrestamo;
	@FXML
	private TableColumn<Prestamo,String> colDniPrestamo;
	@FXML
	private TableColumn<Prestamo,String> colFechaPrestamo;
	@FXML
	private TableColumn<Prestamo,Integer> colCodigoLibroPrestamo;
	@FXML
	private TableView<Prestamo> tablaPrestamos;
	
	@FXML
	void accionAniadirPrestamo(ActionEvent event) {
		try {
	        // Abre la ventana para añadir un nuevo Deportista
	        Stage primaryStage = new Stage();
	        GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/aniadirPrestamo.fxml"));
	        Scene scene = new Scene(root);
	        primaryStage.setTitle("AÑADIR PRESTAMO");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    } catch(Exception e) {
	        // Maneja cualquier excepción que pueda ocurrir al abrir la ventana
	        e.printStackTrace();
	    }
	}
	
	private void cargarTablaPrestamo(String cadena) {
	    try {
	        // Carga la tabla de Deportes con la cadena de búsqueda
	        PrestamoDao prestamoDao = new PrestamoDao();
	        ObservableList<Prestamo> listaPrestamos =  prestamoDao.cargarPrestamos(cadena);
	        tablaPrestamos.setItems(listaPrestamos);
	        tablaPrestamos.refresh();
	    } catch(Exception e) {
	        // Maneja cualquier excepción que pueda ocurrir durante la carga
	        e.printStackTrace();
	    }
	}
	
	/* 	**************************************************************************************************************************************************
 	**************************************************************************************************************************************************
 	*								HISTORICO PRESTAMO
 	************************************************************************************************************************************************** 
 	**************************************************************************************************************************************************
 	*/

	@FXML
	private TableColumn<HistoricoPrestamo,Integer> colIdHistorico;
	@FXML
	private TableColumn<HistoricoPrestamo,String> colDniHistorico;
	@FXML
	private TableColumn<HistoricoPrestamo,Integer> colIdLibroHistorico;
	@FXML
	private TableColumn<HistoricoPrestamo,String> colFechaPrestamoHistorico;
	@FXML
	private TableColumn<HistoricoPrestamo,String> colFechaDevolucionHistorico;
	@FXML
	private TableView<HistoricoPrestamo> tablaHistoricoPrestamos;
	
	@FXML
	void accionAniadirHistorico(ActionEvent event) {
		try {
	        // Abre la ventana para añadir un nuevo Historico
	        Stage primaryStage = new Stage();
	        GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/aniadirHistoricoPrestamo.fxml"));
	        Scene scene = new Scene(root);
	        primaryStage.setTitle("AÑADIR HISTORICO PRESTAMO");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    } catch(Exception e) {
	        // Maneja cualquier excepción que pueda ocurrir al abrir la ventana
	        e.printStackTrace();
	    }
	}
	
	private void cargarTablaHistorico(String cadena) {
	    try {
	        // Carga la tabla de Deportes con la cadena de búsqueda
	        HistoricoDao historicoDao = new HistoricoDao();
	        ObservableList<HistoricoPrestamo> listaHistoricos =  historicoDao.cargarHistoricoPrestamos(cadena);
	        tablaHistoricoPrestamos.setItems(listaHistoricos);
	        tablaHistoricoPrestamos.refresh();
	    } catch(Exception e) {
	        // Maneja cualquier excepción que pueda ocurrir durante la carga
	        e.printStackTrace();
	    }
	}

}
