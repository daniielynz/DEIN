package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;


public class EjercicioK extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/EjercicioKfxml.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/estilosI.css").toExternalForm());
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/recursos_ejercicios1_k/Icono.ico")));
			primaryStage.setTitle("RELOJ");
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


