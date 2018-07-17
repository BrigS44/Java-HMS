
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
import uba.model.Odjel;
import uba.model.Osoblje;
import uba.model.VrstaSpecijalizacije;


public class DodajOsobljeController implements Initializable {
    @FXML
    Button nazadBtn,izvrsiDodajBtn;
   
   @FXML
    TextField imeTF,prezimeTF,telTF,adresaTF,emailTF,jmbgTF,tipTF, korImeTF,
           lozinkaTF,sssTF,placaTF;
   
    @FXML
    ComboBox <Odjel> odjelCB;
    @FXML
    ComboBox <VrstaSpecijalizacije> specCB;
    @FXML
    ComboBox tipCB,spolCB;
    @FXML
    DatePicker odbRodj,odbUposlen;
   
   
    private final ObservableList<VrstaSpecijalizacije> vs = FXCollections.observableArrayList(
            VrstaSpecijalizacije.uzmiNazivSpec()
     );
    
    private final ObservableList<Odjel> od = FXCollections.observableArrayList(
            Odjel.uzmiNazivOdjel()
     );
    private final ObservableList<String> tip = FXCollections.observableArrayList(
    
            new String [] {"Admin", "Lijecnik", "Medicinska sestra","Farmaceut","Laborant"}
    );
    private final ObservableList<String> spol = FXCollections.observableArrayList(
    
            new String [] {"muško", "žensko"}
    );
    
    public void spremiOsoblje(ActionEvent e)throws SQLException{
        
        try {
            
            
            int idSpec = specCB.getValue().dajIdSpec(specCB.getValue().toString());
            int idOdjel = odjelCB.getValue().dajIdOdjel(odjelCB.getValue().toString());
            Float placa = Float.parseFloat(placaTF.getText());
            
            Osoblje o = new Osoblje(imeTF.getText(), prezimeTF.getText(),
                    telTF.getText(), adresaTF.getText(), emailTF.getText(),
                    jmbgTF.getText(),korImeTF.getText(),
                    lozinkaTF.getText(),sssTF.getText(),placa);
            
            o.setSpol(spolCB.getSelectionModel().getSelectedItem().toString());
            o.setDatumRodjenja(formater.format(odbRodj.getValue()));
            o.setDatumZaposlenja(formater.format(odbUposlen.getValue()));
            o.setTipKor(tipCB.getSelectionModel().getSelectedItem().toString());
            o.setIDspecijalizacija (idSpec);
            o.setIDodjel(idOdjel);
           
            o.spasi();
            this.initialize(null, null);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacija");
            alert.setHeaderText(null);
            alert.setContentText("Uspješno ste dodali novog korisnika! ");
            
        } catch (RuntimeException exc) {
           
            Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Upozorenje");
             alert.setHeaderText("Niste unijeli sve podatke.");
             alert.setContentText("Molimo Vas da provjerite podatke koje ste unijeli i pokušate ponovno.");

             alert.showAndWait();
        }
    }
 
    private final DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        odbRodj.setConverter(new StringConverter<LocalDate>(){
        
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
        
        odbRodj.setOnAction((ActionEvent e)->{
        
            System.out.println(formater.format(odbRodj.getValue()));
        });
        
        odbUposlen.setConverter(new StringConverter<LocalDate>(){
        
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
        
        odbUposlen.setOnAction((ActionEvent e)->{
        
            System.out.println(formater.format(odbUposlen.getValue()));
        });
        
        specCB.setItems(vs);
        odjelCB.setItems(od);
        tipCB.setItems(tip);
        spolCB.setItems(spol);
    } 
    
    public void odbNazad(ActionEvent e){
        ((Node)(e.getSource())).getScene().getWindow().hide();   
    }

}
