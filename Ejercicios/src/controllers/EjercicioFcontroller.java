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


public class EjercicioFcontroller {

    @FXML
    private Button btnAgregar;

    @FXML
    private TableColumn<Persona, String> colApellidos;
    
    private ObservableList<Persona> personas;
    
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
    
    @FXML
    void accionImportar(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Archivos CSV", "*.csv"));
        fileChooser.setInitialFileName("datosTabla.csv");
    	
        try (BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getInitialFileName()))) {
        	
            reader.readLine();
            String line = reader.readLine();
            while (line != null) {
            	String[] partes = line.split(",");
            	
            	Persona p = new Persona(partes[0], partes[1], Integer.parseInt(partes[2]));
            	
            	if(!personas.contains(p)) {
            		personas.add(p);
            		tableInfo.setItems(personas);
                	tableInfo.refresh();
            	}
            	
            	line = reader.readLine();
            }
            alertaInformacion("Se ha importado correctamente");
        } catch (FileNotFoundException e) {
            alertaError("No existe el archivo");
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        }
    }
    
    @FXML
    void accionExportar(ActionEvent event) {
    	if(!personas.isEmpty()) {
    		FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new ExtensionFilter("Archivos CSV", "*.csv"));
            fileChooser.setInitialFileName("datosTabla.csv");
        	
        	try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooser.getInitialFileName()))) {
        		writer.write("Nombre,Apellidos,Edad");
        		writer.newLine();
                for (Persona p : personas) {
                    writer.write(p.getNombre()+","+p.getApellidos()+","+p.getEdad());
                    writer.newLine();
                }
                alertaInformacion("Se ha exportado correctamente");
            } catch (IOException e) {
                e.printStackTrace();
            }
    	}else {
    		alertaError("Tiene que haber alguna persona");
    	}
    	
    }
    
    /*/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    		METODOS 
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
    void mostrarNombresFiltro(String cadena) {
    	listaFiltro = FXCollections.observableArrayList();
    	for (Persona p: personas) {
            if(p.getNombre().contains(cadena)) {
            	listaFiltro.add(p);
            }
        }
    	tableInfo.setItems(listaFiltro);
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
    
    private void crearVentanaEmergente(String titulo, String accion) {
    	// creamos la ventana emergente y el contenedor
    	Stage ventanaEmergente = new Stage();
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
        ventanaEmergente.setTitle("Nueva Persona");
        ventanaEmergente.setResizable(false);
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




