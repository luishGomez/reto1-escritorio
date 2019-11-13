package view;

import escritorior1.EscritorioR1;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isNotNull;
import static org.testfx.matcher.base.NodeMatchers.isNull;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
/**
 * Test que comprueba que el la ventana principal funcione bien.
 * Test that checks that the principal window works well.
 * @author Ricardo Peinado Lastra
 */
public class PrincipalFXControllerIT extends ApplicationTest  {
    
    @Override
    public void start(Stage stage) throws Exception{
        new EscritorioR1().start(stage);
    }
    public PrincipalFXControllerIT() {
        
    }
    
    @Test
    public void testInicioSinProblemas() {
        accederBien();
        verifyThat("#lblBienvenida",isNotNull());
        verifyThat("#lblEmail",isNotNull());
        clickOn("#menuOpciones");
        clickOn("#menuOpcionesCerrarSesion");
        push(KeyCode.ENTER);
        clickOn("#tfContra");
        write("123");
        clickOn("#btnAcceder");
        verifyThat("#lblBienvenida",isNotNull());
        verifyThat("#lblEmail",isNotNull());
        clickOn("#menuOpciones");
        clickOn("#menuOpcionesSalir");
        push(KeyCode.ESCAPE);
        clickOn("#menuOpciones");
        clickOn("#menuOpcionesCerrarSesion");
        push(KeyCode.ENTER);
        /*Modificaciones DIN 13/11/2019*/
        /*Comprueba que se a cerrado la ventana principal y a vuelto a inicio*/
        verifyThat("#btnAcceder",isDisabled());
        
        
    }
    @Test
    public void testInicioSoloTeclas() {
        clickOn("#tfNombreUsuario");
        write("1234");
        clickOn("#tfContra");
        write("123");
        verifyThat("#btnAcceder",isEnabled());
        clickOn("#btnAcceder");
        verifyThat("#lblBienvenida",isNotNull());
        verifyThat("#lblEmail",isNotNull());
        push(KeyCode.ALT);
        push(KeyCode.O);
        push(KeyCode.DOWN);
        push(KeyCode.DOWN);
        push(KeyCode.ENTER);
        push(KeyCode.ENTER);
        /*Modificaciones DIN 13/11/2019*/
        /*Comprueba que se a cerrado la ventana principal y a vuelto a inicio*/
        verifyThat("#btnAcceder",isDisabled());
        clickOn("#tfContra");
        write("123");
        clickOn("#btnAcceder");
        verifyThat("#lblBienvenida",isNotNull());
        verifyThat("#lblEmail",isNotNull());
        push(KeyCode.ALT);
        push(KeyCode.O);
        push(KeyCode.DOWN);
        push(KeyCode.ENTER);
        
    }
    private void accederBien() {
        verifyThat("#btnAcceder",isDisabled());
        clickOn("#tfNombreUsuario");
        write("1234");
        clickOn("#tfContra");
        write("123");
        verifyThat("#btnAcceder",isEnabled());
        clickOn("#btnAcceder");
    }
    
}
