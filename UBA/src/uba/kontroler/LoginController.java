
package uba.kontroler;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uba.model.Korisnici;


public class LoginController implements Initializable {
    
    public static String tipKor;
    public static int IDprijavljeni;
    
    @FXML
    Label statusLbl;
    @FXML 
    TextField korImeTxt;
    @FXML
    PasswordField lozinkaTxt;
    @FXML 
    Button prijavaBtn;
    
    public void prijavise (ActionEvent e){
        
        String KorIme = korImeTxt.getText();
        String lozinka = lozinkaTxt.getText();
        
        
        if(KorIme.equals("") || lozinka.equals("")){
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacija");
            alert.setHeaderText(null);
            alert.setContentText("Uspješna prijava!");
        }
        else{
            if(Korisnici.prijavi(KorIme,lozinka))
            {
                
               
                Korisnici k1 = new Korisnici(KorIme, lozinka);
                IDprijavljeni=k1.dajIDPrijavljeni();
                System.out.println(IDprijavljeni);
                tipKor=k1.dajTip();
                
                if(tipKor.equals("Admin")){
                   
                    try{
                    
                    statusLbl.setTextFill(Color.GREEN);
                    statusLbl.setText("Uspješna prijava!" + tipKor);
                    
                    
                  
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/AdminPocetna.fxml"));
                    AnchorPane prozor = loader.load();
                    
                    AdminPocetnaController controller=loader.getController();
                    controller.setIdPrijavljeni(k1.getIDkorisnik());

                    Scene scene = new Scene(prozor);
                    stage.setScene(scene);
                    stage.setTitle("Admin");
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();                    

                    ((Node)(e.getSource())).getScene().getWindow().hide();
                    
                    }catch (IOException ex){
                    
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (tipKor.equals("Lijecnik")){
                    
                    try {
                        statusLbl.setTextFill(Color.GREEN);
                        statusLbl.setText("Uspješna prijava!" + tipKor);
                        
                        Stage stage = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/LijecnikPocetna.fxml"));
                        AnchorPane prozor = loader.load();
                        
                        LijecnikPocetnaController controller = loader.getController();
                        controller.setIdPrijavljeni(k1.getIDkorisnik());
                        
                        Scene scene = new Scene(prozor);
                        stage.setScene(scene);
                        stage.setTitle("Lijecnik");
                        stage.setResizable(false);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.show();                        
                        
                        ((Node) (e.getSource())).getScene().getWindow().hide();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (tipKor.equals("Medicinska sestra")){
                    
                    try {
                        statusLbl.setTextFill(Color.GREEN);
                        statusLbl.setText("Uspješna prijava!" + tipKor);
                        
                        Stage stage = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uba/view/MedSestraPocetna.fxml"));
                        AnchorPane prozor = loader.load();
                        
                        MedSestraPocetnaController controller = loader.getController();
                        controller.setIdPrijavljeni(k1.getIDkorisnik());
                        
                        Scene scene = new Scene(prozor);
                        stage.setScene(scene);
                        stage.setTitle("Medicinska sestra");
                        stage.setResizable(false);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.show();                        
                        
                        ((Node) (e.getSource())).getScene().getWindow().hide();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                }
            else
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Greška");
                alert.setHeaderText("Unijeli ste netočne korisničke podatke");
                alert.setContentText("Pokušajte ponovno!");

                alert.showAndWait();
            }
        
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
