
package uba.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import uba.baza.Baza;

public class Osoblje extends Korisnici implements Model {
    
    
   
    private SimpleStringProperty strucnaSprema = new SimpleStringProperty();
    private SimpleIntegerProperty IDspecijalizacija = new SimpleIntegerProperty();
    private SimpleIntegerProperty IDodjel = new SimpleIntegerProperty();
    private SimpleStringProperty datumZaposlenja = new SimpleStringProperty();
    private SimpleFloatProperty placa = new SimpleFloatProperty();
    
    public Osoblje(){
        
        super();
        
    }
    
    public Osoblje(int IDkorisnik, String ime, String prezime){
    
      super(IDkorisnik,ime, prezime);
    }

    public Osoblje( int IDkorisnik, String ime, String prezime,String spol, 
    String telefon, String adresa, String email, String datumRodjenja, 
    String JMBG,String tipKor, String korisnickoIme, String lozinka,
    String strucnaSprema, int IDspecijalizacija,int IDodjel,String datumZaposlenja,float placa){
        
        super(IDkorisnik, ime, prezime,spol, telefon, adresa, email, datumRodjenja, JMBG,tipKor, korisnickoIme, lozinka);
        this.strucnaSprema = new SimpleStringProperty(strucnaSprema);
        this.IDspecijalizacija = new SimpleIntegerProperty(IDspecijalizacija);
        this.IDodjel =  new SimpleIntegerProperty(IDodjel);
        this.datumZaposlenja = new SimpleStringProperty(datumZaposlenja);
        this.placa = new SimpleFloatProperty(placa);
    }
    
    public Osoblje(String ime, String prezime,String telefon, 
    String adresa, String email,String JMBG,String korisnickoIme, 
    String lozinka,String strucnaSprema,float placa){
       
        super(ime, prezime,telefon, adresa, email,JMBG,korisnickoIme, lozinka);
        this.strucnaSprema=new SimpleStringProperty(strucnaSprema);
        this.placa = new SimpleFloatProperty(placa);
        
        
    }
    
    public Osoblje (float placa)
    {
        this.placa=new SimpleFloatProperty(placa);
    }
    public String getStrucnaSprema() {
        return strucnaSprema.get();
    }

    public void setStrucnaSprema(String strucnaSprema) {
        this.strucnaSprema.set(strucnaSprema);
    }

    public String getDatumZaposlenja() {
        return datumZaposlenja.get();
    }

    public void setDatumZaposlenja(String datumZaposlenja) {
        this.datumZaposlenja.set(datumZaposlenja);
    }

    public Integer getIDspecijalizacija() {
        return IDspecijalizacija.get();
    }

    public void setIDspecijalizacija(Integer IDspecijalizacija) {
        this.IDspecijalizacija.set(IDspecijalizacija);
    }

    public Integer getIDodjel() {
        return IDodjel.get();
    }

    public void setIDodjel(Integer IDodjel) {
        this.IDodjel.set(IDodjel);
    }
     public float getPlaca() {
        return placa.get();
    }

    public void setPlaca(float placa) {
        this.placa.set(placa);
    }

    

    public String dajOsobljeSpec(){
        
        ObservableList<VrstaSpecijalizacije> specijalizacija = VrstaSpecijalizacije.uzmiSveSpec();
        for(VrstaSpecijalizacije vs : specijalizacija){
            if(vs.getIDspecijalizacija()  == this.getIDspecijalizacija())
            { return vs.getNazivSpec();}   
        }
        return null;
    }
    
    
    public String dajOsobljeOdjel(){
        
        ObservableList<Odjel> odjel = Odjel.uzmiSveOdjele();
        for(Odjel o : odjel){
            if(o.getIDodjel() == this.getIDodjel())
            { return o.getNazivOdjel();}
        }
        return null;
    }
    
     
    @Override
    public void spasi() {
        //spajamo se na bazu
        Baza DB = new Baza();
        try {
            PreparedStatement insert = DB.exec(""
                    + "INSERT INTO korisnici VALUES(null, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?,?,?,?,?)");
            
        
        insert.setString(1, this.getIme());
        insert.setString(2, this.getPrezime());
        insert.setString(3, this.getSpol());
        insert.setString(4, this.getTelefon());
        insert.setString(5, this.getAdresa());
        insert.setString(6, this.getEmail());
        insert.setString(7, this.getDatumRodjenja());
        insert.setString(8, this.getJMBG());
        insert.setString(9, this.getTipKor());
        insert.setString(10,this.getKorisnickoIme());
        insert.setString(11,this.getLozinka());
        insert.setString(12,this.getStrucnaSprema());
        insert.setInt   (13,this.getIDspecijalizacija());
        insert.setInt   (14,this.getIDodjel());
        insert.setString(15,this.getDatumZaposlenja());
        insert.setFloat(16,this.getPlaca());
        
        insert.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom ubacivanja u bazu: " + ex.getMessage());
        }
 
    }

    @Override
    public void uredi() {
        
        Baza DB = new Baza();
        String sql =
                "UPDATE korisnici SET ime=?, prezime=?,spol=?,"
                + "telefon=?, adresa=?, email=?, datumRodjenja=?,"
                + "JMBG=?, vrstaKorisnika=?, korisnickoIme=?, lozinka=?, "
                + "strucnaSprema=?, IDspecijalizacija=?, IDodjel=?,datumUposlenja=?,placa=?"
                + " WHERE IDkorisnik=?";
        
        PreparedStatement ps = DB.exec(sql);
       try {
        ps.setString(1,this. getIme());
        ps.setString(2,this. getPrezime());
        ps.setString(3,this. getSpol());
        ps.setString(4,this.getTelefon());
        ps.setString(5,this.getAdresa());
        ps.setString(6,this. getEmail());
        ps.setString(7, this.getDatumRodjenja());
        ps.setString(8,this.getJMBG());
        ps.setString(9, this.getTipKor());
        ps.setString(10, this.getKorisnickoIme());
        ps.setString(11,this.getLozinka());
        ps.setString(12,this.getStrucnaSprema());
        ps.setInt   (13,this.getIDspecijalizacija());
        ps.setInt   (14,this.getIDodjel());
        ps.setString(15,this.getDatumZaposlenja());
        ps.setFloat(16,this.getPlaca());
        
        ps.setInt(17, this.getIDkorisnik());
        ps.executeUpdate();
        
        
        } 
        catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita update: " + ex.getMessage());
        }
    }
    
    @Override
    public void brisi() {
        try {
            
            Baza DB = new Baza();
            PreparedStatement delete =DB.exec("DELETE FROM korisnici WHERE IDkorisnik = ?");
            delete.setInt(1, this.getIDkorisnik());
            delete.executeUpdate();        
            
            
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita delete: "+ex.getMessage());
        }
    }
  
    public static ObservableList<Osoblje> uzmiSvoOsoblje(){
    
        ObservableList<Osoblje> osobljeLista =FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM korisnici");
        
        try {
            while ((rs.next())) {
                osobljeLista.add(new Osoblje(rs.getInt("IDkorisnik"), rs.getString("ime"), rs.getString("prezime"),
                        rs.getString("spol"), rs.getString("telefon"), rs.getString("adresa"),
                        rs.getString("email"), rs.getString("datumRodjenja"), rs.getString("JMBG"), rs.getString("vrstaKorisnika"),
                        rs.getString("korisnickoIme"), rs.getString("lozinka"),rs.getString("strucnaSprema"),
                        rs.getInt("IDspecijalizacija"), rs.getInt("IDodjel"), rs.getString("datumUposlenja"),rs.getFloat("placa")));

            }
        } catch (SQLException ex) {
            
            System.out.println("Nastala je greška: " + ex.getMessage());
        }
        
        return osobljeLista;
    }
    
     public static ObservableList<Osoblje> ImePrezimeOsoblje(){
    
        ObservableList<Osoblje> imeprezimeO = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT IDkorisnik,ime,prezime FROM korisnici");
    
        try {
            while (rs.next()) {
                
                imeprezimeO.add(new Osoblje(rs.getInt("IDkorisnik"),rs.getString("ime"),rs.getString("prezime")));
            }
            
        } catch (SQLException ex) {
            System.out.println("Nastala je greška ime prezime osoblje: " + ex.getMessage());
        }
        return imeprezimeO;
        }
     
    
    @Override
    public String toString(){
        
        return this.getIme()+" "+ this.getPrezime();
   
    }
    
    public static float prosjecnaPlacaOsoblje(){
        
        try {
            Baza DB = new Baza();
            PreparedStatement ps = DB.exec("SELECT AVG(placa) FROM korisnici");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (SQLException ex) {
        }
        return 0;
    
    }
}
