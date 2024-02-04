package model;

/**
 * Clase que representa la ayuda de una sección, con un texto descriptivo,
 * un archivo HTML asociado y la indicación de si es local o no.
 */
public class Help {
    private String text;
    private String html;
    private boolean local = true;

    /**
     * Constructor que recibe el texto descriptivo y el archivo HTML asociado.
     *
     * @param text Texto descriptivo de la sección de ayuda.
     * @param html Nombre del archivo HTML asociado.
     */
    public Help(String text, String html) {
        this.text = text;
        this.html = html;
    }

    /**
     * Constructor que recibe el texto descriptivo, el archivo HTML asociado y
     * la indicación de si es local o no.
     *
     * @param text  Texto descriptivo de la sección de ayuda.
     * @param html  Nombre del archivo HTML asociado.
     * @param local Indicación de si el archivo HTML es local o no.
     */
    public Help(String text, String html, boolean local) {
        this.text = text;
        this.html = html;
        this.local = local;
    }

    /**
     * Método para obtener el texto descriptivo de la sección de ayuda.
     *
     * @return Texto descriptivo.
     */
    public String getText() {
        return text;
    }

    /**
     * Método para obtener el nombre del archivo HTML asociado.
     *
     * @return Nombre del archivo HTML.
     */
    public String getHtml() {
        return html;
    }

    /**
     * Método para verificar si el archivo HTML es local.
     *
     * @return true si el archivo es local, false si es externo.
     */
    public boolean isLocal() {
        return local;
    }

    /**
     * Método para obtener una representación en cadena de la sección de ayuda.
     *
     * @return Texto descriptivo.
     */
    @Override
    public String toString() {
        return text;
    }
}

