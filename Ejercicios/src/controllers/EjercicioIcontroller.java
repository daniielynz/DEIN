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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Persona;
import dao.PersonaDao;


public class EjercicioIcontroller {

	// Atributos para los botones
    @FXML
    private Button btnAgregar;
    
    @FXML
    private Button btnModificar;
    
    @FXML
    private Button btnEliminar;

    // Atributos para las columnas de la tabla y atributo de la tabla
    @FXML
    private TableColumn<Persona, String> colApellidos;

    @FXML
    private TableColumn<Persona, Integer> colEdad;

    @FXML
    private TableColumn<Persona, String> colNombre;

    @FXML
    private TableView<Persona> tableInfo;

    // Atributos para los textField
    @FXML
    private TextField tfApellidos;

    @FXML
    private TextField tfEdad;

    @FXML
    private TextField tfNombre;
    
    @FXML
    private TextField tfBuscarNombre;
    
    // Atributo de la ventana emergente
    private Stage ventanaEmergente;

    // Este método se ejecuta al inicializar la interfaz de usuario
    @FXML
    void initialize() {
        // Creamos un Tooltip y lo asociamos a los botones y al campo de búsqueda
        Tooltip tooltip = new Tooltip("Botón para modificar la persona seleccionada");
        btnModificar.setTooltip(tooltip);
        tooltip.setText("Botón para eliminar la persona seleccionada");
        btnEliminar.setTooltip(tooltip);
        tooltip.setText("Buscar personas por nombre");
        tfBuscarNombre.setTooltip(tooltip);

        // Asignamos un evento al TextField de filtrado por nombre
        tfBuscarNombre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Este código se ejecutará cuando se presione "Enter" en el TextField de búsqueda.
                String cadena = tfBuscarNombre.getText();
                mostrarNombresFiltro(cadena);
            }
        });

        // Inicializamos los campos de entrada
        tfNombre = new TextField();
        tfApellidos = new TextField();
        tfEdad = new TextField();

        // Asignamos eventos a la tabla para manejar clics en los registros
        tableInfo.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Persona p = tableInfo.getSelectionModel().getSelectedItem();
                if (p != null) {
                    tfNombre.setText(p.getNombre());
                    tfApellidos.setText(p.getApellidos());
                    tfEdad.setText(p.getEdad() + "");
                }
            }
        });

        // Creamos un menú contextual para la tabla
        ContextMenu contextMenu = new ContextMenu();
        MenuItem modifyItem = new MenuItem("Modificar");
        MenuItem deleteItem = new MenuItem("Eliminar");

        // Manejamos eventos de clic para las opciones del menú contextual
        modifyItem.setOnAction(e -> {
            Persona p = tableInfo.getSelectionModel().getSelectedItem();
            if (p != null) {
                crearVentanaEmergente("Editar Persona", "modificar");
            }
        });

        deleteItem.setOnAction(e -> {
            Persona p = tableInfo.getSelectionModel().getSelectedItem();
            if (p != null) {
                // Aquí puedes implementar la lógica para eliminar el registro seleccionado
                borrarPersonaLista(p);
                alertaInformacion("Se ha eliminado a la persona seleccionada");
            }
        });

        // Agregamos las dos opciones al menú contextual
        contextMenu.getItems().addAll(modifyItem, deleteItem);
        // Asociamos el menú contextual a la tabla
        tableInfo.setContextMenu(contextMenu);

        // Asignamos a las columnas de la tabla sus correspondientes propiedades
        colNombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Persona, String>("apellidos"));
        colEdad.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("edad"));

        // En caso de que existan personas en la base de datos, las cargamos en la tabla
        cargarTabla();
    }

    
    // Este método se ejecuta al hacer clic en el botón "Agregar"
    @FXML
    void accionAgregar(ActionEvent event) {
        // Vaciamos los campos de entrada
        vaciarCampos();
        // Creamos una ventana emergente para agregar una nueva persona
        crearVentanaEmergente("Nueva Persona", "guardar");
    }

    // Este método se ejecuta al hacer clic en el botón "Eliminar"
    @FXML
    private void accionEliminar(ActionEvent event) {
        // Guardamos la persona seleccionada en la tabla
        Persona p = tableInfo.getSelectionModel().getSelectedItem();
        // Borramos la persona de la lista y la base de datos
        borrarPersonaLista(p);
        // Mostramos una alerta informativa
        alertaInformacion("Se ha eliminado a la persona seleccionada");
    }

    // Este método se ejecuta al hacer clic en el botón "Modificar"
    @FXML
    void accionModificar(ActionEvent event) {
        // Creamos una ventana emergente para editar una persona existente
        crearVentanaEmergente("Editar Persona", "modificar");
    }

    
    
    /*/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    		METODOS 
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
 // Este método carga la tabla de información con datos de la base de datos
    void cargarTabla() {
        try {
            // Creamos una instancia de PersonaDao para gestionar la base de datos
            PersonaDao personaDao = new PersonaDao();
            // Creamos una lista observable para almacenar las personas
            ObservableList<Persona> personas = FXCollections.observableArrayList();
            // Cargamos las personas desde la base de datos
            personas = personaDao.cargarPersonas();
            // Establecemos la lista de personas en la tabla
            tableInfo.setItems(personas);
            // Actualizamos la visualización de la tabla
            tableInfo.refresh();
        } catch (Exception e) {
            // Manejamos cualquier excepción que pueda ocurrir, aunque no se realiza ninguna acción específica en caso de error
        }
    }

    // Este método muestra en la tabla de información los nombres que coinciden con la cadena proporcionada
    void mostrarNombresFiltro(String cadena) {
        // Creamos una instancia de PersonaDao para gestionar la base de datos
        PersonaDao personaDao = new PersonaDao();
        // Creamos una lista observable para almacenar las personas filtradas por nombre
        ObservableList<Persona> personas = FXCollections.observableArrayList();
        // Buscamos las personas que coincidan con la cadena de nombre proporcionada
        personas = personaDao.buscarPersonasPorNombre(cadena);
        // Establecemos la lista de personas filtradas en la tabla
        tableInfo.setItems(personas);
        // Actualizamos la visualización de la tabla
        tableInfo.refresh();
        // Después de mostrar los resultados, vaciamos los campos de entrada
        vaciarCampos();
    }

    // Este método se ejecuta al hacer clic en el botón "Guardar"
    void guardar(ActionEvent event) {
        // Validamos que los campos de entrada sean correctos y no contengan errores
        String errores = validarCampos();

        if (errores.isEmpty()) {
            // Creamos una instancia de Persona con los datos proporcionados en los campos de entrada
            Persona p = new Persona(tfNombre.getText(), tfApellidos.getText(), Integer.parseInt(tfEdad.getText()));
            // Añadimos la persona a la tabla y a la base de datos
            aniadirPersona(p);
            // Mostramos una alerta informativa
            alertaInformacion("Se ha añadido a la persona correctamente");
        } else {
            // Mostramos una alerta de error con los mensajes de error
            alertaError(errores);
        }
    }

    // Este método se ejecuta al hacer clic en el botón "Modificar"
    void modificar(ActionEvent event) {
        // Antes de modificar, validamos que los campos de entrada no contengan errores
        String errores = validarCampos();

        if (errores.isEmpty()) {
            // Obtenemos el ID de la persona seleccionada en la tabla
            int idPersona = tableInfo.getSelectionModel().getSelectedItem().getIdPersona();
            // Obtenemos los datos de los campos de entrada
            String nombre = tfNombre.getText();
            String apellidos = tfApellidos.getText();
            int edad = Integer.parseInt(tfEdad.getText());
            // Creamos una nueva instancia de Persona con los datos actualizados
            Persona p = new Persona(idPersona, nombre, apellidos, edad);
            // Modificamos la persona en la base de datos
            try {
                PersonaDao personaDao = new PersonaDao();
                personaDao.modificarPersona(p);
                // Actualizamos la tabla con los cambios
                cargarTabla();
            } catch (Exception e) {
                // Manejamos cualquier excepción que pueda ocurrir, aunque no se realiza ninguna acción específica en caso de error
            }

            // Cerramos la ventana emergente y mostramos una alerta informativa
            ventanaEmergente.close();
            alertaInformacion("Se ha modificado la persona seleccionada");
        } else {
            // Mostramos una alerta de error con los mensajes de error
            alertaError(errores);
        }
    }

    
 // Este método crea y muestra una ventana emergente con campos de entrada y botones
    private void crearVentanaEmergente(String titulo, String accion) {
        // Creamos una nueva instancia de la clase Stage para la ventana emergente
        ventanaEmergente = new Stage();
        // Creamos un contenedor VBox como raíz de la ventana emergente
        VBox contenedorRaiz = new VBox();

        // Creamos contenedores HBox para cada campo de entrada (Nombre, Apellidos, Edad)
        HBox contenedorNombre = new HBox();
        HBox contenedorApellidos = new HBox();
        HBox contenedorEdad = new HBox();

        // Establecemos un espaciado entre elementos en los contenedores HBox
        contenedorNombre.setSpacing(10);
        contenedorApellidos.setSpacing(10);
        contenedorEdad.setSpacing(10);

        // Si el campo de Nombre no existe, creamos uno nuevo (tfNombre)
        if (tfNombre == null) {
            tfNombre = new TextField();
        }
        // Agregamos una etiqueta "Nombre" y el campo de Nombre al contenedor
        contenedorNombre.getChildren().addAll(new javafx.scene.control.Label("Nombre"), tfNombre);

        // Si el campo de Apellidos no existe, creamos uno nuevo (tfApellidos)
        if (tfApellidos == null) {
            tfApellidos = new TextField();
        }
        // Agregamos una etiqueta "Apellidos" y el campo de Apellidos al contenedor
        contenedorApellidos.getChildren().addAll(new javafx.scene.control.Label("Apellidos"), tfApellidos);

        // Si el campo de Edad no existe, creamos uno nuevo (tfEdad)
        if (tfEdad == null) {
            tfEdad = new TextField();
        }
        // Agregamos una etiqueta "Edad" y el campo de Edad al contenedor
        contenedorEdad.getChildren().addAll(new javafx.scene.control.Label("Edad"), tfEdad);

        // Creamos un contenedor HBox para los botones (Guardar y Cerrar)
        HBox contenedorBotones = new HBox();
        contenedorBotones.setSpacing(10);

        // Creamos un botón "Guardar"
        Button guardarBtn = new Button("Guardar");
        // Configuramos el evento del botón en función de la acción (guardar o modificar)
        if (accion.equals("guardar")) {
            guardarBtn.setOnAction(e -> guardar(e));
        } else if (accion.equals("modificar")) {
            guardarBtn.setOnAction(e -> modificar(e));
        }

        // Creamos un botón "Cerrar" y configuramos su evento para cerrar la ventana emergente
        Button cerrarBtn = new Button("Cerrar");
        cerrarBtn.setOnAction(e -> ventanaEmergente.close());

        // Agregamos los botones al contenedor de botones
        contenedorBotones.getChildren().addAll(guardarBtn, cerrarBtn);

        // Agregamos todos los contenedores al contenedor raíz
        contenedorRaiz.getChildren().addAll(contenedorNombre, contenedorApellidos, contenedorEdad, contenedorBotones);

        // Configuramos propiedades del contenedor raíz
        contenedorRaiz.setPadding(new Insets(20));
        contenedorRaiz.setSpacing(20);

        // Creamos una escena con el contenedor raíz
        Scene escena = new Scene(contenedorRaiz);
        // Establecemos la escena en la ventana emergente
        ventanaEmergente.setScene(escena);

        // Establecemos el título de la ventana emergente según la acción (Nueva Persona o Modificar Persona)
        if (accion.equals("guardar")) {
            ventanaEmergente.setTitle("Nueva Persona");
        } else if (accion.equals("modificar")) {
            ventanaEmergente.setTitle("Modificar Persona");
        }

        // Desactivamos la posibilidad de redimensionar la ventana emergente
        ventanaEmergente.setResizable(false);
        // Mostramos la ventana emergente
        ventanaEmergente.show();
    }

    
 // Este método borra una persona de la lista en la base de datos y actualiza la tabla
    private void borrarPersonaLista(Persona p) {
        try {
            // Creamos una instancia de PersonaDao para gestionar la base de datos
            PersonaDao personaDao = new PersonaDao();
            // Eliminamos la persona de la base de datos
            personaDao.eliminarPersona(p);
            // Actualizamos la tabla que muestra las personas
            cargarTabla();
        } catch (Exception e) {
            // Manejamos cualquier excepción que pueda ocurrir, aunque no se realiza ninguna acción específica en caso de error
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
        // Obtenemos el dato del campo Edad y validamos que sea numérico y no esté vacío
        int edad = 0;
        try {
            edad = Integer.parseInt(tfEdad.getText());
        } catch (NumberFormatException e) {
            // En caso de que la conversión a entero falle, añadimos un mensaje de error a la cadena de errores
            errores += "La edad tiene que ser numérica\n";
        }

        return errores;
    }

    
 // Este método muestra una ventana emergente de tipo "Error" con un mensaje y un botón "Aceptar"
    private void alertaError(String mensaje) {
        // Creamos una nueva ventana emergente de tipo "Error" utilizando AlertType.ERROR
        Alert ventanaEmergente = new Alert(AlertType.ERROR);
        // Establecemos el título de la ventana emergente como "Información"
        ventanaEmergente.setTitle("Información");
        // Configuramos el contenido de la ventana emergente con el mensaje proporcionado
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
        // Creamos una nueva ventana emergente de tipo "Información" utilizando AlertType.INFORMATION
        Alert ventanaEmergente = new Alert(AlertType.INFORMATION);
        // Establecemos el título de la ventana emergente como "Información"
        ventanaEmergente.setTitle("Información");
        // Configuramos el contenido de la ventana emergente con el mensaje proporcionado
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

    // Este método agrega una instancia de Persona a la base de datos y actualiza la tabla
    private void aniadirPersona(Persona p) {
        try {
            // Creamos una instancia de PersonaDao para gestionar la base de datos
            PersonaDao personaDao = new PersonaDao();
            // Añadimos la persona a la base de datos
            personaDao.aniadirPersona(p);
            // Cerramos la ventana emergente actual
            ventanaEmergente.close();
            // Actualizamos la tabla que muestra las personas
            cargarTabla();
        } catch (Exception e) {
            // Manejamos cualquier excepción que pueda ocurrir
        }
    }

    // Este método solo sirve para borrar el contenido de tres campos de entrada (Nombre, Apellidos, Edad)
    private void vaciarCampos() {
        // Borramos el contenido del campo de Nombre
        tfNombre.setText("");
        // Borramos el contenido del campo de Apellidos
        tfApellidos.setText("");
        // Borramos el contenido del campo de Edad
        tfEdad.setText("");
    }


    
}




