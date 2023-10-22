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
    		// URL del coche 
        	System.out.println("La URL del coche de fondo (FUNCIONA)\n"+this.imgCoche.getImage().getUrl());
        	
        	imgLuces = new ImageView();
        	// Activar la imagen de las luces encendidas
        	String imagePath = getClass().getResource("/images/autoLuz.png").toString();
        	Image image = new Image(imagePath);
    		this.imgLuces = new ImageView(image);
    		System.out.println("La URL de la imagen de las luces es\n"+this.imgLuces.getImage().getUrl());
    		
    		// Cambiar la imagen del boton de apagar y encender las luces
        	String imagePath2 = getClass().getResource("/images/lucesOff.png").toString();
        	Image image2 = new Image(imagePath2);
    		this.imgEncenderApagar = new ImageView(image2);
    		System.out.println("La URL de la imagen del boton encender apagar es\n"+this.imgEncenderApagar.getImage().getUrl());
    		
    		this.lucesApagadas=false;
    	}else {
    		// URL del coche 
    		this.imgLuces = new ImageView();
    		
    		// Cambiar la imagen del boton de apagar y encender las luces
        	String imagePath2 = getClass().getResource("/images/lucesOn.png").toString();
        	Image image2 = new Image(imagePath2);
    		this.imgEncenderApagar = new ImageView(image2);
    		System.out.println("La URL de la imagen del boton encender apagar es\n"+this.imgEncenderApagar.getImage().getUrl());
    		
    		this.lucesApagadas=true;
    	}
    	
    }

    @FXML
    void accionCambiarColor(MouseEvent event) {
    	System.out.println(event.getSource());
    	ImageView imgV = (ImageView) event.getSource();
    	String imagePath = getClass().getResource("/images/colores/"+imgV.getId()+".jpg").toString();
    	Image image = new Image(imagePath);
		this.imgCoche = new ImageView(image);
		System.out.println("Imagen del coche\n"+this.imgCoche.getImage().getUrl());
    }

}

