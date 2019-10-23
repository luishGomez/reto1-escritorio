/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author sergio
 */
public class EscritorioR1 extends Application{
    
    
    
    private static final Logger LOGGER = Logger.getLogger("reto1_escritorio.view.main");
    @Override
    public void start(Stage stage) throws Exception {
        LOGGER.info("voy a leer mi fxml");
        FXMLLoader loader= new FXMLLoader(getClass().getResource("Registrarse.fxml"));
        Parent root = (Parent)loader.load();
        
       RegistrarseFXMLController viewController=
                    ((RegistrarseFXMLController)loader.getController());
            
            
        viewController.setStage(stage);
        viewController.initStage(root);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
