
package uba.model;

import uba.baza.Baza;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Pregled implements Model{
   
    /*public static final int OBAVLJENZT=1;
    public static final int IMEP=2;
    public static final int PREZIMEP=3;
    public static final int IMEL=4;
    public static final int PREZIMEL=5;*/
    
    private SimpleIntegerProperty IDpregled = new SimpleIntegerProperty();
    private SimpleStringProperty naslovPreg = new SimpleStringProperty();
    private SimpleStringProperty dijagnoza = new SimpleStringProperty();
    private SimpleStringProperty opisDijagnoze = new SimpleStringProperty();
    private SimpleStringProperty datumPreg = new SimpleStringProperty();
    private SimpleStringProperty vrijemePreg = new SimpleStringProperty();
    private SimpleIntegerProperty FKZakazano = new SimpleIntegerProperty();
    private SimpleStringProperty terapija = new SimpleStringProperty();
    private boolean obavljenZak ;
    private int idPacijent;
    private String imePacijent ;
    private String prezimePacijent;
    private String imeLijecnik;
    private String prezimeLijecnik;

    public Pregled() {
    }


   

    public Pregled(int IDpregled,String naslovPreg, String dijagnoza, String opisDijagnoze, String datumPreg, String vrijemePreg, int FKZakazano, String terapija) {
        this.IDpregled = new SimpleIntegerProperty(IDpregled);
        this.naslovPreg = new SimpleStringProperty(naslovPreg);
        this.dijagnoza = new SimpleStringProperty(dijagnoza);
        this.opisDijagnoze = new SimpleStringProperty(opisDijagnoze);
        this.datumPreg = new SimpleStringProperty(datumPreg);
        this.vrijemePreg = new SimpleStringProperty(vrijemePreg);
        this.FKZakazano = new SimpleIntegerProperty(FKZakazano);
        this.terapija = new SimpleStringProperty(terapija);
    }
    
    public Pregled(int IDpregled,String naslovPreg, String dijagnoza, String opisDijagnoze, String datumPreg, String vrijemePreg, int FKZakazano, String terapija,
    boolean obavljenZak,String imePacijent,String prezimePacijent,String imeLijecnik,String prezimeLijecnik){
    
        this.IDpregled = new SimpleIntegerProperty(IDpregled);
        this.naslovPreg = new SimpleStringProperty(naslovPreg);
        this.dijagnoza = new SimpleStringProperty(dijagnoza);
        this.opisDijagnoze = new SimpleStringProperty(opisDijagnoze);
        this.datumPreg = new SimpleStringProperty(datumPreg);
        this.vrijemePreg = new SimpleStringProperty(vrijemePreg);
        this.FKZakazano = new SimpleIntegerProperty(FKZakazano);
        this.terapija = new SimpleStringProperty(terapija);
        this.obavljenZak= obavljenZak;
        this.imePacijent = imePacijent;
        this.prezimePacijent = prezimePacijent;
        this.imeLijecnik =imeLijecnik;
        this.prezimeLijecnik = prezimeLijecnik;
   
    }
    
    public Pregled(String naslovPreg, String dijagnoza, String opisDijagnoze,String vrijemePreg,String terapija){
    
        this.naslovPreg = new SimpleStringProperty(naslovPreg);
        this.dijagnoza = new SimpleStringProperty(dijagnoza);
        this.opisDijagnoze = new SimpleStringProperty(opisDijagnoze);
        this.vrijemePreg = new SimpleStringProperty(vrijemePreg);
        this.terapija = new SimpleStringProperty(terapija);
    }
    public Pregled(String dijagnoza, String opisDijagnoze,String datumPreg,String terapija, int idPacijent){
    
        this.dijagnoza = new SimpleStringProperty(dijagnoza);
        this.dijagnoza = new SimpleStringProperty(dijagnoza);
        this.opisDijagnoze = new SimpleStringProperty(opisDijagnoze);
        this.datumPreg = new SimpleStringProperty(datumPreg);
        this.terapija = new SimpleStringProperty(terapija);
        this.idPacijent = idPacijent;
    }

    public Integer getIDpregled() {
        return IDpregled.get();
    }

    public void setIDpregled(int IDpregled) {
        this.IDpregled.set(IDpregled);
    }

   

    public String getNaslovPreg() {
        return naslovPreg.get();
    }

    public void setNaslovPreg(String naslovPreg) {
        this.naslovPreg.set(naslovPreg);
    }

    public String getDijagnoza() {
        return dijagnoza.get();
    }

    public void setDijagnoza(String dijagnoza) {
        this.dijagnoza.set(dijagnoza);
    }

    public String getOpisDijagnoze() {
        return opisDijagnoze.get();
    }

    public void setOpisDijagnoze(String opisDijagnoze) {
        this.opisDijagnoze.set(opisDijagnoze);
    }

    public String getDatumPreg() {
        return datumPreg.get();
    }

    public void setDatumPreg(String datumPreg) {
        this.datumPreg.set(datumPreg);
    }

    public String getVrijemePreg() {
        return vrijemePreg.get();
    }

    public void setVrijemePreg(String vrijemePreg) {
        this.vrijemePreg.set(vrijemePreg);
    }

    public int getFKZakazano() {
        return FKZakazano.get();
    }

    public void setFKZakazano(int FKZakazano) {
        this.FKZakazano.set(FKZakazano);
    }

    public String getTerapija() {
        return terapija.get();
    }

    public void setTerapija(String terapija) {
        this.terapija.set(terapija);
    }
    
    public String getImePacijent() {
        return imePacijent;
    }

    public void setImePacijent(String imePacijent) {
        this.imePacijent=imePacijent;
    }

    public String getPrezimePacijent() {
        return prezimePacijent;
    }

    public void setPrezimePacijent(String prezimePacijent) {
        this.prezimePacijent=prezimePacijent;
    }

    public String getImeLijecnik() {
        return imeLijecnik;
    }

    public void setImeLijecnik(String imeLijecnik) {
        this.imeLijecnik=imeLijecnik;
    }

    public String getPrezimeLijecnik() {
        return prezimeLijecnik;
    }

    public void setPrezimeLijecnik(String prezimeLijecnik) {
        this.prezimeLijecnik=prezimeLijecnik;
    }
    
    public String getObavljenZak() {
        if(this.obavljenZak == true)
            return "Obavljeno";
        else
            return "Na čekanju";
    }

    public void setObavljenZak(boolean obavljenZak) {
        this.obavljenZak=obavljenZak;
    }

    public int getIdPacijent() {
        return idPacijent;
    }

    public void setIdPacijent(int idPacijent) {
        this.idPacijent = idPacijent;
    }
    
    
     public String dajZakazaniTerminPregled(){
        
        ObservableList<ZakazaniTermin> termin = ZakazaniTermin.uzmiSveZakazaneTermine();
        for(ZakazaniTermin zt : termin){
            if(zt.getIDzakazano()  == this.getFKZakazano())
            { return zt.getVrstaPregleda()+ "  " + zt.getDatumZak() + "  " + zt.getVremenskiTermin();}   
        }
        return null;
    }
    
    @Override
    public void spasi() {
        //spajamo se na bazu
        Baza DB = new Baza();
        
        try {
            PreparedStatement insert = DB.exec(""
                    + "INSERT INTO pregled VALUES(null, ?, ?, ?,?, ?, ?, ?)");
            
           
            insert.setString(1, this.naslovPreg.getValue());
            insert.setString(2, this.dijagnoza.getValue());
            insert.setString(3, this.opisDijagnoze.getValue());
            insert.setString(4, this.datumPreg.getValue());
            insert.setString(5, this.vrijemePreg.getValue());
            insert.setInt(6, this.FKZakazano.getValue());
            insert.setString(7, this.terapija.getValue());
           
            
            //update koristimo kada azuriramo (insert, update, delete) podate, a execute select
            insert.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom ubacivanja u bazu pregled : " + ex.getMessage());
        }
        
        
    }

    @Override
    public void uredi() {
        
        Baza DB = new Baza();
         String sql=
                "UPDATE pregled SET naslov=?,  dijagnoza=?,"
                + "opisDijagnoze=?, datumPregleda=?,vrijemePregleda=?, IDzakazano=?, "
                + "terapija=?"
                + " WHERE IDpregled=?";
         
        PreparedStatement ps = DB.exec(sql);
       try {
       
        ps.setString(1, this.naslovPreg.getValue());
        ps.setString(2, this.dijagnoza.getValue());
        ps.setString(3, this.opisDijagnoze.getValue());
        ps.setString(4, this.datumPreg.getValue());
        ps.setString(5, this.vrijemePreg.getValue());
        ps.setInt(6, this.FKZakazano.getValue());
        ps.setString(7,this.terapija.getValue());
        
       
        
        ps.setInt(8, this.IDpregled.getValue());
        ps.executeUpdate();
        
       
        
        } 
        catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita update pregled: " + ex.getMessage());
        }
    }
    
    @Override
    public void brisi() {
        try {
            Baza DB = new Baza();
            
            PreparedStatement delete=DB.exec("DELETE FROM pregled WHERE IDpregled = ?");
            delete.setInt(1, this.IDpregled.getValue());
            delete.executeUpdate();        
            
            
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
    }
    
     public static ObservableList<Pregled> uzmiSvePreglede(){
    
        ObservableList<Pregled> pregledLista =FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT pregled.*, zakazani_termin.obavljeno,pacijent.ime, pacijent.prezime, korisnici.ime, korisnici.prezime \n" +
                        "FROM pregled \n" +
                        "LEFT JOIN zakazani_termin ON pregled.IDzakazano = zakazani_termin.IDzakazano \n" +
                        "LEFT JOIN pacijent ON zakazani_termin.IDpacijent = pacijent.IDpacijent\n" +
                        "LEFT JOIN korisnici ON zakazani_termin.IDlijecnik = korisnici.IDkorisnik");
        
        try {
            while ((rs.next())) {
                pregledLista.add(new Pregled(rs.getInt("IDpregled"), rs.getString("naslov"), 
                        rs.getString("dijagnoza"), rs.getString("opisDijagnoze"), rs.getString("datumPregleda"),
                        rs.getString("vrijemePregleda"),rs.getInt("IDzakazano"),rs.getString("terapija"),
                        rs.getBoolean("zakazani_termin.obavljeno"),rs.getString("pacijent.ime"),rs.getString("pacijent.prezime"), 
                        rs.getString("korisnici.ime"), rs.getString("korisnici.prezime")));

            }
        } catch (SQLException ex) {
            
            System.out.println("Nastala je greška: " + ex.getMessage());
        }
        
        return pregledLista;
    }
     
    public static ObservableList<Pregled> uzmiDijagnoze(int id){
    
        ObservableList<Pregled> pregledLista =FXCollections.observableArrayList();
        try {
        Baza DB = new Baza();
        PreparedStatement st= DB.exec("SELECT pregled.dijagnoza, pregled.opisDijagnoze, pregled.datumPregleda, pregled.terapija,pacijent.IDpacijent FROM pregled \n" +
        "LEFT JOIN zakazani_termin ON pregled.IDzakazano = zakazani_termin.IDzakazano \n" +
        "LEFT JOIN pacijent ON zakazani_termin.IDpacijent = pacijent.IDpacijent \n" +
        " WHERE pacijent.IDpacijent= ?");
        st.setInt(1,id);
        
        ResultSet rs = st.executeQuery();
      
            while ((rs.next())) {
                pregledLista.add(new Pregled( rs.getString("dijagnoza"), rs.getString("opisDijagnoze"), rs.getString("datumPregleda"),
                        rs.getString("terapija"), rs.getInt("pacijent.IDpacijent")));

            }
        } catch (SQLException ex) {
            
            System.out.println("Nastala je greška: " + ex.getMessage());
        }
        
        return pregledLista;
    }
    
     
    
    
    
}
