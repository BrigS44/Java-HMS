package uba.model;


import uba.baza.Baza;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;





public class Pacijent implements Model {
    
    
    
    private SimpleIntegerProperty IDpacijent = new SimpleIntegerProperty();
    private SimpleStringProperty imePac = new SimpleStringProperty();
    private SimpleStringProperty prezimePac = new SimpleStringProperty();
    private SimpleStringProperty spolPac = new SimpleStringProperty();
    private SimpleStringProperty telefonPac = new SimpleStringProperty();
    private SimpleStringProperty adresaPac = new SimpleStringProperty();
    private SimpleStringProperty emailPac = new SimpleStringProperty();
    private SimpleStringProperty datumRodjenja = new SimpleStringProperty();
    private SimpleStringProperty JMBGPac = new SimpleStringProperty();
    
    

    public Pacijent() {
        
        
    }

    public Pacijent(int IDpacijent, String imePac, String prezimePac, String spolPac, String telefonPac, String adresaPac, String emailPac, String datumRodjenja, String JMBGPac) {
        
        this.IDpacijent = new SimpleIntegerProperty (IDpacijent);
        this.imePac =  new SimpleStringProperty(imePac);
        this.prezimePac = new SimpleStringProperty(prezimePac);
        this.spolPac = new SimpleStringProperty(spolPac);
        this.telefonPac = new SimpleStringProperty(telefonPac);
        this.adresaPac = new SimpleStringProperty(adresaPac);
        this.emailPac = new SimpleStringProperty(emailPac);
        this.datumRodjenja = new SimpleStringProperty(datumRodjenja);
        this.JMBGPac = new SimpleStringProperty(JMBGPac);
    }
    
    public Pacijent(String imePac, String prezimePac, String spolPac, String telefonPac, String adresaPac, String emailPac, String datumRodjenja, String JMBGPac){
    
        this.imePac =  new SimpleStringProperty(imePac);
        this.prezimePac = new SimpleStringProperty(prezimePac);
        this.spolPac = new SimpleStringProperty( spolPac);
        this.telefonPac = new SimpleStringProperty(telefonPac);
        this.adresaPac = new SimpleStringProperty(adresaPac);
        this.emailPac = new SimpleStringProperty(emailPac);
        this.datumRodjenja = new SimpleStringProperty(datumRodjenja);
        this.JMBGPac = new SimpleStringProperty(JMBGPac);
    }

    public Pacijent(String imePac, String prezimePac,String telefonPac, String adresaPac, String emailPac,String JMBGPac){
    
        this.imePac =  new SimpleStringProperty(imePac);
        this.prezimePac = new SimpleStringProperty(prezimePac);
        this.telefonPac = new SimpleStringProperty(telefonPac);
        this.adresaPac = new SimpleStringProperty(adresaPac);
        this.emailPac = new SimpleStringProperty(emailPac);
        this.JMBGPac = new SimpleStringProperty(JMBGPac);
    }
    
    public Pacijent(int IDpacijent,String imePac, String prezimePac){
    
        this.IDpacijent= new SimpleIntegerProperty (IDpacijent);
        this.imePac =  new SimpleStringProperty(imePac);
        this.prezimePac = new SimpleStringProperty(prezimePac);
    }
    
    public Integer getIDpacijent() {
        return IDpacijent.get();
    }

    public void setIDpacijent(Integer IDpacijent) {
        this.IDpacijent.set(IDpacijent);
    }

    public String getImePac() {
        return imePac.get();
    }

    public void setImePac(String imePac) {
        this.imePac.set(imePac);
    }

    public String getPrezimePac() {
        return prezimePac.get();
    }

    public void setPrezimePac(String prezimePac) {
        this.prezimePac.set(prezimePac);
    }

    public String getSpolPac() {
        return spolPac.get();
    }

    public void setSpolPac(String spolPac) {
        this.spolPac .set(spolPac);
    }
    
    public String getTelefonPac() {
        return telefonPac.get();
    }

    public void setTelefonPac(String telefonPac) {
        this.telefonPac.set(telefonPac);
    }

    public String getAdresaPac() {
        return adresaPac.get();
    }

    public void setAdresaPac(String adresaPac) {
        this.adresaPac.set(adresaPac);
    }

    public String getEmailPac() {
        return emailPac.get();
    }

    public void setEmailPac(String emailPac) {
        this.emailPac .set(emailPac);
    }

    public String getDatumRodjenja() {
        return datumRodjenja.get();
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja.set(datumRodjenja);
    }
    
    public StringProperty datumRodjenjaProperty(){
    
        return datumRodjenja;
    }

    public String getJMBGPac() {
        return JMBGPac.get();
    }

    public void setJMBGPac(String JMBGPac) {
        this.JMBGPac.set(JMBGPac);
    }

    @Override
    public void spasi() {
        //spajamo se na bazu
        Baza DB = new Baza();
        
        try {
            PreparedStatement insert = DB.exec(""
                    + "INSERT INTO pacijent VALUES(null, ?, ?, ?,?, ?, ?, ?,?)");
            
            insert.setString(1, this.imePac.getValue());
            insert.setString(2, this.prezimePac.getValue());
            insert.setString(3, this.spolPac.getValue());
            insert.setString(4, this.telefonPac.getValue());
            insert.setString(5, this.adresaPac.getValue());
            insert.setString(6, this.emailPac.getValue());
            insert.setString(7, this.datumRodjenja.getValue());
            insert.setString(8, this.JMBGPac.getValue());
            
            
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
                "UPDATE pacijent SET ime=?, prezime=?, spol=?,"
                + "telefon=?, adresa=?,email=?, datumRodjenja=?,"
                + "JMBG=?"
                + "WHERE IDpacijent=?";
        PreparedStatement ps = DB.exec(sql);
       try {
        ps.setString(1,this.imePac.getValue());
        ps.setString(2,this.prezimePac.getValue());
        ps.setString(3,this.spolPac.getValue());
        ps.setString(4,this.telefonPac.getValue());
        ps.setString(5,this.adresaPac.getValue());
        ps.setString(6,this.emailPac.getValue());
        ps.setString(7,this.datumRodjenja.getValue());
        ps.setString(8,this.JMBGPac.getValue());
       
        
        ps.setInt(9, this.IDpacijent.getValue());
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
            
            PreparedStatement delete=DB.exec("DELETE FROM pacijent WHERE IDpacijent = ?");
            delete.setInt(1, this.IDpacijent.getValue());
            delete.executeUpdate();        
            
            
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
    }
    
     public static ObservableList<Pacijent> uzmiSvePacijente(){
    
        ObservableList<Pacijent> pacijentiLista = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM pacijent");
        
        try{
            
            while(rs.next()){
                
                pacijentiLista.add(new Pacijent(rs.getInt("IDpacijent"),rs.getString("ime"),rs.getString("prezime"),
                        rs.getString("spol"),rs.getString("telefon"),rs.getString("adresa"),
                        rs.getString("email"),rs.getString("datumRodjenja"),rs.getString("JMBG")));
                
            }
        
        }catch(SQLException ex){
        
            System.out.println("Nastala je greška: " + ex.getMessage());
        }
        
        return pacijentiLista;
        
    }
     
   /* public static Pacijent dajPacijent(int ID){
        
        Pacijent p = null;
        
        Baza DB = new Baza();
        
        try {
            
            PreparedStatement st  = DB.exec("SELECT * FROM pacijent WHERE IDpacijent = ?");
            st.setInt(1,ID);
            
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
            
                p= new Pacijent(rs.getInt("IDpacijent"),rs.getString("ime"),rs.getString("prezime"),
                        rs.getString("spol"),rs.getString("telefon"),rs.getString("adresa"),
                        rs.getString("email"),rs.getString("datumRodjenja"),rs.getString("JMBG"));
            }
                       
            
        } catch (SQLException sQLException) {
        }
        return p;
    }*/
    
    public static ObservableList<Pacijent> ImePrezimePacijent(){
    
        ObservableList<Pacijent> imeprezimeP = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT  IDpacijent,ime,prezime FROM pacijent");
    
        try {
            while (rs.next()) {
                
                imeprezimeP.add(new Pacijent(rs.getInt("IDpacijent"),rs.getString("ime"), rs.getString("prezime")));
            }
            
        } catch (SQLException ex) {
            System.out.println("Nastala je greška ime prezime pacijent: " + ex.getMessage());
        }
        return imeprezimeP;
        }
     
     @Override
    public String toString(){
        
        return this.imePac.getValue()+" "+ this.prezimePac.getValue();
   
    }
    
   
  
}
