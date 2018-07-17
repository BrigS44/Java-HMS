/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uba.model;

import uba.baza.Baza;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class Test implements Model {
    
    private int IDtest;
    private String stanjeTest;
    private String naslovTest;
    private String datumTest;
    private String vrijemeTest;
    private String rezultat;
    private int FKLaborant;
    private int FKVrstaTesta;
    private int FKZakazano;
    

    public Test() {
        
        this.IDtest = 0;
        this.stanjeTest = "";
        this.naslovTest = "";
        this.datumTest = "";
        this.vrijemeTest = "";
        this.rezultat = "";
        this.FKLaborant = 0;
        this.FKVrstaTesta = 0;
        this.FKZakazano = 0;
        
    }

    public Test(int IDtest, String stanjeTest, String naslovTest, String datumTest, String vrijemeTest, String rezultat, int FKLaborant, int FKVrstaTesta, int FKZakazano) {
        this.IDtest = IDtest;
        this.stanjeTest = stanjeTest;
        this.naslovTest = naslovTest;
        this.datumTest = datumTest;
        this.vrijemeTest = vrijemeTest;
        this.rezultat = rezultat;
        this.FKLaborant = FKLaborant;
        this.FKVrstaTesta = FKVrstaTesta;
        this.FKZakazano = FKZakazano;
        
    }

    public int getIDtest() {
        return IDtest;
    }

    public void setIDtest(int IDtest) {
        this.IDtest = IDtest;
    }

    public String getStanjeTest() {
        return stanjeTest;
    }

    public void setStanjeTest(String stanjeTest) {
        this.stanjeTest = stanjeTest;
    }

    public String getNaslovTest() {
        return naslovTest;
    }

    public void setNaslovTest(String naslovTest) {
        this.naslovTest = naslovTest;
    }

    public String getDatumTest() {
        return datumTest;
    }

    public void setDatumTest(String datumTest) {
        this.datumTest = datumTest;
    }

    public String getVrijemeTest() {
        return vrijemeTest;
    }

    public void setVrijemeTest(String vrijemeTest) {
        this.vrijemeTest = vrijemeTest;
    }

    public String getRezultat() {
        return rezultat;
    }

    public void setRezultat(String rezultat) {
        this.rezultat = rezultat;
    }

    public int getFKLaborant() {
        return FKLaborant;
    }

    public void setFKLaborant(int FKLaborant) {
        this.FKLaborant = FKLaborant;
    }
    
    public int getFKVrstaTesta() {
        return FKVrstaTesta;
    }

    public void setFKVrstaTesta(int FKVrstaTesta) {
        this.FKVrstaTesta = FKVrstaTesta;
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
            
            insert.setString(1, this.stanjeTest);
            insert.setString(2, this.naslovTest);
            insert.setString(3, this.datumTest);
            insert.setString(4, this.vrijemeTest);
            insert.setString(5, this.rezultat);
            insert.setInt(6, this.FKLaborant);
            insert.setInt(7, this.FKVrstaTesta);
            insert.setInt(8, this.FKZakazano);
            
           
            
            
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
                "UPDATE test SET stanje=?, naslov=?"
                + "datum=?, vrijemeTesta=?,rezultat=?, fkLaborant=?,fkVrstaTestao=?, "
                + "fkZakazano"
                + " WHERE IDtest=?";
        PreparedStatement ps=DB.exec(sql);
       try {
        ps.setString(1, this.stanjeTest);
        ps.setString(2, this.naslovTest);
        ps.setString(3, this.datumTest);
        ps.setString(4, this.vrijemeTest);
        ps.setString(5, this.rezultat);
        ps.setInt(6, this.FKLaborant);
        ps.setInt(7, this.FKVrstaTesta);
        ps.setInt(8, this.FKZakazano);
        
       
       
        
        ps.setInt(9, IDtest);
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
            
            PreparedStatement delete=DB.exec("DELETE FROM test WHERE IDtest = ?");
            delete.setInt(1, this.IDtest);
            delete.executeUpdate();        
            
            
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
    }
    
    
}
