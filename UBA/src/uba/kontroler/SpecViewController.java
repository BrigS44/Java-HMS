
package uba.kontroler;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uba.model.VrstaSpecijalizacije;



public class SpecViewController implements Initializable {

    @FXML
    private Button nazadBtn,dodajSpec,izvrsiUpdateBtn,obrisiSpec,osvjeziBtn;
   
    @FXML
    private TableView<VrstaSpecijalizacije> specID;
    @FXML
    private TableColumn<VrstaSpecijalizacije, Integer> IDspec;
    @FXML
    private TableColumn<VrstaSpecijalizacije, String> naziv;
    
    @FXML
    TextField getID,updateNaziv;
    
    public void odbDodajSpec(ActionEvent e) throws IOException{
    
         
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/DodajSpec.fxml"));
        AnchorPane prozor = loader.load();
        stage.setTitle("Dodavanje specijalizacije");
        Scene scene = new Scene(prozor);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(dodajSpec.getScene().getWindow());
        stage.showAndWait();
        
    }    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        ObservableList<VrstaSpecijalizacije> data = VrstaSpecijalizacije.uzmiSveSpec();
        
        IDspec.setCellValueFactory(new PropertyValueFactory<>("IDspecijalizacija"));
        naziv.setCellValueFactory(new PropertyValueFactory<>("nazivSpec"));
        
        specID.setItems(data);
        
        odbRedizTablice();
    
    }    
    
     public void odbRedizTablice(){
     
         specID.setOnMouseClicked((MouseEvent event) -> {
             
             VrstaSpecijalizacije vs = specID.getItems().get(specID.getSelectionModel().getSelectedIndex());
             getID.setText(String.valueOf(vs.getIDspecijalizacija()));
             updateNaziv.setText(vs.getNazivSpec());
         });
     }
    
    public void azurirajSpec (ActionEvent e){
        
        try{
            VrstaSpecijalizacije vs= new VrstaSpecijalizacije();
            vs.setIDspecijalizacija(Integer.parseInt(getID.getText()));
            vs.setNazivSpec(updateNaziv.getText());
           
            vs.uredi();
            this.initialize(null, null);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacija");
            alert.setHeaderText(null);
            alert.setContentText("Uspješno ste  ažurirali podatke specijalizacije ! ");
            
                   
        } catch (RuntimeException exc) {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Upozorenje");
             alert.setHeaderText("Niste unijeli sve podatke.");
             alert.setContentText("Molimo Vas da provjerite podatke koje ste unijeli i pokušate ponovno");

             alert.showAndWait();
        }
    
    }
    
    public void brisiSpec(ActionEvent e){
            
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Prozor za potvrdu!");
        alert.setHeaderText("Brisanje podataka!");
        alert.setContentText("Jeste li sigurni da želite obrisati odabranu stavku? ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
           
            VrstaSpecijalizacije vs = new VrstaSpecijalizacije();
            vs.setIDspecijalizacija(Integer.parseInt(getID.getText()));
            vs.brisi();
            this.initialize(null, null);
        } else {
            alert.close();  
        }       
    }
 
    public void odbNazad(ActionEvent e){
        ((Node)(e.getSource())).getScene().getWindow().hide();   
    }
    
}
