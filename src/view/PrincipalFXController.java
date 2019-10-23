/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package view;

import clases.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * Es el controlador de la ventana principal.fxml
 * 
 * @author Ricardo Peinado Lastra
 */
public class PrincipalFXController {
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger("view.PrincipalFXController");
    private User user;
    
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuArchivo;
    @FXML
    private MenuItem menuArchivoGuardar;
    @FXML
    private MenuItem menuArchivoSubir;
    @FXML
    private MenuItem menuArchivoSalir;
    @FXML
    private Menu menuEditar;
    @FXML
    private MenuItem menuEditarBorrar;
    @FXML
    private Menu menuCuenta;
    @FXML
    private MenuItem menuCuentaDatos;
    @FXML
    private MenuItem menuCuentaCerrarSesion;
    @FXML
    private Menu menuHelp;
    @FXML
    private MenuItem menuHelpLanguages;
    
    private Stage stage;
    /**
     * Cierra sesión del usuario en cuestion.
     * @param event La acción a recivir.
     */
    @FXML
    private void btnLogOut(ActionEvent event){
    /**
     * Establece en escenario.
     */    
    }
    public void setStage  (Stage stage){
        this.stage=stage;
    }
    /**
     * Inicializa en escenario para la vista.
     * @param root La vista principal.fxml ya cargada.
     */
    @FXML
    public void initStage(Parent root) {
        
        
    }
}
