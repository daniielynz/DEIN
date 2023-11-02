package controllers;

import dao.AeropuertoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AeropuertoPrivado;
import model.AeropuertoPublico;

public class ControllerListadoAeropuertos {
	// atributos de la tabla de aeropuertos privados
    @FXML
    private TableColumn<AeropuertoPrivado, String> colAnio;

    @FXML
    private TableColumn<AeropuertoPrivado, String>  colCalle;

    @FXML
    private TableColumn<AeropuertoPrivado, String>  colCapacidad;

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
    
    @FXML
    private TableColumn<AeropuertoPrivado, Integer> colNTrabajadores;

    @FXML
    private TableColumn<AeropuertoPrivado, String>  colFinanciacion;
    
    // atributos de la tabla de aeropuertos publicos
    @FXML
    private TableColumn<AeropuertoPrivado, String> colAnio2;

    @FXML
    private TableColumn<AeropuertoPrivado, String>  colCalle2;

    @FXML
    private TableColumn<AeropuertoPrivado, String>  colCapacidad2;

    @FXML
    private TableColumn<AeropuertoPrivado, String>  colCiudad2;

    @FXML
    private TableColumn<AeropuertoPrivado, Integer>  colId2;

    @FXML
    private TableColumn<AeropuertoPrivado, Integer>  colNSocios2;

    @FXML
    private TableColumn<AeropuertoPrivado, String>  colNombre2;

    @FXML
    private TableColumn<AeropuertoPrivado, Integer> colNumero2;

    @FXML
    private TableColumn<AeropuertoPrivado, String>  colPais2;
    
    @FXML
    private TableColumn<AeropuertoPrivado, Integer> colNTrabajadores2;

    @FXML
    private TableColumn<AeropuertoPrivado, String>  colFinanciacion2;

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
    
    @FXML
    void initialize() {
        Tooltip tooltip = new Tooltip("Buscar Aeropuertos por nombre");
        tfNombre.setTooltip(tooltip);
        
    	// ponemos evento al TextField del filtrado por nombre
        tfNombre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Este código se ejecutará cuando se presione "Enter" en el TextField.
                String cadena = tfNombre.getText();
                //mostrarNombresFiltro(cadena);
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
        		colAnio.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado,String>("anio") );
        		colCapacidad.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado,String>("capacidad") );
        		colNSocios.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado,Integer>("NSocios") );
        		// en caso de que existan personas en la base de datos, las cargamos en la tabla
        		cargarTablaAeropuertosPrivados();
            }
        });
     // ponemos evento al TextField del filtrado por nombre
        rbPublicos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	tableAeropuertosPrivados.setVisible(false);
            	tableAeropuertosPublicos.setVisible(true);
            	// cargamos los datos de la tabla
            	colId2.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado, Integer>("Id") );
        		colNombre2.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado,String>("nombre") );
        		colPais2.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado,String>("pais") );
        		colCiudad2.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado,String>("ciudad") );
        		colCalle2.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado,String>("calle") );
        		colNumero2.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado,Integer>("numero") );
        		colAnio2.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado,String>("anio") );
        		colCapacidad2.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado,String>("capacidad") );
        		colNTrabajadores2.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado,Integer>("numTrabajadores") );
        		colFinanciacion2.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado,String>("financiacion") );
        		// en caso de que existan personas en la base de datos, las cargamos en la tabla
        		cargarTablaAeropuertosPublicos();
            }
        });
    	
    }
    
    void cargarTablaAeropuertosPrivados() {
    	try {
            AeropuertoDao aeropuertoDao = new AeropuertoDao();
            ObservableList<AeropuertoPrivado> aeropuertosPrivados = FXCollections.observableArrayList();
            aeropuertosPrivados = aeropuertoDao.cargarAeropuertosPrivados();
            tableAeropuertosPrivados.setItems(aeropuertosPrivados);
            tableAeropuertosPrivados.refresh();
        } catch(Exception e) {}
    }
    
    void cargarTablaAeropuertosPublicos() {
    	try {
            AeropuertoDao aeropuertoDao = new AeropuertoDao();
            ObservableList<AeropuertoPublico> aeropuertosPublico = FXCollections.observableArrayList();
            aeropuertosPublico = aeropuertoDao.cargarAeropuertosPublicos();
            tableAeropuertosPublicos.setItems(aeropuertosPublico);
            tableAeropuertosPublicos.refresh();
        } catch(Exception e) {}
    }
    /*
    void mostrarNombresFiltro(String cadena) {
    	PersonaDao personaDao = new PersonaDao();
        ObservableList<Persona> personas = FXCollections.observableArrayList();
        personas=personaDao.buscarPersonasPorNombre(cadena);
        tableInfo.setItems(personas);
        tableInfo.refresh();
    	// Despues de borrar vacia los campos
    	vaciarCampos();
    }*/
    
    // Este metodo solo sirve para vaciar campos
    private void vaciarCampos() {
    	tfNombre.setText("");
    }
}
