/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escritorior1;

import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import view.ControladorGeneral;
import view.InicioFXController;


/**
 *
 * @author Luis
 */
public class EscritorioR1 extends javafx.application.Application {

    @Override
    public void start(Stage stage) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/inicio.fxml"));

            Parent root = (Parent)loader.load();

            InicioFXController controller = ((InicioFXController)loader.getController());
            controller.setStage(stage);
            controller.initStage(root);
        }catch(IOException e){
            ControladorGeneral.showErrorAlert("Error al cargar la ventana.");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
