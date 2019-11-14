package view;

import clases.User;
import java.net.URL;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
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
    private Stage stageAyuda;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuOpciones;
    @FXML
    private Menu menuAyuda;
    @FXML
    private MenuItem menuAyudaContenido;
    @FXML
    private MenuItem menuOpcionesSalir;
    @FXML
    private MenuItem menuOpcionesCerrarSesion;
    @FXML
    private Label lblBienvenida;
    @FXML
    private Label lblEmail;
    
    
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
            KeyCodeCombination keyAyuda=new KeyCodeCombination(KeyCode.F1);
            /* Modificacion DIN 13/11/2019 */
            Runnable mostrarAyuda= () -> ayuda();
            scene.getAccelerators().put(keyAyuda, mostrarAyuda);
            /*-FIN-*/
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
            menuOpcionesSalir.setMnemonicParsing(true);
            menuOpcionesSalir.setText("_Salir");
            menuOpcionesCerrarSesion.setMnemonicParsing(true);
            menuOpcionesCerrarSesion.setText("_Cerrar sesión");
            /*Modificación DIN 13/1/2019*/
            menuAyuda.setMnemonicParsing(true);
            menuAyuda.setText("_Ayuda");
            menuAyudaContenido.setMnemonicParsing(true);
            menuAyudaContenido.setText("Co_ntenido de ayuda");
            /*Fin de la modificación de DIN 13/11/2019*/
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
                    /*Modificaciones DIN 13/11/2019*/
                    LOGGER.info("Cerrando la aplicación.");
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
                    LOGGER.info("Cerrando sesión.");
                    stage.hide();
                }
            });
        }catch(Exception e){
            LOGGER.severe(e.getMessage());
        }
    }
    /**
     * Inserta el usuario de la sesión iniciada.
     * Inserts the user of the login.
     * @param user Los datos del usuario. / User data.
     */
    public void setUser(User user){
        this.user=user;
    }
    /*Modificación DIN 13/1/2019*/
    /**
     * Crea y muestra la ventana de ayuda gracias a cargar un html.
     * Create and shows the help window that it was load from one html.
     */
    @FXML
    public void ayuda(){
        LOGGER.info("algo");
        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
        
        URL url = this.getClass().getResource("./ayudaPrincipal.html");
        webEngine.load(url.toString());
        
        stageAyuda=new Stage();
        stageAyuda.setTitle(webEngine.getTitle());
        
        Button ayudaCerrar=new Button("Cerrar");        
        ayudaCerrar.setOnAction(this::cerrarAyuda);
        ayudaCerrar.setMnemonicParsing(true);
        ayudaCerrar.setText("_Cerrar");
        /* Al pulsar enter encima del boton se ejecute */
        ayudaCerrar.setOnKeyPressed((key) ->{
            if(key.getCode().equals(KeyCode.ENTER)) {
                 ayudaCerrar.fire();
            }
        });
        stageAyuda.setOnShowing((event)->{
            ayudaCerrar.requestFocus();
            
        });
        VBox root = new VBox();
        root.getChildren().addAll(browser,ayudaCerrar);
        
        Scene escenaAyuda=new Scene(root);
        stageAyuda.setScene(escenaAyuda);
        stageAyuda.initModality(Modality.APPLICATION_MODAL);
        
        stageAyuda.show();
    }
    /**
     * Cierra la ventana de ayuda.
     * Close the help window.
     * @param event El propio evento.
     */
    public void cerrarAyuda(ActionEvent event){
        stageAyuda.hide();
    }
    /*Fin de la modificación de DIN 13/11/2019*/
}
