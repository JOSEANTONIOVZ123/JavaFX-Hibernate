package com.example.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal que inicia la aplicación de gestión de películas.
 */
public class App extends Application {

    /**
     * Método principal para iniciar la aplicación JavaFX.
     * @param primaryStage El escenario principal de la aplicación.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // Cargar el archivo FXML para la interfaz principal
            Parent root = FXMLLoader.load(getClass().getResource("/application.fxml"));

            // Configurar la escena con el archivo FXML cargado
            Scene scene = new Scene(root);

            // Configurar el título y mostrar la ventana principal
            primaryStage.setTitle("Gestión de Películas y Copias");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.err.println("Error al cargar la interfaz: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Método main para lanzar la aplicación.
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }
}
