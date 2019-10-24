/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;



import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author sergio
 */
public class RegistrarseFXMLController{
    private static final Logger LOGGER = Logger.getLogger("reto1_escritorio.view");
    private Boolean email=false;
    Tooltip tooltip = new Tooltip();
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtNombreUsuario;
    @FXML
    private PasswordField pswContraseña;
    @FXML
    private PasswordField pswConfirmarContraseña;
    @FXML
    private Label lblMostrarContraseña;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Label lblNombreDeLogo;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblNombreUsuario;
    @FXML
    private Label lblContraseña;
    @FXML
    private Label lblConfirmarContraseña;
    
   @FXML
    private void btnVolver(ActionEvent event){
        //ventana login iniciar stage
    }
    @FXML
     private void btnRegistrar(ActionEvent event){
        //ventana logout iniciar stage 
        
     }
              
    
    /*
     Iniciamos el stage con la scena y su parent respectivo de Registrarse.
     */
    public void initStage(Parent root){
       //Crear scena y stage
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        //Enviar scena al stage.
        stage.setScene(scene);
        //Maximizable deshabilitado.
        stage.setResizable(false);
        stage.setTitle("Registro");
        //El foco estará en Nombre.
        txtNombre.requestFocus();
        //añadimos controladores.
        stage.setOnShowing(this::HandleWindowShowing);
        txtNombre.textProperty().addListener(this::HandleTextChanged);
        txtNombreUsuario.textProperty().addListener(this::HandleTextChanged);
        txtEmail.textProperty().addListener(this::HandleTextChanged);
        txtEmail.focusedProperty().addListener(this::focusChanged);
        pswContraseña.textProperty().addListener(this::HandleTextChanged);
        pswConfirmarContraseña.textProperty().addListener(this::HandleTextChanged);
        //tooltips de ayuda para los botones
        btnRegistrar.setTooltip(new Tooltip("Registro en la base de datos"));
        btnCancelar.setTooltip(new Tooltip("Regresar al login"));
        //textos de ayuda promptext
        txtNombre.setPromptText("ej. Aitor Sanchez");
        txtNombreUsuario.setPromptText("ej. Aitor_89");
        pswContraseña.setPromptText("Contraseña");
        pswConfirmarContraseña.setPromptText("Confirmar");
        //Mostrar ventana
        stage.show();
    }
    private void HandleWindowShowing(WindowEvent e){
        //boton registrar desabilitado
         btnRegistrar.setDisable(true);
        // pswConfirmarContraseña.setDisable(true);
    }
    private void HandleTextChanged(ObservableValue e, String newValue ,String oldValue){
        Boolean contraCorrecto = false;
         if(txtNombre.getText().length()>40){
             LOGGER.info("exceso de caracteres");
             txtNombre.setText(newValue); 
         }
         if(txtNombreUsuario.getText().length()>15){
             LOGGER.info("exceso de caracteres");
             txtNombreUsuario.setText(newValue);
         }
         if(txtEmail.getText().length()>40){
             LOGGER.info("exceso de caracteres");
             txtEmail.setText(newValue); 
         }
         if(pswContraseña.getText().length()>40){
             LOGGER.info("exceso de caracteres");
             pswContraseña.setText(newValue); 
         }
         if(!pswContraseña.getText().trim().isEmpty() && pswConfirmarContraseña.isFocused()){
             if(pswConfirmarContraseña.getText().equals(pswContraseña.getText())){
                 LOGGER.info("coinciden");
                 contraCorrecto=true;
             }
                
             else{
                 LOGGER.info("no conciden");
                 contraCorrecto = false;
             }
                
         }
         LOGGER.info(email.toString());
         if(!txtNombre.getText().trim().isEmpty() && !txtNombreUsuario.getText().trim().isEmpty() && !txtEmail.getText().trim().isEmpty() && !pswContraseña.getText().trim().isEmpty() && !pswConfirmarContraseña.getText().trim().isEmpty() && contraCorrecto && email)
             btnRegistrar.setDisable(false);
         else
             btnRegistrar.setDisable(true);
        
    }
    public void focusChanged (ObservableValue value, Boolean newValue, Boolean oldValue){
        if(newValue)
            email = comprobarEmail();
                
        else if(oldValue)
            LOGGER.info("nada");
            
    }

    private Boolean comprobarEmail() {
        Boolean b=false;
        /*
            prob cambiar metodo d validacion
        */
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        
        Matcher matcher = pattern.matcher(txtEmail.getText().toString());
        
        if(matcher.find()){
            LOGGER.info("Email correct");
         b=true;
        }
           
        else{
            LOGGER.info("Email erroneo");
            b=false;
        }
        return b;
    }
}
