
package uba.kontroler;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import uba.model.Pacijent;


public class PacijentiController implements Initializable {

    public static int idP;
    
    @FXML
    private Button nazadBtn;
    @FXML
    private TableView<Pacijent> pacijentiID;
    @FXML
    private TableColumn<Pacijent, Integer> IDpacijent;
    @FXML
    private TableColumn<Pacijent, String> ime,prezime,spol,telefon,adresa,email,datRodjenja,JMBG;
    
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
           
        });
      }
    public void odbNazad(ActionEvent e){
        ((Node)(e.getSource())).getScene().getWindow().hide();   
    }
}
