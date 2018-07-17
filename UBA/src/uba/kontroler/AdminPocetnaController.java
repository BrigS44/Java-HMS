
package uba.kontroler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminPocetnaController implements Initializable {

    private int id_logirani;
   
    @FXML
    Button osobljeView,pacijentiView,preglediView,receptiView,testoviView,izvjesceView,odjeliView,specView,odjaviseBtn;
    
    public void setIdPrijavljeni(int id){
        this.id_logirani=id;
    }
    
    public void odbOsoblje(ActionEvent e) throws IOException{
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/OsobljeView.fxml"));
        AnchorPane prozor = loader.load();
        stage.setTitle("Pregled osoblja u tablici");
        Scene scene = new Scene(prozor);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(osobljeView.getScene().getWindow());
        stage.show();
        
    }
    
    public void odbPacijent(ActionEvent e) throws IOException{
        Stage pozornica=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/PacijentView.fxml"));
        AnchorPane prozor = loader.load();
        pozornica.setTitle("Pregled pacijenata");
        Scene scene = new Scene(prozor);
        pozornica.setScene(scene);
        pozornica.setResizable(false);
        pozornica.initModality(Modality.APPLICATION_MODAL);
        pozornica.initOwner(pacijentiView.getScene().getWindow());
        pozornica.show();
    }
    public void odbPregled(ActionEvent e) throws IOException{
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/PregledView.fxml"));
        AnchorPane prozor = loader.load();
        stage.setTitle("Pregled obavljenih pregleda u tablici");
        Scene scene = new Scene(prozor);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(preglediView.getScene().getWindow());
        stage.show();
        
    }
    
    public void odbRecept(ActionEvent e) throws IOException{
       Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/TestoviView.fxml"));
        AnchorPane prozor = loader.load();
        stage.setTitle("");
        Scene scene = new Scene(prozor);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(receptiView.getScene().getWindow());
        stage.show();
        
    }
    public void odbTest(ActionEvent e) throws IOException{
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/TestoviView.fxml"));
        AnchorPane prozor = loader.load();
        stage.setTitle("");
        Scene scene = new Scene(prozor);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(testoviView.getScene().getWindow());
        stage.show();
        
    }
    
    public void odbIzvjesce(ActionEvent e) throws IOException{
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/IzvjescaView.fxml"));
        AnchorPane prozor = loader.load();
        stage.setTitle("Pregled izvjesca u tablici");
        Scene scene = new Scene(prozor);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(izvjesceView.getScene().getWindow());
        stage.show();
        
    }
    public void odbOdjeli(ActionEvent e) throws IOException{
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/OdjeliView.fxml"));
        AnchorPane prozor = loader.load();
        stage.setTitle("Pregled odjela u tablici");
        Scene scene = new Scene(prozor);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(odjeliView.getScene().getWindow());
        stage.show();
        
    }
    
    public void odbSpec(ActionEvent e) throws IOException{
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/SpecView.fxml"));
        AnchorPane prozor = loader.load();
        stage.setTitle("Pregled specijalizacija u tablici");
        Scene scene = new Scene(prozor);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(specView.getScene().getWindow());
        stage.show();
        
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
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
