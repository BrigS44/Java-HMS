
package uba.model;

import uba.baza.Baza;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class VrstaTestova implements Model{
    
    private int IDvrstatesta;
    private String nazivVrsteTesta;

    public VrstaTestova() {
        
        this.IDvrstatesta = 0;
        this.nazivVrsteTesta = "";
    }

    public VrstaTestova(int IDvrstatesta, String nazivVrsteTesta) {
        this.IDvrstatesta = IDvrstatesta;
        this.nazivVrsteTesta = nazivVrsteTesta;
    }

    public int getIDvrstatesta() {
        return IDvrstatesta;
    }

    public void setIDvrstatesta(int IDvrstatesta) {
        this.IDvrstatesta = IDvrstatesta;
    }

    public String getNazivVrsteTesta() {
        return nazivVrsteTesta;
    }

    public void setNazivVrsteTesta(String nazivVrsteTesta) {
        this.nazivVrsteTesta = nazivVrsteTesta;
    }
    
     @Override
    public void spasi() {
        //spajamo se na bazu
        
        Baza DB = new Baza();
        try {
            PreparedStatement insert = DB.exec(""
                    + "INSERT INTO vrsta_testova VALUES(null, ?)");
            
            insert.setString(1, this.nazivVrsteTesta);
            
            
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
                "UPDATE vrsta_testova SET naziv=?"
                + " WHERE IDvrstetesta=?";
       
       PreparedStatement ps = DB.exec(sql);
       try {
        ps.setString(1, this.nazivVrsteTesta);
        
        ps.setInt(2, IDvrstatesta);
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
            
            PreparedStatement delete=DB.exec("DELETE FROM vrsta_testova WHERE IDvrstatesta = ?");
            delete.setInt(1, this.IDvrstatesta);
            delete.executeUpdate();        
            
           
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
    }
    
}
