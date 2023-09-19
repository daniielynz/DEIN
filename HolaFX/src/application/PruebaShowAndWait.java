// ShowAndWaitApp.java
package application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PruebaShowAndWait extends Application {
    protected static int counter = 0;
    protected Stage lastOpenStage;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Crea la caja
        VBox root = new VBox();
        // Crear el boton Open
        Button openButton = new Button("Open");
        // Le da la accion de abrir al boton y suma 1 al contador
        openButton.setOnAction(e -> open(++counter));
        // Añade el boton a la caja
        root.getChildren().add(openButton);
        // Crea la escena, la añade al escenario, le da un titulo y la muestra
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.setTitle("The Primary Stage");
        stage.show();
        // Establecemos este escenario como el ultimmo que se ha abierto
        this.lastOpenStage = stage;
    }

    private void open(int stageNumber) {
        // Crea un nuevo escenario y le pone como titulo el numero de escenario en el que estamos
        Stage stage = new Stage();
        stage.setTitle("#" + stageNumber);
        // Crear el boton de "Say hello"
        Button sayHelloButton = new Button("Say Hello");
        // Si pulsas el boton que acabamos de crear te sale el siguiente mensaje con el numero de escenario en el que estamos
        sayHelloButton.setOnAction(e -> System.out.println("Hello from #" + stageNumber));
        // Creamos el boton de "Open" y le damos como accion que cree otro escenario y le sumamos 1 a la cantidad de escenarios que llevamos creados
        Button openButton = new Button("Open");
        openButton.setOnAction(e -> open(++counter));
        // Crea una caja
        VBox root = new VBox();
        // Le añade a la caja los dos botones
        root.getChildren().addAll(sayHelloButton, openButton);
        // Crear una escena y la añade al escenario
        Scene scene = new Scene(root, 200, 200);
        stage.setScene(scene);
        // Se establecen las coordenadas de la siguiente ventana
        stage.setX(this.lastOpenStage.getX() + 50);
        stage.setY(this.lastOpenStage.getY() + 50);
        // Establecemos este escenario como el ultimmo que se ha abierto
        this.lastOpenStage = stage;
        // Indicamos el numero de escenario anterior
        System.out.println("Before stage.showAndWait(): " + stageNumber);
        // Hasta que el usuario no cierre la ventana, esta no se cerrará
        stage.showAndWait();
        // Indicamos el numero de escenario posterior
        System.out.println("After stage.showAndWait(): " + stageNumber);
    }
}