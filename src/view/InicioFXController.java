/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Luis
 */
public class InicioFXController {
    
    public final static int MIN_CARACTERES = 3;
    public final static int MAX_CARACTERES = 255;
    
    @FXML
    private TextField tfNombreUsuario;
    @FXML
    private TextField tfContra;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnIniciarSesion;
    
    private Stage stage;
    
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
        //Add Listener
        tfNombreUsuario.textProperty().addListener(this::tfNombreUsuarioChange);
        btnRegistrar.setOnAction(this::btnRegistrarOnClick);
        btnIniciarSesion.setOnAction(this::btnLoginOnClick);
        stage.show();
    }
    /**
     * Añade las propiedades a los controladores de la escena.
     * @param w 
     */
    private void handleWindowShowing(WindowEvent w){
        //El campo Contraseña y el botón Iniciar sesión estarán deshabilitado.
        tfContra.setDisable(true);
        tfContra.setPromptText("Introduzca la contraseña");
        btnIniciarSesion.setDisable(true);
        //El foco estará en el campo Nombre de usuario.
        tfNombreUsuario.requestFocus();
        tfNombreUsuario.setPromptText("Introduzca el nombre de usuario");
        //Tooltips
        btnRegistrar.setTooltip(
                new Tooltip("Pulse para abrir formulario de registro."));
        btnIniciarSesion.setTooltip(new Tooltip("Pulse para iniciar sesión."));
        //La ventana no puede cambiar de tamaño.
        stage.setResizable(false);
    }
    /**
     * Comprueba que los controladores label esten bien informados.
     * @param observable
     * @param oldValue
     * @param newValue 
     */
    private void tfNombreUsuarioChange(ObservableValue observable,
             String oldValue,
             String newValue){
        if(tfNombreUsuario.toString().trim().length()>= MIN_CARACTERES){
            tfContra.setDisable(false);
        }
        if(tfContra.toString().trim().length()>=MIN_CARACTERES){
            btnIniciarSesion.setDisable(false);
        }
        if(tfNombreUsuario.toString().trim().length()>= MAX_CARACTERES || 
                tfContra.toString().trim().length()>= MAX_CARACTERES){
            
        }
    }
    /**
     * Acción activada por el botón Registrar. Muestra la ventana Registrar.
     * @param event 
     */
    private void btnRegistrarOnClick(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("Registrarse.fxml"));
        
        Parent root = (Parent)loader.load();
        
        RegistrarseFXController controller = 
                ((RegistrarseFXController)loader.getController());
        
        controller.initStage(root);
    }
    /**
     * 
     * @param event 
     */
    private void btnLoginOnClick(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("principal.fxml"));
        
        Parent root = (Parent)loader.load();
        
        PrincipalFXController controller = 
                ((PrincipalFXController)loader.getController());
            
        controller.initStage(root);
    }
}
