package application;
import javafx.application.Application;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST;

public class WindowEventApp extends Application {
    private CheckBox puedeCerrarCbx;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage escenario) {
        puedeCerrarCbx = new CheckBox("Puede Cerrar Ventana");
        Button cerrarBtn = new Button("Cerrar");
        cerrarBtn.setOnAction(e -> escenario.close());
        Button ocultarBtn = new Button("Ocultar");
        ocultarBtn.setOnAction(e -> {
            mostrarDialogo(escenario);
            escenario.hide();
        });
        HBox contenedorRaiz = new HBox();
        contenedorRaiz.setPadding(new Insets(20));
        contenedorRaiz.setSpacing(20);
        contenedorRaiz.getChildren().addAll(puedeCerrarCbx, cerrarBtn, ocultarBtn);
        // Agregar manejadores de eventos de ventana al escenario
        escenario.setOnShowing(e -> manejarEvento(e));
        escenario.setOnShown(e -> manejarEvento(e));
        escenario.setOnHiding(e -> manejarEvento(e));
        escenario.setOnHidden(e -> manejarEvento(e));
        escenario.setOnCloseRequest(e -> manejarEvento(e));
        Scene escena = new Scene(contenedorRaiz);
        escenario.setScene(escena);
        escenario.setTitle("Eventos de Ventana");
        escenario.show();
    }

    public void manejarEvento(WindowEvent e) {
        // Consumir el evento si el CheckBox no está seleccionado
        // para evitar que el usuario cierre la ventana
        EventType<WindowEvent> tipo = e.getEventType();
        if (tipo == WINDOW_CLOSE_REQUEST && !puedeCerrarCbx.isSelected()) {
            e.consume();
        }
        System.out.println(tipo + ": Consumido=" + e.isConsumed());
    }

    public void mostrarDialogo(Stage ventanaPrincipal) {
        Stage ventanaEmergente = new Stage();
        Button mostrarBtn = new Button("Haga clic para Mostrar Ventana Principal");
        mostrarBtn.setOnAction(e -> {
            ventanaEmergente.close();
            ventanaPrincipal.show();
        });
        HBox contenedorRaiz = new HBox();
        contenedorRaiz.setPadding(new Insets(20));
        contenedorRaiz.setSpacing(20);
        contenedorRaiz.getChildren().addAll(mostrarBtn);
        Scene escena = new Scene(contenedorRaiz);
        ventanaEmergente.setScene(escena);
        ventanaEmergente.setTitle("Ventana Emergente");
        ventanaEmergente.show();
    }
}

/*package application;
import javafx.application.Application;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST;

public class WindowEventApp extends Application {
    private CheckBox canCloseCbx;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        canCloseCbx = new CheckBox("Can Close Window");
        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> stage.close());
        Button hideBtn = new Button("Hide");
        hideBtn.setOnAction(e -> {
            showDialog(stage);
            stage.hide();
        });
        HBox root = new HBox();
        root.setPadding(new Insets(20));
        root.setSpacing(20);
        root.getChildren().addAll(canCloseCbx, closeBtn, hideBtn);
        // Add window event handlers to the stage
        stage.setOnShowing(e -> handle(e));
        stage.setOnShown(e -> handle(e));
        stage.setOnHiding(e -> handle(e));
        stage.setOnHidden(e -> handle(e));
        stage.setOnCloseRequest(e -> handle(e));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Window Events");
        stage.show();
    }

    public void handle(WindowEvent e) {
        // Consume the event if the CheckBox is not selected
        // thus preventing the user from closing the window
        EventType<WindowEvent> type = e.getEventType();
        if (type == WINDOW_CLOSE_REQUEST && !canCloseCbx.isSelected()) {
            e.consume();
        }
        System.out.println(type + ": Consumed=" + e.isConsumed());
    }

    public void showDialog(Stage mainWindow) {
        Stage popup = new Stage();
        Button closeBtn = new Button("Click to Show Main Window");
        closeBtn.setOnAction(e -> {
            popup.close();
            mainWindow.show();
        });
        HBox root = new HBox();
        root.setPadding(new Insets(20));
        root.setSpacing(20);
        root.getChildren().addAll(closeBtn);
        Scene scene = new Scene(root);
        popup.setScene(scene);
        popup.setTitle("Popup");
        popup.show();
    }
} */
