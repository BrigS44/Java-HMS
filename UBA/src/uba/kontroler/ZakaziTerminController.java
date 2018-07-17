
package uba.kontroler;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import uba.model.Osoblje;
import uba.model.Pacijent;
import uba.model.ZakazaniTermin;


public class ZakaziTerminController implements Initializable {

    @FXML
    Button nazadBtn,izvrsiDodajBtn;
    
    @FXML
    TextField vrstaTF,nazivTF,obavljenoTF,vrijemeTF;
    
    @FXML
    ComboBox <Osoblje> lijecnikCB;
    @FXML
    ComboBox <Pacijent> pacijentCB;
    @FXML
    DatePicker odbDatum;
    @FXML
    ComboBox vrstaCB,jeLiCB;
    
  
    
    private final ObservableList<Osoblje> lijecnici = FXCollections.observableArrayList(
            Osoblje.ImePrezimeOsoblje()
     );
    
    private final ObservableList<Pacijent> pacijenti = FXCollections.observableArrayList(
            Pacijent.ImePrezimePacijent()
     ); 
    
    private final ObservableList<String> vrsta = FXCollections.observableArrayList(
    
            new String[]{"obicni pregled","uputnica za specijalisticki pregled","operacija"}
    );
    
    private final ObservableList<String> jeLi = FXCollections.observableArrayList(
            
            new String []{"Obavljeno", "Na čekanju"}
    );
    
    public void spremiZT(ActionEvent e)throws SQLException{
        
        if(ZakazaniTermin.jeLiZakazan(formater.format(odbDatum.getValue()), vrijemeTF.getText(), lijecnikCB.getValue().getIDkorisnik()))
        {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Upozorenje");
             alert.setHeaderText("Odabrani termin je već zakazan!!");
             alert.setContentText("Molimo Vas da odabrete slobodan termin za Vaš pregled.");

             alert.showAndWait();
            return;
        }
        
        try {
            
            int idDoc = lijecnikCB.getValue().getIDkorisnik();
            int idPac = pacijentCB.getValue().getIDpacijent();
            
            
            ZakazaniTermin zt = new ZakazaniTermin(nazivTF.getText(),
                    vrijemeTF.getText());
            
            zt.setVrstaPregleda(vrstaCB.getSelectionModel().getSelectedItem().toString());
            zt.setDatumZak(formater.format(odbDatum.getValue()));
            
            zt.setFKLijecnik(idDoc);
            zt.setFKPacijent(idPac);
            zt.setObavljeno(jeLiCB.getSelectionModel().getSelectedItem().toString());
            zt.spasi();
            this.initialize(null, null);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacija");
            alert.setHeaderText(null);
            alert.setContentText("Uspješno ste zakazali novi termin ");

            alert.showAndWait();
        } catch (RuntimeException exc) {
             
            Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Upozorenje");
             alert.setHeaderText("Niste unijeli sve podatke.");
             alert.setContentText("Molimo Vas da provjerite podatke koje ste unijeli i pokušate ponovno");

             alert.showAndWait();
        }
    }
    
    private final DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        odbDatum.setConverter(new StringConverter<LocalDate>(){
        
            @Override
            public String toString(LocalDate t){
                
                if(t!=null){
                    return formater.format(t);
                }
                return null;
            }
            
            @Override
            public LocalDate fromString(String string){
                
                if(string != null && !string.trim().isEmpty()){
                
                    return LocalDate.parse(string,formater);
                }
                return null;
            }
        
        });
        
        odbDatum.setOnAction((ActionEvent e)->{
        
            System.out.println(formater.format(odbDatum.getValue()));
        });
        
        lijecnikCB.setItems(lijecnici);
        pacijentCB.setItems(pacijenti);
        vrstaCB.setItems(vrsta);
        jeLiCB.setItems(jeLi);
    }    
    public void odbNazad(ActionEvent e){
        ((Node)(e.getSource())).getScene().getWindow().hide();   
    }
}
