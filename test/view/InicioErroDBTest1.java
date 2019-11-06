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
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 *
 * @author 2dam
 */
public class InicioErroDBTest1 extends ApplicationTest{
    public InicioErroDBTest1() {
    }
    @Override
    public void start(Stage stage) throws Exception{
        new EscritorioR1().start(stage);
    }
    @Test
    public void testSinBaseDeDatos(){
        write("1234");
        push(KeyCode.TAB);
        write("123");
        push(KeyCode.ENTER);
        verifyThat("Ha ocurrido un error en el servidor, intentelo otra vez o vuelva mas tarde.",isVisible());
        push(KeyCode.ENTER);
    }
}
