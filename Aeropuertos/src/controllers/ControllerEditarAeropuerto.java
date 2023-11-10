package controllers;

import java.awt.Taskbar.State;

import dao.AeropuertoDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.AeropuertoPrivado;
import model.AeropuertoPublico;

public class ControllerEditarAeropuerto {

    @FXML
    private VBox boxPrivados;

    @FXML
    private VBox boxPublicos;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ToggleGroup rbGrupo;

    @FXML
    private RadioButton rbPrivado;

    @FXML
    private RadioButton rbPublico;

    @FXML
    private TextField tfAnioInauguracion;

    @FXML
    private TextField tfCalle;

    @FXML
    private TextField tfCapacidad;

    @FXML
    private TextField tfCiudad;

    @FXML
    private TextField tfFinanciacion;

    @FXML
    private TextField tfNSocios;

    @FXML
    private TextField tfNTrabajadores;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfNumero;

    @FXML
    private TextField tfPais;
    
    private String tipoAeropuerto;
    
    private AeropuertoPrivado aeropuertoPrivado;
    
    private AeropuertoPublico aeropuertoPublico;

    @FXML
    void initialize() {
    	rbPrivado.setDisable(true);
    	rbPublico.setDisable(true);
    	
    	ControllerListadoAeropuertos c = new ControllerListadoAeropuertos();
    	if(c.getAeropuertoPrivadoSeleccionado() != null) {
    		// si el aeropuerto seleccionado es privado, lo guardamos
    		this.aeropuertoPrivado = c.getAeropuertoPrivadoSeleccionado();
    		this.aeropuertoPublico = null;
    		// rellenamos los textfields
			tfNombre.setText(aeropuertoPrivado.getNombre().toString());
			tfPais.setText(aeropuertoPrivado.getPais().toString());
			tfCiudad.setText(aeropuertoPrivado.getCiudad().toString());
			tfCalle.setText(aeropuertoPrivado.getCalle().toString());
			tfNumero.setText(aeropuertoPrivado.getNumero()+"");
			tfAnioInauguracion.setText(aeropuertoPrivado.getAnioInauguracion()+"");
			tfCapacidad.setText(aeropuertoPrivado.getCapacidad()+"");
			rbPrivado.setSelected(true);
			tfNSocios.setText(aeropuertoPrivado.getNSocios()+"");
			//mostramos los campos de privado
			this.boxPrivados.setVisible(true);
			this.boxPublicos.setVisible(false);
			
    	}else if(c.getAeropuertoPublicoSeleccionado() != null) {
    		// si el aeropuerto seleccionado es publico, lo guardamos
    		this.aeropuertoPublico = c.getAeropuertoPublicoSeleccionado();
    		this.aeropuertoPrivado = null;
    		// rellenamos los textfields
			tfNombre.setText(aeropuertoPublico.getNombre().toString());
			tfPais.setText(aeropuertoPublico.getPais().toString());
			tfCiudad.setText(aeropuertoPublico.getCiudad().toString());
			tfCalle.setText(aeropuertoPublico.getCalle().toString());
			tfNumero.setText(aeropuertoPublico.getNumero()+"");
			tfAnioInauguracion.setText(aeropuertoPublico.getAnioInauguracion()+"");
			tfCapacidad.setText(aeropuertoPublico.getCapacidad()+"");
			rbPublico.setSelected(true);
			tfFinanciacion.setText(aeropuertoPublico.getFinanciacion()+"");
			tfNTrabajadores.setText(aeropuertoPublico.getNTrabajadores()+"");
			//mostramos los campos de privado
			this.boxPublicos.setVisible(true);
			this.boxPrivados.setVisible(false);
    	}
    }
    
    @FXML
    void accionCancelar(ActionEvent event) {
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
    	stage.close();
    }
    
    protected void editarAeropuerto() {
    	try {
    		Stage primaryStage = new Stage();
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/EditarAeropuerto.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("AVIONES-AEROPUERTOS");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void accionGuardar(ActionEvent event) {
    	String errores = validar();
    	if(errores.isEmpty()) {
    		if(aeropuertoPrivado != null) {
    			// Si el aeropuerto a editar es privado
    			int numero = Integer.parseInt(tfNumero.getText().toString());
        		int NSocios = Integer.parseInt(tfNSocios.getText().toString());
        		String nombre = tfNombre.getText().toString();
        		String pais = tfPais.getText().toString();
        		String ciudad = tfCiudad.getText().toString();
        		String calle = tfCalle.getText().toString();
        		int anio = Integer.parseInt(tfAnioInauguracion.getText().toString());
        		int capacidad = Integer.parseInt(tfCapacidad.getText().toString());
        		
        		AeropuertoPrivado a = new AeropuertoPrivado(this.aeropuertoPrivado.getId(), numero, NSocios, anio, capacidad, nombre, pais, ciudad, calle);
        		
        		AeropuertoDao aeropuertoDao = new AeropuertoDao();
                aeropuertoDao.editarAeropuertoPrivado(a);
                vaciarCampos();
    		}else if(aeropuertoPublico != null) {
    			System.out.println(aeropuertoPublico);
    			// Si el aeropuerto a editar es publico
    			int numero = Integer.parseInt(tfNumero.getText().toString());
    			Float financiacion = Float.parseFloat(tfFinanciacion.getText().toString());
        		int numTrabajadores = Integer.parseInt(tfNTrabajadores.getText().toString());
        		String nombre = tfNombre.getText().toString();
        		String pais = tfPais.getText().toString();
        		String ciudad = tfCiudad.getText().toString();
        		String calle = tfCalle.getText().toString();
        		int anio = Integer.parseInt(tfAnioInauguracion.getText().toString());
        		int capacidad = Integer.parseInt(tfCapacidad.getText().toString());
        		
        		AeropuertoPublico a = new AeropuertoPublico(this.aeropuertoPublico.getId(),numero, financiacion, numTrabajadores, anio, capacidad, nombre, pais, ciudad, calle);
        		
        		AeropuertoDao aeropuertoDao = new AeropuertoDao();
                aeropuertoDao.editarAeropuertoPublico(a);
                vaciarCampos();
    		}
            
    		alertaInformacion("Se ha modificado correctamente el Aeropuerto");
    	}else {
    		alertaError(errores);
    	}
    }
    
    private void vaciarCampos() {
    	tfNumero.setText("");
    	tfCalle.setText("");
    	tfAnioInauguracion.setText("");
    	tfCapacidad.setText("");
    	tfCiudad.setText("");
    	tfFinanciacion.setText("");
    	tfNSocios.setText("");
    	tfNombre.setText("");
    	tfNTrabajadores.setText("");
    	tfPais.setText("");
    	rbPrivado.setSelected(false);
    	rbPublico.setSelected(false);
    }
    
    private String validar() {
    	String errores = "";
    	
    	if(tfNombre.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo Nombre\n";
        }
    	if(tfPais.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo Pais\n";
        }
    	if(tfCiudad.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo Ciudad\n";
        }
    	if(tfCalle.getText().isEmpty()) {
            errores += "Tienes que rellenar el campo Calle\n";
        }
    	if(tfNumero.getText().isEmpty()) {
    		errores += "Tienes que rellenar el campo Numero\n";
        }else {
        	try {
    			// Intenta convertir el String a un número
                Integer.parseInt(tfNumero.getText());
            } catch (NumberFormatException e) {
                // Si hay una excepción, no es numérico
                errores += "El campo Numero tiene que ser numerico\n";
            }
        }
    	if(tfAnioInauguracion.getText().isEmpty()) {
    		errores += "Tienes que rellenar el campo Año Inauguracion\n";
        }else {
        	try {
    			// Intenta convertir el String a un número
                Integer.parseInt(tfAnioInauguracion.getText());
            } catch (NumberFormatException e) {
                // Si hay una excepción, no es numérico
                errores += "El campo Año Inauguracion tiene que ser numerico\n";
            }
        }
    	if(tfCapacidad.getText().isEmpty()) {
    		errores += "Tienes que rellenar el campo Capacidad\n";
        }else {
        	try {
    			// Intenta convertir el String a un número
                Integer.parseInt(tfCapacidad.getText());
            } catch (NumberFormatException e) {
                // Si hay una excepción, no es numérico
                errores += "El campo Capacidad tiene que ser numerico\n";
            }
        }
    	// validamos si se ha seleccionado algun radioButton
		if(rbPrivado.isSelected()) {
			// validamos el campo de Nº Socios
	    	if(tfNSocios.getText().isEmpty()) {
	    		errores += "Tienes que rellenar el campo Numero socios\n";
	        }else {
	        	try {
	    			// Intenta convertir el String a un número
	                Integer.parseInt(tfNSocios.getText());
	            } catch (NumberFormatException e) {
	                // Si hay una excepción, no es numérico
	                errores += "El campo de Numero de socios tiene que ser numerico\n";
	            }
	        }
		}else if(rbPublico.isSelected()) {
			// validamos los campos de financiacion y de numero de trabajadores
	    	if(tfFinanciacion.getText().isEmpty()) {
	    		errores += "Tienes que rellenar el campo Financiacion\n";
	        }else {
	        	if (!tfFinanciacion.getText().matches("^-?[0-9]+([\\.,][0-9]+)?$")) {
	        		errores += "El campo Financiacion tiene que ser decimal\n";
	        	}
	        }
	    	if(tfNTrabajadores.getText().isEmpty()) {
	    		errores += "Tienes que rellenar el campo Numero de trabajadores\n";
	        }else {
	        	try {
	    			// Intenta convertir el String a un número
	                Integer.parseInt(tfNTrabajadores.getText());
	            } catch (NumberFormatException e) {
	                // Si hay una excepción, no es numérico
	                errores += "El campo Numero de trabajadores tiene que ser numerico\n";
	            }
    		}
    	}
    	return errores;
    }
    
 // Este método muestra una ventana emergente de tipo "Error" con un mensaje y un botón "Aceptar
    private void alertaError(String mensaje) {
        // Creamos una nueva ventana emergente de tipo "Error"
        Alert ventanaEmergente = new Alert(AlertType.ERROR);
        // Establecemos el título de la ventana emergente
        ventanaEmergente.setTitle("Información");
        // Establecemos el contenido de la ventana emergente como el mensaje proporcionado
        ventanaEmergente.setContentText(mensaje);
        // Creamos un botón "Aceptar" para cerrar la ventana emergente
        Button ocultarBtn = new Button("Aceptar");
        // Configuramos el evento del botón para ocultar la ventana emergente al hacer clic en "Aceptar"
        ocultarBtn.setOnAction(e -> {
            ventanaEmergente.hide();
        });
        // Mostramos la ventana emergente
        ventanaEmergente.show();
    }

    // Este método muestra una ventana emergente de tipo "Información" con un mensaje y un botón "Aceptar"
    private void alertaInformacion(String mensaje) {
        // Creamos una nueva ventana emergente de tipo "Información"
        Alert ventanaEmergente = new Alert(AlertType.INFORMATION);
        // Establecemos el título de la ventana emergente
        ventanaEmergente.setTitle("Información");
        // Establecemos el contenido de la ventana emergente como el mensaje proporcionado
        ventanaEmergente.setContentText(mensaje);
        // Creamos un botón "Aceptar" para cerrar la ventana emergente
        Button ocultarBtn = new Button("Aceptar");
        // Configuramos el evento del botón para ocultar la ventana emergente al hacer clic en "Aceptar"
        ocultarBtn.setOnAction(e -> {
            ventanaEmergente.hide();
        });
        // Mostramos la ventana emergente
        ventanaEmergente.show();
    }


}
