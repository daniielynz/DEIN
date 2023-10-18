package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.Persona;
import dao.PersonaDao;


public class EjercicioHcontroller {

    @FXML
    private Button btnAgregar;

    @FXML
    private TableColumn<Persona, String> colApellidos;
    
    private ObservableList<Persona> listaFiltro;

    @FXML
    private TableColumn<Persona, Integer> colEdad;

    @FXML
    private TableColumn<Persona, String> colNombre;

    @FXML
    private TableView<Persona> tableInfo;

    @FXML
    private TextField tfApellidos;

    @FXML
    private TextField tfEdad;

    @FXML
    private TextField tfNombre;
    
    @FXML
    private TextField tfBuscarNombre;
    
    private Stage ventanaEmergente;

    @FXML
    void initialize() {
    	// ponemos evento al TextField del filtrado por nombre
    	tfBuscarNombre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Este código se ejecutará cuando se presione "Enter" en el TextField.
                String cadena = tfBuscarNombre.getText();
                mostrarNombresFiltro(cadena);
            }
        });
    	
    	tfNombre = new TextField();
    	tfApellidos = new TextField();
    	tfEdad = new TextField();
    	
    	//ponemos events a la tabla
    	tableInfo.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Persona p = tableInfo.getSelectionModel().getSelectedItem();
                if(p!=null) {
                	tfNombre.setText(p.getNombre());
                    tfApellidos.setText(p.getApellidos());
                    tfEdad.setText(p.getEdad()+"");
                }
            }
        });
    	
    	// asignamos a la columna colNombre su cabera NOMBRE, asignado en FXML
		colNombre.setCellValueFactory(new PropertyValueFactory<Persona,String>("nombre") );
		colApellidos.setCellValueFactory(new PropertyValueFactory<Persona,String>("apellidos") );
		colEdad.setCellValueFactory(new PropertyValueFactory<Persona,Integer>("edad") );
		
		// en caso de que existan personas en la base de datos, las cargamos en la tabla
		cargarTabla();
    }
    
    @FXML
    void accionAgregar(ActionEvent event){
    	crearVentanaEmergente("Nueva Persona", "guardar");
    }
    
    @FXML
    private void accionEliminar(ActionEvent event) {
    	// Guardo la persona seleccionada
    	Persona p = tableInfo.getSelectionModel().getSelectedItem();
    	// La borro de la lista
    	borrarPersonaLista(p);
    	alertaInformacion("Se ha eliminado a la persona seleccionada");
    }
    
    @FXML
    void accionModificar(ActionEvent event) {
    	crearVentanaEmergente("Editar Persona", "modificar");
    }
    
    
    /*/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    		METODOS 
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
    void cargarTabla() {
    	try {
            PersonaDao personaDao = new PersonaDao();
            ObservableList<Persona> personas = FXCollections.observableArrayList();
            personas=personaDao.cargarPersonas();
            tableInfo.setItems(personas);
            tableInfo.refresh();
        } catch(Exception e) {}
    }
    
    void mostrarNombresFiltro(String cadena) {
    	PersonaDao personaDao = new PersonaDao();
        ObservableList<Persona> personas = FXCollections.observableArrayList();
        personas=personaDao.buscarPersonasPorNombre(cadena);
        tableInfo.setItems(personas);
        tableInfo.refresh();
    	// Despues de borrar vacia los campos
    	vaciarCampos();
    }
    
    void guardar(ActionEvent event){
    	// Validamos que los campos sean correctos
    	String errores = validarCampos();
    	 
    	if(errores.isEmpty()) {
    		// Creamos la persona
        	Persona p = new Persona(tfNombre.getText(), tfApellidos.getText(), Integer.parseInt(tfEdad.getText()));
        	// La añadimos a la tabla
        	aniadirPersona(p);
        	alertaInformacion("Se ha añadido a la persona correctamente");
    	}else {
    		alertaError(errores);
    	}
    }
    
    void modificar(ActionEvent event) {
    	//Antes de modificar, validamos que los campos no tengan errores
    	String errores = validarCampos();
 
    	if(errores.isEmpty()) {
    		int idPersona = tableInfo.getSelectionModel().getSelectedItem().getIdPersona();
    		// Si no da errores sacamos los datos
    		String nombre = tfNombre.getText();
        	String apellidos = tfApellidos.getText();
        	int edad = Integer.parseInt(tfEdad.getText());
        	// generamos una persona con los datos nuevos
        	Persona p = new Persona(idPersona, nombre, apellidos, edad);
        	// borramos la persona seleccionada
        	try {
                PersonaDao personaDao = new PersonaDao();
                personaDao.modificarPersona(p);
                cargarTabla();
            } catch(Exception e) {}
        	
        	ventanaEmergente.close();
        	alertaInformacion("Se ha modificado la persona seleccionada");
    	}else {
    		// mostramos los errores
    		alertaError(errores);
    	}
    }
    
    private void crearVentanaEmergente(String titulo, String accion) {
    	// creamos la ventana emergente y el contenedor
    	ventanaEmergente = new Stage();
    	VBox contenedorRaiz = new VBox();
    		// contenedor para el Nombre
    		HBox contenedorNombre = new HBox();
    		contenedorNombre.setSpacing(10);
    		if(tfNombre == null) {
    			tfNombre = new TextField();
    		}
    		contenedorNombre.getChildren().addAll(new javafx.scene.control.Label("Nombre"), tfNombre);
    		
    		// contenedor para los Apellidos
    		HBox contenedorApellidos = new HBox();
    		contenedorApellidos.setSpacing(10);
    		if(tfApellidos == null) {
    			tfApellidos = new TextField();
    		}
    		contenedorApellidos.getChildren().addAll(new javafx.scene.control.Label("Apellidos"), tfApellidos);
    		
    		// contenedor para la Edad
    		HBox contenedorEdad = new HBox();
    		contenedorEdad.setSpacing(10);
    		if(tfEdad == null) {
    			tfEdad = new TextField();
    		}
    		contenedorEdad.getChildren().addAll(new javafx.scene.control.Label("Edad"), tfEdad);
	    	
    		// contenedor para los botones
    		HBox contenedorBotones = new HBox();
    		contenedorBotones.setSpacing(10);
	    	Button guardarBtn = new Button("Guardar");
	    	if(accion.equals("guardar")) {
	    		guardarBtn.setOnAction(e -> guardar(e));
	    	}else if(accion.equals("modificar")) {
	    		guardarBtn.setOnAction(e -> modificar(e));
	    	}
	        // Creamos el boton de cerrar y le damos el evento de cerrar
	        Button cerrarBtn = new Button("Cerrar");
	        cerrarBtn.setOnAction(e -> ventanaEmergente.close());
	        
	        contenedorBotones.getChildren().addAll(guardarBtn, cerrarBtn);
	        
        // añadimos los botones
        contenedorRaiz.getChildren().addAll(contenedorNombre, contenedorApellidos, contenedorEdad, contenedorBotones);
        // Creamos propiedades para el contenedor
        contenedorRaiz.setPadding(new Insets(20));
        contenedorRaiz.setSpacing(20);
        // Creamos la escena
        Scene escena = new Scene(contenedorRaiz);
        ventanaEmergente.setScene(escena);
        if(accion.equals("guardar")) {
        	ventanaEmergente.setTitle("Nueva Persona");
    	}else if(accion.equals("modificar")) {
    		ventanaEmergente.setTitle("Modificar Persona");
    	}
        ventanaEmergente.setResizable(false);
        ventanaEmergente.show();
    }
    
    private void borrarPersonaLista(Persona p) {
    	try {
            PersonaDao personaDao = new PersonaDao();
            personaDao.eliminarPersona(p);
            cargarTabla();
        } catch(Exception e) {}
    }
    
    private String validarCampos() {
    	String errores = "";
    	
    	// Obtenemos el dato del campo Nombre y validamos que no este vacio
    	String nombre = tfNombre.getText();
    	if(nombre.isEmpty()) {
    		errores+= "Tienes que rellenar el campo Nombre\n";
    	}
    	// Obtenemos el dato del campo Apellidos y validamos que no este vacio
    	String apellidos = tfApellidos.getText();
    	if(apellidos.isEmpty()) {
    		errores+= "Tienes que rellenar el campo Apellidos\n";
    	}
    	// Obtenemos el dato del campo Edad y validamos que sea numerico y no este vacio
    	int edad = 0;
    	try {
    		edad = Integer.parseInt(tfEdad.getText());
		}catch(NumberFormatException e) {
			errores+= "La edad tiene que ser numerica\n";
		}
    	
    	return errores;
    }
    
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
    
    private void aniadirPersona(Persona p) {
    	try {
            PersonaDao personaDao = new PersonaDao();
            personaDao.aniadirPersona(p);
            ventanaEmergente.close();
            cargarTabla();
        } catch(Exception e) {}
    }
    
    // Este metodo solo sirve para vaciar campos
    private void vaciarCampos() {
    	tfNombre.setText("");
    	tfApellidos.setText("");
    	tfEdad.setText("");
    }

    
}




