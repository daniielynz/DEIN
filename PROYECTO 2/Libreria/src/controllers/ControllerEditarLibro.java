package controllers;

import dao.LibroDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Libro;

public class ControllerEditarLibro {
	private Libro libro;
	
	// Atributo de la ventana emergente
    private Stage ventanaEmergente;
    
    @FXML
    private TextField tfTitulo;
    
    @FXML
    private TextField tfAutor;
    
    @FXML
    private TextField tfEditorial;
    
    @FXML
    private TextField tfEstado;
    
    @FXML
    private TextField tfBaja;
    
    @FXML
    private Button btnAceptar;
    
    @FXML
    private Button btnCancelar;

	
	public void editarLibro(Libro libro) {
		this.libro = libro;
		
        // Creamos una nueva instancia de la clase Stage para la ventana emergente
        ventanaEmergente = new Stage();
        
        // Le ponemos titulo a la ventana
        ventanaEmergente.setTitle("MODIFICAR LIBRO");
        
        // Creamos un contenedor VBox como raíz de la ventana emergente
        VBox contenedorRaiz = new VBox();

        // Creamos contenedores HBox para cada campo de entrada
        HBox contenedorTitulo = new HBox();
        HBox contenedorAutor = new HBox();
        HBox contenedorEditorial = new HBox();
        HBox contenedorEstado = new HBox();
        HBox contenedorBaja = new HBox();

        // Establecemos un espaciado entre elementos en los contenedores HBox
        contenedorTitulo.setSpacing(10);
        contenedorAutor.setSpacing(10);
        contenedorEditorial.setSpacing(10);
        contenedorEstado.setSpacing(10);
        contenedorBaja.setSpacing(10);

        // le damos valor a los TextFields
        tfTitulo = new TextField();
        tfTitulo.setText(libro.getTitulo()+"");
        
        tfAutor = new TextField();
        tfAutor.setText(libro.getAutor());
        
        tfEditorial = new TextField();
        tfEditorial.setText(libro.getEditorial() + "");
        
        tfEstado = new TextField();
        tfEstado.setText(libro.getEstado() + "");
        
        tfBaja = new TextField();
        tfBaja.setText(libro.getBaja() + "");
        
        // Le ponemos los Labels junto a su TextField correspondiente
        contenedorTitulo.getChildren().addAll(new javafx.scene.control.Label("Titulo"), tfTitulo);
        contenedorAutor.getChildren().addAll(new javafx.scene.control.Label("Autor"), tfAutor);
        contenedorEditorial.getChildren().addAll(new javafx.scene.control.Label("Editorial"), tfEditorial);
        contenedorEstado.getChildren().addAll(new javafx.scene.control.Label("Estado"), tfEstado);
        contenedorBaja.getChildren().addAll(new javafx.scene.control.Label("Baja"), tfBaja);

        // Creamos un contenedor HBox para los botones (Guardar y Cerrar)
        HBox contenedorBotones = new HBox();
        contenedorBotones.setSpacing(10);

        // Creamos un botón "Guardar"
        Button guardarBtn = new Button("Guardar");
        guardarBtn.setOnAction(e -> modificar(e));

        // Creamos un botón "Cerrar" y configuramos su evento para cerrar la ventana emergente
        Button cerrarBtn = new Button("Cerrar");
        cerrarBtn.setOnAction(e -> ventanaEmergente.close());

        // Agregamos los botones al contenedor de botones
        contenedorBotones.getChildren().addAll(guardarBtn, cerrarBtn);

        // Agregamos todos los contenedores al contenedor raíz
        contenedorRaiz.getChildren().addAll(contenedorTitulo, contenedorAutor, contenedorEditorial, contenedorEstado, contenedorBaja, contenedorBotones);

        // Configuramos propiedades del contenedor raíz
        contenedorRaiz.setPadding(new Insets(20));
        contenedorRaiz.setSpacing(20);

        // Creamos una escena con el contenedor raíz
        Scene escena = new Scene(contenedorRaiz);
        // Establecemos la escena en la ventana emergente
        ventanaEmergente.setScene(escena);

        // Desactivamos la posibilidad de redimensionar la ventana emergente
        ventanaEmergente.setResizable(false);
        // Mostramos la ventana emergente
        ventanaEmergente.show();
    }
	
	void modificar(ActionEvent event) {
        // Antes de modificar, validamos que los campos de entrada no contengan errores
        String errores = validarCampos();

        if (errores.isEmpty()) {
            try {
            	LibroDao libroDao = new LibroDao();
            	// Le ponemos los datos nuevos al deportista
            	libro.setTitulo(this.tfTitulo.getText().toString());
            	libro.setAutor(this.tfAutor.getText().toString());
            	libro.setEditorial(this.tfEditorial.getText().toString());
            	libro.setEstado(this.tfEstado.getText().toString());
            	libro.setBaja(Integer.parseInt(this.tfBaja.getText().toString()));
            	
            	libroDao.editarLibro(libro);
                
                ventanaEmergente.close();
                alertaInformacion("Se ha modificado el libro seleccionado\nActualiza la tabla para ver los cambios");
            } catch (Exception e) {
                // Manejamos cualquier excepción que pueda ocurrir, aunque no se realiza ninguna acción específica en caso de error
            }
        } else {
            // Mostramos una alerta de error con los mensajes de error
            alertaError(errores);
        }
    }
	
	// Este método valida los campos de entrada y retorna los errores como una cadena
    private String validarCampos() {
    	String errores = "";
    	
    	if(tfTitulo.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo titulo\n";
        }
    	
    	if(tfAutor.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo autor\n";
        }
    	
    	if(tfEditorial.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo editorial\n";
        }
    	
    	if(tfEstado.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo estado\n";
        }
    	
    	if(tfBaja.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo baja\n";
        }else {
        	try {
                Integer.parseInt(tfBaja.getText());
                
                if(!tfBaja.getText().equals("1") && !tfBaja.getText().equals("0")) {
                	 errores += "El campo de baja tiene que ser 0 o 1\n";
                }
                	
            } catch (NumberFormatException e) {
                errores += "El campo de baja tiene que ser numerico\n";
            }
        }
    	
    	return errores;
    }
	
	@FXML
    void accionCancelar(ActionEvent event) {
    	// Cierra la ventana actual al hacer clic en el botón Cancelar
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
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
	
}
