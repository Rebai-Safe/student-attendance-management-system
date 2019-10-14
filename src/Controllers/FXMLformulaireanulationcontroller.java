/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Connexion;
import com.jfoenix.controls.JFXTextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

/**
 *
 * @author S
 */
public class FXMLformulaireanulationcontroller {
    @FXML
    private JFXTextField eleve;
    @FXML
    private JFXTextField classe;
    @FXML
    private JFXTextField seance;
     @FXML
    private JFXTextField date;
    @FXML
    private void versannulationabsence(ActionEvent event) {
        //connexion BD
             Connexion c= new Connexion();
	     c.driver();
	     c.OpenConnexion();
             //test sur la format d'une date---------------------
       String d=date.getText();
       SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD");
        try {
            Date dte = df.parse(d);
            
        } 
        catch (ParseException ex) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez respecter la fomat de date (YYYY-MM-DD)");
            alert.showAndWait();
            return;
          
           
        }
             
        //parcours     
  
    String ideleve=null,idclasse=null,idseance=null;
    String dte=date.getText();
//-----
    ResultSet rsideleve=c.selectExec("select ideleve from eleve where nom = '"+eleve.getText()+"'");
    ResultSet rsidclasse=c.selectExec("select idclasse from classe where nomclasse = '"+classe.getText()+"'");
    ResultSet rsidseance=c.selectExec("select idseance from seance where libelle = '"+seance.getText()+"'");
        try {
           //ideleve
            while(rsideleve.next())
            {
            ideleve=rsideleve.getString("ideleve");
            }
         //idclasse
            while(rsidclasse.next())
            {
            idclasse=rsidclasse.getString("idclasse");
            }
          //idseance
           while(rsidseance.next())
            {
            idseance=rsidseance.getString("idseance");
            }
        
        } catch (SQLException ex) {
           Logger.getLogger(FXMLconsultationcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        if(idclasse==null || idseance==null || ideleve==null)
        {
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Verifier les champs");
            alert.showAndWait();
            return;
        }
    
     
    //insertion dans la table presence
   int r;
r=c.updateExec("update presence set etat='p' where idclasse="+idclasse+" and idseance="+idseance+" and ideleve="+ideleve+" and date='"+dte+"'");
if(r!=0)
{
Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("l'absence est annulé avec succés");
            alert.showAndWait();
            return;
    
    }
 else
 {
 
 Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Erreur Verifier les champs");
            alert.showAndWait();
            return;
 }
        
    }     
    }
    

