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
		    	InputStream jasper = ControllerEjercicio4.class.getResourceAsStream("/jasper/Ejercicio4/formularioMedico.jasper");
				try {
					JasperReport report = (JasperReport) JRLoader.loadObject(jasper);
			        JasperPrint jprint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
			        JasperViewer viewer = new JasperViewer(jprint, false);
			        viewer.setVisible(true);
				} catch (Exception e) {
		            Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.setHeaderText(null);
		            alert.setTitle("ERROR");
		            alert.setContentText("Ha ocurrido un error");
		            alert.showAndWait();
		            e.printStackTrace();
		        }
		        
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}
    
	@FXML
	private void generarDatos() {
		String errores = validarCampos();
		
		if(errores.isEmpty()) {
			Map<String, Object> parametros = new HashMap<String, Object>(7);
	    	
	    	parametros.put("NOM_MEDICO", tfNombreMedico.getText());
	    	parametros.put("TRATAMIENTO", taTratamiento.getText());
	    	parametros.put("COD_MEDICO", Integer.parseInt(tfCodigoMedico.getText())); 
	    	parametros.put("ESP_MEDICO", tfEspecialidadMedico.getText());
	    	parametros.put("NUM_PACIENTE", Integer.parseInt(tfNumeroPaciente.getText()));
	    	parametros.put("NOM_PACIENTE", tfNombrePaciente.getText());
	    	parametros.put("DIR_PACIENTE", tfDireccionPaciente.getText());
	    	
	    	ControllerEjercicio4.generarInforme(parametros);
		}else {
			alertaError(errores);
		}
    	
    }
	
	private String validarCampos() {
    	String errores = "";
    	
    	// Obtenemos el dato del campo Nombre y validamos que no este vacio
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