package application;

import java.util.Locale;
import java.util.ResourceBundle;
import conexion.Propiedades;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;

/**
 * Clase principal de la aplicación que extiende la clase Application de JavaFX.
 */
public class Main extends Application {

    /**
     * Método principal que inicia la aplicación.
     *
     * @param primaryStage El escenario principal de la aplicación.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // Obtener configuración de idioma y región desde un archivo de propiedades
            String idioma = Propiedades.getValor("idioma");
            String region = Propiedades.getValor("region");

            // Establecer la configuración regional por defecto
            Locale.setDefault(new Locale(idioma, region));

            // Cargar los recursos de idioma
            ResourceBundle bundle = ResourceBundle.getBundle("idiomas/messages");

            // Cargar la interfaz de usuario desde un archivo FXML
            GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/Listado.fxml"), bundle);

            // Crear la escena y agregar hojas de estilo
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/estilos.css").toExternalForm());

            // Establecer el título de la ventana principal usando la configuración de idioma
            primaryStage.setTitle(bundle.getString("titulo"));

            // Establecer la escena en la ventana principal y mostrarla
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            // Manejar excepciones imprimiendo la traza de errores
            e.printStackTrace();
        }
    }

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
