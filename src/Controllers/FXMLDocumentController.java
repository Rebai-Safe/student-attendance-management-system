/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Connexion;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author S
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
   JFXPasswordField password;
    @FXML
    JFXTextField user;
 Connexion c= new Connexion();
 ResultSet rs;
     
    
 
    @FXML
 public void login(ActionEvent event) throws SQLException
 {
            //-------------recuperation champs
             String username=user.getText();
             String spassword=password.getText();
             //------------test sur les champs
             if(username.isEmpty() || spassword.isEmpty())
             {
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer dans tous les champs");
            alert.showAndWait();
            return;
             }
             
	     c.driver();
	     c.OpenConnexion();  
            rs= c.selectExec("select * from employee   where user='"+username+"' and password='"+spassword+"';");
             //-------------test existence de l'employee
            if (!(rs.next())) 
            {
               Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vérifiez votre identifiant ou votre mot de passe");
            alert.showAndWait();
            }
            else
            {
//goto fentre choix
            loadwindow("/View/FXMLfenetrechoix.fxml","");
            
            
            }
 }       
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           
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
    }}