package controllers;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ControllerEjercicio2 {

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private ToggleGroup radioGroup;

    @FXML
    private RadioButton rbCalculos;

    @FXML
    private RadioButton rbPersonas;

    @FXML
    private RadioButton rbSubinformes;

    @FXML
    void accionAceptar(ActionEvent event) {
    	if (rbCalculos.isSelected()) {
            
    		try {
		        JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/reports/informe.jasper"));
		        JasperPrint jprint = JasperFillManager.fillReport(report, parameters, con.getConexion());
		        JasperViewer viewer = new JasperViewer(jprint, false);
		        viewer.setVisible(true);
		    } catch (Exception e) {
		        Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setHeaderText(null);
		        alert.initOwner(this.btnAceptar.getScene().getWindow());
		        alert.setTitle("ERROR");
		        alert.setContentText("Ha ocurrido un error");
		        alert.showAndWait();
		        e.printStackTrace();
		    }
    		
        } else if (rbPersonas.isSelected()) {
        	System.out.println("1");
        } else if (rbSubinformes.isSelected()) {
        	System.out.println("3");
        }
    }

    @FXML
    void accionCancelar(ActionEvent event) {
    	rbPersonas.setSelected(false);
    	rbCalculos.setSelected(false);
    	rbSubinformes.setSelected(false);
    }

}
