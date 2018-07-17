
package uba.model;

import uba.baza.Baza;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Odjel implements Model {
    
    private SimpleIntegerProperty IDodjel=new SimpleIntegerProperty();
    private SimpleStringProperty nazivOdjel= new SimpleStringProperty();

    public Odjel() {
       
    }

    public Odjel(int IDodjel, String nazivOdjel) {
        this.IDodjel = new SimpleIntegerProperty (IDodjel);
        this.nazivOdjel = new SimpleStringProperty (nazivOdjel);
    }
    
    public Odjel(String nazivOdjel){
    
        this.nazivOdjel = new SimpleStringProperty (nazivOdjel);
    }

    public Integer getIDodjel() {
        return IDodjel.get();
    }

    public void setIDodjel(Integer IDodjel) {
        this.IDodjel.set(IDodjel);
    }

    public String getNazivOdjel() {
        return nazivOdjel.get();
    }

    public void setNazivOdjel(String nazivOdjel) {
        this.nazivOdjel.set(nazivOdjel);
    }
    
    @Override
    public void spasi() {
        //spajamo se na bazu
        Baza DB = new Baza();
        
        try {
            PreparedStatement insert = DB.exec(""
                    + "INSERT INTO odjel VALUES(null, ?)");
            
            insert.setString(1, this.nazivOdjel.getValue());
            
            
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
                "UPDATE odjel SET naziv=?"
                + " WHERE IDodjel=?";
        PreparedStatement ps = DB.exec(sql);
       try {
        ps.setString(1, this.nazivOdjel.getValue());
        
        ps.setInt(2, this.IDodjel.getValue());
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
            
            PreparedStatement ps=DB.exec("DELETE FROM odjel WHERE IDodjel = ?");
            ps.setInt(1, this.IDodjel.getValue());
            ps.executeUpdate();        
            
            
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
    }
    
    public static ObservableList<Odjel> uzmiSveOdjele(){
    
        ObservableList<Odjel> odjeliLista = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM odjel");
        
        try{
            
            while(rs.next()){
                
                odjeliLista.add(new Odjel(rs.getInt("IDodjel"),rs.getString("naziv")));
                
            }
        
        }catch(SQLException ex){
        
            System.out.println("Nastala je greška: " + ex.getMessage());
        }
        
        return odjeliLista;
        
    }
    
    public static ObservableList<Odjel> uzmiNazivOdjel(){
    
        ObservableList<Odjel> odjelNaziv = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT naziv FROM odjel");
    
        try {
            while (rs.next()) {
                
                odjelNaziv.add(new Odjel(rs.getString("naziv")));
            }
            
        } catch (SQLException ex) {
            System.out.println("Nastala je greška: " + ex.getMessage());
        }
        return odjelNaziv;
        }
    
 
    @Override
    public String toString(){
        return this.nazivOdjel.getValue();
    }
    
    public int dajIdOdjel(String x) throws SQLException {
        int y = 0;
        Baza DB = new Baza();
        PreparedStatement select = DB.exec("SELECT IDodjel FROM odjel WHERE naziv=?");
        select.setString(1, x);
        ResultSet rs = select.executeQuery();
        if (rs.next()) {
            return rs.getInt(1); 
        }
        return 0;
    }
    
    
}
