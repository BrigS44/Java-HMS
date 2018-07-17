
package uba.kontroler;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import uba.model.Odjel;


public class OdjeliViewController implements Initializable {
    
    @FXML
    private Button nazadBtn,dodajOdjel,izvrsiUpdateBtn,obrisiOdjel;
    
    @FXML
    TableView<Odjel> odjeliID;
    @FXML
    TableColumn <Odjel, Integer> IDodjel;
    @FXML
    TableColumn<Odjel, String> naziv;
    @FXML
    TextField getID,updateNaziv;
    
    
     public void odbDodajOdjel(ActionEvent e) throws IOException{
    
         
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/DodajOdjel.fxml"));
        AnchorPane prozor = loader.load();
        stage.setTitle("Dodavanje odjela");
        Scene scene = new Scene(prozor);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(dodajOdjel.getScene().getWindow());
        stage.showAndWait();
        
    }    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Odjel> data = Odjel.uzmiSveOdjele();
        IDodjel.setCellValueFactory(new PropertyValueFactory<>("IDodjel"));
        naziv.setCellValueFactory(new PropertyValueFactory<>("nazivOdjel"));
        
        odjeliID.setItems(data);
        
        odbRedizTablice();
    }    
    
    private void odbRedizTablice(){
    
       odjeliID.setOnMouseClicked(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event) {
               Odjel o1 = odjeliID.getItems().get(odjeliID.getSelectionModel().getSelectedIndex());
               getID.setText(String.valueOf(o1.getIDodjel()));
               updateNaziv.setText(o1.getNazivOdjel());
           }
    
    
    });
      }
    
    public void azurirajOdjel (ActionEvent e) throws ParseException{
        
        try{
            Odjel o1= new Odjel();
            o1.setIDodjel(Integer.parseInt(getID.getText()));
            o1.setNazivOdjel(updateNaziv.getText());

            o1.uredi();
            this.initialize(null, null);
             
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Informacija");
             alert.setHeaderText(null);
             alert.setContentText("Uspješno ste  ažurirali podatke izvješća! "); 
             
        } catch (RuntimeException exc) {
            
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Upozorenje");
             alert.setHeaderText("Niste unijeli sve podatke.");
             alert.setContentText("Molimo Vas da provjerite podatke koje ste unijeli i pokušate ponovno");

             alert.showAndWait();
        }
    
    }
    
    public void brisiOdjel(ActionEvent e){
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Prozor za potvrdu!");
        alert.setHeaderText("Brisanje podataka!");
        alert.setContentText("Jeste li sigurni da želite obrisati odabranu stavku? ");

        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK){
            Odjel o2=new Odjel();
            o2.setIDodjel(Integer.parseInt(getID.getText()));
            o2.brisi();
            this.initialize(null, null);
            
        } else {
            alert.close();  
        }       
    }

    public void odbNazad(ActionEvent e){
        ((Node)(e.getSource())).getScene().getWindow().hide();   
    }
    
}
