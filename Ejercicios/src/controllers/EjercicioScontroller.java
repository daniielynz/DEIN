package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;
import model.Animal;


public class EjercicioScontroller {

    @FXML
    private Button btnAgregar;
    
    private ObservableList<Animal> animales;
    //
    @FXML
    private TableColumn<Animal, Integer> colEdad;

    @FXML
    private TableColumn<Animal, String> colEspecie;

    @FXML
    private TableColumn<Animal, String> colFechaPrimeraCita;

    @FXML
    private TableColumn<Animal, String> colFoto;

    @FXML
    private TableColumn<Animal, String> colNombre;

    @FXML
    private TableColumn<Animal, String> colObservaciones;

    @FXML
    private TableColumn<Animal, Integer> colPeso;

    @FXML
    private TableColumn<Animal, String> colRaza;

    @FXML
    private TableColumn<Animal, String> colSexo;
    
    @FXML
    private TableView<Animal> tableInfo;

    // textfields de la ventana emergente
    @FXML
    private TextField tfEdad;

    @FXML
    private TextField tfEspecie;
    
    @FXML
    private TextField tfFechaPrimeraCita;
    
    @FXML
    private TextField tfFoto;
    
    @FXML
    private TextField tfNombre;
    
    @FXML
    private TextField tfObservaciones;
    
    @FXML
    private TextField tfPeso;
    
    @FXML
    private TextField tfRaza;
    
    @FXML
    private TextField tfSexo;

    @FXML
    void initialize() {
    	// ponemos events a la tabla
    	tableInfo.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Animal a = tableInfo.getSelectionModel().getSelectedItem();
                if(a!=null) {
                	tfEdad.setText(a.getEdad()+"");
                	tfEspecie.setText(a.getEspecie());
                	tfFechaPrimeraCita.setText(a.getFechaPrimeraCita());
                	tfFoto.setText(a.getFoto());
                	tfNombre.setText(a.getNombre());
                	tfObservaciones.setText(a.getObservaciones());
                	tfPeso.setText(a.getPeso()+"");
                	tfRaza.setText(a.getRaza());
                	tfSexo.setText(a.getSexo());
                }
            }
        });
    	// inicializamos la lista con los datos de los animales
    	animales = FXCollections.observableArrayList();
    	// asignamos 
		colNombre.setCellValueFactory(new PropertyValueFactory<Animal,String>("nombre") );
		colSexo.setCellValueFactory(new PropertyValueFactory<Animal,String>("sexo") );
		colRaza.setCellValueFactory(new PropertyValueFactory<Animal,String>("raza") );
		colObservaciones.setCellValueFactory(new PropertyValueFactory<Animal,String>("observaciones") );
		colFechaPrimeraCita.setCellValueFactory(new PropertyValueFactory<Animal,String>("fechaPrimeraCita") );
		colEspecie.setCellValueFactory(new PropertyValueFactory<Animal,String>("especie") );
		colFoto.setCellValueFactory(new PropertyValueFactory<Animal,String>("foto") );
		colPeso.setCellValueFactory(new PropertyValueFactory<Animal,Integer>("peso") );
		colEdad.setCellValueFactory(new PropertyValueFactory<Animal,Integer>("edad") );
    	
    }
    
    @FXML
    void accionAgregar(ActionEvent event){
    	crearVentanaEmergente("Nuevo Animal", "guardar");
    }
    
    
    
    @FXML
    private void accionEliminar(ActionEvent event) {
    	// Guardo la Animal seleccionada
    	Animal a = tableInfo.getSelectionModel().getSelectedItem();
    	// La borro de la lista
    	borrarAnimalLista(a);
    	alertaInformacion("Se ha eliminado el Animal seleccionado");
    }
    
    @FXML
    void accionModificar(ActionEvent event) {
    	crearVentanaEmergente("Editar Animal", "modificar");
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
    		// Creamos el Animal
        	Animal p = new Animal(tfNombre.getText(), tfEspecie.getText(), tfRaza.getText(), tfSexo.getText(), tfObservaciones.getText(), tfFechaPrimeraCita.getText(), tfFoto.getText(), Integer.parseInt(tfEdad.getText()), Integer.parseInt(tfPeso.getText()));
        	// Lo añadimos a la tabla
        	aniadirAnimal(p);
        	alertaInformacion("Se ha añadido el Animal correctamente");
    	}else {
    		alertaError(errores);
    	}
    }
    
    void modificar(ActionEvent event) {
    	//Antes de modificar, validamos que los campos no tengan errores
    	String errores = validarCampos();
 
    	if(errores.isEmpty()) {
    		Animal p = new Animal(tfNombre.getText(), tfEspecie.getText(), tfRaza.getText(), tfSexo.getText(), tfObservaciones.getText(), tfFechaPrimeraCita.getText(), tfFoto.getText(), Integer.parseInt(tfEdad.getText()), Integer.parseInt(tfPeso.getText()));
        	// borramos el Animal seleccionado
        	borrarAnimalLista(tableInfo.getSelectionModel().getSelectedItem());
        	// Añadimos el Animal seleccionado
        	aniadirAnimal(p);
        	alertaInformacion("Se ha modificado el Animal seleccionado");
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
    		
    		// contenedor para la Especie
    		HBox contenedorEspecie = new HBox();
    		contenedorEspecie.setSpacing(10);
    		if(tfEspecie == null) {
    			tfEspecie = new TextField();
    		}
    		contenedorEspecie.getChildren().addAll(new javafx.scene.control.Label("Especie"), tfEspecie);
    		
    		// contenedor para la Raza
    		HBox contenedorRaza = new HBox();
    		contenedorRaza.setSpacing(10);
    		if(tfRaza == null) {
    			tfRaza = new TextField();
    		}
    		contenedorRaza.getChildren().addAll(new javafx.scene.control.Label("Raza"), tfRaza);
    		
    		// contenedor para el Sexo
    		HBox contenedorSexo = new HBox();
    		contenedorSexo.setSpacing(10);
    		if(tfSexo == null) {
    			tfSexo = new TextField();
    		}
    		contenedorSexo.getChildren().addAll(new javafx.scene.control.Label("Sexo"), tfSexo);
    		
    		// contenedor para la Edad
    		HBox contenedorEdad = new HBox();
    		contenedorEdad.setSpacing(10);
    		if(tfEdad == null) {
    			tfEdad = new TextField();
    		}
    		contenedorEdad.getChildren().addAll(new javafx.scene.control.Label("Edad"), tfEdad);
    		
    		// contenedor para el Peso
    		HBox contenedorPeso = new HBox();
    		contenedorPeso.setSpacing(10);
    		if(tfPeso == null) {
    			tfPeso = new TextField();
    		}
    		contenedorPeso.getChildren().addAll(new javafx.scene.control.Label("Peso"), tfPeso);
    		
    		// contenedor para las Observaciones
    		HBox contenedorObservaciones = new HBox();
    		contenedorObservaciones.setSpacing(10);
    		if(tfObservaciones == null) {
    			tfObservaciones = new TextField();
    		}
    		contenedorObservaciones.getChildren().addAll(new javafx.scene.control.Label("Observaciones"), tfObservaciones);
    		
    		// contenedor para la Fecha de la primera cita
    		HBox contenedorFechaPrimeraConsulta = new HBox();
    		contenedorFechaPrimeraConsulta.setSpacing(10);
    		if(tfFechaPrimeraCita == null) {
    			tfFechaPrimeraCita = new TextField();
    		}
    		contenedorFechaPrimeraConsulta.getChildren().addAll(new javafx.scene.control.Label("Fecha primera consulta"), tfFechaPrimeraCita);
    		
    		// contenedor para la Foto
    		HBox contenedorFoto= new HBox();
    		contenedorFoto.setSpacing(10);
    		if(tfFoto == null) {
    			tfFoto = new TextField();
    		}
    		contenedorFoto.getChildren().addAll(new javafx.scene.control.Label("Foto"), tfFoto);
	    	
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
        contenedorRaiz.getChildren().addAll(contenedorNombre, contenedorEspecie, contenedorRaza, contenedorSexo, contenedorEdad, contenedorPeso, contenedorObservaciones, contenedorFechaPrimeraConsulta, contenedorFoto, contenedorBotones);
        // Creamos propiedades para el contenedor
        contenedorRaiz.setPadding(new Insets(20));
        contenedorRaiz.setSpacing(20);
        // Creamos la escena
        Scene escena = new Scene(contenedorRaiz);
        ventanaEmergente.setScene(escena);
        if(accion.equals("guardar")) {
        	ventanaEmergente.setTitle("Nuevo Animal");
    	}else if(accion.equals("modificar")) {
    		ventanaEmergente.setTitle("Modificar Animal");
    	}
        ventanaEmergente.setResizable(false);
        ventanaEmergente.show();
    }
    
    private void borrarAnimalLista(Animal a) {
    	// Compruebo si ese Animal ya existe en la lista para poder borrarlo
    	if(animales.contains(a)) {
    		// si el Animal existe
    		// lo borramos de la lista y cargamos la tabla de nuevo
    		animales.remove(a);
    		tableInfo.setItems(animales);
        	tableInfo.refresh();
        	// Despues de borrar vacia los campos
        	vaciarCampos();
    	}else {
    		// mensaje de error
    		alertaError("Esa Animal no existe");
    	}
    }
    
    
    private String validarCampos() {
    	String errores = "";
    	
    	// Validamos que los TextField no esten vacios
    	if(tfNombre.getText().isEmpty()) {
    		errores+= "Tienes que rellenar el campo Nombre\n";
    	}
    	if(tfEdad.getText().isEmpty()) {
    		errores+= "Tienes que rellenar el campo Edad\n";
    	}
    	if(tfEspecie.getText().isEmpty()) {
    		errores+= "Tienes que rellenar el campo Especie\n";
    	}
    	if(tfFechaPrimeraCita.getText().isEmpty()) {
    		errores+= "Tienes que rellenar el campo de Fecha de primera cita\n";
    	}
    	if(tfFoto.getText().isEmpty()) {
    		errores+= "Tienes que rellenar el campo foto\n";
    	}
    	if(tfObservaciones.getText().isEmpty()) {
    		errores+= "Tienes que rellenar el campo observaciones\n";
    	}
    	if(tfRaza.getText().isEmpty()) {
    		errores+= "Tienes que rellenar el campo raza\n";
    	}
    	// Validamos que el TextField de la edad y del peso sean numericos
    	int edad = 0;
    	try {
    		edad = Integer.parseInt(tfEdad.getText());
		}catch(NumberFormatException e) {
			errores+= "La edad tiene que ser numerica\n";
		}
    	int peso = 0;
    	try {
    		peso = Integer.parseInt(tfPeso.getText());
		}catch(NumberFormatException e) {
			errores+= "El peso tiene que ser numerico\n";
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
    
    private void aniadirAnimal(Animal a) {
    	// Comprueba si el Animal ya existe en la lista
    	if(!animales.contains(a)) {
    		// si no existe
    		// añade el nueva Animal y cargamos la tabla de nuevo
    		animales.add(a);
    		tableInfo.setItems(animales);
        	tableInfo.refresh();
        	// Despues de borrar vacia los campos
        	vaciarCampos();
    	}else {
    		// mensaje de error
    		alertaError("Ese animal ya esta registrado");
    	}
    }
    
    // Este metodo solo sirve para vaciar campos
    private void vaciarCampos() {
    	tfEdad.setText("");
    	tfEspecie.setText("");
    	tfFechaPrimeraCita.setText("");
    	tfFoto.setText("");
    	tfNombre.setText("");
    	tfObservaciones.setText("");
    	tfPeso.setText("");
    	tfRaza.setText("");
    	tfSexo.setText("");
    }

    
}




