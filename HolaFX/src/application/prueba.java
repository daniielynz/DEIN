// KnowingHostDetailsApp.java
package application;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class prueba extends Application {
    public static void main(String[] args) {
        // Método principal de la aplicación que inicia la aplicación JavaFX
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Inicio del método start, que es el punto de entrada principal de la aplicación

        // Guardamos en una variable tipo String la URL de Yahoo
        String yahooURL = "http://www.yahoo.com";

        // Creamos un botón para ir a la URL de Yahoo
        Button openURLButton = new Button("Ir a Yahoo!");

        // Le asignamos una acción al botón para abrir la URL de Yahoo cuando se haga clic
        openURLButton.setOnAction(e -> getHostServices().showDocument(yahooURL));

        // Creamos otro botón para mostrar una alerta
        Button showAlert = new Button("Mostrar Alerta");

        // Le asignamos una acción al botón para mostrar una alerta cuando se haga clic
        showAlert.setOnAction(e -> showAlert());

        // Creamos un contenedor VBox para organizar los elementos en una disposición vertical
        VBox root = new VBox();

        // Agregamos los botones y todos los detalles relacionados con el host al VBox
        root.getChildren().addAll(openURLButton, showAlert);

        // Obtenemos los detalles del host y los almacenamos en un mapa
        Map<String, String> hostdetails = getHostDetails();

        // Iteramos a través de los detalles del host y los agregamos como etiquetas al VBox
        for (Map.Entry<String, String> entry : hostdetails.entrySet()) {
            String desc = entry.getKey() + ": " + entry.getValue();
            root.getChildren().add(new Label(desc));
        }

        // Creamos una escena que contiene el VBox
        Scene scene = new Scene(root);

        // Establecemos la escena en el escenario (ventana principal)
        stage.setScene(scene);

        // Establecemos el título de la ventana
        stage.setTitle("Conociendo el Host");

        // Mostramos la ventana
        stage.show();
    }

    // Método protegido para obtener detalles del host
    protected Map<String, String> getHostDetails() {
        Map<String, String> map = new HashMap<>();

        // Obtenemos una referencia a los servicios del host
        HostServices host = this.getHostServices();

        // Obtenemos el CodeBase del host y lo agregamos al mapa
        String codeBase = host.getCodeBase();
        map.put("CodeBase", codeBase);

        // Obtenemos el DocumentBase del host y lo agregamos al mapa
        String documentBase = host.getDocumentBase();
        map.put("DocumentBase", documentBase);

        // Resolvemos la URI de la imagen de splash y la agregamos al mapa
        String splashImageURI = host.resolveURI(documentBase, "splash.jpg");
        map.put("Splash Image URI", splashImageURI);

        return map;
    }

    // Método protegido para mostrar una alerta
    protected void showAlert() {
        // Creamos una nueva ventana (Stage) para la alerta
        Stage s = new Stage(StageStyle.UTILITY);

        // Configuramos la modalidad de la ventana como APPLICATION_MODAL
        s.initModality(Modality.APPLICATION_MODAL);

        // Creamos una etiqueta con el mensaje de la alerta
        Label msgLabel = new Label("¡Esto es una alerta de JavaFX!");

        // Creamos un grupo (Group) que contiene la etiqueta
        Group root = new Group(msgLabel);

        // Creamos una escena con el grupo
        Scene scene = new Scene(root);

        // Establecemos la escena en la ventana de alerta
        s.setScene(scene);

        // Establecemos el título de la ventana de alerta
        s.setTitle("Alerta de JavaFX");

        // Mostramos la ventana de alerta
        s.show();
    }
}

