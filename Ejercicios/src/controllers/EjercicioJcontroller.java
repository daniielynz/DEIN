package controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class EjercicioJcontroller {

    @FXML
    private ImageView azulClaro;

    @FXML
    private ImageView azulOscuro;

    @FXML
    private ImageView imgCoche;

    @FXML
    private ImageView imgEncenderApagar;

    @FXML
    private ImageView grisClaro;

    @FXML
    private ImageView grisOscuro;

    @FXML
    private ImageView imgLuces;

    @FXML
    private ImageView negro;

    @FXML
    private ImageView oro;

    @FXML
    private ImageView plata;

    @FXML
    private ImageView rojo;
    
    private boolean lucesApagadas;
    
    @FXML
    void initialize() {
    	lucesApagadas = true;
    }

    @FXML
    void accionApagarEncender(MouseEvent event) {
    	if(lucesApagadas==true) {
        	// Activar la imagen de las luces encendidas
        	String imagePath = getClass().getResource("/images/autoLuz.png").toString();
        	Image image = new Image(imagePath);
        	this.imgLuces.setImage(image);
    		
    		// Cambiar la imagen del boton de apagar y encender las luces
        	String imagePath2 = getClass().getResource("/images/lucesOff.png").toString();
        	Image image2 = new Image(imagePath2);
    		this.imgEncenderApagar.setImage(image2);
    		
    		this.lucesApagadas=false;
    	}else {
    		// URL del coche 
    		Image image = null;
        	this.imgLuces.setImage(image);
    		
    		// Cambiar la imagen del boton de apagar y encender las luces
        	String imagePath2 = getClass().getResource("/images/lucesOn.png").toString();
        	Image image2 = new Image(imagePath2);
    		this.imgEncenderApagar.setImage(image2);
    		
    		this.lucesApagadas=true;
    	}
    	
    }

    @FXML
    void accionCambiarColor(MouseEvent event) {
    	// Recuperamos el id del ImageView que hemos pulsado
    	ImageView imgV = (ImageView) event.getSource();
    	// creamos la ruta donde esta el coche que vamos a mostrar y creamos la nueva imagen
    	String imagePath = getClass().getResource("/images/coches/"+imgV.getId()+".png").toString();
    	Image image = new Image(imagePath);
		this.imgCoche.setImage(image);
    }

}

