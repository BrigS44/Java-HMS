package uba.model;

import uba.baza.Baza;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ZakazaniTermin  implements Model{
    
    private SimpleIntegerProperty IDzakazano = new SimpleIntegerProperty();
    private SimpleStringProperty naslovZak = new SimpleStringProperty();
    private SimpleStringProperty vrstaPregleda = new SimpleStringProperty();
    private SimpleStringProperty datumZak = new SimpleStringProperty();
    private SimpleStringProperty vremenskiTermin = new SimpleStringProperty();
    
    private SimpleIntegerProperty FKLijecnik = new SimpleIntegerProperty();
    private SimpleIntegerProperty FKPacijent = new SimpleIntegerProperty();
    private SimpleBooleanProperty obavljeno = new SimpleBooleanProperty();
    private String imeP;
    private String prezimeP;
    private String imeL;
    private String prezimeL;

    

    public ZakazaniTermin() {
        
    }

    public ZakazaniTermin(int IDzakazano, String naslovZak, String vrstaPregleda, String datumZak, String vremenskiTermin,int FKLijecnik, int FKPacijent, boolean obavljeno) {
        
        this.IDzakazano = new SimpleIntegerProperty(IDzakazano);
        this.naslovZak = new SimpleStringProperty(naslovZak);
        this.vrstaPregleda = new SimpleStringProperty(vrstaPregleda);
        this.datumZak = new SimpleStringProperty(datumZak);
        this.vremenskiTermin = new SimpleStringProperty(vremenskiTermin);
     
        this.FKLijecnik =new SimpleIntegerProperty(FKLijecnik);
        this.FKPacijent =new SimpleIntegerProperty(FKPacijent);
        this.obavljeno = new SimpleBooleanProperty(obavljeno);
    }
    
    public ZakazaniTermin( String naslovZak, String vremenskiTermin){
        
       
        this.naslovZak = new SimpleStringProperty(naslovZak);
        this.vremenskiTermin = new SimpleStringProperty(vremenskiTermin);
    }
    public ZakazaniTermin (int IDzakazano,String vrstaPregleda,String datumZak, String vremenskiTermin){
    
        this.IDzakazano = new SimpleIntegerProperty(IDzakazano);
        this.vrstaPregleda = new SimpleStringProperty(vrstaPregleda);
        this.datumZak = new SimpleStringProperty(datumZak);
        this.vremenskiTermin = new SimpleStringProperty(vremenskiTermin);
    
    }
    public ZakazaniTermin(int IDzakazano,boolean obavljeno,String imeP,String prezimeP, String imeL,String prezimeL){
        
        this.IDzakazano = new SimpleIntegerProperty(IDzakazano);
        this.obavljeno = new SimpleBooleanProperty(obavljeno);
        this.imeP = imeP;
        this.prezimeP = prezimeP;
        this.imeL =imeL;
        this.prezimeL = prezimeL;
    }
    
    public ZakazaniTermin(String naslovZak){
    
        this.naslovZak = new SimpleStringProperty (naslovZak);
    }

    public int getIDzakazano() {
        return IDzakazano.get();
    }

    public void setIDzakazano(int IDzakazano) {
        this.IDzakazano.set(IDzakazano);
    }

    public String getNaslovZak() {
        return naslovZak.get();
    }

    public void setNaslovZak(String naslovZak) {
        this.naslovZak.set(naslovZak);
    }

    public String getVrstaPregleda() {
        return vrstaPregleda.get();
    }

    public void setVrstaPregleda(String vrstaPregleda) {
        this.vrstaPregleda.set(vrstaPregleda);
    }

    public String getDatumZak() {
        return datumZak.get();
    }

    public void setDatumZak(String datumZak) {
        this.datumZak.set(datumZak);
    }

    public String getVremenskiTermin() {
        return vremenskiTermin.get();
    }

    public void setVremenskiTermin(String vremenskiTermin) {
        this.vremenskiTermin.set(vremenskiTermin);
    }

    public int getFKLijecnik() {
        return FKLijecnik.get();
    }

    public void setFKLijecnik(int FKLijecnik) {
        this.FKLijecnik.set(FKLijecnik);
    }

    public int getFKPacijent() {
        return FKPacijent.get();
    }

    public void setFKPacijent(int FKPacijent) {
        this.FKPacijent.set(FKPacijent);
    }
    
    public String getObavljeno() {
        if(obavljeno.get() == true)
            return "Obavljeno";
        else
            return "Na čekanju";
    }

    public void setObavljeno(String obavljeno) {
        if(obavljeno.equals("Obavljeno"))
            this.obavljeno.set(true);
        else
            this.obavljeno.set(false);
    }
   
    public boolean getObavljeno1() {
        return obavljeno.get();
    }

    public void setObavljeno1(boolean obavljeno) {
        this.obavljeno.set(obavljeno);
    }
    
    public String getImeP() {
        return imeP;
    }

    public void setImeP(String imeP) {
        this.imeP = imeP;
    }

    public String getPrezimeP() {
        return prezimeP;
    }

    public void setPrezimeP(String prezimeP) {
        this.prezimeP = prezimeP;
    }

    public String getImeL() {
        return imeL;
    }

    public void setImeL(String imeL) {
        this.imeL = imeL;
    }

    public String getPrezimeL() {
        return prezimeL;
    }

    public void setPrezimeL(String prezimeL) {
        this.prezimeL = prezimeL;
    }
   
    
    public String dajZakazaniTerPacijent(){
        
        ObservableList<Pacijent> pacijent = Pacijent.uzmiSvePacijente();
        for(Pacijent p : pacijent){
            if(p.getIDpacijent()  == this.getFKPacijent())
            { return p.getImePac()+" "+ p.getPrezimePac();}   
        }
        return null;
    }
    
    public String dajLijecnikZakazano(){
        
        ObservableList<Osoblje> osoblje = Osoblje.uzmiSvoOsoblje();
        for(Osoblje o : osoblje){
            if(o.getIDkorisnik()  == this.getFKLijecnik())
            { return o.getIme()+" "+o.getPrezime();}   
        }
        return null;
    }
    
    @Override
    public void spasi() {
        //spajamo se na bazu
        Baza DB = new Baza();
        
        try {
            PreparedStatement insert = DB.exec(""
                    + "INSERT INTO zakazani_termin VALUES(null, ?, ?, ?,?, ?, ?, ?)");
            
            insert.setString(1, this.naslovZak.getValue());
            insert.setString(2, this.vrstaPregleda.getValue());
            insert.setString(3, this.datumZak.getValue());
            insert.setString(4, this.vremenskiTermin.getValue());
            insert.setInt(5, this.FKPacijent.getValue());
            insert.setInt(6, this.FKLijecnik.getValue());
            insert.setBoolean(7, this.obavljeno.getValue());
           
            
            //update koristimo kada azuriramo (insert, update, delete) podate, a execute select
            insert.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom ubacivanja u bazu zt: " + ex.getMessage());
        }
        
       
    }
    
    public void urediObavljeno(){
        
        Baza DB = new Baza();
       String sql =
                "UPDATE zakazani_termin SET obavljeno=? "
                + " WHERE IDzakazano=?";
        PreparedStatement ps = DB.exec(sql);
        try {
        
        ps.setBoolean(1, this.obavljeno.getValue());
        
       
        
        ps.setInt(2, this.IDzakazano.getValue());
        ps.executeUpdate();
        
       
        
        } 
        catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: " + ex.getMessage());
        }
    
    }
    

    @Override
    public void uredi() {
       
       Baza DB = new Baza();
       String sql =
                "UPDATE zakazani_termin SET naslov=?, vrstaPregleda=?, datum=?,"
                + "vremenskiTermin=?,IDPacijent=?,IDLijecnik=?,obavljeno=? "
                + " WHERE IDzakazano=?";
        PreparedStatement ps = DB.exec(sql);
        try {
        ps.setString(1, this.naslovZak.getValue());
        ps.setString(2, this.vrstaPregleda.getValue());
        ps.setString(3, this.datumZak.getValue());
        ps.setString(4, this.vremenskiTermin.getValue());
        ps.setInt(5, this.FKPacijent.getValue());
        ps.setInt(6, this.FKLijecnik.getValue());
        ps.setBoolean(7, this.obavljeno.getValue());
        
       
        
        ps.setInt(8, this.IDzakazano.getValue());
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
            
            PreparedStatement delete=DB.exec("DELETE FROM zakazani_termin WHERE IDzakazano = ?");
            delete.setInt(1, this.IDzakazano.getValue());
            delete.executeUpdate();        
            
            
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
    }
    
    public static ObservableList<ZakazaniTermin> uzmiSveZakazaneTermine(){
    
        ObservableList<ZakazaniTermin> ztLista =FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM zakazani_termin");
        
        try {
            while ((rs.next())) {
                ztLista.add(new ZakazaniTermin(rs.getInt("IDzakazano"), rs.getString("naslov"), 
                        rs.getString("vrstaPregleda"), rs.getString("datum"), rs.getString("vremenskiTermin"),
                        rs.getInt("IDlijecnik"),rs.getInt("IDpacijent"),rs.getBoolean("obavljeno")));

            }
        } catch (SQLException ex) {
            
            System.out.println("Nastala je greška podaci iz zakazanih: " + ex.getMessage());
        }
        
        return ztLista;
    }
    
    public static ObservableList<ZakazaniTermin> uzmiZakazaniTerminPodaci(){
    
        ObservableList<ZakazaniTermin> zakNaziv = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT IDzakazano,vrstaPregleda,datum,vremenskiTermin FROM zakazani_termin");
    
        try {
            while (rs.next()) {
                
                zakNaziv.add(new ZakazaniTermin(rs.getInt("IDzakazano"),rs.getString("vrstaPregleda"), rs.getString("datum"),rs.getString("vremenskiTermin")));
            }
            
        } catch (SQLException ex) {
            System.out.println("Nastala je greška: " + ex.getMessage());
        }
        return zakNaziv;
        }
    
    @Override
    public String toString(){
         return this.vrstaPregleda.getValue() + "  " + this.datumZak.getValue() + "  " + this.vremenskiTermin.getValue();
         
    }
    
    
    
    public static ObservableList<ZakazaniTermin> uzmiPodatkeOPaciLijec(){
    ObservableList<ZakazaniTermin> data = FXCollections.observableArrayList();
    Baza DB = new Baza();
    ResultSet rs = DB.select("SELECT zakazani_termin.IDzakazano,zakazani_termin.obavljeno,pacijent.ime, pacijent.prezime,korisnici.ime, korisnici.prezime \n" +
                             "FROM zakazani_termin \n" +
                             "LEFT JOIN pacijent ON zakazani_termin.IDpacijent = pacijent.IDpacijent \n" +
                             "LEFT JOIN korisnici ON zakazani_termin.IDlijecnik = korisnici.IDkorisnik ");
    
        try {
            while (rs.next()) {

                data.add(new ZakazaniTermin(rs.getInt("IDzakazano"),rs.getBoolean("obavljeno"), rs.getString("pacijent.ime"), rs.getString("pacijent.prezime"),
                rs.getString("korisnici.ime"),rs.getString("korisnici.prezime")
            
         ));
    }
    } catch (SQLException ex) {
        System.out.println("Nastala je greška: " + ex.getMessage());
    }
        return data;
    }
    
    public static ObservableList<ZakazaniTermin> uzmiTermineLijecnik(int id){
        
    ObservableList<ZakazaniTermin> data = FXCollections.observableArrayList();
    
    
    try {
    Baza DB = new Baza();
    PreparedStatement st= DB.exec("SELECT * FROM zakazani_termin WHERE IDlijecnik =?");
    st.setInt(1,id);
    ResultSet rs = st.executeQuery();
        
            
            while (rs.next()) {

                data.add(new ZakazaniTermin(rs.getInt("IDzakazano"), rs.getString("naslov"), 
                        rs.getString("vrstaPregleda"), rs.getString("datum"), rs.getString("vremenskiTermin"),
                        rs.getInt("IDlijecnik"),rs.getInt("IDpacijent"),rs.getBoolean("obavljeno")
            
         ));
    }
    } catch (SQLException ex) {
        System.out.println("Nastala je greška lijecnik termin: " + ex.getMessage());
    }
        return data;
    }
    
    public static boolean jeLiZakazan(String datum, String vrijeme, int IDlijecnik){
        
    int broj = -1;
    
    try {
    Baza DB = new Baza();
    PreparedStatement st= DB.exec("SELECT COUNT(*) AS broj FROM `zakazani_termin` WHERE `datum` = ? AND `IDlijecnik` = ? AND `vremenskiTermin` BETWEEN ? AND ADDTIME(?, '00:15:00')");
    
    st.setString(1, datum);
    st.setInt(2,IDlijecnik);
    st.setString(3,vrijeme);
    st.setString(4,vrijeme);
    
    ResultSet rs = st.executeQuery();
    
    rs.next();
    broj = rs.getInt("broj");

    } catch (SQLException ex) {
        System.out.println("Nastala je greška lijecnik termin: " + ex.getMessage());
    }
        if(broj>0)
            return true;
        else
            return false;
    }
    
}
