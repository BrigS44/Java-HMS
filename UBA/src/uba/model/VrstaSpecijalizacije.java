
package uba.model;

import uba.baza.Baza;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class VrstaSpecijalizacije  implements Model{
    
    private SimpleIntegerProperty IDspecijalizacija= new SimpleIntegerProperty() ;
    private SimpleStringProperty nazivSpec = new SimpleStringProperty();

    public VrstaSpecijalizacije() {
        
       
    }

    public VrstaSpecijalizacije(int IDspecijalizacija, String nazivSpec) {
        
        this.IDspecijalizacija = new SimpleIntegerProperty (IDspecijalizacija);
        this.nazivSpec = new SimpleStringProperty (nazivSpec);
    }
    
     public VrstaSpecijalizacije(String nazivSpec){
    
        this.nazivSpec = new SimpleStringProperty (nazivSpec);
    }

    public int getIDspecijalizacija() {
        return IDspecijalizacija.get();
    }

    public void setIDspecijalizacija(Integer IDspecijalizacija) {
        this.IDspecijalizacija.set(IDspecijalizacija);
    }

    public String getNazivSpec() {
        return nazivSpec.get();
    }

    public void setNazivSpec(String nazivSpec) {
        this.nazivSpec.set(nazivSpec);
    }

  
    @Override
    public void spasi() {
        //spajamo se na bazu
       Baza DB = new Baza();
        
        try {
            PreparedStatement insert = DB.exec(""
                    + "INSERT INTO vrsta_specijalizacije VALUES(null, ?)");
            
            insert.setString(1, this.nazivSpec.getValue());
            
            
            //update koristimo kada azuriramo (insert, update, delete) podate, a execute select
            insert.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom ubacivanja u bazu: " 
                    + ex.getMessage());
        }
        
       
    }

    @Override
    public void uredi() {
        
        Baza DB = new Baza();
        String sql=
                "UPDATE vrsta_specijalizacije SET naziv=?"
                + " WHERE IDspecijalizacija=?";
        
       PreparedStatement ps = DB.exec(sql);
       try {
        ps.setString(1, this.nazivSpec.getValue());
        
        ps.setInt(2, this.IDspecijalizacija.getValue());
        ps.executeUpdate();
        
        
        
        } 
        catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: " 
                    + ex.getMessage());
        }
    }
    
    @Override
    public void brisi() {
        try {
            Baza DB = new Baza();
            
            PreparedStatement delete=DB.exec("DELETE FROM vrsta_specijalizacije WHERE IDspecijalizacija = ?");
            delete.setInt(1, this.IDspecijalizacija.getValue());
            delete.executeUpdate();        
            
           
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
    }
    public static ObservableList<VrstaSpecijalizacije> uzmiSveSpec(){
    
        ObservableList<VrstaSpecijalizacije> specLista = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM vrsta_specijalizacije");
        
        try{
            
            while(rs.next()){
                
                specLista.add(new VrstaSpecijalizacije(rs.getInt("IDspecijalizacija"),rs.getString("naziv")));
                
            }
        
        }catch(SQLException ex){
        
            System.out.println("Nastala je greška: " + ex.getMessage());
        }
        
        return specLista;
        
    }
    
    public static ObservableList<VrstaSpecijalizacije> uzmiNazivSpec(){
    
        ObservableList<VrstaSpecijalizacije> specNaziv = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT naziv FROM vrsta_specijalizacije");
    
        try {
            while (rs.next()) {
                
                specNaziv.add(new VrstaSpecijalizacije(rs.getString("naziv")));
            }
            
        } catch (SQLException ex) {
            System.out.println("Nastala je greška: " + ex.getMessage());
        }
        return specNaziv;
        }
    
 
    @Override
    public String toString(){
        return this.nazivSpec.getValue();
    }
    
    public int dajIdSpec(String x) throws SQLException {
        int y = 0;
        Baza DB = new Baza();
        PreparedStatement select = DB.exec("SELECT IDspecijalizacija FROM vrsta_specijalizacije WHERE naziv=?");
        select.setString(1, x);
        ResultSet rs = select.executeQuery();
        if (rs.next()) {
            return rs.getInt(1); 
        }
        return 0;
    }
}
