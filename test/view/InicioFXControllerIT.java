package view;

import escritorior1.EscritorioR1;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isNotNull;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 * Test que comprueba el inicio de la aplicación.
 * @author Ricardo Peinado Lastra
 */
public class InicioFXControllerIT extends ApplicationTest {
    
    public InicioFXControllerIT() {
    }
    @Override
    public void start(Stage stage) throws Exception{
        new EscritorioR1().start(stage);
    }
   
    @Test
    public void testLoginMin() {
        clickOn("#tfNombreUsuario");
        write("12");
        clickOn("#tfContra");
        write("1234");
        verifyThat("#btnAcceder",isDisabled());
    }
    
    @Test
    public void testPasswordMin() {
        clickOn("#tfNombreUsuario");
        write("1234");
        clickOn("#tfContra");
        write("12");
        verifyThat("#btnAcceder",isDisabled());
    }
    
    @Test
    public void testLoginAndIdMax() {
        clickOn("#tfNombreUsuario");
        write("1234512345123456");
        verifyThat("Has superado el máximo tamaño de nombre de usuario, 15.",isVisible());
        push(KeyCode.ENTER);
        clickOn("#tfContra");
        write("1234512345123456");
        verifyThat("Has superado el máximo tamaño de contraseña, 15.",isVisible());
        push(KeyCode.ENTER);
    }
    
    @Test
    public void testLoginError() {
        clickOn("#tfNombreUsuario");
        write("123");
        clickOn("#tfContra");
        write("1234");
        clickOn("#btnAcceder");
        verifyThat("Nombre de usuario incorrecto.",isVisible());
        push(KeyCode.ENTER);
    }
    
    @Test
    public void testPasswordError() {
        clickOn("#tfNombreUsuario");
        write("1234");
        clickOn("#tfContra");
        write("1234");
        clickOn("#btnAcceder");
        verifyThat("Contraseña incorrecta.",isVisible());
        push(KeyCode.ENTER);
    }
    
    @Test
    public void testBien() {
        clickOn("#tfNombreUsuario");
        write("1234");
        clickOn("#tfContra");
        write("123");
        clickOn("#btnAcceder");
        verifyThat("#lblEmail",isNotNull());
        clickOn("#menuOpciones");
        clickOn("#menuOpcionesCerrarSesion");
        push(KeyCode.ENTER);
    }
    
    @Test
    public void testLoginMinTeclas() {
        write("12");
        push(KeyCode.TAB);
        write("1234");
        verifyThat("#btnAcceder",isDisabled());
    }
    
    @Test
    public void testPasswordMinTeclas() {
        write("1234");
        push(KeyCode.TAB);
        write("12");
        verifyThat("#btnAcceder",isDisabled());
    }
    
    @Test
    public void testLoginAndIdMaxTeclas() {
        write("1234512345123456");
        verifyThat("Has superado el máximo tamaño de nombre de usuario, 15.",isVisible());
        push(KeyCode.ENTER);
        push(KeyCode.TAB);
        write("1234512345123456");
        verifyThat("Has superado el máximo tamaño de contraseña, 15.",isVisible());
        push(KeyCode.ENTER);
    }
    
    @Test
    public void testLoginErrorTeclas() {
        write("123");
        push(KeyCode.TAB);
        write("1234");
        push(KeyCode.ENTER);
        verifyThat("Nombre de usuario incorrecto.",isVisible());
        push(KeyCode.ENTER);
    }
    
    @Test
    public void testPasswordErrorTeclas() {
        write("1234");
        push(KeyCode.TAB);
        write("1234");
        push(KeyCode.ENTER);
        verifyThat("Contraseña incorrecta.",isVisible());
        push(KeyCode.ENTER);
    }
    
    @Test
    public void testBienTeclas() {
        write("1234");
        push(KeyCode.TAB);
        write("123");
        push(KeyCode.ENTER);
        verifyThat("#lblEmail",isNotNull());
        clickOn("#menuOpciones");
        clickOn("#menuOpcionesCerrarSesion");
        push(KeyCode.ENTER);
    }
    
   
}
