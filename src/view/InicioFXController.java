/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
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
    
    private Stage stage;
    public LogicCliente logic = new LogicFactory().getLogicCliente();
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    /**
     * Función que muestra nuestra escena en el stage
     * @param root
     */
    public void initStage(Parent root){
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnShowing(this::handleWindowShowing);
        stage.setTitle("App");
        //Add Listener
        tfNombreUsuario.textProperty().addListener(this::textChanged);
        tfContra.textProperty().addListener(this::textChanged);
        tfNombreUsuario.setOnKeyPressed(this::keyPress);
        tfContra.setOnKeyPressed(this::keyPress);
        btnRegistrar.setOnAction(this::btnRegistrarOnClick);
        btnAcceder.setOnAction(this::btnLoginOnClick);
        btnSalir.setOnAction(this::btnSalirOnClick);
        stage.show();
    }
    /**
     * Añade las propiedades a los controladores de la escena.
     * @param w
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
     * Comprueba que los controladores label esten bien informados.
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
    
    private void keyPress(KeyEvent key){
        if(key.getCode().equals(KeyCode.ENTER)) {
            btnAcceder.fire();
        }
    }
    /**
     * Acción activada por el botón Registrar. Muestra la ventana Registrar.
     * @param event
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
     *
     * @param event
     */
    private void btnLoginOnClick(ActionEvent event){
        String nombre = tfNombreUsuario.getText().toString();
        String contra = tfContra.getText().toString();
        try{
            User user = logic.login(nombre, contra);
            if(user != null){
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
        }catch(LoginIDException e){
            showErrorAlert("Nombre de usuario incorrecto.");
        }catch(DAOException e){
            showErrorAlert("Problemas con la base de datos, intentelo en un rato.");
        }catch(ServerException e){
            showErrorAlert("Problemas con el servidor.");
        }catch(LogicException e){
            showErrorAlert("Problemas con el servidor, intentelo en un rato.");
        }catch(EsperaCompletaException e){
            showErrorAlert("El servidor no se encuentra disponible en estos momentos.");
        }
    }
    
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