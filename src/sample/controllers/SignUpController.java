package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.User;
import sample.animations.Shake;

/**
 * @author Vitalii
 * Rejestracja użytkownika
 */
public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField suPasswdField;

    @FXML
    private Button suCreateButton;

    @FXML
    private TextField suLoginField;

    @FXML
    private TextField suPhoneField;

    @FXML
    private TextField suLastNameField;

    @FXML
    private TextField suNameField;

    @FXML
    private RadioButton suFMRadBut;

    @FXML
    private RadioButton suMRadBut;

    @FXML
    private Button suBackButton;

    @FXML
    void initialize() {
//suBackButton
        suBackButton.setOnAction((event -> {
            suBackButton.getScene().getWindow().hide();
        }));
//suCreateButton
        suCreateButton.setOnAction(event -> {
            suNewUser();
        });

    }

    /**
     * Rejestracja
     */
    private void suNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String firstName = suNameField.getText();
        String lastName = suLastNameField.getText();
        String telefon = suPhoneField.getText();
        String sex = "";
        if(suMRadBut.isSelected()) {
            sex = "Male";
        } else {
            sex = "Female";
        }
        String login = suLoginField.getText();
        String passwd = suPasswdField.getText();
        if(suLoginField.getText().length()<4){
            Shake suLFanum = new Shake(suLoginField);
            suLFanum.playAnim();
        } else if(suPasswdField.getText().length()<4) {
            Shake suPWFanum = new Shake(suPasswdField);
            suPWFanum.playAnim();
        } else if(suPhoneField.getText().length()<4) {
            Shake suFFanum = new Shake(suPhoneField);
            suFFanum.playAnim();
        }
        User user = new User(firstName, lastName, telefon, sex, login, passwd);

        dbHandler.signUpUser(user);
        suBackButton.getScene().getWindow().hide();
    }
}
