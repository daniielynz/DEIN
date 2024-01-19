package application;

import java.sql.Connection;
import java.sql.DriverManager;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Ejercicio1 extends Application {
	Connection connection = null;
    
    public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		try {
			conexion();
			JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/jasper/Ejercicio1/informe.jasper"));
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
	}
	
	private void conexion() {
    	try {
    		String url = "jdbc:mysql://localhost:3306/paises";
    		String user = "admin";
    		String password = "dm2";
    		
    		connection = DriverManager.getConnection(url, user, password);
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }
}
