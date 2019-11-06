package view;

import escritorior1.EscritorioR1;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 * Test que comprueba el inicio con el servidor caído.
 * Test that checks the start with the fallen server.
 * @author Ricardo Peinado Lastra
 */
public class InicioErroServerTest extends ApplicationTest{
    public InicioErroServerTest() {
    }
    @Override
    public void start(Stage stage) throws Exception{
        new EscritorioR1().start(stage);
    }
    @Test
    public void testSinServidor(){
        write("1234");
        push(KeyCode.TAB);
        write("123");
        push(KeyCode.ENTER);
        verifyThat("Problemas con el servidor, intentelo en un rato.",isVisible());
        push(KeyCode.ENTER);
    }
   
}
