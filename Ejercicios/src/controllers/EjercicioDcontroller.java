package controllers;

import java.awt.Label;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Persona;


public class EjercicioDcontroller {

    @FXML
    private Button btnAgregar;

    @FXML
    private TableColumn<Persona, String> colApellidos;
    
    private ObservableList<Persona> personas;

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
    void initialize() {
    	personas = FXCollections.observableArrayList();
    	// asignamos a la columna colNombre su cabera NOMBRE, asignado en FXML
		colNombre.setCellValueFactory(new PropertyValueFactory<Persona,String>("nombre") );
		colApellidos.setCellValueFactory(new PropertyValueFactory<Persona,String>("apellidos") );
		colEdad.setCellValueFactory(new PropertyValueFactory<Persona,Integer>("edad") );
    	
    }
    
    @FXML
    void accionAgregar(ActionEvent event){
    	// creamos la ventana emergente y el contenedor
    	Stage ventanaEmergente = new Stage();
    	VBox contenedorRaiz = new VBox();
    		// contenedor para el Nombre
    		HBox contenedorNombre = new HBox();
    		contenedorNombre.setSpacing(10);
    		tfNombre = new TextField();
    		contenedorNombre.getChildren().addAll(new javafx.scene.control.Label("Nombre"), tfNombre);
    		
    		// contenedor para los Apellidos
    		HBox contenedorApellidos = new HBox();
    		contenedorApellidos.setSpacing(10);
    		tfApellidos = new TextField();
    		contenedorApellidos.getChildren().addAll(new javafx.scene.control.Label("Apellidos"), tfApellidos);
    		
    		// contenedor para la Edad
    		HBox contenedorEdad = new HBox();
    		contenedorEdad.setSpacing(10);
    		tfEdad = new TextField();
    		contenedorEdad.getChildren().addAll(new javafx.scene.control.Label("Edad"), tfEdad);
	    	
    		// contenedor para los botones
    		HBox contenedorBotones = new HBox();
    		contenedorBotones.setSpacing(10);
	    	Button guardarBtn = new Button("Guardar");
	    	guardarBtn.setOnAction(e -> guardar(e));
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
        ventanaEmergente.setTitle("Nueva Persona");
        ventanaEmergente.setResizable(false);
        ventanaEmergente.show();
    }
    
    /*/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    		METODOS 
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
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
    	// Comprueba si la persona ya existe en la lista
    	if(!personas.contains(p)) {
    		// si no existe
    		// añade la nueva persona y cargamos la tabla de nuevo
    		personas.add(p);
    		tableInfo.setItems(personas);
        	tableInfo.refresh();
        	// Despues de borrar vacia los campos
        	vaciarCampos();
    	}else {
    		// mensaje de error
    		alertaError("Esa persona ya existe");
    	}
    }
    
    // Este metodo solo sirve para vaciar campos
    private void vaciarCampos() {
    	tfNombre.setText("");
    	tfApellidos.setText("");
    	tfEdad.setText("");
    }

    
}



