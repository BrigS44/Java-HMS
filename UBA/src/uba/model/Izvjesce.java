
package uba.model;

import uba.baza.Baza;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Izvjesce implements Model {
    
    private SimpleIntegerProperty IDizvjesce = new SimpleIntegerProperty();
    private SimpleStringProperty VrstaIzvjesca = new SimpleStringProperty();
    private SimpleStringProperty naslovIz = new SimpleStringProperty();
    private SimpleStringProperty OpisIz = new SimpleStringProperty();
    private SimpleStringProperty datumIz = new SimpleStringProperty();
    private SimpleStringProperty vrijemeIz = new SimpleStringProperty();
    private SimpleIntegerProperty FKLijecnik = new SimpleIntegerProperty();
    private SimpleIntegerProperty FKPacijent = new SimpleIntegerProperty();
    

    public Izvjesce() {}

    public Izvjesce(int IDizvjesce, String VrstaIzvjesca, String naslovIz, String OpisIz, String datumIz, String vrijemeIz, int FKLijecnik, int FKPacijent) {
      
        this.IDizvjesce = new SimpleIntegerProperty(IDizvjesce) ;
        this.VrstaIzvjesca= new SimpleStringProperty(VrstaIzvjesca);
        this.naslovIz = new SimpleStringProperty(naslovIz);
        this.OpisIz = new SimpleStringProperty(OpisIz);
        this.datumIz = new SimpleStringProperty(datumIz);
        this.vrijemeIz = new SimpleStringProperty(vrijemeIz);
        this.FKLijecnik = new SimpleIntegerProperty( FKLijecnik);
        this.FKPacijent =  new SimpleIntegerProperty(FKPacijent);
        
    }
    public Izvjesce(String naslovIz, String OpisIz,String vrijemeIz){
    
       
        this.naslovIz = new SimpleStringProperty(naslovIz);
        this.OpisIz = new SimpleStringProperty(OpisIz);
        this.vrijemeIz = new SimpleStringProperty(vrijemeIz);
    }

    public int getIDizvjesce() {
        return IDizvjesce.get();
    }

    public void setIDizvjesce(Integer IDizvjesce) {
        this.IDizvjesce.set(IDizvjesce);
    }

    public String getVrstaIzvjesca() {
        return VrstaIzvjesca.get();
    }

    public void setVrstaIzvjesca(String VrstaIzvjesca) {
        this.VrstaIzvjesca.set(VrstaIzvjesca);
    }

    public String getNaslovIz() {
        return naslovIz.get();
    }

    public void setNaslovIz(String naslovIz) {
        this.naslovIz.set(naslovIz);
    }

    public String getOpisIz() {
        return OpisIz.get();
    }

    public void setOpisIz(String OpisIz) {
        this.OpisIz.set(OpisIz);
    }
    
    public String getDatumIz() {
        return datumIz.get();
    }

    public void setDatumIz(String datumIz) {
        this.datumIz.set(datumIz);
    }

    public String getVrijemeIz() {
        return vrijemeIz.get();
    }

    public void setVrijemeIz(String vrijemeIz) {
        this.vrijemeIz.set(vrijemeIz);
    }

    public int getFKLijecnik() {
        return FKLijecnik.get();
    }

    public void setFKLijecnik(Integer FKLijecnik) {
        this.FKLijecnik.set(FKLijecnik);
    }

    public int getFKPacijent() {
        return FKPacijent.get();
    }

    public void setFKPacijent(Integer FKPacijent) {
        this.FKPacijent.set(FKPacijent);
    }

    public String dajIzvjesceOsoblje(){
        
        ObservableList<Osoblje> osoblje = Osoblje.uzmiSvoOsoblje();
        for(Osoblje o : osoblje){
            if(o.getIDkorisnik()  == this.getFKLijecnik())
            { return o.getIme()+" "+o.getPrezime();}   
        }
        return null;
    }
    
    public String dajIzvjescePacijent(){
        
        ObservableList<Pacijent> pacijent = Pacijent.uzmiSvePacijente();
        for(Pacijent p : pacijent){
            if(p.getIDpacijent()  == this.getFKPacijent())
            { return p.getImePac()+" "+ p.getPrezimePac();}   
        }
        return null;
    }
    
    @Override
    public void spasi() {
        //spajamo se na bazu
        Baza DB = new Baza();
        
        try {
            PreparedStatement insert = DB.exec(""
                    + "INSERT INTO izvjesce VALUES(null, ?, ?, ?,?, ?, ?, ?)");
            
            insert.setString(1, this.VrstaIzvjesca.getValue());
            insert.setString(2, this.naslovIz.getValue());
            insert.setString(3, this.OpisIz.getValue());
            insert.setString(4, this.datumIz.getValue());
            insert.setString(5, this.vrijemeIz.getValue());
            insert.setInt(6, this.FKLijecnik.getValue());
            insert.setInt(7, this.FKPacijent.getValue());
            
            
            //update koristimo kada azuriramo (insert, update, delete) podate, a execute select
            insert.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom ubacivanja u bazu: " + ex.getMessage());
        }
        
    }
    
    @Override
    public void uredi() {
        
        Baza DB = new Baza();
        String sql =
                "UPDATE izvjesce SET vrstaIzvjesca=?, naziv=?, opis=?,"
                +"datum=?, vrijeme=?,IDkorisnik=?,IDpacijent=?"
                +" WHERE IDizvjesce=?";
        
        PreparedStatement ps=DB.exec(sql);
       try {
           
        ps.setString(1, this.VrstaIzvjesca.getValue());
        ps.setString(2, this.naslovIz.getValue());
        ps.setString(3, this.OpisIz.getValue());
        ps.setString(4, this.datumIz.getValue());
        ps.setString(5, this.vrijemeIz.getValue());
        ps.setInt(6, this.FKLijecnik.getValue());
        ps.setInt(7, this.FKPacijent.getValue());
        
       
        ps.setInt(8, this.IDizvjesce.getValue());
        ps.executeUpdate();
        
               
        } 
        catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita update izvjesce: " + ex.getMessage());
        }
    }
    
    @Override
    public void brisi() {
        try {
            Baza DB = new Baza();
            
            PreparedStatement delete= DB.exec("DELETE FROM izvjesce WHERE IDizvjesce = ?");
            delete.setInt(1, this.IDizvjesce.getValue());
            delete.executeUpdate();        
            
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
    }
    
    public static ObservableList<Izvjesce> uzmiSvaIzvjesca(){
    
        ObservableList<Izvjesce> izLista =FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM izvjesce");
        
        try {
            while ((rs.next())) {
                izLista.add(new Izvjesce(rs.getInt("IDizvjesce"), rs.getString("vrstaIzvjesca"), 
                        rs.getString("naziv"), rs.getString("opis"), rs.getString("datum"),
                        rs.getString("vrijeme"), rs.getInt("IDkorisnik"), rs.getInt("IDpacijent")));

            }
        } catch (SQLException ex) {
            
            System.out.println("Nastala je greška: " + ex.getMessage());
        }
        
        return izLista;
    }
    
    
}
