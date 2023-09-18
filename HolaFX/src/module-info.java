/**
 * 
 */
/**
 * 
 */
module HolaFX {
	requires javafx.controls;
	requires javafx.web;
	requires javafx.fxml;
	requires javafx.swing;
	requires javafx.media;
	requires javafx.graphics;
	requires javafx.base;
	opens application to javafx.graphics, javafx.base;
}