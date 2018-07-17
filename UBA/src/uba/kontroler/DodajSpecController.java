
package uba.kontroler;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import uba.model.VrstaSpecijalizacije;

public class DodajSpecController implements Initializable {
    
    @FXML
    Button nazadBtn;
    @FXML
    TextField nazivTxt;
    @FXML
    Button izvrsiDodajBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }    
    public void spremiSpec(ActionEvent e){
    
        try {
            
            VrstaSpecijalizacije vs = new VrstaSpecijalizacije(nazivTxt.getText());
              
            vs.spasi();
            this.initialize(null, null);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacija");
            alert.setHeaderText(null);
            alert.setContentText("Uspješno ste dodali novu specijalizaciju! ");
            
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
