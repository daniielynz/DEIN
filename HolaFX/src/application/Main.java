package application;

import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage escenario) throws Exception {
		// cambiando el titulo
		escenario.setTitle("HolaFX aplicacion, escenario vacio");
		VBox raiz= new VBox();
		
		
		// mostrando escena
		escenario.show();
	}
}
