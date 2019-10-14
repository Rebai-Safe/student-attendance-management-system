/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.*; 

/**
 *
 * @author S
 */
public class Connexion implements java.io.Serializable {
        Connection con = null;
	ResultSet rs = null ;
	Statement stmt = null ;
	
	public Connexion () {} ;
	
	
	
	//------------------------------------------------------
	
	
	
	public boolean driver()
	{ 
		try{
	
	 Class.forName("com.mysql.jdbc.Driver");
	 return true ; }
	 catch(Exception e){
	 System.out.println("Erreur lors du chargement du pilote :"+ e.getMessage());
	 return false ;
	 } 
}

	//------------------------------------------------------
	

	public boolean OpenConnexion()
	{ 
		try{
	 String url = "jdbc:mysql://localhost:3306/lycee";
	 con = DriverManager.getConnection(url,"root","root");

	 return true ;
	 }
	 catch (Exception e)
	{
	 System.out.println("Echec de l'ouverture de la connexion :"+ e.getMessage());
	 return false ;
	 }
	} 

	
	//-------------------------------------------------
	public boolean closeConnection()
	{ try{ 
		con.close();
	 return true ;
	 }
	 catch (Exception e){
	 System.out.println("Echec de la fermeture de laconnexion :"+ e.getMessage());
	 return false ;
	 } }
	//-------------------------------------------------
	
	public ResultSet selectExec(String sql){
	 try{ 
	 stmt =con.createStatement();
	 rs = stmt.executeQuery(sql);
	 
	 } catch(Exception e){
	 System.out.println("Echec de l'exécution de la requêtesql :"+e.getMessage());
	 } return rs ; } 

	
	//-------------------------------------------------

	public int updateExec(String sql)
	{
	 int i = 0 ;
	 try{
	 con.setAutoCommit(false);
	 stmt = con.createStatement() ;
	 i = stmt.executeUpdate(sql) ;
	 con.commit();
	 }
	 catch(Exception e)
	{
	 System.out.println("Echec de l'exécution de la requêtesql :"+e.getMessage());
	}
	 return i ;
	} 

	//-----------------------------
	public boolean closeResultSet(){
		 try{ 
		 rs.close();
		 return true ; }
		 catch (Exception e){
		 System.out.println("Echec de la fermeture de l'objetResultSet :"+ e.getMessage());
		 return false ;
		 } }
		public boolean closeStatement()
		{ try{ stmt.close(); return true ;
		 }
		 catch (Exception e){ System.out.println("Echec de la fermeture de l'objet Statement :"+ e.getMessage());
		return false ;
		 }
		} 
    
}
