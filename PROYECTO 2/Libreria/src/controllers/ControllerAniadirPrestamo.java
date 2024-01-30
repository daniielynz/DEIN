package controllers;

import java.time.format.DateTimeFormatter;

import dao.AlumnoDao;
import dao.LibroDao;
import dao.PrestamoDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Alumno;
import model.Libro;
import model.Prestamo;

public class ControllerAniadirPrestamo {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<Alumno> cbAlumnos;

    @FXML
    private ComboBox<Libro> cbLibros;
    
    @FXML
    private DatePicker dpFechaPrestamo;
    
    @FXML
    void initialize() {
    	AlumnoDao alumnosDao = new AlumnoDao();
        ObservableList<Alumno> listaOlimpiadas =  alumnosDao.cargarAlumnos("");
        cbAlumnos.setItems(listaOlimpiadas);
        
        LibroDao librosDao = new LibroDao();
        ObservableList<Libro> listaLibros =  librosDao.cargarLibros("");
        cbLibros.setItems(listaLibros);
    }

    @FXML
    void accionCancelar(ActionEvent event) {
    	// Cierra la ventana actual al hacer clic en el botón Cancelar
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void accionGuardar(ActionEvent event) {
    	
    	String errores = validar();
    	
    	if(errores.isEmpty()) {
    		// Le damos formato a la fecha
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fechaFormateada = dpFechaPrestamo.getValue().atStartOfDay().format(formatter);
    		
    		Prestamo prestamo = new Prestamo(this.cbAlumnos.getSelectionModel().getSelectedItem().getDni(), this.cbLibros.getSelectionModel().getSelectedItem().getCodigo(), fechaFormateada);
    		
	    	// Añadir el prestamo
    		PrestamoDao dao = new PrestamoDao();
	    	dao.aniadirPrestamo(prestamo);
	    	
	    	vaciarCampos();
    	}else {
    		alertaError(errores);
    	}
    }
    
    private String validar() {
    	String errores = "";
    	
    	if(dpFechaPrestamo.getValue().toString().isEmpty()) {
            errores += "Tienes que rellenar el campo Nombre\n";
        }
    	
    	if(this.cbAlumnos.getSelectionModel().getSelectedItem() == null) {
            errores += "Tienes que seleccionar un alumno\n";
        }
    	
    	if(this.cbLibros.getSelectionModel().getSelectedItem() == null) {
            errores += "Tienes que seleccionar un libro\n";
        }
    	
    	return errores;
    }
    
    private void vaciarCampos() {
    	dpFechaPrestamo.setValue(null);
    	cbAlumnos.getSelectionModel().select(0);
    	cbLibros.getSelectionModel().select(0);
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

}


