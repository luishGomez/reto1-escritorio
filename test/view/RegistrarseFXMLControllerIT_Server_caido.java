package view;


import escritorior1.EscritorioR1;

import javafx.stage.Stage;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import org.junit.Test;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 * Test que prueba la ventana registrarse con el servidor caído.
 * Test that tests the register window with the server down.
 * @author Sergio 
 */
public class RegistrarseFXMLControllerIT_Server_caido extends ApplicationTest{
    private String mensaje = "xxxxx";
    private String emailCorrecto ="pedro@gmail.com";

   @Override
    public void start(Stage stage) throws Exception{
        new EscritorioR1().start(stage);
    }

    /**
     * Comprobacion servidor caido
     */
    
    @Test
    public void test_ServidorCaido(){
        clickOn("#btnRegistrar");
        clickOn("#txtNombre");
        write(mensaje);
        clickOn("#txtNombreUsuario");
        write(mensaje);
        clickOn("#txtEmail");
        write(emailCorrecto);
        clickOn("#pswContrasena");
        write(mensaje);
        clickOn("#pswConfirmarContrasena");
        write(mensaje);
        clickOn("#btnRegistrar2");
        verifyThat("El servidor no responde.",isVisible());
    }
}
