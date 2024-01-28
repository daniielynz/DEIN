package controllers;

import dao.AlumnoDao;
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
import model.Deporte;
import model.Alumno;
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
    private RadioButton rbAlumnos, rbLibros, rbHistoricoPrestamo, rbPrestamo;
    
    @FXML
    void initialize() {
		   // ponemos evento al radioButon de Prestamos
		   rbAlumnos.setOnAction(new EventHandler<ActionEvent>() {
		        @Override
		        public void handle(ActionEvent event) {
		        	ocultarTablas();
		        	tablaAlumnos.setVisible(true);
		        	
		        	// cargamos los datos de la tabla
		        	colDni.setCellValueFactory(new PropertyValueFactory<Alumno,Integer>("dni") );
		        	colNombreAlumno.setCellValueFactory(new PropertyValueFactory<Alumno,String>("nombre") );
		        	colApellido1Alumno.setCellValueFactory(new PropertyValueFactory<Alumno,String>("apellido1") );
		        	colApellido2Alumno.setCellValueFactory(new PropertyValueFactory<Alumno,String>("apellido2") );
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
    }
    
    private void ocultarTablas(){
    	tablaAlumnos.setVisible(false);
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
    private TableColumn<Alumno,Integer> colDni;
    @FXML
    private TableColumn<Alumno,String> colNombreAlumno;
    @FXML
    private TableColumn<Alumno,String> colApellido1Alumno;
    @FXML
    private TableColumn<Alumno,String> colApellido2Alumno;
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

}
