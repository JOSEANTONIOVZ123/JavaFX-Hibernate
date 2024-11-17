package com.example.Controller;

import com.example.model.Copia;
import com.example.CopiaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.ButtonType;
import java.util.Optional;

/**
 * Controlador para la gestión de copias en la aplicación.
 */
public class AppController {

    @FXML
    private TableView<Copia> tablaCopias;

    private final CopiaDAO copiaDAO = new CopiaDAO();
    private final ObservableList<Copia> listaCopias = FXCollections.observableArrayList();

    /**
     * Inicializa el controlador y carga los datos en la tabla.
     */
    @FXML
    public void initialize() {
        cargarDatos();
    }

    /**
     * Método para cargar todas las copias desde la base de datos.
     */
    private void cargarDatos() {
        listaCopias.setAll(copiaDAO.borrarCopia());
        tablaCopias.setItems(listaCopias);
    }

    /**
     * Método para añadir una nueva copia.
     */
    public void agregarCopia() {
        try {
            Copia nuevaCopia = new Copia(); // Aquí deberías obtener los datos de un formulario
            copiaDAO.borrarCopia();
            listaCopias.add(nuevaCopia);
            mostrarAlerta("Éxito", "Copia agregada exitosamente.", AlertType.INFORMATION);
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo agregar la copia: " + e.getMessage(), AlertType.ERROR);
        }
    }

    /**
     * Método para eliminar la copia seleccionada.
     */
    public void eliminarCopia() {
        Copia seleccionada = tablaCopias.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            // Confirmación antes de eliminar
            if (confirmarAccion("¿Seguro que deseas eliminar esta copia?")) {
                copiaDAO.modificarCopia(seleccionada.getId());
                listaCopias.remove(seleccionada);
                mostrarAlerta("Éxito", "Copia eliminada correctamente.", AlertType.INFORMATION);
            }
        } else {
            mostrarAlerta("Advertencia", "Selecciona una copia para eliminar.", AlertType.WARNING);
        }
    }

    /**
     * Método para actualizar la copia seleccionada.
     */
    public void actualizarCopia() {
        Copia seleccionada = tablaCopias.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            try {
                copiaDAO.guardarCopia(seleccionada);
                mostrarAlerta("Éxito", "Copia actualizada con éxito.", AlertType.INFORMATION);
            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo actualizar la copia: " + e.getMessage(), AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Advertencia", "Selecciona una copia para actualizar.", AlertType.WARNING);
        }
    }

    /**
     * Método para mostrar una alerta informativa.
     */
    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /**
     * Método para confirmar una acción con el usuario.
     * @param mensaje El mensaje de confirmación a mostrar.
     * @return true si el usuario confirma, false en caso contrario.
     */
    private boolean confirmarAccion(String mensaje) {
        Alert confirmacion = new Alert(AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmación");
        confirmacion.setContentText(mensaje);
        Optional<ButtonType> resultado = confirmacion.showAndWait();
        return resultado.isPresent() && resultado.get() == ButtonType.OK;
    }
}
