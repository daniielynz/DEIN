package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Persona;

public class EjercicioCcontroller {

    @FXML
    private Button btnAgregar;
    
    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

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
    	
    	personas = FXCollections.observableArrayList();
    	// asignamos a la columna colNombre su cabera NOMBRE, asignado en FXML
		colNombre.setCellValueFactory(new PropertyValueFactory<Persona,String>("nombre") );
		colApellidos.setCellValueFactory(new PropertyValueFactory<Persona,String>("apellidos") );
		colEdad.setCellValueFactory(new PropertyValueFactory<Persona,Integer>("edad") );
    	
    }
    
    @FXML
    void accionAceptar(ActionEvent event) {
    	String errores = validarCampos();
    	
    	if(errores.isEmpty()) {
    		// Si no hay errores creo el objeto persona
    		Persona p = new Persona(tfNombre.getText(), tfApellidos.getText(), Integer.parseInt(tfEdad.getText()));
    		// LLamo al metodo que se encarga de comprobar y añadir
    		aniadirPersona(p);
    		alertaInformacion("Se ha añadido a la persona correctamente");
    	}else {
    		alertaError(errores);
    	}
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
    	//Antes de modificar, validamos que los campos no tengan errores
    	String errores = validarCampos();
 
    	if(errores.isEmpty()) {
    		// Si no da errores sacamos los datos
    		String nombre = tfNombre.getText();
        	String apellidos = tfApellidos.getText();
        	int edad = Integer.parseInt(tfEdad.getText());
        	// generamos una persona con los datos nuevos
        	Persona p = new Persona(nombre, apellidos, edad);
        	// borramos la persona seleccionada
        	borrarPersonaLista(tableInfo.getSelectionModel().getSelectedItem());
        	// Añadimos la persona seleccionada
        	aniadirPersona(p);
        	alertaInformacion("Se ha modificado la persona seleccionada");
    	}else {
    		// mostramos los errores
    		alertaError(errores);
    	}
    }
    
    /*/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    		METODOS 
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
    
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
    
    private void borrarPersonaLista(Persona p) {
    	// Compruebo si esa persona ya existe en la lista para poder borrarla
    	if(personas.contains(p)) {
    		// si la persona existe
    		// la borramos de la lista y cargamos la tabla de nuevo
    		personas.remove(p);
    		tableInfo.setItems(personas);
        	tableInfo.refresh();
        	// Despues de borrar vacia los campos
        	vaciarCampos();
    	}else {
    		// mensaje de error
    		alertaError("Esa persona no existe");
    	}
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


