package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.Help;

/**
 * Controlador para la vista del visor offline.
 */
public class VisorOfflineController implements Initializable {
    
    @FXML
    private TreeView<Help> arbol;
    
    @FXML
    private WebView visor;
    
    private WebEngine webEngine;

    /**
     * Carga la ayuda en el visor, ya sea desde un recurso local o una ubicación externa.
     *
     * @param file   El nombre del archivo de ayuda.
     * @param local  True si la ayuda es local, False si es externa.
     */
    private void loadHelp(String file, boolean local) {
        // Verifica si el objeto "visor" no es nulo
        if (visor != null) {
            // Si la ayuda es local
            if (local) {
                // Carga la ayuda local desde un recurso en el proyecto
                webEngine.load(getClass().getResource("/help/" + file).toExternalForm());
            } else {
                // Si la ayuda no es local, se carga directamente desde la ubicación especificada por "file"
                webEngine.load(file);
            }
        }
    }

    /**
     * Inicializa el controlador después de que su elemento raíz haya sido completamente procesado.
     *
     * @param arg0 La ubicación utilizada para resolver rutas relativas para el objeto raíz o null si la ubicación no se conoce.
     * @param arg1 El recurso utilizado para localizar el objeto raíz o null si el objeto raíz no fue localizado.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Creamos el árbol del panel de la izquierda, el índice
        TreeItem<Help> rItem = new TreeItem<Help>(new Help("Raiz", ""));
        TreeItem<Help> r1Item = new TreeItem<Help>(new Help("Index", "index.html"));
        r1Item.getChildren().add(new TreeItem<Help>(new Help("Html 1", "html1.html")));
        r1Item.getChildren().add(new TreeItem<Help>(new Help("Html 2", "html2.html")));
        rItem.getChildren().add(r1Item);
        arbol.setRoot(rItem);
        arbol.setShowRoot(false);

        // Mostramos el contenido inicial en el visor de la derecha
        webEngine = visor.getEngine();
        webEngine.load(getClass().getResource("/help/index.html").toExternalForm());

        // Añadimos un evento para cambiar de html al pinchar en el árbol
        arbol.setOnMouseClicked(e -> {
            if (arbol.getSelectionModel().getSelectedItem() != null) {
                Help elemento = (Help) arbol.getSelectionModel().getSelectedItem().getValue();
                if (elemento.getHtml() != null) {
                    loadHelp(elemento.getHtml(), elemento.isLocal());
                }
            }
        });
    }
}
