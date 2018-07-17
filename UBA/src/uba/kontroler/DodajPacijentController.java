
package uba.kontroler;

import java.net.URL;
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
import uba.model.Pacijent;

public class DodajPacijentController implements Initializable {

    @FXML
    Button nazadBtn,izvrsiDodajBtn;
    
    @FXML
    TextField imeTxt,prezimeTxt,spolTxt,telTxt,adresaTxt,emailTxt,jmbgTxt;
    
    @FXML
    DatePicker odbDatum;
    
    @FXML
    ComboBox spolCB;

    private final DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
     private final ObservableList<String> spol = FXCollections.observableArrayList(
    
            new String [] {"muško", "žensko"}
    );

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
         spolCB.setItems(spol);
    }    

    public void spremiPacijent(ActionEvent e){
    
        try {
            Pacijent p = new Pacijent(imeTxt.getText(), prezimeTxt.getText(),
                    telTxt.getText(), adresaTxt.getText(), emailTxt.getText(),
                    jmbgTxt.getText());
            p.setSpolPac(spolCB.getSelectionModel().getSelectedItem().toString());
            p.setDatumRodjenja(formater.format(odbDatum.getValue()));
            p.spasi();
            this.initialize(null, null);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacija");
            alert.setHeaderText(null);
            alert.setContentText("Uspješno ste dodali novog pacijenta! ");
                      
        } catch (RuntimeException exc) {
            
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Upozorenje");
             alert.setHeaderText("Niste unijeli sve podatke.");
             alert.setContentText("Molimo Vas da provjerite podatke koje ste unijeli i pokušate ponovno.");

             alert.showAndWait();
        }
    }
    public void odbNazad(ActionEvent e){
        ((Node)(e.getSource())).getScene().getWindow().hide();   
    }
    
}
