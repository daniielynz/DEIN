package application;

import conexion.ConexionBD;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Ejercicio1 extends Application {
	
    public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
	    try {
	        // Establecer conexión a la base de datos usando la clase ConexionBD
	        ConexionBD con = new ConexionBD("urlPaises");

	        // Cargar el informe Jasper desde el archivo .jasper
	        JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/jasper/Ejercicio1/informe.jasper"));

	        // Llenar el informe con datos desde la base de datos utilizando la conexión
	        JasperPrint jprint = JasperFillManager.fillReport(report, null, con.getConexion());

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
	}

}
