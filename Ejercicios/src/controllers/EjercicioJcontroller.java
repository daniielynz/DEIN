package controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class EjercicioJcontroller {
	
	// Imagen donde van las luces cuando se encienden
	@FXML
    private ImageView imgLuces;
	
	// Imagen donde va el coche
	@FXML
    private ImageView imgCoche;
	
	// Imagen del boton de encender y apagar
	@FXML
    private ImageView imgEncenderApagar;
	
	// Atributos para los imageview de la seleccion de color para el coche
    @FXML
    private ImageView azulClaro;

    @FXML
    private ImageView azulOscuro;

    @FXML
    private ImageView grisClaro;

    @FXML
    private ImageView grisOscuro;

    @FXML
    private ImageView negro;

    @FXML
    private ImageView oro;

    @FXML
    private ImageView plata;

    @FXML
    private ImageView rojo;
    
    // atributo booleano que controla cuando las luces estan apagadas o encendidas
    private boolean lucesApagadas;
    
    // Este método se ejecuta al inicializar la interfaz de usuario
    @FXML
    void initialize() {
        // Inicializamos la variable "lucesApagadas" como verdadera
        lucesApagadas = true;
    }

    // Este método se ejecuta al hacer clic en el botón para apagar o encender las luces
    @FXML
    void accionApagarEncender(MouseEvent event) {
        // Verificamos el estado de las luces (apagadas o encendidas)
        if (lucesApagadas == true) {
            // Activamos la imagen de las luces encendidas
            String imagePath = getClass().getResource("/images/autoLuz.png").toString();
            Image image = new Image(imagePath);
            this.imgLuces.setImage(image);
            
            // Cambiamos la imagen del botón de apagar y encender las luces
            String imagePath2 = getClass().getResource("/images/lucesOff.png").toString();
            Image image2 = new Image(imagePath2);
            this.imgEncenderApagar.setImage(image2);
            
            // Actualizamos el estado de las luces como encendidas
            this.lucesApagadas = false;
        } else {
            // Desactivamos la imagen del coche y cambiamos la imagen del botón
            Image image = null;
            this.imgLuces.setImage(image);
            
            String imagePath2 = getClass().getResource("/images/lucesOn.png").toString();
            Image image2 = new Image(imagePath2);
            this.imgEncenderApagar.setImage(image2);
            
            // Actualizamos el estado de las luces como apagadas
            this.lucesApagadas = true;
        }
    }

    // Este método se ejecuta al hacer clic en las imágenes de los coches
    @FXML
    void accionCambiarColor(MouseEvent event) {
        // Recuperamos el ID del ImageView que se ha pulsado
        ImageView imgV = (ImageView) event.getSource();
        // Creamos la ruta de la imagen del coche seleccionado y la mostramos
        String imagePath = getClass().getResource("/images/coches/" + imgV.getId() + ".png").toString();
        Image image = new Image(imagePath);
        this.imgCoche.setImage(image);
    }


}

