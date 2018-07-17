
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
import javafx.util.Callback;
import uba.model.Osoblje;
import uba.model.Pacijent;
import uba.model.ZakazaniTermin;


public class ZakazaniTerminViewController implements Initializable {
    
    //gumbi
    @FXML
    Button izvrsiUpdateBtn,nazadBtn,brisiZTBtn,dodajZT;
    
    @FXML
    TableView<ZakazaniTermin> ztID;
    @FXML
    TableColumn<ZakazaniTermin, Integer> IDzt;
    @FXML
    TableColumn<ZakazaniTermin, String> nazivTbl,vrstaTbl,datumTbl,vrijemeTbl,lijecnikTbl,pacijentTbl;
    
    @FXML
    TableColumn<ZakazaniTermin, Boolean> obavljenoTbl;
    
    @FXML
    TextField idTF,nazivTF,datumTF,vrijemeTF,obavljenoTF;
    
    @FXML
    ComboBox <Osoblje> lijecnikCB;
    @FXML
    ComboBox <Pacijent> pacijentCB;
    @FXML
    ComboBox vrstaCB,jeLiCB;
    
   
    
    private final ObservableList<Pacijent> pacijenti = FXCollections.observableArrayList(
            Pacijent.ImePrezimePacijent()
     ); 
    
    private final ObservableList<Osoblje> lijecnici = FXCollections.observableArrayList(
            Osoblje.ImePrezimeOsoblje()
     );
     private final ObservableList<String> vrsta = FXCollections.observableArrayList(
    
            new String[]{"obicni pregled","uputnica za specijalisticki pregled","operacija"}
    );
     private final ObservableList<String> jeLi = FXCollections.observableArrayList(
            
            new String []{"Obavljeno", "Na čekanju"}
    );
   
    public void odbDodajZT(ActionEvent e) throws IOException{
    
         
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/ZakaziTermin.fxml"));
        AnchorPane prozor = loader.load();
        stage.setTitle("Zakazi termin za pregled!");
        Scene scene = new Scene(prozor);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(dodajZT.getScene().getWindow());
        stage.show();
        
    }    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
         ObservableList<ZakazaniTermin> data = ZakazaniTermin.uzmiSveZakazaneTermine();        
         
        IDzt.setCellValueFactory(new PropertyValueFactory<>("IDzakazano"));
        nazivTbl.setCellValueFactory(new PropertyValueFactory<>("naslovZak"));
        vrstaTbl.setCellValueFactory(new PropertyValueFactory<>("vrstaPregleda"));
        datumTbl.setCellValueFactory(new PropertyValueFactory<>("datumZak"));
        vrijemeTbl.setCellValueFactory(new PropertyValueFactory<>("vremenskiTermin"));
        
        lijecnikTbl.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ZakazaniTermin,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ZakazaniTermin, String> param) {
                return new SimpleStringProperty(param.getValue().dajLijecnikZakazano());    
            }
        
        });
        pacijentTbl.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ZakazaniTermin,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ZakazaniTermin, String> param) {
                return new SimpleStringProperty(param.getValue().dajZakazaniTerPacijent());    
            }
        
        });
        obavljenoTbl.setCellValueFactory(new PropertyValueFactory<>("obavljeno"));
        ztID.setItems(data);
        odbRedizTablice();
    }    
    
    private void odbRedizTablice(){
    
        ztID.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                ZakazaniTermin zt = ztID.getItems().get(ztID.getSelectionModel().getSelectedIndex());
                idTF.setText(String.valueOf(zt.getIDzakazano()));
                vrstaCB.setPromptText(zt.getVrstaPregleda());
                vrstaCB.setItems(vrsta);
                nazivTF.setText(zt.getNaslovZak());
                datumTF.setText(zt.getDatumZak());
                vrijemeTF.setText(zt.getVremenskiTermin());
                lijecnikCB.setPromptText(zt.dajLijecnikZakazano());
                lijecnikCB.setItems(lijecnici);
                pacijentCB.setPromptText(zt.dajZakazaniTerPacijent());
                pacijentCB.setItems(pacijenti);
                jeLiCB.setPromptText(zt.getObavljeno());
                jeLiCB.setItems(jeLi);
                
            }
  
    });
    }
    
    public void azurirajZakazani(ActionEvent e) throws SQLException{
    
        try {
            ZakazaniTermin zt = new ZakazaniTermin();
            zt.setIDzakazano(Integer.parseInt(idTF.getText()));
            zt.setVrstaPregleda(vrstaCB.getSelectionModel().getSelectedItem().toString());
            zt.setNaslovZak(nazivTF.getText());
            
            zt.setDatumZak(datumTF.getText());
            zt.setVremenskiTermin(vrijemeTF.getText());
           
            int idDoc = lijecnikCB.getValue().getIDkorisnik();
            zt.setFKLijecnik(idDoc);   
            int idPac = pacijentCB.getValue().getIDpacijent();
            zt.setFKPacijent(idPac);
            zt.setObavljeno(jeLiCB.getSelectionModel().getSelectedItem().toString());
            zt.uredi();
            this.initialize(null, null);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacija");
            alert.setHeaderText(null);
            alert.setContentText("Uspješno ste  ažurirali podatke zakazanog termina! ");
            
        } catch (RuntimeException exc) {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Upozorenje");
             alert.setHeaderText("Niste unijeli sve podatke.");
             alert.setContentText("Molimo Vas da provjerite podatke koje ste unijeli i pokušate ponovno");

             alert.showAndWait();
        }
    }
    
    public void brisiZakazani(ActionEvent e){
 
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Prozor za potvrdu!");
        alert.setHeaderText("Brisanje podataka!");
        alert.setContentText("Jeste li sigurni da želite obrisati odabranu stavku? ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
           
            ZakazaniTermin zt = new ZakazaniTermin();
            zt.setIDzakazano(Integer.parseInt(idTF.getText()));
            zt.brisi();
            this.initialize(null, null);
        } else {
            alert.close();  
        }       
    }
    
   public void odbNazad(ActionEvent e){
        ((Node)(e.getSource())).getScene().getWindow().hide();   
    }
   
}
