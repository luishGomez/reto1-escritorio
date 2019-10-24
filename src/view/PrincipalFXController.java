/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package view;

import clases.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Es el controlador de la ventana principal.fxml.
 * The controller of the principal.fxml window.
 * @author Ricardo Peinado Lastra
 */
public class PrincipalFXController {
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger("view.PrincipalFXController");
    private User user;
    private Stage stage;
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
    @FXML
    private Label lblBienvenida;
    @FXML
    private Label lblEmail;
    
    
    /**
     * Inicializa en escenario para la vista.
     * Initialize the stage of the view.
     * @param root La vista principal.fxml ya cargada.
     */
    @FXML
    public void initStage(Parent root) {
        try{
            LOGGER.info("Iniciando la ventana LogOut");
            Scene scene=new Scene(root);
            stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Principal");
            stage.setResizable(true);
            stage.setMaximized(true);
            //Vamos a rellenar los datos en la ventana.
            stage.setOnShowing(this::handleWindowShowing);
            stage.show();
        }catch(Exception e){
            LOGGER.severe(e.getMessage());
        }
        
    }
    /**
     * Rellena los datos al enseñar la vista.
     * Load the data in the view.
     * @param event El evento en cuestión./The current view.
     */
    private void handleWindowShowing(WindowEvent event){
        try{
            LOGGER.info("handlWindowShowing --> LogOut");
            lblBienvenida.setText("Bienvenido "+user.getFullname());
            lblEmail.setText(user.getEmail());
        }catch(Exception e){
            LOGGER.severe(e.getMessage());
        }
    }
    /**
     * Cierra la aplicación.
     * Close the application.
     */
    @FXML
    private void onActionSalir(){
        try{
            //Creamos la alerta con el tipo confirmación, con su texto y botones de
            //aceptar y cancelar.
            Alert alertCerrarAplicacion = new Alert(AlertType.CONFIRMATION,"Si sale de la aplicación cerrara\nautomáticamente la sesión.",ButtonType.NO,ButtonType.YES);
            //Añadimos titulo a la ventana como el alert.
            alertCerrarAplicacion.setTitle("Cerrar la aplicación");
            alertCerrarAplicacion.setHeaderText("¿Quieres salir de la aplicación?");
            //Si acepta cerrara la aplicación.
            alertCerrarAplicacion.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    Platform.exit();
                }
            });
        }catch(Exception e){
            LOGGER.severe(e.getMessage());
        }
    }
    /**
     * Cierra sesión.
     * Log out.
     */
    @FXML
    private void onActionCerrarSesion(){
        try{
            //Creamos la alerta del tipo confirmación.
            Alert alertCerrarSesion = new Alert(AlertType.CONFIRMATION);
            //Ponemos titulo de la ventana como titulo para la alerta.
            alertCerrarSesion.setTitle("Cerrar sesión");
            alertCerrarSesion.setHeaderText("¿Quieres cerrar sesión?");
            //Si acepta se cerrara esta ventana.
            alertCerrarSesion.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    stage.hide();
                }
            });
        }catch(Exception e){
            LOGGER.severe(e.getMessage());
        }
    }
    public void setUser(User user){
        this.user=user;
    }
}
