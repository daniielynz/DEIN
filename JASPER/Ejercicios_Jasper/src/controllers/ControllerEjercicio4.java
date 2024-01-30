package controllers;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JREmptyDataSource;


public class ControllerEjercicio4 {

    @FXML
    private Button btnGenerarInforme;

    @FXML
    private Button btnVaciarCampos;

    @FXML
    private Button btnSalir;

    @FXML
    private TextArea taTratamiento;

    @FXML
    private TextField tfCodigoMedico;

    @FXML
    private TextField tfDireccionPaciente;

    @FXML
    private TextField tfEspecialidadMedico;

    @FXML
    private TextField tfNombreMedico;

    @FXML
    private TextField tfNombrePaciente;

    @FXML
    private TextField tfNumeroPaciente;

   
	public static void generarInforme(Map<String, Object> parameters){
		try {
		    // Obtener el archivo Jasper 
		    InputStream jasper = ControllerEjercicio4.class.getResourceAsStream("/jasper/Ejercicio4/formularioMedico.jasper");

		    try {
		        // Cargar el informe Jasper desde el archivo obtenido
		        JasperReport report = (JasperReport) JRLoader.loadObject(jasper);

		        // Llenar el informe con datos sin uso de base de datos
		        JasperPrint jprint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

		        // Crear un visor de informes Jasper y mostrarlo en pantalla
		        JasperViewer viewer = new JasperViewer(jprint, false);
		        viewer.setVisible(true);
		    } catch (Exception e) {
		        // Manejar cualquier excepción 
		        Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setHeaderText(null);
		        alert.setTitle("ERROR");
		        alert.setContentText("Ha ocurrido un error");
		        alert.showAndWait();
		        e.printStackTrace();
		    }
		} catch (Exception e) {
		    // Manejar cualquier excepción
		    e.printStackTrace();
		}

	}
    
	@FXML
	private void generarDatos() {
		// Validar los campos y almacenar los mensajes de error en la variable "errores"
		String errores = validarCampos();

		// Verificar si no hay errores de validación
		if (errores.isEmpty()) {
		    // Crear un mapa de parámetros para pasar al método generarInforme
		    Map<String, Object> parametros = new HashMap<>(7);
		    
		    // Agregar valores al mapa de parámetros utilizando los datos de los campos de la interfaz
		    parametros.put("NOM_MEDICO", tfNombreMedico.getText());
		    parametros.put("TRATAMIENTO", taTratamiento.getText());
		    parametros.put("COD_MEDICO", Integer.parseInt(tfCodigoMedico.getText()));
		    parametros.put("ESP_MEDICO", tfEspecialidadMedico.getText());
		    parametros.put("NUM_PACIENTE", Integer.parseInt(tfNumeroPaciente.getText()));
		    parametros.put("NOM_PACIENTE", tfNombrePaciente.getText());
		    parametros.put("DIR_PACIENTE", tfDireccionPaciente.getText());

		    // Llamar al método estático generarInforme del controlador ControllerEjercicio4
		    ControllerEjercicio4.generarInforme(parametros);
		} else {
		    // Si hay errores, mostrar una alerta de error con los mensajes obtenidos
		    alertaError(errores);
		}

    }
	
	private String validarCampos() {
    	String errores = "";
    	
    	// Validamos si los campos estan vacios
    	if(tfNombreMedico.getText().isEmpty()) {
    		errores+= "Tienes que rellenar el campo Nombre del paciente\n";
    	}
    	if(taTratamiento.getText().isEmpty()) {
    		errores+= "Tienes que rellenar el campo Tratamiento\n";
    	}
    	if(tfCodigoMedico.getText().isEmpty()) {
    		errores+= "Tienes que rellenar el campo Nombre\n";
    	}
    	if(tfEspecialidadMedico.getText().isEmpty()) {
    		errores+= "Tienes que rellenar el campo Especialidad del medico\n";
    	}
    	if(tfNumeroPaciente.getText().isEmpty()) {
    		errores+= "Tienes que rellenar el campo Numero de paciente\n";
    	}
    	if(tfNombrePaciente.getText().isEmpty()) {
    		errores+= "Tienes que rellenar el campo Nombre de paciente\n";
    	}
    	if(tfDireccionPaciente.getText().isEmpty()) {
    		errores+= "Tienes que rellenar el campo Direccion de paciente\n";
    	}
    	// validamos si los campos son numericos
    	try {
    		Integer.parseInt(tfNumeroPaciente.getText());
		}catch(NumberFormatException e) {
			errores+= "El numero de paciente tiene que ser numerica\n";
		}
    	try {
    		Integer.parseInt(tfCodigoMedico.getText());
		}catch(NumberFormatException e) {
			errores+= "El codigo del medico tiene que ser numerico\n";
		}
    	
    	return errores;
    }
	
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

	@FXML
    void vaciarCampos(ActionEvent event) {
		// vaciar todos los campos
    	taTratamiento.clear();
        tfCodigoMedico.clear();
        tfDireccionPaciente.clear();
        tfEspecialidadMedico.clear();
        tfNombreMedico.clear();
        tfNombrePaciente.clear();
        tfNumeroPaciente.clear();
    }

    @FXML
    void salir(ActionEvent event) {
    	((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }
    
   
    

}