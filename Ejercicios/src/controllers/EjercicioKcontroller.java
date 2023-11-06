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

	// Atributos de la hora
    @FXML
    private ImageView hora1;

    @FXML
    private ImageView hora2;

    // Atributos de los minutos
    @FXML
    private ImageView minuto1;

    @FXML
    private ImageView minuto2;

    // Atributos de los segundos
    @FXML
    private ImageView segundo1;

    @FXML
    private ImageView segundo2;
    
    // Este método se ejecuta al inicializar la interfaz de usuario
    @FXML
    void initialize() {
        // Inicializa un temporizador para actualizar la hora cada segundo
        Timer timer = new Timer();

        // Crea un mapa asociativo que relaciona dígitos (como cadena) con nombres de archivos de imagen
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

        // Define una tarea que se ejecutará cada segundo
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Coloca aquí el código que deseas que se ejecute periódicamente
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String formattedTime = now.format(formatter);

                // Actualiza las imágenes de los dígitos de la hora
                String imagePath;
                Image image;
                
                // Primer dígito de la hora
                imagePath = getClass().getResource("/images/recursos_ejercicios1_k/" + arrayAsociativo.get(formattedTime.charAt(0) + "")).toString();
                image = new Image(imagePath);
                hora1.setImage(image);
                
                // Segundo dígito de la hora
                imagePath = getClass().getResource("/images/recursos_ejercicios1_k/" + arrayAsociativo.get(formattedTime.charAt(1) + "")).toString();
                image = new Image(imagePath);
                hora2.setImage(image);
                
                // Primer dígito de los minutos
                imagePath = getClass().getResource("/images/recursos_ejercicios1_k/" + arrayAsociativo.get(formattedTime.charAt(3) + "")).toString();
                image = new Image(imagePath);
                minuto1.setImage(image);
                
                // Segundo dígito de los minutos
                imagePath = getClass().getResource("/images/recursos_ejercicios1_k/" + arrayAsociativo.get(formattedTime.charAt(4) + "")).toString();
                image = new Image(imagePath);
                minuto2.setImage(image);
                
                // Primer dígito de los segundos
                imagePath = getClass().getResource("/images/recursos_ejercicios1_k/" + arrayAsociativo.get(formattedTime.charAt(6) + "")).toString();
                image = new Image(imagePath);
                segundo1.setImage(image);
                
                // Segundo dígito de los segundos
                imagePath = getClass().getResource("/images/recursos_ejercicios1_k/" + arrayAsociativo.get(formattedTime.charAt(7) + "")).toString();
                image = new Image(imagePath);
                segundo2.setImage(image);
            }
        };

        // Programa la tarea para que se ejecute cada segundo (1000 milisegundos)
        timer.schedule(task, 0, 1000);
    }

}

