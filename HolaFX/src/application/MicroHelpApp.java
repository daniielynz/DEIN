// MicroHelpApp.java
package application;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MicroHelpApp extends Application {
    private Text textoAyuda = new Text();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        TextField fName = new TextField();
        TextField lName = new TextField();
        TextField salario = new TextField();
        Button btnCerrar = new Button("Cerrar");
        btnCerrar.setOnAction(e -> Platform.exit());
        fName.getProperties().put("microAyuda", "Nombre");
        lName.getProperties().put("microAyuda", "Apellidos");
        salario.getProperties().put("microAyuda", "Introduce un salario superior a 2000€");
        // El texto de ayuda no esta administrado
        textoAyuda.setManaged(false);
        textoAyuda.setTextOrigin(VPos.TOP);
        textoAyuda.setFill(Color.RED);
        textoAyuda.setFont(Font.font(null, 9));
        textoAyuda.setMouseTransparent(true);
        // Añadir todos los nodos al panel central
        GridPane root = new GridPane();
        root.add(new Label("Nombre:"), 1, 1);
        root.add(fName, 2, 1);
        root.add(new Label("Apellidos:"), 1, 2);
        root.add(lName, 2, 2);
        root.add(new Label("Salario:"), 1, 3);
        root.add(salario, 2, 3);
        root.add(btnCerrar, 3, 3);
        root.add(textoAyuda, 4, 3);
        Scene escena = new Scene(root, 300, 100);
        // Agrega un detector de cambios a la escena, para que sepamos cuándo cambia el foco y muestra la micro ayuda
        escena.focusOwnerProperty().addListener((ObservableValue<? extends Node> value, Node oldNode, Node newNode) -> focusChanged(value, oldNode, newNode));
        stage.setScene(escena);
        stage.setTitle("Mostrar Ayuda");
        stage.show();
    }

    public void focusChanged(ObservableValue<? extends Node> value, Node oldNode, Node newNode) {
        // El foco ha cambiado al siguiente nodo
        String microTextoAyuda = (String) newNode.getProperties().get("microTextoAyuda");
        if (microTextoAyuda != null && microTextoAyuda.trim().length() > 0) {
            textoAyuda.setText(microTextoAyuda);
            textoAyuda.setVisible(true);
            // Position the help text node
            double x = newNode.getLayoutX() + newNode.getLayoutBounds().getMinX() - textoAyuda.getLayoutBounds().getMinX();
            double y = newNode.getLayoutY() + newNode.getLayoutBounds().getMinY() + newNode.getLayoutBounds().getHeight() - textoAyuda.getLayoutBounds().getMinX();
            textoAyuda.setLayoutX(x);
            textoAyuda.setLayoutY(y);
            textoAyuda.setWrappingWidth(newNode.getLayoutBounds().getWidth());
        } else {
            textoAyuda.setVisible(false);
        }
    }
}