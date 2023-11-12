package application;
	
import java.util.Locale;
import java.util.ResourceBundle;
import conexion.Propiedades;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Obtenemos el valor de atributo "Idioma" de la clase "Propiedades"
			String idioma = Propiedades.getValor("idioma");
			// Obtenemos el valor de atributo "region" de la clase "Propiedades"
			String region = Propiedades.getValor("region");
			Locale.setDefault(new Locale(idioma, region));
			ResourceBundle bundle = ResourceBundle.getBundle("idiomas/messages");			
			
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/Listado.fxml"), bundle);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/estilos.css").toExternalForm());
			primaryStage.setTitle(bundle.getString("titulo"));
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}


