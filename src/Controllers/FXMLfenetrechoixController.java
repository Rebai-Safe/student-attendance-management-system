/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author S
 */
public class FXMLfenetrechoixController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void chargerremplir(ActionEvent event) {
        loadwindow("/View/FXMLformulaire.fxml","Remplissage presence");
    }

    @FXML
    private void chargerconsultation(ActionEvent event) {
         loadwindow("/View/FXMLformulaireeabsence.fxml","Consultation absence");
    }
    
     @FXML
    private void chargerannulation(ActionEvent event) {
         loadwindow("/View/FXMLformulaireanulation.fxml","Annulation absence");
    }
    void loadwindow(String loc,String title)
    {
        try {
            Parent parent=FXMLLoader.load(getClass().getResource(loc));
            Stage stage= new Stage(StageStyle.DECORATED);
             stage.getIcons().add(new Image("/View/S.png"));
             stage.setResizable(false);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLfenetrechoixController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
  
}
