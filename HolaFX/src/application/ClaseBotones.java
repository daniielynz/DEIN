package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ClaseBotones extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage escenario) throws Exception {
		// cambiando el titulo
		escenario.setTitle("Prueba clase botones");
		
		// Guarda un mensaje temporal
		Text mensaje = new Text("Saludo vacio");

		// los dos botones
		Button botonHola = new Button("Hola");
		Button botonAdios = new Button("Adios");

		// funcionalidad botones
		botonHola.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0){
				mensaje.setText(botonHola.getText());
			}
		});
		botonAdios.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0){
				mensaje.setText(botonAdios.getText());
			}
		});
		
		// Contenedor y añadir componentes
		VBox raiz= new VBox();
		raiz.getChildren().addAll(mensaje,botonHola, botonAdios);

		// Escena de trabajo
		Scene escena=new Scene(raiz, 400, 400);

		// titulo y mostrar escena
		escenario.setScene(escena);
		escenario.setTitle("Hola FX prueba de botones");
		escenario.show();
	}
}


