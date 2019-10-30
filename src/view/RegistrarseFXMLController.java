/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package view;



import businessLogic.LogicCliente;
import businessLogic.LogicFactory;
import clases.*;
import exceptions.DAOException;
import exceptions.EsperaCompletaException;
import exceptions.LogicException;
import exceptions.LoginIDException;
import exceptions.ServerException;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static view.ControladorGeneral.showErrorAlert;

/**
 *
 * @author sergio
 */
public class RegistrarseFXMLController{
    private static final Logger LOGGER = Logger.getLogger("reto1_escritorio.view.Registro");
    private Stage stage;
    private User user=new User();
    
    private LogicCliente logic = new LogicFactory().getLogicCliente();
    
    Tooltip tooltip = new Tooltip();
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtNombreUsuario;
    @FXML
    private PasswordField pswContrasena;
    @FXML
    private PasswordField pswConfirmarContrasena;
    @FXML
    private Label lblMostrarContrasena;
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
    private Label lblContrasena;
    @FXML
    private Label lblConfirmarContrasena;
    
    @FXML
    private void btnVolver(ActionEvent event){
        stage.hide();
    }
    @FXML
    private void btnRegistrar(ActionEvent event){
        user.setEmail(txtEmail.getText().trim());
        user.setFullname(txtNombre.getText());
        user.setLogin(txtNombreUsuario.getText());
        user.setPassword(pswContrasena.getText());
        
        if(!esEmail(txtEmail.getText().trim()))
            lblEmail.setTextFill(Color.web("red"));
        else{
            lblEmail.setTextFill(Color.web("black"));
            try{
                if(logic.registro(user)){
                    Alert alert=new Alert(AlertType.INFORMATION);
                    alert.setTitle("Informacion de resgistro");
                    alert.setHeaderText("Se ha registrado correctamente");
                    alert.showAndWait();
                    stage.hide();
                }
            }catch(LoginIDException e){
                //modificar mas adelante
                showErrorAlert("Ese ide ya existe");
            }catch(DAOException e){
                showErrorAlert("Ha ocurrido un error en el servidor, intentelo otra vez o vuelva mas tarde.");
            }catch(ServerException e){
                showErrorAlert("Error en el servidor.");
            }catch(EsperaCompletaException e){
                showErrorAlert("Intentelo otra vez o vuelva mas tarde.");
            }catch(LogicException e){
                showErrorAlert("El servidor no responde.");
            }
        }
    }
    
    
    public void setUser(User user){
        this.user = user;
    }
    /**
     * Iniciamos el stage con la scena y su parent respectivo.
     * @param root
     */
    public void initStage(Parent root){
        //Crear scena y stage
        Scene scene = new Scene(root);
        stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        //Enviar scena al stage.
        stage.setScene(scene);
        //Maximizable deshabilitado.
        stage.setResizable(false);
        stage.setTitle("Registro");
        //El foco estará en Nombre.
        txtNombre.requestFocus();
        //anadimos controladores.
        stage.setOnShowing(this::HandleWindowShowing);
        txtNombre.textProperty().addListener(this::HandleTextChanged);
        txtNombreUsuario.textProperty().addListener(this::HandleTextChanged);
        txtEmail.textProperty().addListener(this::HandleTextChanged);
        //txtEmail.focusedProperty().addListener(this::focusChanged);
        pswContrasena.textProperty().addListener(this::HandleTextChanged);
        pswConfirmarContrasena.textProperty().addListener(this::HandleTextChanged);
        //tooltips de ayuda para los botones
        btnCancelar.setTooltip(new Tooltip("Regresar al login"));
        //textos de ayuda promptext
        txtNombre.setPromptText("ej. Aitor Sanchez");
        txtNombreUsuario.setPromptText("ej. Aitor_89");
        txtEmail.setPromptText("ej. Aitor_Sanchez@algo.com");
        pswContrasena.setPromptText("Contrasena");
        pswConfirmarContrasena.setPromptText("Confirmar");
        //Mostrar ventana
        stage.show();
    }
    /**
     * Configuracion del inicio de la ventana.
     * @param e
     */
    private void HandleWindowShowing(WindowEvent e){
        //boton registrar desabilitado
        btnRegistrar.setDisable(true);
        
    }
    /**
     * Controlador de eventos de cambio de texto.
     * @param e
     * @param newValue
     * @param oldValue
     */
    private void HandleTextChanged(ObservableValue e, String newValue ,String oldValue){
        //comprobacion de Nombre completo su caracteres maximos.
        if(txtNombre.getText().length()>40 && txtNombre.isFocused()){
            LOGGER.info("El nombre tiene exceso de caracteres");
            //txtNombre.setText(newValue);
            txtNombre.setText(newValue.trim().substring(0,40));
        }
        //comprobacion de Nombre de usuario usuario caracteres maximos.
        if(txtNombreUsuario.getText().length()>15 && txtNombreUsuario.isFocused()){
            LOGGER.info("El nombre de usuario tiene exceso de caracteres");
            txtNombreUsuario.setText(newValue.trim().substring(0,15));
        }
        //comprobacion de Email usuario caracteres maximos.
        if(txtEmail.isFocused()){
            if(txtEmail.getText().length()>40)
                txtEmail.setText(newValue.trim().substring(0,40));
        }
        //Comprobacion de contrasena caracteres maximos.
        if(pswContrasena.getText().length()>15 && pswContrasena.isFocused())
            pswContrasena.setText(newValue.trim().substring(0,15));
        if(pswConfirmarContrasena.getText().length()>15 && pswConfirmarContrasena.isFocused())
            pswConfirmarContrasena.setText(newValue.trim().substring(0,15));
        //Comprobacion de que todos los campos esten habilitados.
        if(!txtNombre.getText().trim().isEmpty() && !txtNombreUsuario.getText().trim().isEmpty()
                && !txtEmail.getText().trim().isEmpty() && !pswContrasena.getText().trim().isEmpty()
                && !pswConfirmarContrasena.getText().trim().isEmpty()
                && passwordsCorrect(pswContrasena.getText().trim(),pswConfirmarContrasena.getText().trim())){
            btnRegistrar.setDisable(false);
        }else{
            if(!passwordsCorrect(pswContrasena.getText().trim(),pswConfirmarContrasena.getText().trim()) && pswConfirmarContrasena.getText().trim().length()>=3 && pswContrasena.getText().trim().length()>=3)
                lblConfirmarContrasena.setTextFill(Color.web("red"));
            else
                lblConfirmarContrasena.setTextFill(Color.web("black"));
            btnRegistrar.setDisable(true);
        }
    }
    /**
     * Comprobacion del formato del Email.
     * @param email
     * @return  true si el email es correcto
     */
    private  boolean esEmail(String email) {
        boolean resu=true;
        String firstPart,secondPart,thirdPart;
        if(email.length()<5 || email.length()>40){
            resu=false;
        }else{
            try{
                firstPart=email.substring(0, email.indexOf('@'));
                secondPart=email.substring(email.indexOf('@')+1,email.indexOf('.', email.indexOf('@')));
                thirdPart=email.substring(email.indexOf('.',email.indexOf('.', email.indexOf('@')))+1);
                if(!isEmailFirstPart(firstPart) || !isEmailSecondPart(secondPart) || !isEmailThridPart(thirdPart))
                    resu=false;
            }catch(StringIndexOutOfBoundsException e){
                resu=false;
            }
        }
        return resu;
    }
    /**
     * Comprobar primera parte del email (anterior al "@")
     * @param cadena.
     * @return true si es correcto.
     */
    private  boolean isEmailFirstPart(String cadena) {
        boolean resu=true;
        for(int i=0;i<cadena.length();i++){
            if(!Character.isAlphabetic(cadena.charAt(i)) && !Character.isDigit(cadena.charAt(i)) && cadena.charAt(i)!='.' && cadena.charAt(i)!='-' && cadena.charAt(i)!='_'){
                resu=false;
                break;
            }
        }
        return resu;
    }
    /**
     * Comprobar segunda parte del email (despues del "@" y antes del punto).
     * @param cadena.
     * @return true si es correcto.
     */
    private  boolean isEmailSecondPart(String cadena) {
        boolean resu=true;
        for(int i=0;i<cadena.length();i++){
            if(!Character.isAlphabetic(cadena.charAt(i)) && !Character.isDigit(cadena.charAt(i)) && cadena.charAt(i)!='-'){
                resu=false;
                break;
            }
        }
        return resu;
    }
    /**
     * Comprobar tercera parte del email (despues del ".").
     * @param cadena.
     * @return true si esta correcto.
     */
    private  boolean isEmailThridPart(String cadena) {
        boolean resu=true;
        for(int i=0;i<cadena.length();i++){
            if(!Character.isAlphabetic(cadena.charAt(i)) && !Character.isDigit(cadena.charAt(i))){
                resu=false;
                break;
            }
        }
        return resu;
    }
    /**
     * Comprobar que contrasena y confirmarContrasena sean identicos y cumplan el max tamaño de acuerdo a la base de datos.
     * @param contrasena.
     * @param contrasenaConfirmacion.
     * @return true si esta correcto.
     */
    public boolean passwordsCorrect(String contrasena,String contrasenaConfirmacion){
        boolean resultado=true;
        if(contrasena.length()>=3 && contrasena.length()<=40 && contrasenaConfirmacion.length()>=3 && contrasenaConfirmacion.length()<=40){
            if(!contrasena.equals(contrasenaConfirmacion)){
                resultado=false;
                
            }
        }else{
            resultado=false;
            
        }
        return resultado;
        
    }
    
    
}
