
package uba.kontroler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import uba.model.Izvjesce;
import uba.model.Osoblje;
import uba.model.Pacijent;


public class DodajIzvjesceController implements Initializable {
    
    @FXML
    Button nazadBtn,izvrsiDodajBtn,dodajPacijent;
    
    @FXML
    TextField vrstaTF,nazivTF,opisTF,vrijemeTF;
    
    @FXML
    ComboBox <Osoblje> lijecnikCB;
    @FXML
    ComboBox <Pacijent> pacijentCB;
    @FXML
    ComboBox vrstaCB;
    
    @FXML
    DatePicker odbDatum;
    
    
    private final ObservableList<Osoblje> lijecnici = FXCollections.observableArrayList(
            Osoblje.ImePrezimeOsoblje()
     );
   
    private final ObservableList<Pacijent> pacijenti = FXCollections.observableArrayList(
            Pacijent.ImePrezimePacijent()
     ); 
    
    private final ObservableList<String> vrste = FXCollections.observableArrayList(
    
            new String[]{"smrt","rodjenje","operacija"}
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
    
    public void spremiIzvjesce(ActionEvent e)throws SQLException{
        
        try {
            
            int idDoc = lijecnikCB.getValue().getIDkorisnik();
            int idPac = pacijentCB.getValue().getIDpacijent();
            
            Izvjesce iz = new Izvjesce(nazivTF.getText(), opisTF.getText(),
                    vrijemeTF.getText());
            
            iz.setVrstaIzvjesca(vrstaCB.getSelectionModel().getSelectedItem().toString());
            iz.setDatumIz(formater.format(odbDatum.getValue()));
            
            iz.setFKLijecnik(idDoc);
            iz.setFKPacijent(idPac);
            iz.spasi();
            
            this.initialize(null, null);
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informacija");
            alert.setHeaderText(null);
            alert.setContentText("Uspješno ste dodali novo izvješće! ");

            alert.showAndWait();
            
        } catch (RuntimeException exc) {
             Alert alert = new Alert(AlertType.WARNING);
             alert.setTitle("Upozorenje");
             alert.setHeaderText("Niste unijeli sve podatke.");
             alert.setContentText("Molimo Vas da provjerite podatke koje ste unijeli i pokušate ponovno.");

             alert.showAndWait();
        }
    }
    
    private final DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //formatirnje datuma u string i obrnuto
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
        
        lijecnikCB.setItems(lijecnici);
        pacijentCB.setItems(pacijenti);
        vrstaCB.setItems(vrste);
    }    
    
    public void odbNazad(ActionEvent e){
        ((Node)(e.getSource())).getScene().getWindow().hide();   
    }
    
}
