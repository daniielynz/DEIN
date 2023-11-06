package controllers;

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
import javafx.stage.Stage;
import model.Persona;
import dao.PersonaDao;


public class EjercicioHcontroller {
	// Creamos los atributos de la clase
    // Atributos para cada columna de la tabla y para la tabla
    @FXML
    private TableColumn<Persona, String> colApellidos;

    @FXML
    private TableColumn<Persona, Integer> colEdad;

    @FXML
    private TableColumn<Persona, String> colNombre;

    @FXML
    private TableView<Persona> tableInfo;

    // Atributos para cada textfield sobre rellenar datos
    @FXML
    private TextField tfApellidos;

    @FXML
    private TextField tfEdad;

    @FXML
    private TextField tfNombre;
    
    @FXML
    private TextField tfBuscarNombre;
    
    // Atributo para boton de agregar
    @FXML
    private Button btnAgregar;
    
    // Atributo para la ventana emergente
    private Stage ventanaEmergente;
    
    // Metodo que se ejecuta cuando se ejecuta el controlador
    @FXML
    void initialize() {
    	// Ponemos evento al TextField de filtrado por nombre (tfBuscarNombre)
    	tfBuscarNombre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Este código se ejecutará cuando se presione "Enter" en el TextField.
                String cadena = tfBuscarNombre.getText();
                mostrarNombresFiltro(cadena);
            }
        });
    	
    	// Inicializamos los textfields 
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
    	
    	// Configura las columnas de la tabla para mostrar los atributos correspondientes del objeto "Persona".
		colNombre.setCellValueFactory(new PropertyValueFactory<Persona,String>("nombre") );
		colApellidos.setCellValueFactory(new PropertyValueFactory<Persona,String>("apellidos") );
		colEdad.setCellValueFactory(new PropertyValueFactory<Persona,Integer>("edad") );
		
		// en caso de que existan personas en la base de datos, las cargamos en la tabla
		cargarTabla();
    }
    
    @FXML
    void accionAgregar(ActionEvent event){
    	// Creamos la ventana emergente y le indicamos el titulo de la ventana y la accion que va a tener que hacer
    	crearVentanaEmergente("Nueva Persona", "guardar");
    }
    
    @FXML
    private void accionEliminar(ActionEvent event) {
    	// Guardo la persona seleccionada
    	Persona p = tableInfo.getSelectionModel().getSelectedItem();
    	// La borro de la lista
    	borrarPersonaLista(p);
    	// llamamos al metodo que sacara por pantalla una alerta de informacion con el texto introducido
    	alertaInformacion("Se ha eliminado a la persona seleccionada");
    }
    
    @FXML
    void accionModificar(ActionEvent event) {
    	// Creamos la ventana emergente y le indicamos el titulo de la ventana y la accion que va a tener que hacer
    	crearVentanaEmergente("Editar Persona", "modificar");
    }
    
    
    /*/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    		METODOS 
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
    
    // metodo que carga la tabla
    void cargarTabla() {
    	try {
            // creamos lista con personas que añadiremos a la tabla
            ObservableList<Persona> personas = FXCollections.observableArrayList();
            // Creamos un objeto de tipo PersonaDao para hacer las llamadas a BDD
            PersonaDao personaDao = new PersonaDao();
            personas=personaDao.cargarPersonas();
            // Le añadimos la lista de personas a la tabla
            tableInfo.setItems(personas);
            tableInfo.refresh();
        } catch(Exception e) {}
    }
    
    void mostrarNombresFiltro(String cadena) {
        // Creamos una instancia de la clase PersonaDao
        PersonaDao personaDao = new PersonaDao();
        // Creamos una lista observable de Personas
        ObservableList<Persona> personas = FXCollections.observableArrayList();
        // Buscamos personas por nombre y las almacenamos en la lista
        personas = personaDao.buscarPersonasPorNombre(cadena);
        // Establecemos la lista de personas en la tabla
        tableInfo.setItems(personas);
        // Actualizamos la tabla para reflejar los cambios
        tableInfo.refresh();
        // Llamamos a la función para vaciar los campos
        vaciarCampos();
    }

    void guardar(ActionEvent event) {
        // Validamos que los campos sean correctos y almacenamos los errores
        String errores = validarCampos();

        if (errores.isEmpty()) {
            // Creamos una nueva instancia de Persona con los datos de los campos
            Persona p = new Persona(tfNombre.getText(), tfApellidos.getText(), Integer.parseInt(tfEdad.getText()));
            // Añadimos la persona a la tabla
            aniadirPersona(p);
            // Mostramos una alerta de información
            alertaInformacion("Se ha añadido a la persona correctamente");
        } else {
            // Mostramos una alerta de error con los errores de validación
            alertaError(errores);
        }
    }

    void modificar(ActionEvent event) {
        // Antes de modificar, validamos que los campos no tengan errores y almacenamos los errores
        String errores = validarCampos();

        if (errores.isEmpty()) {
            // Obtenemos el ID de la persona seleccionada en la tabla
            int idPersona = tableInfo.getSelectionModel().getSelectedItem().getIdPersona();
            // Obtenemos los datos de los campos
            String nombre = tfNombre.getText();
            String apellidos = tfApellidos.getText();
            int edad = Integer.parseInt(tfEdad.getText());
            // Creamos una nueva instancia de Persona con los datos nuevos
            Persona p = new Persona(idPersona, nombre, apellidos, edad);
            try {
                // Creamos una instancia de PersonaDao
                PersonaDao personaDao = new PersonaDao();
                // Modificamos la persona en la base de datos
                personaDao.modificarPersona(p);
                // Recargamos la tabla
                cargarTabla();
            } catch (Exception e) {
            }

            // Cerramos la ventana emergente de modificación
            ventanaEmergente.close();
            // Mostramos una alerta de información
            alertaInformacion("Se ha modificado la persona seleccionada");
        } else {
            // Mostramos una alerta de error con los errores de validación
            alertaError(errores);
        }
    }

    // Este método crea una ventana emergente para agregar o modificar una persona
    private void crearVentanaEmergente(String titulo, String accion) {
        // Creamos una instancia de la clase Stage para la ventana emergente
        ventanaEmergente = new Stage();
        // Creamos un contenedor VBox como raíz de la ventana emergente
        VBox contenedorRaiz = new VBox();

        // Creamos contenedores HBox para cada campo de entrada (Nombre, Apellidos, Edad)
        HBox contenedorNombre = new HBox();
        HBox contenedorApellidos = new HBox();
        HBox contenedorEdad = new HBox();

        // Creamos campos de entrada (TextFields) si no existen
        if (tfNombre == null) {
            tfNombre = new TextField();
        }
        if (tfApellidos == null) {
            tfApellidos = new TextField();
        }
        if (tfEdad == null) {
            tfEdad = new TextField();
        }

        // Configuramos los contenedores de cada campo con etiquetas y campos de entrada
        contenedorNombre.getChildren().addAll(new javafx.scene.control.Label("Nombre"), tfNombre);
        contenedorApellidos.getChildren().addAll(new javafx.scene.control.Label("Apellidos"), tfApellidos);
        contenedorEdad.getChildren().addAll(new javafx.scene.control.Label("Edad"), tfEdad);

        // Creamos un contenedor HBox para los botones (Guardar y Cerrar)
        HBox contenedorBotones = new HBox();
        contenedorBotones.setSpacing(10);
        Button guardarBtn = new Button("Guardar");

        // Configuramos el evento del botón en función de la acción (guardar o modificar)
        if (accion.equals("guardar")) {
            guardarBtn.setOnAction(e -> guardar(e));
        } else if (accion.equals("modificar")) {
            guardarBtn.setOnAction(e -> modificar(e));
        }

        // Creamos el botón de cerrar y configuramos su evento
        Button cerrarBtn = new Button("Cerrar");
        cerrarBtn.setOnAction(e -> ventanaEmergente.close());

        // Agregamos los botones al contenedor de botones
        contenedorBotones.getChildren().addAll(guardarBtn, cerrarBtn);

        // Agregamos los contenedores al contenedor raíz
        contenedorRaiz.getChildren().addAll(contenedorNombre, contenedorApellidos, contenedorEdad, contenedorBotones);

        // Configuramos propiedades del contenedor raíz
        contenedorRaiz.setPadding(new Insets(20));
        contenedorRaiz.setSpacing(20);

        // Creamos una escena con el contenedor raíz
        Scene escena = new Scene(contenedorRaiz);
        ventanaEmergente.setScene(escena);

        // Establecemos el título de la ventana emergente según la acción
        if (accion.equals("guardar")) {
            ventanaEmergente.setTitle("Nueva Persona");
        } else if (accion.equals("modificar")) {
            ventanaEmergente.setTitle("Modificar Persona");
        }

        // Desactivamos la posibilidad de redimensionar la ventana
        ventanaEmergente.setResizable(false);
        // Mostramos la ventana emergente
        ventanaEmergente.show();
    }

    // Este método borra una persona de la lista
    private void borrarPersonaLista(Persona p) {
        try {
            // Creamos una instancia de PersonaDao
            PersonaDao personaDao = new PersonaDao();
            // Eliminamos la persona de la base de datos
            personaDao.eliminarPersona(p);
            // Recargamos la tabla
            cargarTabla();
        } catch (Exception e) {
        }
    }

    // Este método valida los campos de entrada y retorna los errores como una cadena
    private String validarCampos() {
        String errores = "";

        // Obtenemos el dato del campo Nombre y validamos que no esté vacío
        String nombre = tfNombre.getText();
        if (nombre.isEmpty()) {
            errores += "Tienes que rellenar el campo Nombre\n";
        }
        // Obtenemos el dato del campo Apellidos y validamos que no esté vacío
        String apellidos = tfApellidos.getText();
        if (apellidos.isEmpty()) {
            errores += "Tienes que rellenar el campo Apellidos\n";
        }
        // Obtenemos el dato
        return errores;
    }
    
    private void aniadirPersona(Persona p) {
    	try {
            PersonaDao personaDao = new PersonaDao();
            personaDao.aniadirPersona(p);
            ventanaEmergente.close();
            cargarTabla();
        } catch(Exception e) {}
    }
    
 // Este método se utiliza para borrar el contenido de tres campos de entrada
    private void vaciarCampos() {
        // Borramos el contenido del campo de Nombre
        tfNombre.setText("");
        // Borramos el contenido del campo de Apellidos
        tfApellidos.setText("");
        // Borramos el contenido del campo de Edad
        tfEdad.setText("");
    }

    // Este método muestra una ventana emergente de tipo "Error" con un mensaje y un botón "Aceptar
    private void alertaError(String mensaje) {
        // Creamos una nueva ventana emergente de tipo "Error"
        Alert ventanaEmergente = new Alert(AlertType.ERROR);
        // Establecemos el título de la ventana emergente
        ventanaEmergente.setTitle("Información");
        // Establecemos el contenido de la ventana emergente como el mensaje proporcionado
        ventanaEmergente.setContentText(mensaje);
        // Creamos un botón "Aceptar" para cerrar la ventana emergente
        Button ocultarBtn = new Button("Aceptar");
        // Configuramos el evento del botón para ocultar la ventana emergente al hacer clic en "Aceptar"
        ocultarBtn.setOnAction(e -> {
            ventanaEmergente.hide();
        });
        // Mostramos la ventana emergente
        ventanaEmergente.show();
    }

    // Este método muestra una ventana emergente de tipo "Información" con un mensaje y un botón "Aceptar"
    private void alertaInformacion(String mensaje) {
        // Creamos una nueva ventana emergente de tipo "Información"
        Alert ventanaEmergente = new Alert(AlertType.INFORMATION);
        // Establecemos el título de la ventana emergente
        ventanaEmergente.setTitle("Información");
        // Establecemos el contenido de la ventana emergente como el mensaje proporcionado
        ventanaEmergente.setContentText(mensaje);
        // Creamos un botón "Aceptar" para cerrar la ventana emergente
        Button ocultarBtn = new Button("Aceptar");
        // Configuramos el evento del botón para ocultar la ventana emergente al hacer clic en "Aceptar"
        ocultarBtn.setOnAction(e -> {
            ventanaEmergente.hide();
        });
        // Mostramos la ventana emergente
        ventanaEmergente.show();
    }


    
}




