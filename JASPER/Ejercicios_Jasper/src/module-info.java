module Ejercicios_Jasper {
    requires javafx.controls;
    requires java.desktop;
    requires javafx.web;
    requires javafx.fxml;
    requires javafx.swing;
    requires javafx.media;
    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;
<<<<<<< HEAD
=======
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
>>>>>>> parent of 10537ea (DELETE JASPER)
    requires jasperreports; // Agregar la dependencia de JasperReports

    opens application to javafx.graphics, javafx.fxml;
    opens controllers to javafx.graphics, javafx.fxml;
}
