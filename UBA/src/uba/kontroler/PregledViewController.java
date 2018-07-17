
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
import javax.swing.JOptionPane;
import uba.model.Pregled;
import uba.model.ZakazaniTermin;

/**
 * FXML Controller class
 *
 * @author Brigita
 */
public class PregledViewController implements Initializable {
    
    //gumbi
    @FXML
    Button izvrsiUpdateBtn,nazadBtn,brisiPregBtn,ZTview,dodajPregled;
   
    @FXML
    TableView<Pregled> pregledID;
    @FXML
    TableColumn<Pregled, Integer> IDpregled;
    @FXML
    TableColumn<Pregled, String> nazivTbl,datumpTbl,vrijemepTbl,dijagnozaTbl,opisdTbl,
            idZakazanoTbl,terapijaTbl,imepTbl,prezimepTbl,imelTbl,prezimelTbl;
    @FXML
    TableColumn<Pregled, Boolean> obavljenoTbl;
    
    //textField
    @FXML
    TextField idTF,nazivTF,dijagnozaTF,datumTF,vrijemeTF,opisdTF,terapijaTF;
    
    @FXML
    ComboBox <ZakazaniTermin> zakazanitCB;
 
    private final ObservableList<ZakazaniTermin> zakter = FXCollections.observableArrayList(
            ZakazaniTermin.uzmiZakazaniTerminPodaci()
     );
    
    public void odbDodajPregled(ActionEvent e) throws IOException{
    
         
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/DodajPregled.fxml"));
        AnchorPane prozor = loader.load();
        stage.setTitle("Dodaj pregled! ");
        Scene scene = new Scene(prozor);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(dodajPregled.getScene().getWindow());
        stage.show();
        
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Pregled> data = Pregled.uzmiSvePreglede();
         
        IDpregled.setCellValueFactory(new PropertyValueFactory<>("IDpregled"));
        nazivTbl.setCellValueFactory(new PropertyValueFactory<>("naslovPreg"));
        dijagnozaTbl.setCellValueFactory(new PropertyValueFactory<>("dijagnoza"));
        opisdTbl.setCellValueFactory(new PropertyValueFactory<>("opisDijagnoze"));
        datumpTbl.setCellValueFactory(new PropertyValueFactory<>("datumPreg"));
        vrijemepTbl.setCellValueFactory(new PropertyValueFactory<>("vrijemePreg"));
        terapijaTbl.setCellValueFactory(new PropertyValueFactory<>("terapija"));
        
        idZakazanoTbl.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pregled,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Pregled, String> param) {
                return new SimpleStringProperty(param.getValue().dajZakazaniTerminPregled());  
                //prikaz u tablici je dobar sve normlano ispisuje 
            }
        
        });
        
        obavljenoTbl.setCellValueFactory(new PropertyValueFactory<>("obavljenZak"));
        imepTbl.setCellValueFactory(new PropertyValueFactory<>("imePacijent"));
        prezimepTbl.setCellValueFactory(new PropertyValueFactory<>("prezimePacijent"));
        imelTbl.setCellValueFactory(new PropertyValueFactory<>("imeLijecnik"));
        prezimelTbl.setCellValueFactory(new PropertyValueFactory<>("prezimeLijecnik"));
        
        pregledID.setItems(data);
        odbRedizTablice();
    }   
    
    private void odbRedizTablice(){
    
        pregledID.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Pregled preg = pregledID.getItems().get(pregledID.getSelectionModel().getSelectedIndex());
                idTF.setText(String.valueOf(preg.getIDpregled()));
                nazivTF.setText(preg.getNaslovPreg());
                dijagnozaTF.setText(preg.getDijagnoza());
                opisdTF.setText(preg.getOpisDijagnoze());
                datumTF.setText(preg.getDatumPreg());
                vrijemeTF.setText(preg.getVrijemePreg());
                zakazanitCB.setPromptText(preg.dajZakazaniTerminPregled());//ovo je dobro
                zakazanitCB.setItems(zakter);
                terapijaTF.setText(preg.getTerapija());
               
            }
  
    });
    }
    
    public void azurirajPregled(ActionEvent e) throws SQLException{
    
        try {
            Pregled preg = new Pregled();
            preg.setIDpregled(Integer.parseInt(idTF.getText()));
            preg.setNaslovPreg(nazivTF.getText());
            preg.setDijagnoza(dijagnozaTF.getText());
            preg.setOpisDijagnoze(opisdTF.getText());
            preg.setDatumPreg(datumTF.getText());
            preg.setVrijemePreg(vrijemeTF.getText());
           
            int idzt = zakazanitCB.getValue().getIDzakazano();
            preg.setFKZakazano(idzt);   
            preg.setTerapija(terapijaTF.getText());
            
            preg.uredi();
            this.initialize(null, null);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacija");
            alert.setHeaderText(null);
            alert.setContentText("Uspješno ste  ažurirali podatke pregleda! ");
            
        } catch (RuntimeException exc) {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Upozorenje");
             alert.setHeaderText("Niste unijeli sve podatke.");
             alert.setContentText("Molimo Vas da provjerite podatke koje ste unijeli i pokušate ponovno");

             alert.showAndWait();
        }
    }
    
    public void brisiPregled(ActionEvent e){
    
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Prozor za potvrdu!");
        alert.setHeaderText("Brisanje podataka!");
        alert.setContentText("Jeste li sigurni da želite obrisati odabranu stavku? ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
         
            Pregled preg = new Pregled();
            preg.setIDpregled(Integer.parseInt(idTF.getText()));
            preg.brisi();
            this.initialize(null, null);
        } else {
            alert.close();  
        }       
    }
    
   public void odbNazad(ActionEvent e){
        ((Node)(e.getSource())).getScene().getWindow().hide();   
    }
   
   
   public void odbZT(ActionEvent e) throws IOException{
       
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/ZakazaniTerminView.fxml"));
        AnchorPane prozor = loader.load();
        stage.setTitle("Pregled zakazanih termina u tablici");
        Scene scene = new Scene(prozor);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(ZTview.getScene().getWindow());
        stage.show();
        
    }
    
}
