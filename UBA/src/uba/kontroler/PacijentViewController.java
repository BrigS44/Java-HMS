
package uba.kontroler;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uba.model.Pacijent;


public class PacijentViewController implements Initializable {

   //public static int idP;
    
   @FXML
    private Button nazadBtn,dodajPacijent,izvrsiUpdateBtn,obrisiPacijent;
   
    @FXML
    private TableView<Pacijent> pacijentiID;

    @FXML
    private TableColumn<Pacijent, Integer> IDpacijent;
    @FXML
    private TableColumn<Pacijent, String> ime,prezime,spol,telefon,adresa,email,datRodjenja,JMBG;
    
    
    @FXML
    TextField getID,updateIme,updatePrezime,updateTelefon,updateAdresa,updateEmail,
            updateRodj,updateJmbg;
    
    
    @FXML
    ComboBox spolCB;
    
    private final ObservableList<String> spollist = FXCollections.observableArrayList(
    
            new String [] {"muško", "žensko"}
    );
    
 
    public void odbDodajPacijent(ActionEvent e) throws IOException{
    
         
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/DodajPacijent.fxml"));
        AnchorPane prozor = loader.load();
        stage.setTitle("Dodavanje pacijenta!");
        Scene scene = new Scene(prozor);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(dodajPacijent.getScene().getWindow());
        stage.showAndWait();
        
    }    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        ObservableList<Pacijent> data = Pacijent.uzmiSvePacijente();
        
        IDpacijent.setCellValueFactory(new PropertyValueFactory<>("IDpacijent"));
        ime.setCellValueFactory(new PropertyValueFactory<>("imePac"));
        prezime.setCellValueFactory(new PropertyValueFactory<>("prezimePac"));
        spol.setCellValueFactory(new PropertyValueFactory<>("spolPac"));
        telefon.setCellValueFactory(new PropertyValueFactory<>("telefonPac"));
        adresa.setCellValueFactory(new PropertyValueFactory<>("adresaPac"));
        email.setCellValueFactory(new PropertyValueFactory<>("emailPac"));
        datRodjenja.setCellValueFactory(new PropertyValueFactory<>("datumRodjenja"));
        JMBG.setCellValueFactory(new PropertyValueFactory<>("JMBGPac"));
        
        pacijentiID.setItems(data);
        
        odbRedizTablice();
        
        pacijentiID.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
               
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/PacijentProfil.fxml"));
                try {
                    loader.load();
                } catch (Exception e) {
                }
                
                PacijentProfilController ppc = loader.getController();
                ppc.dajID(pacijentiID.getSelectionModel().getSelectedItem().getIDpacijent());
                ppc.PrikaziPodatkePacijent(""+ pacijentiID.getSelectionModel().getSelectedItem().getIDpacijent(),
                                           pacijentiID.getSelectionModel().getSelectedItem().getImePac(),
                                           pacijentiID.getSelectionModel().getSelectedItem().getPrezimePac(),
                                           pacijentiID.getSelectionModel().getSelectedItem().getSpolPac(),
                                           pacijentiID.getSelectionModel().getSelectedItem().getTelefonPac(),
                                           pacijentiID.getSelectionModel().getSelectedItem().getAdresaPac(),
                                           pacijentiID.getSelectionModel().getSelectedItem().getEmailPac(),
                                           pacijentiID.getSelectionModel().getSelectedItem().getDatumRodjenja(),
                                           pacijentiID.getSelectionModel().getSelectedItem().getJMBGPac());
                ppc.PrikaziTablicu();
                
                Parent p = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.show();
            }
        
        
        });
    
    
    }    
    
    private void odbRedizTablice(){
    
        
        pacijentiID.setOnMouseClicked((MouseEvent event) -> {
            Pacijent p1 = pacijentiID.getItems().get(pacijentiID.getSelectionModel().getSelectedIndex());
           
            getID.setText(String.valueOf(p1.getIDpacijent()));
            updateIme.setText(p1.getImePac());
            updatePrezime.setText(p1.getPrezimePac());
            spolCB.setPromptText(p1.getSpolPac());
            spolCB.setItems(spollist);
            updateTelefon.setText(p1.getTelefonPac());
            updateAdresa.setText(p1.getAdresaPac());
            updateEmail.setText(p1.getEmailPac());
            updateRodj.setText(p1.getDatumRodjenja());
            updateJmbg.setText(p1.getJMBGPac());
        });
      }
    
   
    public void azurirajPacijenta (ActionEvent e){
        
        try{
            Pacijent p1= new Pacijent();
            p1.setIDpacijent(Integer.parseInt(getID.getText()));
            p1.setImePac(updateIme.getText());
            p1.setPrezimePac(updatePrezime.getText());
            p1.setSpolPac(spolCB.getSelectionModel().getSelectedItem().toString());
            p1.setTelefonPac(updateTelefon.getText());
            p1.setAdresaPac(updateAdresa.getText());
            p1.setEmailPac(updateEmail.getText());
            p1.setDatumRodjenja(updateRodj.getText());
            p1.setJMBGPac(updateJmbg.getText());
            
            p1.uredi();
            this.initialize(null, null);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacija");
            alert.setHeaderText(null);
            alert.setContentText("Uspješno ste  ažurirali podatke pacijenata! ");
                   
        } catch (RuntimeException exc) {
            
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Upozorenje");
             alert.setHeaderText("Niste unijeli sve podatke.");
             alert.setContentText("Molimo Vas da provjerite podatke koje ste unijeli i pokušate ponovno");

             alert.showAndWait();
        }
    
    }
    
    public void brisiPacijenta(ActionEvent e){
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Prozor za potvrdu!");
        alert.setHeaderText("Brisanje podataka!");
        alert.setContentText("Jeste li sigurni da želite obrisati odabranu stavku? ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Pacijent p2=new Pacijent();
            p2.setIDpacijent(Integer.parseInt(getID.getText()));
            p2.brisi();
            this.initialize(null, null);
        } else {
            alert.close();  
        }       
    }
 
    public void odbNazad(ActionEvent e){
        ((Node)(e.getSource())).getScene().getWindow().hide();   
    }
    
}
