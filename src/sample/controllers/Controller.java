package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.User;
import sample.animations.Shake;

/**
 * @author Vitalii
 * Logowanie do programu
 */
public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField passwdField;

    @FXML
    private Button authLogInButton;

    @FXML
    private TextField loginField;

    @FXML
    private Button loginSignUpButton;

    @FXML
    void initialize() {
        //authLogInButton
        authLogInButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String loginPass = passwdField.getText().trim();

            if(!loginText.equals("") && !loginPass.equals("")) {
                loginUser(loginText, loginPass);
            } else {
                System.out.println("Login or/and pass are empty");
            }
        });
//loginSignUpButton
        loginSignUpButton.setOnAction(event -> {
            //loginSignUpButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxml/signUp.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

    /**
     * @param loginText Pole logowania
     * @param loginPass Pole hasła
     */
    void loginUser(String loginText, String loginPass) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPasswd(loginPass);
        ResultSet result = dbHandler.getUser(user);
        ResultSet result1 = dbHandler.getSUUser(user);

        int couner = 0;
        while(true) {
            try {
                if (!result.next()) {
                    break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            couner++;
        } if(couner == 1) {
            authLogInButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxml/app_user.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } else {
            while(true) {
                try {
                    if (!result1.next()) {
                        break;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                couner++;
            }
            if(couner == 1) {
                authLogInButton.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/fxml/app_suuser.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } else {
                Shake userLoginAnim = new Shake(loginField);
                Shake userPassAnim = new Shake(passwdField);
                userLoginAnim.playAnim();
                userPassAnim.playAnim();
            }
        }
    }
}
