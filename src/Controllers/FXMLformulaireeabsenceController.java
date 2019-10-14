/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.FXMLconsultationcontroller;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * FXML Controller class
 *
 * @author S
 */
public class FXMLformulaireeabsenceController implements Initializable {
    @FXML
    private JFXTextField eleve;
    @FXML
    private JFXTextField prenom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   @FXML
    private void versconsulationabsence(ActionEvent event) {
        
        
      
             //------------test sur les champs
             if((eleve.getText()).isEmpty() || (prenom.getText()).isEmpty())
             {
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer dans tous les champs");
            alert.showAndWait();
            return;
             }
             //charger la fentre de consulation absence et envoyer le nom de classe
        try {
            FXMLLoader loader=new FXMLLoader();
            Pane parent=loader.load(getClass().getResource("/View/FXMLconsultation.fxml").openStream());
            FXMLconsultationcontroller fc=(FXMLconsultationcontroller)loader.getController();
            fc.getclasse(eleve.getText(),prenom.getText());
            fc.initCol();
            fc.loadata();
            Stage stage= new Stage(StageStyle.DECORATED);
           
            stage.setScene(new Scene(parent));
            stage.show();
            
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLfenetrechoixController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
