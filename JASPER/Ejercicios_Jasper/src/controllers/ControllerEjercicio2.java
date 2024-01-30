package controllers;

import java.sql.SQLException;

import conexion.ConexionBD;
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
	
	// botones aceptar y cancelar
	@FXML
	private Button btnAceptar;

	@FXML
	private Button btnCancelar;

	@FXML
	private ToggleGroup radioGroup;  // Grupo de botones de alternancia entre los 3 ejercicios

	@FXML
	private RadioButton rbPersonas;   // Botón para el ejercicio 1
	
	@FXML
	private RadioButton rbCalculos;   // Botón  para el ejercicio 2

	@FXML
	private RadioButton rbSubinformes; // Botón  para el ejercicio 3


    @FXML
    void accionAceptar(ActionEvent event) throws SQLException {
    	ConexionBD con = new ConexionBD("urlAgenda");
    	if (rbPersonas.isSelected()) {
    		try {
    		    // Cargar el informe Jasper desde el archivo .jasper
    		    JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/jasper/Ejercicio2/agenda.jasper"));

    		    // Llenar el informe con datos desde la base de datos
    		    JasperPrint jprint = JasperFillManager.fillReport(report, null, con.getConexion());

    		    // Crear un visor de informes Jasper y mostrarlo en pantalla
    		    JasperViewer viewer = new JasperViewer(jprint, false);
    		    viewer.setVisible(true);
    		} catch (Exception e) {
    		    // Manejar cualquier excepción que pueda ocurrir durante el proceso
    		    // Mostrar un cuadro de diálogo de error y escribir la traza de la excepción en la consola
    		    Alert alert = new Alert(Alert.AlertType.ERROR);
    		    alert.setHeaderText(null);
    		    alert.setTitle("ERROR");
    		    alert.setContentText("Ha ocurrido un error");
    		    alert.showAndWait();
    		    e.printStackTrace();
    		}

        } else if (rbCalculos.isSelected()) {
        	try {
        		// Cargar el informe Jasper desde el archivo .jasper
    			JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/jasper/Ejercicio2/agendaCalculos.jasper"));
    			
    			// Llenar el informe con datos desde la base de datos
    	        JasperPrint jprint = JasperFillManager.fillReport(report, null, con.getConexion());
    	        
    	        // Crear un visor de informes Jasper y mostrarlo en pantalla
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
        } else if (rbSubinformes.isSelected()) {
        	try {
        		// Cargar el informe Jasper desde el archivo .jasper
    			JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/jasper/Ejercicio2/agendaSubinformes.jasper"));
    			
    			// Llenar el informe con datos desde la base de datos
    	        JasperPrint jprint = JasperFillManager.fillReport(report, null, con.getConexion());
    	        
    	        // Crear un visor de informes Jasper y mostrarlo en pantalla
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
        }
    }

    @FXML
    void accionCancelar(ActionEvent event) {
    	// quitar el select a todos los botones
    	rbPersonas.setSelected(false);
    	rbCalculos.setSelected(false);
    	rbSubinformes.setSelected(false);
    }

}
