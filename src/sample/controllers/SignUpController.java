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

        suBackButton.setOnAction((event -> {
            suBackButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxml/sample.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        }));

        suCreateButton.setOnAction(event -> {
            suNewUser();
        });

    }

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
        //ififififif...else
        User user = new User(firstName, lastName, telefon, sex, login, passwd);

        dbHandler.signUpUser(user);
    }
}
