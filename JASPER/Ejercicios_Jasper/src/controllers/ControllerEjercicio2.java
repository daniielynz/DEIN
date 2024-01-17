package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import net.sf.jasperreports.engine.JREmptyDataSource;
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
    
    Connection connection = null;
    
    private void conexion() {
    	try {
    		String url = "jdbc:mysql://localhost:3306/agenda";
    		String user = "admin";
    		String password = "dm2";
    		
    		connection = DriverManager.getConnection(url, user, password);
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }

    @FXML
    void accionAceptar(ActionEvent event) {
    	//conexion();
    	conexion();
    	if (rbPersonas.isSelected()) {
    		try {
    			JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/agenda.jasper"));
    	        JasperPrint jprint = JasperFillManager.fillReport(report, null, connection);
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
        } else if (rbCalculos.isSelected()) {
        	System.out.println("2");
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
