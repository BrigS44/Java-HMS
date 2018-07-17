
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
import javafx.scene.control.Alert.AlertType;
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
import uba.model.Izvjesce;
import uba.model.Osoblje;
import uba.model.Pacijent;




public class IzvjescaViewController implements Initializable {
    
    //gumbi
    @FXML
    Button izvrsiUpdateBtn,nazadBtn,brisiIzBtn,dodajIz;
    
    @FXML
    TableView<Izvjesce> izvjesceID;
    @FXML
    TableColumn<Izvjesce, Integer> IDizvjesce;
    @FXML
    TableColumn<Izvjesce, String> vrstaTbl,nazivTbl,opisTbl,datumTbl,vrijemeTbl,
            lijecnikTbl,pacijentTbl;
    
    //textfield
    @FXML
    TextField idTF,nazivTF,opisTF,datumTF,vrijemeTF;
    
    @FXML
    ComboBox <Osoblje> lijecnikCB;
    @FXML
    ComboBox <Pacijent> pacijentCB;
    @FXML
    ComboBox vrstaCB;
    
    
    private final ObservableList<Osoblje> lijecnici = FXCollections.observableArrayList(
            Osoblje.ImePrezimeOsoblje()
     );
    
    private final ObservableList<Pacijent> pacijenti = FXCollections.observableArrayList(
            Pacijent.ImePrezimePacijent()
     ); 
     private final ObservableList<String> vrste = FXCollections.observableArrayList(
    
            new String[]{"smrt","rodjenje","operacija"}
    );
     
    public void odbDodajIz(ActionEvent e) throws IOException{
    
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/DodajIzvjesce.fxml"));
        AnchorPane prozor = loader.load();
        stage.setTitle("Dodavanje izvjesca!");
        Scene scene = new Scene(prozor);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(dodajIz.getScene().getWindow());
        stage.show();
    }    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Izvjesce> data = Izvjesce.uzmiSvaIzvjesca();
        
        IDizvjesce.setCellValueFactory(new PropertyValueFactory<>("IDizvjesce"));
        vrstaTbl.setCellValueFactory(new PropertyValueFactory<>("VrstaIzvjesca"));
        nazivTbl.setCellValueFactory(new PropertyValueFactory<>("naslovIz"));
        opisTbl.setCellValueFactory(new PropertyValueFactory<>("OpisIz"));
        datumTbl.setCellValueFactory(new PropertyValueFactory<>("datumIz"));
        vrijemeTbl.setCellValueFactory(new PropertyValueFactory<>("vrijemeIz"));
        
        lijecnikTbl.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Izvjesce,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Izvjesce, String> param) {
                return new SimpleStringProperty(param.getValue().dajIzvjesceOsoblje());    
            }
        
        });
        pacijentTbl.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Izvjesce,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Izvjesce, String> param) {
                return new SimpleStringProperty(param.getValue().dajIzvjescePacijent());    
            }
        
        });
        
        izvjesceID.setItems(data);
        odbRedizTablice();
        
    }    
    
    private void odbRedizTablice(){
    
        izvjesceID.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                
                Izvjesce iz = izvjesceID.getItems().get(izvjesceID.getSelectionModel().getSelectedIndex());
                idTF.setText(String.valueOf(iz.getIDizvjesce()));
                vrstaCB.setPromptText(iz.getVrstaIzvjesca());
                vrstaCB.setItems(vrste);
                nazivTF.setText(iz.getNaslovIz());
                opisTF.setText(iz.getOpisIz());
                datumTF.setText(iz.getDatumIz());
                vrijemeTF.setText(iz.getVrijemeIz());
                lijecnikCB.setPromptText(iz.dajIzvjesceOsoblje());
                lijecnikCB.setItems(lijecnici);
                pacijentCB.setPromptText(iz.dajIzvjescePacijent());
                pacijentCB.setItems(pacijenti);
               
            }
     });
    }
    
    public void azurirajIzvjesce(ActionEvent e) throws SQLException{
    
        try {
            Izvjesce iz = new Izvjesce();
            iz.setIDizvjesce(Integer.parseInt(idTF.getText()));
            iz.setVrstaIzvjesca(vrstaCB.getSelectionModel().getSelectedItem().toString());
            iz.setNaslovIz(nazivTF.getText());
            iz.setOpisIz(opisTF.getText());
            iz.setDatumIz(datumTF.getText());
            iz.setVrijemeIz(vrijemeTF.getText());
           
            int idDoc = lijecnikCB.getValue().getIDkorisnik();
            iz.setFKLijecnik(idDoc);   
            int idPac = pacijentCB.getValue().getIDpacijent();
            iz.setFKPacijent(idPac);
            
            iz.uredi();
            this.initialize(null, null);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacija");
            alert.setHeaderText(null);
            alert.setContentText("Uspješno ste  ažurirali podatke izvješća! ");

            alert.showAndWait();
            
        } catch (RuntimeException exc) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Upozorenje");
             alert.setHeaderText("Niste unijeli sve podatke.");
             alert.setContentText("Molimo Vas da provjerite podatke koje ste unijeli i pokušate ponovno");

             alert.showAndWait();
        }
    }
    
    public void brisiIzvjesce(ActionEvent e){
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Prozor za potvrdu!");
        alert.setHeaderText("Brisanje podataka!");
        alert.setContentText("Jeste li sigurni da želite obrisati odabranu stavku? ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Izvjesce o2 = new Izvjesce();
            o2.setIDizvjesce(Integer.parseInt(idTF.getText()));
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
