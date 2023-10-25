package controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class EjercicioKcontroller {

    @FXML
    private ImageView hora1;

    @FXML
    private ImageView hora2;

    @FXML
    private ImageView minuto1;

    @FXML
    private ImageView minuto2;

    @FXML
    private ImageView segundo1;

    @FXML
    private ImageView segundo2;
    
    @FXML
    void initialize() {
    	// Inicializa el temporizador para actualizar la hora cada segundo
    	Timer timer = new Timer();
    	
    	Map<String, String> arrayAsociativo = new HashMap<>();
    	arrayAsociativo.put("0", "ZERO.png");
    	arrayAsociativo.put("1", "ONE.png");
    	arrayAsociativo.put("2", "TWO.png");
    	arrayAsociativo.put("3", "THREE.png");
    	arrayAsociativo.put("4", "FOUR.png");
    	arrayAsociativo.put("5", "FIVE.png");
    	arrayAsociativo.put("6", "SIX.png");
    	arrayAsociativo.put("7", "SEVEN.png");
    	arrayAsociativo.put("8", "EIGHT.png");
    	arrayAsociativo.put("9", "NINE.png");
    	
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Coloca aquí el código que quieres que se ejecute periódicamente
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String formattedTime = now.format(formatter);
                
                String imagePath;
                Image image;
                // Imagen del primer numero
                imagePath = getClass().getResource("/images/recursos_ejercicios1_k/"+arrayAsociativo.get(formattedTime.charAt(0)+"")).toString();
            	image = new Image(imagePath);
        		hora1.setImage(image);
        		// Imagen del primer numero
                imagePath = getClass().getResource("/images/recursos_ejercicios1_k/"+arrayAsociativo.get(formattedTime.charAt(1)+"")).toString();
            	image = new Image(imagePath);
        		hora2.setImage(image);
        		// Imagen del primer numero
                imagePath = getClass().getResource("/images/recursos_ejercicios1_k/"+arrayAsociativo.get(formattedTime.charAt(3)+"")).toString();
            	image = new Image(imagePath);
        		minuto1.setImage(image);
        		// Imagen del primer numero
                imagePath = getClass().getResource("/images/recursos_ejercicios1_k/"+arrayAsociativo.get(formattedTime.charAt(4)+"")).toString();
            	image = new Image(imagePath);
            	minuto2.setImage(image);
        		// Imagen del primer numero
                imagePath = getClass().getResource("/images/recursos_ejercicios1_k/"+arrayAsociativo.get(formattedTime.charAt(6)+"")).toString();
            	image = new Image(imagePath);
        		segundo1.setImage(image);
        		// Imagen del primer numero
                imagePath = getClass().getResource("/images/recursos_ejercicios1_k/"+arrayAsociativo.get(formattedTime.charAt(7)+"")).toString();
            	image = new Image(imagePath);
            	segundo2.setImage(image);
                
            }
        };

        // Programa la tarea para que se ejecute cada segundo
        timer.schedule(task, 0, 1000);
    }
    
    

}

