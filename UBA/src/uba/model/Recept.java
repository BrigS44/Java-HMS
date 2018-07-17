
package uba.model;

import uba.baza.Baza;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Recept implements Model{
    
    private int IDrecept;
    private String stanjeRec;
    private String naslovRec;
    private String opisRec;
    private String datumRec;
    private String vrijemeRec;
    private int FKFarmaceut;
    private int FKZakazano;

    public Recept() {
        
        this.IDrecept = 0;
        this.stanjeRec = "";
        this.naslovRec = "";
        this.opisRec = "";
        this.datumRec = "";
        this.vrijemeRec = "";
        this.FKFarmaceut = 0;
        this.FKZakazano = 0;
    }

    public Recept(int IDrecept, String stanjeRec, String naslovRec, String opisRec, String datumRec, String vrijemeRec, int FKFarmaceut, int FKZakazano) {
        this.IDrecept = IDrecept;
        this.stanjeRec = stanjeRec;
        this.naslovRec = naslovRec;
        this.opisRec = opisRec;
        this.datumRec = datumRec;
        this.vrijemeRec = vrijemeRec;
        this.FKFarmaceut = FKFarmaceut;
        this.FKZakazano = FKZakazano;
    }

    public int getIDrecept() {
        return IDrecept;
    }

    public void setIDrecept(int IDrecept) {
        this.IDrecept = IDrecept;
    }

    public String getStanjeRec() {
        return stanjeRec;
    }

    public void setStanjeRec(String stanjeRec) {
        this.stanjeRec = stanjeRec;
    }

    public String getNaslovRec() {
        return naslovRec;
    }

    public void setNaslovRec(String naslovRec) {
        this.naslovRec = naslovRec;
    }

    public String getOpisRec() {
        return opisRec;
    }

    public void setOpisRec(String opisRec) {
        this.opisRec = opisRec;
    }

    public String getDatumRec() {
        return datumRec;
    }

    public void setDatumRec(String datumRec) {
        this.datumRec = datumRec;
    }

    public String getVrijemeRec() {
        return vrijemeRec;
    }

    public void setVrijemeRec(String vrijemeRec) {
        this.vrijemeRec = vrijemeRec;
    }

    public int getFKFarmaceut() {
        return FKFarmaceut;
    }

    public void setFKFarmaceut(int FKFarmaceut) {
        this.FKFarmaceut = FKFarmaceut;
    }

    public int getFKZakazano() {
        return FKZakazano;
    }

    public void setFKZakazano(int FKZakazano) {
        this.FKZakazano = FKZakazano;
    }
    
    @Override
    public void spasi() {
        //spajamo se na bazu
        Baza DB = new Baza();
        
        try {
            PreparedStatement insert = DB.exec(""
                    + "INSERT INTO recept VALUES(null, ?, ?, ?,?, ?, ?, ?)");
            
            insert.setString(1, this.stanjeRec);
            insert.setString(2, this.naslovRec);
            insert.setString(3, this.opisRec);
            insert.setString(4, this.datumRec);
            insert.setString(5, this.vrijemeRec);
            insert.setInt(6, this.FKFarmaceut);
            insert.setInt(7, this.FKZakazano);
           
            
            
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
                "UPDATE recept SET stanje=?, naslov=?, opis=?"
                + "datum=?, vrijeme=?,fkFarmaceut=?, fkZakazano=?, "
                + " WHERE IDrecept=?";
        PreparedStatement ps = DB.exec(sql);
       try {
        ps.setString(1, this.stanjeRec);
        ps.setString(2, this.naslovRec);
        ps.setString(3, this.opisRec);
        ps.setString(4, this.datumRec);
        ps.setString(5, this.vrijemeRec);
        ps.setInt(6, this.FKFarmaceut);
        ps.setInt(7, this.FKZakazano);
       
       
        
        ps.setInt(8, IDrecept);
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
            
            PreparedStatement delete=DB.exec("DELETE FROM recept WHERE IDrecept = ?");
            delete.setInt(1, this.IDrecept);
            delete.executeUpdate();        
            
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
    }
    
    
    
}
