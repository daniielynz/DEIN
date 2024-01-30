package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Ejercicio4 extends Application {
	
	@Override
	public void start(Stage primaryStage) {
	    try {
	        // Cargar el archivo FXML
	        BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/fxml/ejercicio4.fxml"));

	        // Crear una escena con el nodo raíz
	        Scene scene = new Scene(root);

	        // Configurar el escenario principal (primaryStage)
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