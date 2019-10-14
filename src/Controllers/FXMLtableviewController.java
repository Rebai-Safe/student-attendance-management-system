/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.FXMLconsultationcontroller;
import com.jfoenix.controls.JFXTextField;
import Model.Connexion;
import Controllers.FXMLfenetrechoixController;
import java.io.IOException;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;




/**
 * FXML Controller class
 *
 * @author admin
 */
public class FXMLtableviewController implements Initializable {

    ObservableList<Eleve> liste=FXCollections.observableArrayList();
    @FXML
    private TableColumn<Eleve,String> nomelevecol;
     @FXML
    private TableColumn<Eleve,String> prenomelevecol;
    @FXML
    private TableColumn<Eleve,String> classecol;
   
    @FXML
    private TableColumn<Eleve,String> libellecol;
    @FXML
    private TableColumn<Eleve,String> datecol;
    @FXML
    private TableColumn<Eleve,CheckBox> etatcol;
    @FXML
    private TableView tableview;
    public static String classe;
    public static String seance;
    public static String date;
    @FXML
    private Button savep;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
            
    
            
    
    }
    
    
    public void getparametr(String c,String s,String d)
    {
     classe=c;
     seance=s;
     date=d;
     System.out.print(s);
    }
   
  public  void initCol(){
    nomelevecol.setCellValueFactory(new PropertyValueFactory<>("nomel"));
    prenomelevecol.setCellValueFactory(new PropertyValueFactory<>("prenomel"));
    classecol.setCellValueFactory(new PropertyValueFactory<>("nomcl"));
    libellecol.setCellValueFactory(new PropertyValueFactory<>("lib")); 
    datecol.setCellValueFactory(new PropertyValueFactory<>("date")); 
    etatcol.setCellValueFactory(new PropertyValueFactory<>("etat")); 

   }
   
    
   
    //get data from database and fit them to the table view
    public void loadata()
            {
             Connexion c= new Connexion();
	     c.driver();
	     c.OpenConnexion();
      String requete="select nom,prenom,nomclasse,libelle,s.date from  classe c,eleve e,seance s where c.idclasse=e.idclasse and c.idclasse=s.idclasse and libelle='"+seance+"' and nomclasse='"+classe+"' and s.date='"+date+"'";
             ResultSet rs=c.selectExec(requete);
         System.out.println(requete);
             try {
            while(rs.next())
            {
                 System.out.println(rs.getString("nomclasse"));
                 String nomeleve0=rs.getString("nom");
                 String prenomeleve0=rs.getString("prenom");
                 String nomclasse0=rs.getString("nomclasse");
                 String libelle0=rs.getString("libelle");
                 String date0=rs.getString("s.date");
                 CheckBox change=new CheckBox();
                 change.setVisible(true);
                 Eleve e=new Eleve(nomeleve0,prenomeleve0,nomclasse0,libelle0,date0,change);
                 liste.add(e);
       
            }
        } catch (SQLException ex) {
           Logger.getLogger(FXMLconsultationcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }

      tableview.setItems(liste);
     
            }
//mettre jour table presence
    @FXML
    private void savepresent(ActionEvent event) {
     //connexion BD
       
             Connexion c= new Connexion();
	     c.driver();
	     c.OpenConnexion();
        //parcours du liste et enregistrement dans la table presence     
             liste=tableview.getItems();
             System.out.print(liste.size());
             
             String etateleve;
             String idclasse,ideleve,idseance;
             idclasse="";ideleve="";idseance="";
             int idc,ids,ide;
       //

    for(Eleve currenteleve : liste) {
       if(currenteleve.etat.isSelected())
   {
   etateleve="p";
 
   }
       else
       {
       etateleve="a";
       }
//-----
    ResultSet rsideleve=c.selectExec("select ideleve from eleve where nom = '"+currenteleve.getNomel()+"' and prenom ='"+currenteleve.getPrenomel()+"'");
    ResultSet rsidclasse=c.selectExec("select idclasse from classe where nomclasse = '"+currenteleve.getnomCl()+"'");
    ResultSet rsidseance=c.selectExec("select idseance from seance where libelle = '"+currenteleve.getLib()+"'");
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
    
    
     ids=Integer.parseInt(idseance);
     ide=Integer.parseInt(ideleve);
    
    //insertion dans la table presence
   
c.updateExec("insert into presence (idclasse,idseance,ideleve,etat,date) values("+idclasse+","+ids+","+ide+",'"+etateleve+"','"+currenteleve.getDate()+"')");
//test sur l'insertion            

  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
           
            alert.setContentText("Enregistré avec succés");
            alert.showAndWait();
            return;         
           
    }
 
    }

 
   

//class matching tableview columns
    
    public static  class Eleve
            {
         
          private final SimpleStringProperty nomel;
          private final SimpleStringProperty prenomel;
          private final SimpleStringProperty nomcl;
          private final SimpleStringProperty lib;
          private final SimpleStringProperty date;
          private CheckBox etat;
      
  
  
        public Eleve(String nomeleve,String prenomeleve,String nomclass,String libelle,String date,CheckBox  change)
        {
            
           this.nomel= new SimpleStringProperty(nomeleve);
           this.prenomel= new SimpleStringProperty(prenomeleve);
           this.nomcl = new SimpleStringProperty(nomclass);
           this.lib =new SimpleStringProperty(libelle);
           this.date =new SimpleStringProperty(date);
           this.etat=change;
        
        }

        
            public String getNomel() {
            return nomel.get();
        }
 
        public  String  getnomCl() {
            return nomcl.get() ;
        }

        public  String  getLib() {
            return lib.get();
        }

        public  String  getDate() {
            return date.get();
        }

        public  CheckBox  getEtat() {
            return etat;
        }

        public String getPrenomel() {
            return prenomel.get();
        }
         
    }

//to load fxmltableview

}    
    

