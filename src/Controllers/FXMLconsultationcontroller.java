/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Model.Connexion;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author S
 */
public class FXMLconsultationcontroller implements Initializable {
    
    ObservableList<Eleve> liste=FXCollections.observableArrayList();
   
    @FXML
    private TableView<Eleve> tableview;
    @FXML
    private TableColumn<Eleve, String> ideleve;
    @FXML
    private TableColumn<Eleve, String> nomeleve;
   @FXML
    private AnchorPane rootpane;
    @FXML
    private TableColumn<Eleve,String> classe;
    @FXML
    private TableColumn<Eleve,String> seance;
    @FXML
    private TableColumn<Eleve, String> etat;
    private static String nomel;
     private static String prenomel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    
    public void getclasse(String s,String c)
    {
    nomel=s;
    prenomel=c;
    
    }
    
    void initCol(){
    ideleve.setCellValueFactory(new PropertyValueFactory<>("ideleve"));
    nomeleve.setCellValueFactory(new PropertyValueFactory<>("nom"));
    classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
    seance.setCellValueFactory(new PropertyValueFactory<>("seance"));
    etat.setCellValueFactory(new PropertyValueFactory<>("etat")); 
    }
    public void loadata() 
            {
             Connexion c= new Connexion();
	     c.driver();
	     c.OpenConnexion();
 String requete="select p.ideleve,e.nom,c.nomclasse,s.libelle,p.etat from presence p ,classe c,eleve e,seance s where "
 + "e.ideleve=p.ideleve and c.idclasse=p.idclasse and s.idseance=p.idseance and e.nom='"+nomel+"' and e.prenom='"+prenomel+"'";               
 
 ResultSet rs=c.selectExec(requete);
 
 
 System.out.println(requete);
 try {
     
       while(rs.next())
            {
                    
                 String ideleve0=rs.getString("ideleve");
                 String nomeleve0=rs.getString("nom");
                 String classe0=rs.getString("nomclasse");
                 String seance0=rs.getString("libelle");
                 String etat0=rs.getString("etat");
                
                Eleve e=new Eleve(ideleve0,nomeleve0,classe0,seance0,etat0);
                liste.add(e);
   

              
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLconsultationcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }

      tableview.setItems(liste);
      
      
      
    
            }
    
    
    
    
    public static class Eleve
            {
          private final SimpleStringProperty ideleve;
          private final SimpleStringProperty nom;
          private final SimpleStringProperty classe;
          private final SimpleStringProperty seance ;
          private final SimpleStringProperty etat;

        public Eleve(String ideleve, String nomeleve,String classe, String seance, String etat) {
           this.ideleve  = new SimpleStringProperty(ideleve);
           this.nom = new SimpleStringProperty(nomeleve);
          
            this.classe  = new SimpleStringProperty(classe);
           this.seance = new SimpleStringProperty(seance);
           this.etat =new SimpleStringProperty(etat);
           
        }

        public String getIdeleve() {
            return ideleve.get();
        }
        

        public String getNom() {
            return nom.get();
        }

      

        public String getClasse() {
            return classe.get();
        }

        public String getSeance() {
            return seance.get();
        }

        public String getEtat() {
            return etat.get();
        }

     
       
       
          
          
          
    }
}
