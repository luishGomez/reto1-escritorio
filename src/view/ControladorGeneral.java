package view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Controlador de máximo y mínimos de caracteres de los campos a rellenar.
 * Controller of maximum and minimum characters of the fields to be filled.
 * @author Luis
 */
public class ControladorGeneral {
    
    public final static int MIN_CARACTERES = 3;
    public final static int MAX_CARACTERES = 15;
    
    public static void showErrorAlert(String mensaje){
        Alert alert=new Alert(Alert.AlertType.ERROR,
                              mensaje,
                              ButtonType.OK);
        alert.showAndWait();
    }
}
