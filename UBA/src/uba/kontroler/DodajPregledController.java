
package uba.kontroler;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import uba.model.Pregled;
import uba.model.ZakazaniTermin;

public class DodajPregledController implements Initializable {
    
    @FXML
    Button nazadBtn,izvrsiDodajBtn,izvrsiBtn;
    
    @FXML
    TextField nazivTF,opisdTF,dijagnozaTF,vrijemeTF,terapijaTF;
    
    @FXML
    DatePicker odbDatum;
    
    @FXML
    ComboBox <ZakazaniTermin> zakazanitCB;
    
    @FXML
    Label idlbl,imePlbl, prezimePlbl, imeLlbl, prezimeLlbl;
    
    private final ObservableList<ZakazaniTermin> zakter = FXCollections.observableArrayList(
            ZakazaniTermin.uzmiZakazaniTerminPodaci()
     );
    
    private final ObservableList<ZakazaniTermin> zakter1 = FXCollections.observableArrayList(
            ZakazaniTermin.uzmiPodatkeOPaciLijec()
     );
     
    private final DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public void spremiPregled(ActionEvent e)throws SQLException{
        
        try {
            
            int idZT = zakazanitCB.getValue().getIDzakazano();
           
            Pregled preg = new Pregled(nazivTF.getText(),dijagnozaTF.getText(),opisdTF.getText(),
                    vrijemeTF.getText(),terapijaTF.getText());
            
            preg.setDatumPreg(formater.format(odbDatum.getValue()));
            preg.setFKZakazano(idZT);
            
            preg.spasi();
            this.initialize(null, null);
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacija");
            alert.setHeaderText(null);
            alert.setContentText("Uspješno ste dodali novi pregled! ");
            
        } catch (RuntimeException exc) {
             
            Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Upozorenje");
             alert.setHeaderText("Niste unijeli sve podatke.");
             alert.setContentText("Molimo Vas da provjerite podatke koje ste unijeli i pokušate ponovno.");

             alert.showAndWait();
        }
    }
 
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
       
        zakazanitCB.setItems(zakter);
       
        zakazanitCB.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                  
                    @Override
                    public void changed(ObservableValue ov, Number value, Number new_value) {
                        ZakazaniTermin zt = ZakazaniTermin.uzmiPodatkeOPaciLijec().get(new_value.intValue());
                        
                        imePlbl.setText(zt.getImeP());
                        prezimePlbl.setText(zt.getPrezimeP());
                        imeLlbl.setText(zt.getImeL());
                        prezimeLlbl.setText(zt.getPrezimeL());
                    }
                });
      }   
     
    public void odbNazad(ActionEvent e){
        ((Node)(e.getSource())).getScene().getWindow().hide();   
    }

}
