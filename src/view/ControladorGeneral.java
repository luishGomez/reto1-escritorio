/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Luis
 */
public class ControladorGeneral {
    
    public final static int MIN_CARACTERES = 3;
    public final static int MAX_CARACTERES = 255;
    
    public static void showErrorAlert(String mensaje){
        Alert alert=new Alert(Alert.AlertType.ERROR,
                              mensaje,
                              ButtonType.OK);
        alert.showAndWait();
    }
}
