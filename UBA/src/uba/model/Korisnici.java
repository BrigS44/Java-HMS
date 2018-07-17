
package uba.model;

import java.sql.Date;
import uba.baza.Baza;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Korisnici implements Model {
    
    private SimpleIntegerProperty IDkorisnik = new SimpleIntegerProperty();
    private SimpleStringProperty ime = new SimpleStringProperty();
    private SimpleStringProperty prezime = new SimpleStringProperty();
    private SimpleStringProperty spol = new SimpleStringProperty();
    private SimpleStringProperty telefon = new SimpleStringProperty();
    private SimpleStringProperty adresa = new SimpleStringProperty();
    private SimpleStringProperty email = new SimpleStringProperty();
    private SimpleStringProperty datumRodjenja = new SimpleStringProperty();
    private SimpleStringProperty JMBG = new SimpleStringProperty();
    private SimpleStringProperty tipKor = new SimpleStringProperty();
    private SimpleStringProperty korisnickoIme = new SimpleStringProperty();
    private SimpleStringProperty lozinka = new SimpleStringProperty();
 
    public Korisnici() {
 
    }
    
   public Korisnici(int IDkorisnik, String ime,String prezime){
        
        this.IDkorisnik= new SimpleIntegerProperty(IDkorisnik);
        this.ime = new SimpleStringProperty(ime);
        this.prezime = new SimpleStringProperty(prezime);
    }

    public Korisnici(int IDkorisnik, String ime,String prezime,String spol,  String telefon, String adresa, String email, String datumRodjenja, String JMBG, String tipKor,String korisnickoIme, String lozinka) {
        
        this.IDkorisnik = new SimpleIntegerProperty(IDkorisnik);
        this.ime = new SimpleStringProperty(ime);
        this.prezime = new SimpleStringProperty(prezime);
        this.spol=new SimpleStringProperty(spol);
        this.telefon = new SimpleStringProperty(telefon);
        this.adresa = new SimpleStringProperty(adresa);
        this.email = new SimpleStringProperty(email);
        this.datumRodjenja = new SimpleStringProperty(datumRodjenja);
        this.JMBG = new SimpleStringProperty(JMBG);
        this.tipKor=new SimpleStringProperty(tipKor);
        this.korisnickoIme = new SimpleStringProperty(korisnickoIme);
        this.lozinka = new SimpleStringProperty(lozinka);
       
        
        
    }
    
    public Korisnici( String ime,String prezime,String spol,  String telefon, String adresa, String email,String JMBG, String tipKor,String korisnickoIme, String lozinka){
        this.ime = new SimpleStringProperty(ime);
        this.prezime = new SimpleStringProperty(prezime);
        this.spol=new SimpleStringProperty(spol);
        this.telefon = new SimpleStringProperty(telefon);
        this.adresa = new SimpleStringProperty(adresa);
        this.email = new SimpleStringProperty(email);
        this.JMBG = new SimpleStringProperty(JMBG);
        this.tipKor=new SimpleStringProperty(tipKor);
        this.korisnickoIme = new SimpleStringProperty(korisnickoIme);
        this.lozinka = new SimpleStringProperty(lozinka);
        
    }
    
    public Korisnici( String ime,String prezime,String telefon, String adresa, String email,String JMBG,String korisnickoIme, String lozinka){
        this.ime = new SimpleStringProperty(ime);
        this.prezime = new SimpleStringProperty(prezime);
        this.telefon = new SimpleStringProperty(telefon);
        this.adresa = new SimpleStringProperty(adresa);
        this.email = new SimpleStringProperty(email);
        this.JMBG = new SimpleStringProperty(JMBG);
        this.korisnickoIme = new SimpleStringProperty(korisnickoIme);
        this.lozinka = new SimpleStringProperty(lozinka);
       
    }
    
    
    public Korisnici(String korisnickoIme, String lozinka){
        
        this.korisnickoIme=new SimpleStringProperty(korisnickoIme);
        this.lozinka=new SimpleStringProperty(lozinka);
       
    }

    public String getTipKor() {
        return tipKor.get();
    }

    public void setTipKor(String tipKor) {
        this.tipKor.set(tipKor);
    }
    
    

    public int getIDkorisnik() {
        return IDkorisnik.get();
    }

    public void setIDkorisnik(int IDkorisnik) {
        this.IDkorisnik.set(IDkorisnik);
    }

    public String getIme() {
        return ime.get();
    }

    public void setIme(String ime) {
        this.ime.set(ime);
    }

    public String getPrezime() {
        return prezime.get();
    }

    public void setPrezime(String prezime) {
        this.prezime.set(prezime);
    }

    public String getSpol() {
        return spol.get();
    }

    public void setSpol(String spol) {
        this.spol.set(spol);
    }
  
    public String getTelefon() {
        return telefon.get();
    }

    public void setTelefon(String telefon) {
        this.telefon.set(telefon);
    }

    public String getAdresa() {
        return adresa.get();
    }

    public void setAdresa(String adresa) {
        this.adresa.set(adresa);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getDatumRodjenja() {
        return datumRodjenja.get();
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja.set(datumRodjenja);
    }

    public String getJMBG() {
        return JMBG.get();
    }

    public void setJMBG(String JMBG) {
        this.JMBG.set(JMBG);
    }

    public String getKorisnickoIme() {
        return korisnickoIme.get();
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme.set(korisnickoIme);
    }

    public String getLozinka() {
        return lozinka.get();
    }

   
    public void setLozinka(String lozinka) {
        this.lozinka.set(lozinka);
    }

    

    
    
    @Override
    public void spasi() {
        
    }

    @Override
    public void uredi() {
        
    }
    
    @Override
    public void brisi() {
        try {
            
            Baza DB = new Baza();
            PreparedStatement delete =DB.exec("DELETE FROM korisnici WHERE IDkorisnik = ?");
            delete.setInt(1, this.IDkorisnik.getValue());
            delete.executeUpdate();        
            
            
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
    }
  
    
    public static boolean prijavi (String korisnickoIme, String lozinka){
    
        Baza DB = new Baza();
        PreparedStatement insert = DB.exec("SELECT * FROM korisnici WHERE korisnickoIme =? AND "
                + "lozinka=?");
        try {
            insert.setString(1, korisnickoIme);
            insert.setString(2, lozinka);
            
            ResultSet rs = insert.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška: "+ex.getMessage());
            return false;
        }
    }
    
     public int dajIDPrijavljeni () {
        Baza DB = new Baza();
        PreparedStatement ps = DB.exec("SELECT * FROM korisnici WHERE korisnickoIme =? AND "
                + "lozinka=?");
        try {
            ps.setString(1, this.korisnickoIme.getValue());
            ps.setString(2, this.lozinka.getValue());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
               int id=rs.getInt("IDkorisnik"); 
               return id;
            } 
        } catch (SQLException ex) {
            System.out.println("Nastala je greška: "+ex.getMessage());
        }
        return 0;
    }
     
     public String dajTip(){
    
         try {
              Baza DB = new Baza();
             PreparedStatement ps = DB.exec("SELECT * FROM korisnici WHERE korisnickoIme =? AND "
                + "lozinka=?");
             ps.setString(1, this.korisnickoIme.getValue());
             ps.setString(2, this.lozinka.getValue());
             ResultSet rs = ps.executeQuery();
             if (rs.next()) {
                 String tipKor=rs.getString("vrstaKorisnika");
                 return tipKor;
             }
         } catch (SQLException ex) {
             System.out.println("Nastala je greška dajTip: "+ex.getMessage());
         }
         return null;
     }
     
    
}
