// EventRegistration.java
package application;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class RegistroEventos extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Crea un objeto Circle con centro en (100, 100) y radio de 50
        Circle circle = new Circle(100, 100, 50);
        circle.setFill(Color.CORAL);  // Establece el color de relleno del círculo como CORAL

        // Crea un controlador de eventos MouseEvent
        EventHandler<MouseEvent> mouseEventHandler = e ->
            System.out.println("El controlador de eventos de mouse ha sido llamado.");

        // Registra el controlador de eventos MouseEvent al círculo para eventos de clic del mouse
        circle.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandler);

        // Crea un contenedor HBox
        HBox root = new HBox();
        root.getChildren().add(circle);  // Agrega el círculo como hijo del contenedor

        // Crea una escena y la asigna al escenario (stage)
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Establece el título del escenario
        stage.setTitle("Registrando Controladores de Eventos");

        // Muestra el escenario
        stage.show();
        
        // Ajusta el tamaño del escenario para que se ajuste al contenido de la escena
        stage.sizeToScene();
    }

}
