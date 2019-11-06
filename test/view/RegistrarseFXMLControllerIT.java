package view;


import escritorior1.EscritorioR1;

import javafx.stage.Stage;
import org.junit.FixMethodOrder;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 * Test que prueba la ventana registrar.
 * Test that test window register.
 * @author Sergio 
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegistrarseFXMLControllerIT extends ApplicationTest{
    private final String CONTRASENA_MENSAJE_DEFAULT="Confirmar contraseña";
    private final String EMAIL_MENSAJE_DEFAULT="Email";
    private final String EMAIL_MENSAJE_ERROR="EMAIL ERRONEO";
    private final String CONTRASENA_MENSAJE_ERROR="CONTRASEÑAS ERRONEAS";
    private String mensaje = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
                           + "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    private String contraValidacionErrornea = "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy"
                                            + "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy"
                                            + "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy"
                                            + "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy";
    private String emailCorrecto ="pedro@gmail.com";

   @Override
    public void start(Stage stage) throws Exception{
        new EscritorioR1().start(stage);
    }

    /**
     * comprobacion del tamaño de todos los textfield
     */
    
    @Test
    public void testA_tamañoTextFields(){
        clickOn("#btnRegistrar");
        verifyThat("#btnRegistrar2", isDisabled());
        clickOn("#txtNombre");
        write(mensaje);
        verifyThat("#txtNombre", hasText(mensaje.substring(0, 40)));
        
        clickOn("#txtNombreUsuario");
        write(mensaje);
        verifyThat("#txtNombreUsuario", hasText(mensaje.substring(0, 15)));
        
        clickOn("#txtEmail");
        write(mensaje);
        verifyThat("#txtEmail", hasText(mensaje.substring(0, 40)));
        
        clickOn("#pswContrasena");
        write(mensaje);
        verifyThat("#pswContrasena", hasText(mensaje.substring(0, 15)));
        
        clickOn("#pswConfirmarContrasena");
        write(mensaje);
        verifyThat("#pswConfirmarContrasena", hasText(mensaje.substring(0, 15)));
        
        verifyThat("#btnRegistrar2", isEnabled());

    }
    /**
     * Comprobacion de contraseña sea correcta e igual en la confirmacion
     */
    @Test
    public void testB_contrasenaCorrecta(){
        doubleClickOn("#pswContrasena");
        write(mensaje.substring(0, 2));
        doubleClickOn("#pswConfirmarContrasena");
        write(mensaje.substring(0, 2));
        verifyThat("#btnRegistrar2", isDisabled());
        
        doubleClickOn("#pswContrasena");
        write(mensaje.substring(0, 3));
        doubleClickOn("#pswConfirmarContrasena");
        write(mensaje.substring(0, 3));
        verifyThat("#btnRegistrar2", isEnabled());
        
        doubleClickOn("#pswContrasena");
        write(mensaje.substring(0, 4));
        doubleClickOn("#pswConfirmarContrasena");
        write(contraValidacionErrornea.substring(0, 4));
        verifyThat("#lblConfirmarContrasena",org.testfx.matcher.control.LabeledMatchers.hasText(CONTRASENA_MENSAJE_ERROR));
        
        doubleClickOn("#pswContrasena");
        write(mensaje.substring(0, 3));
        doubleClickOn("#pswConfirmarContrasena");
        write(mensaje.substring(0, 3));
        verifyThat("#lblConfirmarContrasena", org.testfx.matcher.control.LabeledMatchers.hasText(CONTRASENA_MENSAJE_DEFAULT));
    }
    /**
     * Comprobacion de email coccrecto
     */
    @Test
    public void testC_emailCorrecto(){
        doubleClickOn("#txtEmail");
        write(mensaje.substring(0,10));
        clickOn("#btnRegistrar2");
        verifyThat("#lblEmail", org.testfx.matcher.control.LabeledMatchers.hasText(EMAIL_MENSAJE_ERROR));
        doubleClickOn("#txtEmail");
        write(emailCorrecto);
        clickOn("#btnRegistrar2");
        verifyThat("#lblEmail", org.testfx.matcher.control.LabeledMatchers.hasText(EMAIL_MENSAJE_DEFAULT));
      }
    /**
     * Comprobacion de si ya existe un usuario con dicho nombre de usuario
     */
    @Test
    public void testD_comprobarId(){
        doubleClickOn("#txtNombreUsuario");
        write("123");
        clickOn("#btnRegistrar2");
        verifyThat("El nombre de usuario ya existe.",isVisible());
    }
}
