
package uba.kontroler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import uba.model.ZakazaniTermin;

public class LijecnikPocetnaController implements Initializable {
    
    private int id_logirani;
    @FXML
    Button pregledaj,zakazi,dodajIzv,pacijentPregled,updateBtn,odjaviseBtn;
    @FXML
    TableView<ZakazaniTermin> ztID;
    @FXML
    TableColumn<ZakazaniTermin, Integer> IDzt;
    @FXML
    TableColumn<ZakazaniTermin, String> nazivTbl,vrstaTbl,datumTbl,vrijemeTbl,lijecnikTbl,pacijentTbl;
    
    @FXML
    TableColumn<ZakazaniTermin, Boolean> obavljenoTbl;
    
    @FXML
    ComboBox jeLiCB;
    @FXML
    Label idlbl;
    
    private final ObservableList<String> jeLi = FXCollections.observableArrayList(
            
            new String []{"Obavljeno", "Na čekanju"}
    );
    
    public void setIdPrijavljeni(int id){
        this.id_logirani=id;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int idL=LoginController.IDprijavljeni;
        
                
        ObservableList<ZakazaniTermin> data = ZakazaniTermin.uzmiTermineLijecnik(idL);
         
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
    
    public void Pregledaj(ActionEvent e) throws IOException{
        
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/DodajPregled.fxml"));
        AnchorPane prozor = loader.load();
        stage.setTitle("Dodaj novi pregled");
        Scene scene = new Scene(prozor);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(pregledaj.getScene().getWindow());
        stage.show();
        
    }
    
    public void Zakazi(ActionEvent e) throws IOException{
        Stage pozornica=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/ZakaziTermin.fxml"));
        AnchorPane prozor = loader.load();
        pozornica.setTitle("Zakaži novi termin");
        Scene scene = new Scene(prozor);
        pozornica.setScene(scene);
        pozornica.setResizable(false);
        pozornica.initModality(Modality.APPLICATION_MODAL);
        pozornica.initOwner(zakazi.getScene().getWindow());
        pozornica.show();
    }
    public void DodajIzvjesce(ActionEvent e) throws IOException{
        
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/DodajIzvjesce.fxml"));
        AnchorPane prozor = loader.load();
        stage.setTitle("Dodaj novo izvjesce");
        Scene scene = new Scene(prozor);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(dodajIzv.getScene().getWindow());
        stage.show();
        
    }
    
    public void PacijentiPregled(ActionEvent e) throws IOException{
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/Pacijenti.fxml"));
        AnchorPane prozor = loader.load();
        stage.setTitle("Pregled pacijenata");
        Scene scene = new Scene(prozor);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(pacijentPregled.getScene().getWindow());
        stage.show();
        
    }
    
    
     private void odbRedizTablice(){
    
        ztID.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                ZakazaniTermin zt = ztID.getItems().get(ztID.getSelectionModel().getSelectedIndex());
                idlbl.setText(String.valueOf(zt.getIDzakazano()));
                jeLiCB.setPromptText(zt.getObavljeno());
                jeLiCB.setItems(jeLi);
                
            }
  
    });
    }
     
     public void azurirajObavljeno(ActionEvent e) throws SQLException{
    
       try {
            ZakazaniTermin zt = new ZakazaniTermin();
            zt.setIDzakazano(Integer.parseInt(idlbl.getText()));
            zt.setObavljeno(jeLiCB.getSelectionModel().getSelectedItem().toString());
            zt.urediObavljeno();
            this.initialize(null, null);
            
        } catch (RuntimeException exc) {
           
        }
    }
     
     public void odjavise(ActionEvent e) throws IOException{
       
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/Login.fxml"));
        AnchorPane prozor = loader.load();
        stage.setTitle("Prijavi se");
        Scene scene = new Scene(prozor);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.NONE);
        stage.show();
       
        ((Node)(e.getSource())).getScene().getWindow().hide();
    }
    
}
