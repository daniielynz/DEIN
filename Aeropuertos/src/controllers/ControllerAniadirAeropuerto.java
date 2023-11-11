package controllers;

import dao.AeropuertoDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.AeropuertoPrivado;
import model.AeropuertoPublico;

public class ControllerAniadirAeropuerto {

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

    @FXML
    void initialize() {
        // Inicializa el tipo de aeropuerto como una cadena vacía
        this.tipoAeropuerto = "";
    }

    @FXML
    void accionCancelar(ActionEvent event) {
        // Obtiene la ventana actual y la cierra al hacer clic en el botón Cancelar
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void accionGuardar(ActionEvent event) {
        // Valida los campos del formulario y obtiene mensajes de error si los hay
        String errores = validar();

        // Si no hay errores, procesa la información ingresada
        if (errores.isEmpty()) {
            if (tipoAeropuerto.equals("privado")) {
                // Se ha seleccionado el RadioButton "privado"
                
                // Obtén los valores ingresados en los campos de texto y conviértelos al tipo correspondiente
                int numero = Integer.parseInt(tfNumero.getText().toString());
                int NSocios = Integer.parseInt(tfNSocios.getText().toString());
                String nombre = tfNombre.getText().toString();
                String pais = tfPais.getText().toString();
                String ciudad = tfCiudad.getText().toString();
                String calle = tfCalle.getText().toString();
                int anio = Integer.parseInt(tfAnioInauguracion.getText().toString());
                int capacidad = Integer.parseInt(tfCapacidad.getText().toString());

                // Crea un objeto AeropuertoPrivado con los valores obtenidos
                AeropuertoPrivado a = new AeropuertoPrivado(numero, NSocios, anio, capacidad, nombre, pais, ciudad, calle);

                // Guarda el aeropuerto privado utilizando el objeto AeropuertoDao
                AeropuertoDao aeropuertoDao = new AeropuertoDao();
                aeropuertoDao.aniadirAeropuertoPrivado(a);

                // Limpia los campos del formulario
                vaciarCampos();
            } else if (tipoAeropuerto.equals("publico")) {
                // Se ha seleccionado el RadioButton "publico"
                
                // Obtén los valores ingresados en los campos de texto y conviértelos al tipo correspondiente
                int numero = Integer.parseInt(tfNumero.getText().toString());
                Float financiacion = Float.parseFloat(tfFinanciacion.getText().toString());
                int numTrabajadores = Integer.parseInt(tfNTrabajadores.getText().toString());
                String nombre = tfNombre.getText().toString();
                String pais = tfPais.getText().toString();
                String ciudad = tfCiudad.getText().toString();
                String calle = tfCalle.getText().toString();
                int anio = Integer.parseInt(tfAnioInauguracion.getText().toString());
                int capacidad = Integer.parseInt(tfCapacidad.getText().toString());

                // Crea un objeto AeropuertoPublico con los valores obtenidos
                AeropuertoPublico a = new AeropuertoPublico(numero, financiacion, numTrabajadores, anio, capacidad, nombre, pais, ciudad, calle);

                // Guarda el aeropuerto público utilizando el objeto AeropuertoDao
                AeropuertoDao aeropuertoDao = new AeropuertoDao();
                aeropuertoDao.aniadirAeropuertoPublico(a);

                // Limpia los campos del formulario
                vaciarCampos();
            }

            // Muestra una alerta informativa de que se ha añadido correctamente el aeropuerto
            alertaInformacion("Se ha añadido correctamente el Aeropuerto");
        } else {
            // Si hay errores, muestra una alerta de error con los mensajes de error
            alertaError(errores);
        }
    }

    
    // metodo para vaciar todos los campos
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
    	if(tipoAeropuerto.isEmpty()) {
    		errores += "Tienes que elegir algún RadioButton\n";
    	}else {
    		if(tipoAeropuerto.equals("privado")) {
    			// Se ha seleccionado el RadioButton "privado"
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
    		}else if(tipoAeropuerto.equals("publico")) {
    			// Se ha seleccionado el RadioButton "publico"
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
    	}
    	return errores;
    }

    @FXML
    void accionPrivado(ActionEvent event) {
        // Oculta el contenedor de aeropuertos públicos
        boxPublicos.setVisible(false);
        
        // Muestra el contenedor de aeropuertos privados
        boxPrivados.setVisible(true);
        
        // Establece el tipo de aeropuerto como "privado"
        this.tipoAeropuerto = "privado";
    }

    @FXML
    void accionPublico(ActionEvent event) {
        // Muestra el contenedor de aeropuertos públicos
        boxPublicos.setVisible(true);
        
        // Oculta el contenedor de aeropuertos privados
        boxPrivados.setVisible(false);
        
        // Establece el tipo de aeropuerto como "publico"
        this.tipoAeropuerto = "publico";
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
