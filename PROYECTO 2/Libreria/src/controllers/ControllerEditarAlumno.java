package controllers;

import dao.AlumnoDao;
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
import model.Alumno;

public class ControllerEditarAlumno {
	private Alumno alumno;
	
	// Atributo de la ventana emergente
    private Stage ventanaEmergente;
    
    private String dniAntiguo;
    
    @FXML
    private TextField tfDni;
    
    @FXML
    private TextField tfNombre;
    
    @FXML
    private TextField tfApellido1;
    
    @FXML
    private TextField tfApellido2;
    
    @FXML
    private Button btnAceptar;
    
    @FXML
    private Button btnCancelar;

	
	public void editarAlumno(Alumno alumno) {
		this.alumno = alumno;
		this.dniAntiguo = alumno.getDni();
        // Creamos una nueva instancia de la clase Stage para la ventana emergente
        ventanaEmergente = new Stage();
        
        // Le ponemos titulo a la ventana
        ventanaEmergente.setTitle("MODIFICAR ALUMNO");
        
        // Creamos un contenedor VBox como raíz de la ventana emergente
        VBox contenedorRaiz = new VBox();

        // Creamos contenedores HBox para cada campo de entrada
        HBox contenedorDni = new HBox();
        HBox contenedorNombre = new HBox();
        HBox contenedorApellido1 = new HBox();
        HBox contenedorApellido2 = new HBox();

        // Establecemos un espaciado entre elementos en los contenedores HBox
        contenedorDni.setSpacing(10);
        contenedorNombre.setSpacing(10);
        contenedorApellido1.setSpacing(10);
        contenedorApellido2.setSpacing(10);

        // le damos valor a los TextFields
        tfDni = new TextField();
        tfDni.setText(alumno.getDni()+"");
        
        tfNombre = new TextField();
        tfNombre.setText(alumno.getNombre());
        
        tfApellido1 = new TextField();
        tfApellido1.setText(alumno.getApellido1() + "");
        
        tfApellido2 = new TextField();
        tfApellido2.setText(alumno.getApellido2() + "");
        
        // Le ponemos los Labels junto a su TextField correspondiente
        contenedorDni.getChildren().addAll(new javafx.scene.control.Label("DNI"), tfDni);
        contenedorNombre.getChildren().addAll(new javafx.scene.control.Label("Nombre"), tfNombre);
        contenedorApellido1.getChildren().addAll(new javafx.scene.control.Label("Primer apellido"), tfApellido1);
        contenedorApellido2.getChildren().addAll(new javafx.scene.control.Label("Segundo apellido"), tfApellido2);

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
        contenedorRaiz.getChildren().addAll(contenedorDni, contenedorNombre, contenedorApellido1, contenedorApellido2, contenedorBotones);

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
            	AlumnoDao alumnoDao = new AlumnoDao();
            	// Le ponemos los datos nuevos al deportista
            	alumno.setDni(this.tfDni.getText().toString());
            	alumno.setNombre(this.tfNombre.getText().toString());
            	alumno.setApellido1(this.tfApellido1.getText().toString());
            	alumno.setApellido2(this.tfApellido2.getText().toString());
            	
            	alumnoDao.editarAlumno(alumno, this.dniAntiguo);
                
                ventanaEmergente.close();
                alertaInformacion("Se ha modificado el alumno seleccionado\nActualiza la tabla para ver los cambios");
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
    	
    	if(tfDni.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo DNI\n";
        }
    	
    	if(tfNombre.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo Nombre\n";
        }
    	
    	if(tfApellido1.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo Primer Apellido\n";
        }
    	
    	if(tfApellido2.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo Segundo Apellido\n";
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
