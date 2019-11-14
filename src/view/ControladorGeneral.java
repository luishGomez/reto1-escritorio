package view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Controlador de máximo y mínimos de caracteres de los campos a rellenar.
 * Controller of maximum and minimum characters of the fields to be filled.
 * @author Luis
 */
public class ControladorGeneral {
    /**
     * Caracteres mínimo en el campo de texto Nombre de usuario.
     * Nombre de usuario text field minimum character.
     */
    public final static int MIN_CARACTERES = 3;
    /**
     * Caracteres máximo en el campo de contraseña Contraseña.
     * Contraseña password field maximum character.
     */
    public final static int MAX_CARACTERES = 15;
    
    /**
     * Alert de error.
     * Error alert.
     * @param mensaje Mensaje de error a mostrar. Error message to show.
     */
    public static void showErrorAlert(String mensaje){
        Alert alert=new Alert(Alert.AlertType.ERROR,
                              mensaje,
                              ButtonType.OK);
        alert.showAndWait();
    }
}