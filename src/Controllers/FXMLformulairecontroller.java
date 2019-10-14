/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXTextField;
import Controllers.FXMLfenetrechoixController;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author S
 */
public class FXMLformulairecontroller implements Initializable{
    @FXML
    private JFXTextField classe;
    @FXML
    private JFXTextField seance;
     @FXML
    private JFXTextField date;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }
   
    public void send()
            
            
    {
        
        //-------------recuperation champs
             String c=classe.getText();
             String s=seance.getText();
             String d=date.getText();
             //------------test sur les champs
             if(c.isEmpty() || s.isEmpty()|| d.isEmpty() )
             {
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer dans tous les champs");
            alert.showAndWait();
            return;
             }
        //test sur la format d'une date---------------------
     
       SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD");
        try {
            Date dte = df.parse(d);
            
        } catch (ParseException ex) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez respecter la fomat de date (YYYY-MM-DD)");
            alert.showAndWait();
            return;
          
           
        }
        
        //charger la tableview (refistre numerique)
    try {
            FXMLLoader loader=new FXMLLoader();
           Pane parent=loader.load(getClass().getResource("/View/FXMLtableview.fxml").openStream());
            FXMLtableviewController fdc=(FXMLtableviewController)loader.getController();
            fdc.getparametr(c,s,d);
            fdc.initCol();
            fdc.loadata();
            Stage stage= new Stage(StageStyle.DECORATED);
          stage.setResizable(false);
            stage.setScene(new Scene(parent));
            stage.show();
            
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLfenetrechoixController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
