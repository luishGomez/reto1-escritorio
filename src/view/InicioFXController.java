package view;

import businessLogic.*;
import clases.*;
import exceptions.*;
import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * El controlador de la ventana InicioFx para iniciar sesión.
 * The Iniciofx window controller to log in.
 * @author Luis
 */
public class InicioFXController extends ControladorGeneral{
    
    @FXML
    private TextField tfNombreUsuario;
    @FXML
    private PasswordField tfContra;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnAcceder;
    @FXML
    private Button btnSalir;
    @FXML
    private Label lblNombreUsuario;
    @FXML
    private Label lblContra;
    
    private Stage stage;
    public LogicCliente logic = new LogicFactory().getLogicCliente();
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    /**
     * Función que muestra nuestra escena en el stage.
     * Function that shows our scene in the stage.
     * @param root El nodo raíz de la vista. / The root node of view.
     */
    public void initStage(Parent root){
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnShowing(this::handleWindowShowing);
        stage.setTitle("App");
        //Add Listener
        tfNombreUsuario.textProperty().addListener(this::textChanged);
        tfContra.textProperty().addListener(this::textChanged);
        tfNombreUsuario.setOnKeyPressed(this::keyPressLogin);
        tfContra.setOnKeyPressed(this::keyPressLogin);
        btnRegistrar.setOnAction(this::btnRegistrarOnClick);
        btnRegistrar.setOnKeyPressed(this::keyPressRegistrar);
        btnAcceder.setOnAction(this::btnLoginOnClick);
        btnAcceder.setOnKeyPressed(this::keyPressLogin);
        btnSalir.setOnAction(this::btnSalirOnClick);
        btnSalir.setOnKeyPressed(this::keyPressSalir);
        stage.show();
    }
    /**
     * Añade las propiedades a los controladores de la escena.
     * Adds properties to the scene drivers.
     * @param w El propio evento. / The current event.
     */
    private void handleWindowShowing(WindowEvent w){
        //El botón Iniciar sesión estarán deshabilitado.
        btnAcceder.setDisable(true);
        //El foco estará en el campo Nombre de usuario.
        tfNombreUsuario.requestFocus();
        //PromtText
        tfContra.setPromptText("Introduzca la contraseña");
        tfNombreUsuario.setPromptText("Introduzca el nombre de usuario");
        //Tooltips
        btnRegistrar.setTooltip(
                new Tooltip("Pulse para abrir formulario de registro."));
        btnAcceder.setTooltip(new Tooltip("Pulse para iniciar sesión."));
        //La ventana no puede cambiar de tamaño.
        stage.setResizable(false);
    }
    /**
     * Comprueba que los controladores label estén bien informados.
     * Check that label controllers are well informed.
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void textChanged(ObservableValue observable,
            String oldValue,
            String newValue){
        if(tfNombreUsuario.getText().trim().length() >= MIN_CARACTERES &&
                tfContra.getText().trim().length() >= MIN_CARACTERES){
            btnAcceder.setDisable(false);
        }else{
            btnAcceder.setDisable(true);
        }
        if(tfNombreUsuario.getText().trim().length() > MAX_CARACTERES){
            tfNombreUsuario.setText(tfNombreUsuario.getText().trim()
                    .substring(0, MAX_CARACTERES));
            showErrorAlert("Has superado el máximo tamaño de nombre de usuario"
                    + ", "+MAX_CARACTERES+".");
        }
        if(tfContra.getText().trim().length() > MAX_CARACTERES){
            tfContra.setText(tfContra.getText().trim()
                    .substring(0, MAX_CARACTERES));
            showErrorAlert("Has superado el máximo tamaño de contraseña, "+MAX_CARACTERES+".");
        }
    }
    /**
     * Atajo para iniciar sesión.
     * Shortcut to log in.
     * @param key 
     */
    private void keyPressLogin(KeyEvent key){
        if(key.getCode().equals(KeyCode.ENTER)) {
            btnAcceder.fire();
        }else if(key.getCode().equals(KeyCode.ESCAPE)){
            btnSalir.fire();
        }
    }
    /**
     * Atajo para registrase.
     * Shortcut to sign up.
     * @param key 
     */
    private void keyPressRegistrar(KeyEvent key){
        if(key.getCode().equals(KeyCode.ENTER)) {
            btnRegistrar.fire();
        }else if(key.getCode().equals(KeyCode.ESCAPE)){
            btnSalir.fire();
        }
    }
    /**
     * Atajo para salir de la aplicación.
     * Shortcut to exit from the application.
     * @param key 
     */
    private void keyPressSalir(KeyEvent key){
        if(key.getCode().equals(KeyCode.ENTER)) {
            btnSalir.fire();
        }else if(key.getCode().equals(KeyCode.ESCAPE)){
            btnSalir.fire();
        }
    }
    /**
     * Acción activada por el botón Registrar. Muestra la ventana Registrar.
     * Action enabled by the Register button. Displays the Register window.
     * @param event El propio evento. / The current event.
     */
    private void btnRegistrarOnClick(ActionEvent event) {
        
        try {
        FXMLLoader loader = new FXMLLoader(getClass()
        .getResource("Registrarse.fxml"));
        
        Parent root = (Parent) loader.load();
        
        RegistrarseFXMLController controller
        = ((RegistrarseFXMLController) loader.getController());
        
        controller.initStage(root);
        } catch (IOException e) {
        showErrorAlert("Error al cargar la ventana de Registrar.");
        }
    }
    /**
     * Comprobación de Login y Password y inicio de sesión.
     * Login and Password check and login.
     * @param event El propio evento. / The current event.
     */
    private void btnLoginOnClick(ActionEvent event){
        String nombre = tfNombreUsuario.getText().toString();
        String contra = tfContra.getText().toString();
        try{
            User user = logic.login(nombre, contra);
            if(user != null){
                lblNombreUsuario.setTextFill(Color.web("black"));
                lblContra.setTextFill(Color.web("black"));
                try{
                    FXMLLoader loader = new FXMLLoader(getClass()
                            .getResource("principal.fxml"));
                    
                    Parent root = (Parent)loader.load();
                    
                    PrincipalFXController controller =
                            ((PrincipalFXController)loader.getController());
                    controller.setUser(user);
                    controller.initStage(root);
                }catch(IOException e){
                    showErrorAlert("Error al cargar la ventana de Login.");
                }
            }else{
                showErrorAlert("Nombre de usuario o contraseña incorrecto.");
            }
        }catch(PasswordException e){
            showErrorAlert("Contraseña incorrecta.");
            lblContra.setTextFill(Color.web("red"));
            lblNombreUsuario.setTextFill(Color.web("black"));
        }catch(LoginIDException e){
            showErrorAlert("Nombre de usuario incorrecto.");
            lblContra.setTextFill(Color.web("red"));
            lblNombreUsuario.setTextFill(Color.web("red"));
        }catch(DAOException e){
            showErrorAlert("Ha ocurrido un error en el servidor, intentelo otra vez o vuelva mas tarde.");
        }catch(ServerException e){
            showErrorAlert("Problemas con el servidor.");
        }catch(LogicException e){
            showErrorAlert("Problemas con el servidor, intentelo en un rato.");
        }catch(EsperaCompletaException e){
            showErrorAlert("El servidor no se encuentra disponible en estos momentos.");
        }
    }
    /**
     * Botón para salir de la aplicación.
     * Button to exit the application.
     * @param event El propio evento. / The current event.
     */
    public void btnSalirOnClick(ActionEvent event){
        String mensaje = "¿Estás seguro de que desea cerrar la aplicación?";
        Alert alertCerrarAplicacion = new Alert(AlertType.CONFIRMATION,mensaje,ButtonType.NO,ButtonType.YES);
            //Añadimos titulo a la ventana como el alert.
            alertCerrarAplicacion.setTitle("Cerrar la aplicación");
            alertCerrarAplicacion.setHeaderText("¿Quieres salir de la aplicación?");
            //Si acepta cerrara la aplicación.
            alertCerrarAplicacion.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    Platform.exit();
                }
            });
    }
}