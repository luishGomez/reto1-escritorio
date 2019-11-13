package view;

import businessLogic.LogicCliente;
import businessLogic.LogicFactory;
import clases.User;
import exceptions.DAOException;
import exceptions.EsperaCompletaException;
import exceptions.LogicException;
import exceptions.LoginIDException;
import exceptions.PasswordException;
import exceptions.ServerException;
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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static view.ControladorGeneral.showErrorAlert;

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
    private Menu menuOpciones;
    @FXML
    private MenuItem menuOpcionesSalir;
    @FXML
    private MenuItem menuOpcionesCerrarSesion;
    @FXML
    private Label lblBienvenida;
    @FXML
    private Label lblEmail;
    public LogicCliente logic = new LogicFactory().getLogicCliente();
    
    
    /**
     * Función que muestra nuestra escena en el stage.
     * Function that shows our scene in the stage.
     * @param root El nodo raíz de la vista. / The root node of view.
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
            menuOpciones.setMnemonicParsing(true);
            menuOpciones.setText("_Opciones");
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
                    try{
                        logic.finAcceso(user.getLogin());
                    }catch(DAOException e){
                        showErrorAlert("Ha ocurrido un error en el servidor, intentelo otra vez o vuelva mas tarde.");
                    }catch(ServerException e){
                        showErrorAlert("Problemas con el servidor.");
                    }catch(LogicException e){
                        showErrorAlert("Problemas con el servidor, intentelo en un rato.");
                    }catch(EsperaCompletaException e){
                        showErrorAlert("El servidor no se encuentra disponible en estos momentos.");
                    }
                    
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
                     try{
                        logic.finAcceso(user.getLogin());
                    }catch(DAOException e){
                        showErrorAlert("Ha ocurrido un error en el servidor, intentelo otra vez o vuelva mas tarde.");
                    }catch(ServerException e){
                        showErrorAlert("Problemas con el servidor.");
                    }catch(LogicException e){
                        showErrorAlert("Problemas con el servidor, intentelo en un rato.");
                    }catch(EsperaCompletaException e){
                        showErrorAlert("El servidor no se encuentra disponible en estos momentos.");
                    }
                    
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
