package view;


import escritorior1.EscritorioR1;

import javafx.stage.Stage;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import org.junit.Test;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 *
 * @author Sergio 
 */
public class RegistrarseFXMLControllerIT_DB_caido extends ApplicationTest{
    private String mensaje = "xxxxx";
    private String emailCorrecto ="pedro@gmail.com";

   @Override
    public void start(Stage stage) throws Exception{
        new EscritorioR1().start(stage);
    }

    /**
     * Comprobacion Bases de datos caido.
     */
    @Test
    public void test_DBCaido(){
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
        verifyThat("Ha ocurrido un error en el servidor, intentelo otra vez o vuelva mas tarde.",isVisible());
    }
}
