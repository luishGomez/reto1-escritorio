/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author sergio
 */
public class RegistrarseFXMLController{
    private static final Logger LOGGER = Logger.getLogger("reto1_escritorio.view");
    private Stage stage;
    
    public void setStage(Stage stage) {
        this.stage=stage;
    }
    
   @FXML
    private void btnVolver(ActionEvent event){
        LOGGER.info("Voy a volver");
        
    }
    @FXML
     private void btnRegistrar(ActionEvent event){
         LOGGER.info("Voy a resgistrarme");
     }
              
    
    
    public void initStage(Parent root){
       //Create scene an set document for it
        Scene scene = new Scene(root);
        //Set scene in stage and show it.
        stage.setScene(scene);
        stage.setOnShowing(this::onWindowShowing);
        stage.show();
    }
    private void onWindowShowing(WindowEvent e){
         
    }
}
