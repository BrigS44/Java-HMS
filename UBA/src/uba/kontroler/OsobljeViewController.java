
package uba.kontroler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import uba.model.Izvjesce;
import uba.model.Odjel;
import uba.model.Osoblje;
import uba.model.VrstaSpecijalizacije;

public class OsobljeViewController implements Initializable {
   
   //gumbi
    @FXML
    Button izvrsiUpdateBtn,nazadBtn,brisiOsobljeBtn,dodajOsoblje;
   
    //tablica
    @FXML
    private TableView<Osoblje> osobljeID;
    @FXML
    TableColumn<Osoblje, Integer> IDosoblje;
    @FXML
    TableColumn<Osoblje, String> imeTbl,prezimeTbl,spolTbl,telefonTbl,adresaTbl,
            emailTbl,rodjTbl,jmbgTbl,tipTbl,korImeTbl,lozinkaTbl,sssTbl,specTbl,
            odjelTbl,uposlenTbl,placaTbl;
    
    //text field
    @FXML
    TextField idTF,imeTF,prezimeTF,spolTF,telTF,adresaTF,emailTF,rodjTF,jmbgTF,
            korImeTF,lozinkaTF,sssTF,uposlenTF,placaTF;
    
    @FXML
    ComboBox <Odjel> odjelCB;
    @FXML
    ComboBox <VrstaSpecijalizacije> specCB;
    @FXML
    ComboBox tipCB,spolCB;
    @FXML
    Label prosjekLbl;
 
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
    
    public void odbDodajOsoblje(ActionEvent e) throws IOException{
    
         
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/DodajOsoblje.fxml"));
        AnchorPane prozor = loader.load();
        stage.setTitle("Dodavanje osoblja!");
        Scene scene = new Scene(prozor);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(dodajOsoblje.getScene().getWindow());
        stage.showAndWait();
        
    }    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IspisiProsjecnuPlacu();
        
        ObservableList<Osoblje> data = Osoblje.uzmiSvoOsoblje();
        
        imeTbl.setCellValueFactory(new PropertyValueFactory<>("ime"));
        prezimeTbl.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        spolTbl.setCellValueFactory(new PropertyValueFactory<>("spol"));
        telefonTbl.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        adresaTbl.setCellValueFactory(new PropertyValueFactory<>("adresa"));
        emailTbl.setCellValueFactory(new PropertyValueFactory<>("email"));
        rodjTbl.setCellValueFactory(new PropertyValueFactory<>("datumRodjenja"));
        jmbgTbl.setCellValueFactory(new PropertyValueFactory<>("JMBG"));
        tipTbl.setCellValueFactory(new PropertyValueFactory<>("tipKor"));
        korImeTbl.setCellValueFactory(new PropertyValueFactory<>("korisnickoIme"));
        lozinkaTbl.setCellValueFactory(new PropertyValueFactory<>("lozinka"));
        sssTbl.setCellValueFactory(new PropertyValueFactory<>("strucnaSprema"));
        uposlenTbl.setCellValueFactory(new PropertyValueFactory<>("datumZaposlenja"));
        
        specTbl.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Osoblje,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Osoblje, String> param) {
                return new SimpleStringProperty(param.getValue().dajOsobljeSpec());    
            }
        
        });
        odjelTbl.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Osoblje,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Osoblje, String> param) {
                
                return new SimpleStringProperty(param.getValue().dajOsobljeOdjel());
            }
   
        });
        
        IDosoblje.setCellValueFactory(new PropertyValueFactory<>("IDkorisnik"));
        placaTbl.setCellValueFactory(new PropertyValueFactory<>("placa"));
        osobljeID.setItems(data);
        
        odbRedizTablice();
        
        osobljeID.setOnMousePressed(new EventHandler<MouseEvent>(){
            Osoblje o = new Osoblje();
            @Override
            public void handle(MouseEvent event) {
               
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/OsobljeProfil.fxml"));
                try {
                    loader.load();
                } catch (Exception e) {
                }
                
                OsobljeProfilController opc = loader.getController();
                
                opc.PrikaziPodatkeOsoblje(""+osobljeID.getSelectionModel().getSelectedItem().getIDkorisnik(),
                                           osobljeID.getSelectionModel().getSelectedItem().getIme(),
                                           osobljeID.getSelectionModel().getSelectedItem().getPrezime(),
                                           osobljeID.getSelectionModel().getSelectedItem().getSpol(),
                                           osobljeID.getSelectionModel().getSelectedItem().getTelefon(),
                                           osobljeID.getSelectionModel().getSelectedItem().getAdresa(),
                                           osobljeID.getSelectionModel().getSelectedItem().getEmail(),
                                           osobljeID.getSelectionModel().getSelectedItem().getDatumRodjenja(),
                                           osobljeID.getSelectionModel().getSelectedItem().getJMBG(),
                                           osobljeID.getSelectionModel().getSelectedItem().getTipKor(),
                                           osobljeID.getSelectionModel().getSelectedItem().getKorisnickoIme(),
                                           osobljeID.getSelectionModel().getSelectedItem().getLozinka(),
                                           osobljeID.getSelectionModel().getSelectedItem().getStrucnaSprema(),
                                           osobljeID.getSelectionModel().getSelectedItem().dajOsobljeSpec(),
                                           osobljeID.getSelectionModel().getSelectedItem().dajOsobljeOdjel(),
                                           osobljeID.getSelectionModel().getSelectedItem().getDatumZaposlenja()
                                          
                );
                Parent p = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.show();
            }
       
        });
    }    
    
    private void odbRedizTablice(){
    
        osobljeID.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Osoblje o = osobljeID.getItems().get(osobljeID.getSelectionModel().getSelectedIndex());
                idTF.setText(String.valueOf(o.getIDkorisnik()));
                imeTF.setText(o.getIme());
                prezimeTF.setText(o.getPrezime());
                spolCB.setPromptText(o.getSpol());
                spolCB.setItems(spol);
                telTF.setText(o.getTelefon());
                adresaTF.setText(o.getAdresa());
                emailTF.setText(o.getEmail());
                rodjTF.setText(o.getDatumRodjenja());
                jmbgTF.setText(o.getJMBG());
                korImeTF.setText(o.getKorisnickoIme());
                lozinkaTF.setText(o.getLozinka());
                sssTF.setText(o.getStrucnaSprema());
                tipCB.setPromptText(o.getTipKor());
                tipCB.setItems(tip);
                specCB.setPromptText(o.dajOsobljeSpec());
                specCB.setItems(vs);
                odjelCB.setPromptText(o.dajOsobljeOdjel());
                odjelCB.setItems(od);
                uposlenTF.setText(o.getDatumZaposlenja());
                placaTF.setText(String.valueOf(o.getPlaca()));
            }
  
    });
    }
    
    public void azurirajOsoblje(ActionEvent e) throws SQLException{
    
        try {
            Osoblje o1 = new Osoblje();
            o1.setIDkorisnik(Integer.parseInt(idTF.getText()));
            o1.setIme(imeTF.getText());
            o1.setPrezime(prezimeTF.getText());
            o1.setSpol(spolCB.getSelectionModel().getSelectedItem().toString());
            o1.setTelefon(telTF.getText());
            o1.setAdresa(adresaTF.getText());
            o1.setEmail(emailTF.getText());
            o1.setDatumRodjenja(rodjTF.getText());
            o1.setJMBG(jmbgTF.getText());
            o1.setTipKor(tipCB.getSelectionModel().getSelectedItem().toString());
            o1.setKorisnickoIme(korImeTF.getText());
            o1.setLozinka(lozinkaTF.getText());
            o1.setStrucnaSprema(sssTF.getText());
            
            int idSpec = specCB.getValue().dajIdSpec(specCB.getValue().toString());
            o1.setIDspecijalizacija(idSpec);   
            int idOdjel = odjelCB.getValue().dajIdOdjel(odjelCB.getValue().toString());
            o1.setIDodjel(idOdjel);
            
            o1.setDatumZaposlenja(uposlenTF.getText());
            o1.setPlaca(Float.parseFloat(placaTF.getText()));
            o1.uredi();
            
            this.initialize(null, null);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacija");
            alert.setHeaderText(null);
            alert.setContentText("Uspješno ste  ažurirali podatke osoblja! ");
            
        } catch (RuntimeException exc) {
          
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Upozorenje");
             alert.setHeaderText("Niste unijeli sve podatke.");
             alert.setContentText("Molimo Vas da provjerite podatke koje ste unijeli i pokušate ponovno");

             alert.showAndWait();
        }
    }
    
    public void brisiOsoblje(ActionEvent e){
    
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Prozor za potvrdu!");
        alert.setHeaderText("Brisanje podataka!");
        alert.setContentText("Jeste li sigurni da želite obrisati odabranu stavku? ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Osoblje o2 = new Osoblje();
            o2.setIDkorisnik(Integer.parseInt(idTF.getText()));
            o2.brisi();
            
            this.initialize(null, null);
        } else {
            alert.close();  
        }       
        
    }
    
   public void odbNazad(ActionEvent e){
        ((Node)(e.getSource())).getScene().getWindow().hide();   
    }
  
   public void IspisiProsjecnuPlacu(){
   
       Osoblje o = new Osoblje();
       prosjekLbl.setText(String.valueOf(o.prosjecnaPlacaOsoblje()));
   }
   
}
