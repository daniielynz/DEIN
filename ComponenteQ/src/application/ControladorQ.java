package application;

import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.application.Platform;

public class ControladorQ extends GridPane{
	@FXML
	private Label minuto1;

	@FXML
	private Label minuto2;

	@FXML
	private Label segundo1;

	@FXML
	private Label segundo2;
	
	private int tiempo;

	private BooleanProperty fin;
	
	private int totalSegundos;
	
	public ControladorQ() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/EjercicioQfxml.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		inicio();
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@FXML
	void inicio() {
		// Crear una propiedad booleana para rastrear si la cuenta atrás ha finalizado
	    this.fin = new SimpleBooleanProperty(false);  
	    // Inicializa el total de minutos
	    this.tiempo = 90;
	    // validamos que el tiempo sea un numero entre 1 y 99
	    if(this.tiempo>0 && this.tiempo<100) {
	    	// Inicializa el total de segundos
		    this.totalSegundos = this.tiempo * 60;
		    // Crea un temporizador
		    Timer timer = new Timer();  

		    TimerTask task = new TimerTask() {
		        @Override
		        public void run() {
		        	// Verifica si el tiempo restante es mayor o igual a cero
		            if (totalSegundos >= 0) {  
		            	// Calcula los minutos restantes.
		                int minutos = totalSegundos / 60;  
		                // Calcula los segundos restantes
		                int segundos = totalSegundos % 60;

		                Platform.runLater(() -> {
		                    // Convierte los minutos y segundos en una cadena con formato "mmss"
		                    String horaString = String.format("%02d%02d", minutos, segundos);

		                    // Actualiza las etiquetas de minutos y segundos en la interfaz de usuario.
		                    minuto1.setText(horaString.substring(0, 1));
		                    minuto2.setText(horaString.substring(1, 2));
		                    segundo1.setText(horaString.substring(2, 3));
		                    segundo2.setText(horaString.substring(3, 4));
		                });

		                totalSegundos--;  // Reduce el tiempo restante en un segundo
		            } else {
		            	// cambiamos la propiedad fin a true
		                fin.set(true);  
		                // Detiene el temporizador.
		                timer.cancel();  
		            }
		        }
		    };
		    // Programa la tarea para que se ejecute cada segundo
		    timer.schedule(task, 0, 1000);  
	    }else {
	    	System.out.println("El numero tiene que estar entre 1 y 99");
	    }
	    
	}


}