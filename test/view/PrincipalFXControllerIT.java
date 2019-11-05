/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
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
/**
 *
 * @author 2dam
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
        //clickOn("#tfContra");
        //write("123");
        clickOn("#btnAcceder");
        verifyThat("#lblBienvenida",isNotNull());
        verifyThat("#lblEmail",isNotNull());
        clickOn("#menuOpciones");
        clickOn("#menuOpcionesSalir");
        push(KeyCode.ESCAPE);
        clickOn("#menuOpciones");
        clickOn("#menuOpcionesCerrarSesion");
        push(KeyCode.ENTER);
        
        
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
        //clickOn("#tfContra");
        //write("123");
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
