
package uba.model;

import uba.baza.Baza;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Obavijesti implements  Model {
    
    private int IDobavijest;
    private String naslovOb;
    private String datumOb;
    private String vrijemeOb;
    private boolean procitano;
    private int FKKorisnik;
    

    public Obavijesti() {
        
        this.IDobavijest = 0;
        this.naslovOb = "";
        this.datumOb = "";
        this.vrijemeOb = "";
        this.procitano = false;
        this.FKKorisnik = 0;
        
    }

    public Obavijesti(int IDobavijest, String naslovOb, String datumOb, String vrijemeOb, boolean procitano, int FKKorisnik) {
        this.IDobavijest = IDobavijest;
        this.naslovOb = naslovOb;
        this.datumOb = datumOb;
        this.vrijemeOb = vrijemeOb;
        this.procitano = procitano;
        this.FKKorisnik = FKKorisnik;
       
    }

    public int getIDobavijest() {
        return IDobavijest;
    }

    public void setIDobavijest(int IDobavijest) {
        this.IDobavijest = IDobavijest;
    }

    public String getNaslovOb() {
        return naslovOb;
    }

    public void setNaslovOb(String naslovOb) {
        this.naslovOb = naslovOb;
    }

    public String getDatumOb() {
        return datumOb;
    }

    public void setDatumOb(String datumOb) {
        this.datumOb = datumOb;
    }

    public String getVrijemeOb() {
        return vrijemeOb;
    }

    public void setVrijemeOb(String vrijemeOb) {
        this.vrijemeOb = vrijemeOb;
    }
    
    public boolean isProcitano() {
        return procitano;
    }

    public void setProcitano(boolean procitano) {
        this.procitano = procitano;
    }
    

    public int getFKKorisnik() {
        return FKKorisnik;
    }

    public void setFKKorisnik(int FKKorisnik) {
        this.FKKorisnik = FKKorisnik;
    }

    
    @Override
    public void spasi() {
        //spajamo se na bazu
        Baza DB = new Baza();
        try {
            PreparedStatement insert = DB.exec(""
                    + "INSERT INTO obavijesti VALUES(null, ?, ?, ?,?, ?)");
            
            insert.setString(1, this.naslovOb);
            insert.setString(2, this.datumOb);
            insert.setString(3, this.vrijemeOb);
            insert.setBoolean(4, this.procitano);
            insert.setInt(5, this.FKKorisnik);
            
            
            
            //update koristimo kada azuriramo (insert, update, delete) podate, a execute select
            insert.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom ubacivanja u bazu: " + ex.getMessage());
        }
        
       
    }

    @Override
    public void uredi() {
        
        Baza DB = new Baza();
        String sql=
                "UPDATE obavijesti SET naziv=?, datumObavijesti=?, vrijemeObavijesti=?"
                + "procitano=?,fkKorisnik=?"
                + " WHERE IDobavijest=?";
        PreparedStatement ps = DB.exec(sql);
       try {
        ps.setString(1, this.naslovOb);
        ps.setString(2, this.datumOb);
        ps.setString(3, this.vrijemeOb);
        ps.setBoolean(4, this.procitano);
        ps.setInt(5, this.FKKorisnik);
        
        
        ps.setInt(6, IDobavijest);
        ps.executeUpdate();
        
      
        
        } 
        catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: " + ex.getMessage());
        }
    }
    
    @Override
    public void brisi() {
        try {
            
            Baza DB = new Baza();
            PreparedStatement delete=DB.exec("DELETE FROM obavijesti WHERE IDobavijest = ?");
            delete.setInt(1, this.IDobavijest);
            delete.executeUpdate();        
            
            
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
    }
    
}
