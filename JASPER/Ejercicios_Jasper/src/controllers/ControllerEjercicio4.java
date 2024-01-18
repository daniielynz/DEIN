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
    private Button btnLimpiar;

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

   
	public static void generarI(Map<String, Object> parameters){
		 try {
		    	InputStream jasper = ControllerEjercicio4.class.getResourceAsStream("/jasper/formularioMedico.jasper");
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
	void generarInforme(ActionEvent event) {
    		try {
				ControllerEjercicio4.generarI(crearParametros());
				//TODO
			} catch (Exception e) {
	    		Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
	    		alert.show();
	    		e.printStackTrace();
			}
    	
    }
    private Map<String, Object> crearParametros() {
    	Map<String, Object> parametros = new HashMap<String, Object>(7);
    	
    	parametros.put("NOM_MEDICO", tfNombreMedico.getText().trim());
    	parametros.put("TRATAMIENTO", taTratamiento.getText().trim());
    	parametros.put("COD_MEDICO", Integer.parseInt(tfCodigoMedico.getText())); 
    	parametros.put("ESP_MEDICO", tfEspecialidadMedico.getText().trim());
    	parametros.put("NUM_PACIENTE", Integer.parseInt(tfNumeroPaciente.getText()));
    	parametros.put("NOM_PACIENTE", tfNombrePaciente.getText().trim());
    	parametros.put("DIR_PACIENTE", tfDireccionPaciente.getText().trim());
    	
    	
    	return parametros;
    }

    

	@FXML
    void limpiar(ActionEvent event) {
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