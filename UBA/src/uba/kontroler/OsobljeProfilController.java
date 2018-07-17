
package uba.kontroler;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class OsobljeProfilController implements Initializable {
    public static String datumR, datumU;
    @FXML
    private Button nazadBtn;
    @FXML
    Label idLbl,imeLbl,prezimeLbl,spolLbl,telefonLbl,adresaLbl,emailLbl,datRodjLbl,
          jmbgLbl,tipLbl,korImeLbl,lozinkaLbl,sssLbl,specLbl,odjelLbl,datumUpLbl,godLbl,rsLbl;
    
     public void PrikaziPodatkeOsoblje(String id,String ime,String prezime, String spol, String telefon, 
            String adresa,String email, String datum, String jmbg,String tip, 
            String korIme, String lozinka, String sss, String specijalizacija, 
            String odjel, String datumUp){
       
        idLbl.setText(id);
        imeLbl.setText(ime);
        prezimeLbl.setText(prezime);
        spolLbl.setText(spol);
        telefonLbl.setText(telefon);
        adresaLbl.setText(adresa);
        emailLbl.setText(email);
        datRodjLbl.setText(datum);
        jmbgLbl.setText(jmbg);
        tipLbl.setText(tip);
        korImeLbl.setText(korIme);
        lozinkaLbl.setText(lozinka);
        sssLbl.setText(sss);
        specLbl.setText(specijalizacija);
        odjelLbl.setText(odjel);
        datumUpLbl.setText(datumUp);
       
        
        datumR = datum;
        datumU = datumUp;
        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        
            Date rodjenje = null;
            Date uposlen = null;
        try {
            rodjenje = (Date) formater.parse(datumR);
            uposlen = (Date)formater.parse(datumU);
            
        } catch (ParseException parseException) {
        }
        
        int godine = racunajGodine(rodjenje);
        int radniStaz = racunajGodine(uposlen);
        
        godLbl.setText(String.valueOf(godine));
        rsLbl.setText(String.valueOf(radniStaz+" godina radnog staža."));
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    }    
    
    public void odbNazad(ActionEvent e){
        ((Node)(e.getSource())).getScene().getWindow().hide();   
    }
    
   
    
    private static int racunajGodine(Date rodjenje){
    
        Calendar rodjenje1 = Calendar.getInstance();
        rodjenje1.setTime(rodjenje);
        Calendar CurrentDate = Calendar.getInstance();
        CurrentDate.setTime(new Date());
        return CurrentDate.get(Calendar.YEAR) - rodjenje1.get(Calendar.YEAR);
    }
}
