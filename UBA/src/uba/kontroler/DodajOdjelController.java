
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
import javax.swing.JOptionPane;
import uba.model.Odjel;


public class DodajOdjelController implements Initializable {
   
    @FXML
    Button nazadBtn;
    
    @FXML
    TextField nazivTxt;
    @FXML
    Button izvrsiDodajBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    public void spremiOdjel(ActionEvent e){
    
        try {
            
            Odjel o = new Odjel(nazivTxt.getText());
            o.spasi();
            this.initialize(null, null);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacija");
            alert.setHeaderText(null);
            alert.setContentText("Uspješno ste dodali novi odjel! ");

            alert.showAndWait();
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
