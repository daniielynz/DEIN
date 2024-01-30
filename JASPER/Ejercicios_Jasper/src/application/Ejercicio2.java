package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;


public class Ejercicio2 extends Application {
	
	@Override
	public void start(Stage primaryStage) {
	    try {
	        // Cargar el archivo FXML
	        GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/ejercicio2.fxml"));

	        // Crear una escena con el nodo raíz
	        Scene scene = new Scene(root);

	        // Configurar el escenario principal (primaryStage)
	        primaryStage.setTitle("INFORMES");  // Establecer el título del escenario
	        primaryStage.setScene(scene);  // Establecer la escena en el escenario
	        primaryStage.show();  // Mostrar el escenario en pantalla
	    } catch(Exception e) {
	        // Manejar cualquier excepción
	        e.printStackTrace();
	    }
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}

