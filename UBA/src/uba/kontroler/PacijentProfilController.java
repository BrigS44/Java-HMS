
package uba.kontroler;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import uba.model.Pregled;

public class PacijentProfilController implements Initializable {
    
    public static int idp;
    public static String datumR;
    @FXML
    private Button nazadBtn;
    @FXML
    Label idLbl,imeLbl,prezimeLbl,spolLbl,telefonLbl,adresaLbl,emailLbl,datRodjLbl,jmbgLbl,godineLbl;
    
    @FXML
    TableView<Pregled> pregledID;
    @FXML
    TableColumn<Pregled, Integer> idpactbl;
    @FXML
    TableColumn<Pregled, String> datumpTbl,dijagnozaTbl,opisdTbl,terapijaTbl;
     
    
    public void dajID(int id){
       
        idp = id;
     
    }
    public void PrikaziPodatkePacijent(String id,String ime,String prezime, String spol, String telefon, 
            String adresa,String email, String datum, String jmbg){
       
        idLbl.setText(id);
        imeLbl.setText(ime);
        prezimeLbl.setText(prezime);
        spolLbl.setText(spol);
        telefonLbl.setText(telefon);
        adresaLbl.setText(adresa);
        emailLbl.setText(email);
        datRodjLbl.setText(datum);
        
        datumR=datum;
        //System.out.println(datumR);
        jmbgLbl.setText(jmbg);
        
        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");;
        
        Date rodjenje = null;
        
        try {
            rodjenje = (Date) formater.parse(datumR);
            
        } catch (ParseException parseException) {
        }
        
        int currentAge = izracunajDob(rodjenje);
        godineLbl.setText(String.valueOf(currentAge));
        
    }
    
    public void PrikaziTablicu()
    {
        ObservableList<Pregled> data = Pregled.uzmiDijagnoze(idp);
         
        idpactbl.setCellValueFactory(new PropertyValueFactory<>("idPacijent"));
        dijagnozaTbl.setCellValueFactory(new PropertyValueFactory<>("dijagnoza"));
        opisdTbl.setCellValueFactory(new PropertyValueFactory<>("opisDijagnoze"));
        datumpTbl.setCellValueFactory(new PropertyValueFactory<>("datumPreg"));
        terapijaTbl.setCellValueFactory(new PropertyValueFactory<>("terapija"));
        
        pregledID.setItems(data);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }    
    
    public void odbNazad(ActionEvent e){
        ((Node)(e.getSource())).getScene().getWindow().hide();   
    }
    
    private static int izracunajDob(Date rodjenje){
    
        Calendar rodjenje1 = Calendar.getInstance();
        rodjenje1.setTime(rodjenje);
        Calendar CurrentDate = Calendar.getInstance();
        CurrentDate.setTime(new Date());
        return CurrentDate.get(Calendar.YEAR) - rodjenje1.get(Calendar.YEAR);
    }
}
