package controllers;

import dao.AeropuertoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.AeropuertoPrivado;
import model.AeropuertoPublico;

public class ControllerListadoAeropuertos {
	// atributos de la tabla de aeropuertos privados
    @FXML
    private TableColumn<AeropuertoPrivado, Integer> colAnio;

    @FXML
    private TableColumn<AeropuertoPrivado, String>  colCalle;

    @FXML
    private TableColumn<AeropuertoPrivado, Integer>  colCapacidad;

    @FXML
    private TableColumn<AeropuertoPrivado, String>  colCiudad;

    @FXML
    private TableColumn<AeropuertoPrivado, Integer>  colId;

    @FXML
    private TableColumn<AeropuertoPrivado, Integer>  colNSocios;

    @FXML
    private TableColumn<AeropuertoPrivado, String>  colNombre;

    @FXML
    private TableColumn<AeropuertoPrivado, Integer> colNumero;

    @FXML
    private TableColumn<AeropuertoPrivado, String>  colPais;
    
    // atributos de la tabla de aeropuertos publicos
    @FXML
    private TableColumn<AeropuertoPublico, Integer> colAnio2;

    @FXML
    private TableColumn<AeropuertoPublico, String>  colCalle2;

    @FXML
    private TableColumn<AeropuertoPublico, Integer>  colCapacidad2;

    @FXML
    private TableColumn<AeropuertoPublico, String>  colCiudad2;

    @FXML
    private TableColumn<AeropuertoPublico, Integer>  colId2;

    @FXML
    private TableColumn<AeropuertoPublico, String>  colNombre2;

    @FXML
    private TableColumn<AeropuertoPublico, Integer> colNumero2;

    @FXML
    private TableColumn<AeropuertoPublico, String>  colPais2;
    
    @FXML
    private TableColumn<AeropuertoPublico, Integer> colNTrabajadores2;

    @FXML
    private TableColumn<AeropuertoPublico, Float>  colFinanciacion2;

    //
    @FXML
    private RadioButton rbPrivados;

    @FXML
    private RadioButton rbPublicos;

    @FXML
    private TableView<AeropuertoPrivado> tableAeropuertosPrivados;
    
    @FXML
    private TableView<AeropuertoPublico> tableAeropuertosPublicos;

    @FXML
    private TextField tfNombre;
    
    private AeropuertoPrivado aeropuertoPrivadoSeleccionado;
    
    private AeropuertoPublico aeropuertoPublicoSeleccionado;
    
    

	@FXML
    void initialize() {
        Tooltip tooltip = new Tooltip("Buscar Aeropuertos por nombre");
        tfNombre.setTooltip(tooltip);
        
        // Asignamos eventos a las tablas un evento para manejar clics en los registros
        tableAeropuertosPrivados.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
            	AeropuertoPrivado a = tableAeropuertosPrivados.getSelectionModel().getSelectedItem();
                if (a != null) {
                	aeropuertoPrivadoSeleccionado = a;
                	aeropuertoPublicoSeleccionado = null;
                }
            }
        });
        
        // Asignamos eventos a la tabla para manejar clics en los registros
        tableAeropuertosPublicos.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
            	AeropuertoPublico p = tableAeropuertosPublicos.getSelectionModel().getSelectedItem();
                if (p != null) {
                	aeropuertoPublicoSeleccionado = p;
                	aeropuertoPrivadoSeleccionado = null;
                }
            }
        });
    	
        // ponemos evento al TextField del filtrado por nombre
        rbPrivados.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	tableAeropuertosPublicos.setVisible(false);
            	tableAeropuertosPrivados.setVisible(true);
            	// cargamos los datos de la tabla
            	colId.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado, Integer>("Id") );
        		colNombre.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado,String>("nombre") );
        		colPais.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado,String>("pais") );
        		colCiudad.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado,String>("ciudad") );
        		colCalle.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado,String>("calle") );
        		colNumero.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado,Integer>("numero") );
        		colAnio.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado,Integer>("anioInauguracion") );
        		colCapacidad.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado,Integer>("capacidad") );
        		colNSocios.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado,Integer>("NSocios") );
        		// en caso de que existan personas en la base de datos, las cargamos en la tabla
        		cargarTablaAeropuertosPrivados("");
        		// ponemos evento al TextField del filtrado por nombre
                tfNombre.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        // Este código se ejecutará cuando se presione "Enter" en el TextField.
                        String cadena = tfNombre.getText();
                        cargarTablaAeropuertosPrivados(cadena);
                    }
                });
            }
        });
     // ponemos evento al TextField del filtrado por nombre
        rbPublicos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	tableAeropuertosPrivados.setVisible(false);
            	tableAeropuertosPublicos.setVisible(true);
            	// cargamos los datos de la tabla
            	colId2.setCellValueFactory(new PropertyValueFactory<AeropuertoPublico, Integer>("Id") );
        		colNombre2.setCellValueFactory(new PropertyValueFactory<AeropuertoPublico,String>("nombre") );
        		colPais2.setCellValueFactory(new PropertyValueFactory<AeropuertoPublico,String>("pais") );
        		colCiudad2.setCellValueFactory(new PropertyValueFactory<AeropuertoPublico,String>("ciudad") );
        		colCalle2.setCellValueFactory(new PropertyValueFactory<AeropuertoPublico,String>("calle") );
        		colNumero2.setCellValueFactory(new PropertyValueFactory<AeropuertoPublico,Integer>("numero") );
        		colAnio2.setCellValueFactory(new PropertyValueFactory<AeropuertoPublico,Integer>("anioInauguracion") );
        		colCapacidad2.setCellValueFactory(new PropertyValueFactory<AeropuertoPublico,Integer>("capacidad") );
        		colNTrabajadores2.setCellValueFactory(new PropertyValueFactory<AeropuertoPublico,Integer>("NTrabajadores") );
        		colFinanciacion2.setCellValueFactory(new PropertyValueFactory<AeropuertoPublico,Float>("financiacion") );
        		// en caso de que existan personas en la base de datos, las cargamos en la tabla
        		cargarTablaAeropuertosPublicos("");
        		// ponemos evento al TextField del filtrado por nombre
                tfNombre.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        // Este código se ejecutará cuando se presione "Enter" en el TextField.
                        String cadena = tfNombre.getText();
                        cargarTablaAeropuertosPublicos(cadena);
                    }
                });
            }
        });
    	
    }
    
    void cargarTablaAeropuertosPrivados(String cadena) {
    	try {
            AeropuertoDao aeropuertoDao = new AeropuertoDao();
            ObservableList<AeropuertoPrivado> aeropuertosPrivados = FXCollections.observableArrayList();
            aeropuertosPrivados = aeropuertoDao.cargarAeropuertosPrivados(cadena);
            tableAeropuertosPrivados.setItems(aeropuertosPrivados);
            tableAeropuertosPrivados.refresh();
        } catch(Exception e) {}
    }
    
    void cargarTablaAeropuertosPublicos(String cadena) {
    	try {
            AeropuertoDao aeropuertoDao = new AeropuertoDao();
            ObservableList<AeropuertoPublico> aeropuertosPublico = FXCollections.observableArrayList();
            aeropuertosPublico = aeropuertoDao.cargarAeropuertosPublicos(cadena);
            tableAeropuertosPublicos.setItems(aeropuertosPublico);
            tableAeropuertosPublicos.refresh();
        } catch(Exception e) {}
    }
    
    // Este metodo solo sirve para vaciar campos
    private void vaciarCampos() {
    	tfNombre.setText("");
    }
    
    // actions de aeropuertos en el menu
    @FXML
    void accionAniadirAeropuerto(ActionEvent event) {
    	try {
    		Stage primaryStage = new Stage();
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/AniadirAeropuerto.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("AVIONES-AÑADIR AEROPUERTO");
			
			Image icon = new Image(getClass().getResourceAsStream("/images/avion.png"));
			primaryStage.getIcons().add(icon);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void accionBorrarAeropuerto(ActionEvent event) {
    	AeropuertoDao aeropuertoDao = new AeropuertoDao();
    	if(aeropuertoPrivadoSeleccionado != null) {
            aeropuertoDao.borrarAeropuertoPrivado(aeropuertoPrivadoSeleccionado);
    	}else if(aeropuertoPublicoSeleccionado != null) {
    		aeropuertoDao.borrarAeropuertoPublico(aeropuertoPublicoSeleccionado);
    	}
    }
    
    @FXML
    void accionEditarAeropuerto(ActionEvent event) {
    	ControllerEditarAeropuertos c = new ControllerEditarAeropuertos();
    	if(aeropuertoPrivadoSeleccionado != null || aeropuertoPublicoSeleccionado != null) {
    		c.editarAeropuerto(aeropuertoPrivadoSeleccionado, aeropuertoPublicoSeleccionado);
    	}
    	
    }

    @FXML
    void accionInformacionAeropuerto(ActionEvent event) {

    }

    // actions de aviones en el menu
    @FXML
    void accionAniadirAviones(ActionEvent event) {

    }

    @FXML
    void accionAyudaAvion(ActionEvent event) {

    }

    @FXML
    void accionActivarDesactivarAvion(ActionEvent event) {

    }

    @FXML
    void accionBorrarAvion(ActionEvent event) {

    }
    
    // Metodo que crea ventana
    private void crearVentanaEmergente(String accion) {
    	
    }
    
    
    // Metodos de diferentes ventanas emergentes
    private void alertaError(String mensaje) {
    	// Alerta de error con boton
    	Alert ventanaEmergente = new Alert(AlertType.ERROR);
    	ventanaEmergente.setTitle("info");
    	ventanaEmergente.setContentText(mensaje);
    	Button ocultarBtn = new Button("Aceptar");
        ocultarBtn.setOnAction(e -> {
        	ventanaEmergente.hide();
        });
        ventanaEmergente.show();
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
    
    // geter del aeropuerto seleccionado
	public AeropuertoPrivado getAeropuertoPrivadoSeleccionado() {
		return aeropuertoPrivadoSeleccionado;
	}

	public AeropuertoPublico getAeropuertoPublicoSeleccionado() {
		return aeropuertoPublicoSeleccionado;
	}

    
}

