package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import conexion.Propiedades;
import dao.AlumnoDao;
import dao.HistoricoDao;
import dao.LibroDao;
import dao.PrestamoDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Alumno;
import model.HistoricoPrestamo;
import model.Libro;
import model.Prestamo;

/**
 * Controlador para la ventana de listado.
 */
public class ListadoController implements Initializable {

    @FXML
    private ToggleGroup groupRb;

    @FXML
    private TextField tfFiltro;

    @FXML
    private Label lbFiltro;

    @FXML
    private TextField tfFiltro2;

    @FXML
    private Label lbFiltro2;

    @FXML
    private Label lblListado;

    @FXML
    private RadioButton rbAlumnos, rbLibros, rbHistoricoPrestamos, rbPrestamos;

    private ResourceBundle bundle;
    
    /**
     * Inicializa el controlador después de que su elemento raíz haya sido completamente procesado.
     * 
     * @param arg0 La ubicación utilizada para resolver rutas relativas para el objeto raíz o null si la ubicación no se conoce.
     * @param arg1 El recurso utilizado para localizar el objeto raíz o null si el objeto raíz no fue localizado.
     */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		bundle = arg1;
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
		            MenuItem itemModificar = new MenuItem(bundle.getString("Modificar"));

		            // Manejamos eventos de clic para las opciones del menú contextual
		            itemModificar.setOnAction(e -> {
		            	Alumno alumnoSeleccionado = tablaAlumnos.getSelectionModel().getSelectedItem();
		                if(alumnoSeleccionado != null) {
		                	ControllerEditarAlumno contr = new ControllerEditarAlumno();
		                	// Modificamos el Alumno
		                	contr.editarAlumno(alumnoSeleccionado);
		                	cargarTablaAlumnos("");
		                }
		            });
		            
		            // agregamos los items al menu
		            contextMenu.getItems().addAll(itemModificar);
		            tablaAlumnos.setContextMenu(contextMenu);
		    	
		    		// ponemos evento al TextField del filtrado por nombre
		            lbFiltro.setText(bundle.getString("nombreFiltro"));
		            lbFiltro.setVisible(true);
		            
		            tfFiltro.setVisible(true);
		            tfFiltro.setOnAction(new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(ActionEvent event) {
		                    // Este código se ejecutará cuando se presione "Enter" en el TextField.
		                    String cadena = tfFiltro.getText();
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
		            MenuItem itemModificar = new MenuItem(bundle.getString("Modificar"));
		            MenuItem itemBorrar = new MenuItem(bundle.getString("Eliminar"));

		            // Manejamos eventos de clic para las opciones del menú contextual
		            itemModificar.setOnAction(e -> {
		            	Libro libroSeleccionado = tablaLibros.getSelectionModel().getSelectedItem();
		                if(libroSeleccionado != null) {
		                	ControllerEditarLibro contr = new ControllerEditarLibro();
		                	// Modificamos el Libro
		                	contr.editarLibro(libroSeleccionado);
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
		                	alertaInformacion(bundle.getString("mensajeLibroBorrado"));
		                	cargarTablaLibros("");
		                }
		            });
		            // agregamos los items al menu
		            contextMenu.getItems().addAll(itemModificar, itemBorrar);
		            tablaLibros.setContextMenu(contextMenu);
		    	
		            // ponemos evento al TextField del filtrado por nombre
		            lbFiltro.setText(bundle.getString("tituloFiltro"));
		            lbFiltro.setVisible(true);
		            
		            tfFiltro.setVisible(true);
		            tfFiltro.setOnAction(new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(ActionEvent event) {
		                    // Este código se ejecutará cuando se presione "Enter" en el TextField.
		                    String cadena = tfFiltro.getText();
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
		        	cargarTablaPrestamo("");
		    	
		            // ponemos evento al TextField del filtrado por nombre
		            lbFiltro.setText(bundle.getString("dniFiltro"));
		            lbFiltro.setVisible(true);
		            
		            tfFiltro.setVisible(true);
		            tfFiltro.setOnAction(new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(ActionEvent event) {
		                    // Este código se ejecutará cuando se presione "Enter" en el TextField.
		                    String cadena = tfFiltro.getText();
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
		        	colFechaDevolucionHistorico.setCellValueFactory(new PropertyValueFactory<HistoricoPrestamo,String>("fechaDevolucion") );
		        	cargarTablaHistorico("","");
		    		
		        	// Creamos un menú contextual para la tabla
		            ContextMenu contextMenu = new ContextMenu();
		            MenuItem itemModificar = new MenuItem(bundle.getString("Modificar"));
		            MenuItem itemBorrar = new MenuItem(bundle.getString("Eliminar"));

		            // Manejamos eventos de clic para las opciones del menú contextual
		            itemModificar.setOnAction(e -> {
		            	Libro libroSeleccionado = tablaLibros.getSelectionModel().getSelectedItem();
		                if(libroSeleccionado != null) {
		                	ControllerEditarLibro contr = new ControllerEditarLibro();
		                	// Modificamos el Libro
		                	contr.editarLibro(libroSeleccionado);
		                	cargarTablaHistorico("","");
		                }
		            });
		            
		            itemBorrar.setOnAction(e -> {
		            	Libro libroSeleccionado = tablaLibros.getSelectionModel().getSelectedItem();
		                if(libroSeleccionado!=null) {
		                	LibroDao dao = new LibroDao();
		                	// borramos el Libro
		                	dao.borrarLibro(libroSeleccionado);
		                	// mensaje una vez de haya modificado
		                	alertaInformacion(bundle.getString("mensajeHistoricoBorrado"));
		                	cargarTablaHistorico("","");
		                }
		            });
		            // agregamos los items al menu
		            contextMenu.getItems().addAll(itemModificar, itemBorrar);
		            tablaLibros.setContextMenu(contextMenu);
		    	
		            // ponemos evento al TextField del filtrado por nombre
		            lbFiltro.setText(bundle.getString("dniFiltro"));
		            lbFiltro.setVisible(true);
		            
		            tfFiltro.setVisible(true);
		            tfFiltro.setOnAction(new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(ActionEvent event) {
		                	tfFiltro2.setText("");
		                    // Este código se ejecutará cuando se presione "Enter" en el TextField.
		                    String cadena = tfFiltro.getText();
		                    cargarTablaHistorico(cadena,"");
		                }
		            });
		            
		            lbFiltro2.setText(bundle.getString("codigoFiltro"));
		            lbFiltro2.setVisible(true);
		            
		            tfFiltro2.setVisible(true);
		            tfFiltro2.setOnAction(new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(ActionEvent event) {
		                	
		                    // Este código se ejecutará cuando se presione "Enter" en el TextField.
		                    String cadena2 = tfFiltro2.getText();
		                    cargarTablaHistorico("",cadena2);
		                }
		            });
		            
		            // ponemos onclick para que cuando pulses un filtro, se borre lo que esta escrito en el otro y se cargen todos los datos en la tabla
		            tfFiltro2.setOnMouseClicked(new EventHandler<MouseEvent>() {
		                @Override
		                public void handle(MouseEvent event) {
		                	tfFiltro.setText("");
		                	cargarTablaHistorico("","");
		                }
		            });
		            tfFiltro.setOnMouseClicked(new EventHandler<MouseEvent>() {
		                @Override
		                public void handle(MouseEvent event) {
		                	tfFiltro2.setText("");
		                	cargarTablaHistorico("","");
		                }
		            });
		        }
		    });
	}
    
    /**
     * Oculta las tablas y los campos de filtro de búsqueda en la interfaz gráfica.
     */
    private void ocultarTablas() {
        // Oculta las tablas
        tablaAlumnos.setVisible(false);
        tablaHistoricoPrestamos.setVisible(false);
        tablaLibros.setVisible(false);
        tablaPrestamos.setVisible(false);

        // Oculta los campos de filtro de búsqueda
        lbFiltro.setVisible(false);
        tfFiltro.setVisible(false);
        lbFiltro2.setVisible(false);
        tfFiltro2.setVisible(false);

        // Limpia los campos de filtro
        tfFiltro.setText("");
        tfFiltro2.setText("");
    }

    /**
     * Muestra una ventana emergente de información con un mensaje proporcionado.
     *
     * @param mensaje Mensaje que se mostrará en la ventana emergente.
     */
    private void alertaInformacion(String mensaje) {
        // Alerta de información con botón de aceptar
        Alert ventanaEmergente = new Alert(Alert.AlertType.INFORMATION);
        ventanaEmergente.setTitle("info");
        ventanaEmergente.setContentText(mensaje);
        Button ocultarBtn = new Button("Aceptar");
        ocultarBtn.setOnAction(e -> {
            ventanaEmergente.hide();
        });
        ventanaEmergente.show();
    }

    /**
     * Maneja la acción del botón de ayuda offline.
     *
     * @param event Evento de acción.
     */
    @FXML
    public void accionAyudaOffline(ActionEvent event) {
        // Se crea un objeto FXMLLoader para cargar la interfaz de usuario desde un archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VisorAyudaOffline.fxml"));

        // Se declara una variable Parent para contener el nodo raíz del árbol de la interfaz de usuario
        Parent root;

        try {
            // Se carga la interfaz de usuario desde el archivo FXML
            root = loader.load();

            // Se crea una escena con el nodo raíz recién cargado
            Scene scene = new Scene(root);

            // Se crea un nuevo escenario (Stage) para mostrar la escena
            Stage stage = new Stage();
            stage.setScene(scene);

            // Se establece el título de la ventana del escenario
            stage.setTitle(bundle.getString("ayudaOffline"));

            // Se muestra el escenario al usuario
            stage.show();
        } catch (IOException e) {
            // En caso de error al cargar la interfaz de usuario, se muestra una alerta de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

		    
    
    /* 	**************************************************************************************************************************************************
     	**************************************************************************************************************************************************
     	*								ALUMNOS
     	************************************************************************************************************************************************** 
     	**************************************************************************************************************************************************
     */
    
    /**
     * Columna para mostrar el DNI de los alumnos en la tabla.
     */
    @FXML
    private TableColumn<Alumno, String> colDni;

    /**
     * Columna para mostrar el nombre de los alumnos en la tabla.
     */
    @FXML
    private TableColumn<Alumno, String> colNombreAlumno;

    /**
     * Columna para mostrar el primer apellido de los alumnos en la tabla.
     */
    @FXML
    private TableColumn<Alumno, String> colApellido1;

    /**
     * Columna para mostrar el segundo apellido de los alumnos en la tabla.
     */
    @FXML
    private TableColumn<Alumno, String> colApellido2;

    /**
     * Tabla que muestra la lista de alumnos.
     */
    @FXML
    private TableView<Alumno> tablaAlumnos;

    /**
     * Abre la ventana para añadir un nuevo alumno.
     *
     * @param event Evento de acción al hacer clic en el botón "Añadir Alumno".
     */
    @FXML
    void accionAniadirAlumno(ActionEvent event) {
        try {
            String idioma = Propiedades.getValor("idioma");
            String region = Propiedades.getValor("region");

            // Establecer la configuración regional por defecto
            Locale.setDefault(new Locale(idioma, region));

            // Cargar los recursos de idioma
            ResourceBundle bundle = ResourceBundle.getBundle("idiomas/messages");
            // Abre la ventana para añadir un nuevo alumno
            Stage primaryStage = new Stage();
            GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/aniadirAlumno.fxml"), bundle);
            Scene scene = new Scene(root);
            primaryStage.setTitle(bundle.getString("tituloAniadirAlumno"));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            // Maneja cualquier excepción que pueda ocurrir al abrir la ventana
            e.printStackTrace();
        }
    }

    /**
     * Carga la tabla de alumnos con la lista obtenida de la base de datos, filtrada por la cadena de búsqueda.
     *
     * @param cadena Cadena de búsqueda para filtrar la lista de alumnos.
     */
    private void cargarTablaAlumnos(String cadena) {
        try {
            // Carga la tabla de alumnos con la cadena de búsqueda
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

    /**
     * Columna para mostrar el código de los libros en la tabla.
     */
    @FXML
    private TableColumn<Libro, Integer> colCodigoLibro;

    /**
     * Columna para mostrar el título de los libros en la tabla.
     */
    @FXML
    private TableColumn<Libro, String> colTituloLibro;

    /**
     * Columna para mostrar el autor de los libros en la tabla.
     */
    @FXML
    private TableColumn<Libro, String> colAutorLibro;

    /**
     * Columna para mostrar la editorial de los libros en la tabla.
     */
    @FXML
    private TableColumn<Libro, String> colEditorialLibro;

    /**
     * Columna para mostrar el estado de los libros en la tabla.
     */
    @FXML
    private TableColumn<Libro, String> colEstadoLibro;

    /**
     * Columna para mostrar el estado de baja de los libros en la tabla.
     */
    @FXML
    private TableColumn<Libro, Integer> colBajaLibro;

    /**
     * Tabla que muestra la lista de libros.
     */
    @FXML
    private TableView<Libro> tablaLibros;

    /**
     * Abre la ventana para añadir un nuevo libro.
     *
     * @param event Evento de acción al hacer clic en el botón "Añadir Libro".
     */
    @FXML
    void accionAniadirLibro(ActionEvent event) {
        try {
            String idioma = Propiedades.getValor("idioma");
            String region = Propiedades.getValor("region");

            // Establecer la configuración regional por defecto
            Locale.setDefault(new Locale(idioma, region));

            // Cargar los recursos de idioma
            ResourceBundle bundle = ResourceBundle.getBundle("idiomas/messages");
            // Abre la ventana para añadir un nuevo libro
            Stage primaryStage = new Stage();
            GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/aniadirLibro.fxml"), bundle);
            Scene scene = new Scene(root);
            primaryStage.setTitle(bundle.getString("tituloAniadirLibro"));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            // Maneja cualquier excepción que pueda ocurrir al abrir la ventana
            e.printStackTrace();
        }
    }

    /**
     * Carga la tabla de libros con la lista obtenida de la base de datos, filtrada por la cadena de búsqueda.
     *
     * @param cadena Cadena de búsqueda para filtrar la lista de libros.
     */
    private void cargarTablaLibros(String cadena) {
        try {
            // Carga la tabla de libros con la cadena de búsqueda
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

    /**
     * Columna para mostrar el ID de los préstamos en la tabla.
     */
    @FXML
    private TableColumn<Prestamo, Integer> colIdPrestamo;

    /**
     * Columna para mostrar el DNI asociado a los préstamos en la tabla.
     */
    @FXML
    private TableColumn<Prestamo, String> colDniPrestamo;

    /**
     * Columna para mostrar la fecha de los préstamos en la tabla.
     */
    @FXML
    private TableColumn<Prestamo, String> colFechaPrestamo;

    /**
     * Columna para mostrar el código de los libros asociados a los préstamos en la tabla.
     */
    @FXML
    private TableColumn<Prestamo, Integer> colCodigoLibroPrestamo;

    /**
     * Tabla que muestra la lista de préstamos.
     */
    @FXML
    private TableView<Prestamo> tablaPrestamos;

    /**
     * Abre la ventana para añadir un nuevo préstamo.
     *
     * @param event Evento de acción al hacer clic en el botón "Añadir Préstamo".
     */
    @FXML
    void accionAniadirPrestamo(ActionEvent event) {
        try {
            String idioma = Propiedades.getValor("idioma");
            String region = Propiedades.getValor("region");

            // Establecer la configuración regional por defecto
            Locale.setDefault(new Locale(idioma, region));

            // Cargar los recursos de idioma
            ResourceBundle bundle = ResourceBundle.getBundle("idiomas/messages");
            // Abre la ventana para añadir un nuevo préstamo
            Stage primaryStage = new Stage();
            GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/aniadirPrestamo.fxml"), bundle);
            Scene scene = new Scene(root);
            primaryStage.setTitle(bundle.getString("tituloAniadirPrestamo"));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            // Maneja cualquier excepción que pueda ocurrir al abrir la ventana
            e.printStackTrace();
        }
    }

    /**
     * Carga la tabla de préstamos con la lista obtenida de la base de datos, filtrada por la cadena de búsqueda.
     *
     * @param cadena Cadena de búsqueda para filtrar la lista de préstamos.
     */
    private void cargarTablaPrestamo(String cadena) {
        try {
            // Carga la tabla de préstamos con la cadena de búsqueda
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

    /**
     * Columna para mostrar el ID de los registros históricos de préstamos en la tabla.
     */
    @FXML
    private TableColumn<HistoricoPrestamo, Integer> colIdHistorico;

    /**
     * Columna para mostrar el DNI asociado a los registros históricos de préstamos en la tabla.
     */
    @FXML
    private TableColumn<HistoricoPrestamo, String> colDniHistorico;

    /**
     * Columna para mostrar el ID del libro asociado a los registros históricos de préstamos en la tabla.
     */
    @FXML
    private TableColumn<HistoricoPrestamo, Integer> colIdLibroHistorico;

    /**
     * Columna para mostrar la fecha de préstamo de los registros históricos en la tabla.
     */
    @FXML
    private TableColumn<HistoricoPrestamo, String> colFechaPrestamoHistorico;

    /**
     * Columna para mostrar la fecha de devolución de los registros históricos en la tabla.
     */
    @FXML
    private TableColumn<HistoricoPrestamo, String> colFechaDevolucionHistorico;

    /**
     * Tabla que muestra el historial de préstamos.
     */
    @FXML
    private TableView<HistoricoPrestamo> tablaHistoricoPrestamos;

    /**
     * Abre la ventana para añadir un nuevo registro histórico de préstamo.
     *
     * @param event Evento de acción al hacer clic en el botón "Añadir Histórico".
     */
    @FXML
    void accionAniadirHistorico(ActionEvent event) {
        try {
            String idioma = Propiedades.getValor("idioma");
            String region = Propiedades.getValor("region");

            // Establecer la configuración regional por defecto
            Locale.setDefault(new Locale(idioma, region));

            // Cargar los recursos de idioma
            ResourceBundle bundle = ResourceBundle.getBundle("idiomas/messages");
            // Abre la ventana para añadir un nuevo registro histórico de préstamo
            Stage primaryStage = new Stage();
            GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/aniadirHistoricoPrestamo.fxml"), bundle);
            Scene scene = new Scene(root);
            primaryStage.setTitle(bundle.getString("tituloAniadirHistorico"));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            // Maneja cualquier excepción que pueda ocurrir al abrir la ventana
            e.printStackTrace();
        }
    }

    /**
     * Carga la tabla de registros históricos de préstamos con la lista obtenida de la base de datos,
     * filtrada por el DNI del alumno y el código del libro.
     *
     * @param dni    DNI del alumno para filtrar la lista de registros históricos.
     * @param codigo Código del libro para filtrar la lista de registros históricos.
     */
    private void cargarTablaHistorico(String dni, String codigo) {
        try {
            // Carga la tabla de registros históricos con el DNI y código de libro proporcionados
            HistoricoDao historicoDao = new HistoricoDao();
            ObservableList<HistoricoPrestamo> listaHistoricos =  historicoDao.cargarHistoricoPrestamos(dni, codigo);
            tablaHistoricoPrestamos.setItems(listaHistoricos);
            tablaHistoricoPrestamos.refresh();
        } catch(Exception e) {
            // Maneja cualquier excepción que pueda ocurrir durante la carga
            e.printStackTrace();
        }
    }


}
