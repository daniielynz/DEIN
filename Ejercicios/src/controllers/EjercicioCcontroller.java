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
    	
    	personas = FXCollections.observableArrayList();
    	// asignamos a la columna colNombre su cabera NOMBRE, asignado en FXML
		colNombre.setCellValueFactory(new PropertyValueFactory<Persona,String>("nombre") );
		colApellidos.setCellValueFactory(new PropertyValueFactory<Persona,String>("apellidos") );
		colEdad.setCellValueFactory(new PropertyValueFactory<Persona,Integer>("edad") );
		
		//comportamiento de actualización por cada nueva entrada
		colEdad.setCellFactory(col-> {
				TableCell<Persona,Integer>cell = new TableCell<Persona, Integer>() {
					@Override public void updateItem(Integer item, boolean empty) {
						super.updateItem(item, empty);
						this.setText(null);
						this.setGraphic(null);
						if (!empty) {
							this.setText(Integer.toString(item));
						}
					}    					
				};
				cell.setAlignment(Pos.CENTER_RIGHT);
				return cell;
			
		});
    	
    }
    
    @FXML
    void accionRecuperarDatos(MouseEvent event) {
    	
    }
    
    @FXML
    void accionAceptar(ActionEvent event) {
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
			errores+= "El numero de hermanos tiene que ser numerico\n";
		}
    	
    	if(errores.isEmpty()) {
    		// Si no hay errores creo el objeto persona
    		Persona p = new Persona(nombre, apellidos, edad);
    		//tableInfo.refresh();
    		if(!personas.contains(p)) {
    			personas.add(p);
    			// Mostramos la informacion en la ventana emergente
        		Alert ventanaEmergente = new Alert(AlertType.INFORMATION);
            	ventanaEmergente.setTitle("info");
            	ventanaEmergente.setContentText("Persona añadida correctamente");
            	// Creamos el boton de Aceptar y su accion
            	Button ocultarBtn = new Button("Aceptar");
                ocultarBtn.setOnAction(e -> {
                	ventanaEmergente.hide();
                });
            	ventanaEmergente.show();
            	tableInfo.setItems(personas);
            	tableInfo.refresh();
    		}else {
    			// Mostramos ventana emergente de error
        		Alert ventanaEmergente = new Alert(AlertType.ERROR);
            	ventanaEmergente.setTitle("ERROR");
            	ventanaEmergente.setContentText("Esa persona ya existe");
            	// Creamos el boton de Aceptar y su accion
            	Button ocultarBtn = new Button("Aceptar");
                ocultarBtn.setOnAction(e -> {
                	ventanaEmergente.hide();
                });
            	ventanaEmergente.show();
    		}
    	}else {
    		// Mostramos ventana emergente de error
    		Alert ventanaEmergente = new Alert(AlertType.ERROR);
        	ventanaEmergente.setTitle("ERROR");
        	ventanaEmergente.setContentText(errores);
        	// Creamos el boton de Aceptar y su accion
        	Button ocultarBtn = new Button("Aceptar");
            ocultarBtn.setOnAction(e -> {
            	ventanaEmergente.hide();
            });
        	ventanaEmergente.show();
    	}
    }
    
    @FXML
    void accionEliminar(ActionEvent event) {

    }

    @FXML
    void accionModificar(ActionEvent event) {

    }
}


