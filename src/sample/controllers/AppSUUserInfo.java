package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static sample.controllers.AppSUUserController.*;
import static sample.controllers.AppSUUserController.choosedOrderPhone;

/**
 * @author Vitalii
 * Możliwe jest przeglądanie informacji bez możliwości wprowadzania zmian
 */
public class AppSUUserInfo {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button changeMenuCancelButton;

    @FXML
    private TextField changeMenuCommentField;

    @FXML
    private TextField changeMenuTimeHourField;

    @FXML
    private TextField changeMenuTimeMinField;

    @FXML
    private TextField changeMenuDateDDField;

    @FXML
    private TextField changeMenuDateMMField;

    @FXML
    private TextField changeMenuDateYYYYYField;

    @FXML
    private TextField changeMenuUsersNameField;

    @FXML
    private TextField changeMenuUsersLNameField;

    @FXML
    private TextField changeMenuUsersPhoneField;

    @FXML
    void initialize() {
        changeMenuDateDDField.setText(choosedOrderDate.substring(0, 2));
        changeMenuDateMMField.setText(choosedOrderDate.substring(3, 5));
        changeMenuDateYYYYYField.setText(choosedOrderDate.substring(6, 10));
        changeMenuTimeHourField.setText(choosedOrderTime.substring(0, 2));
        changeMenuTimeMinField.setText(choosedOrderTime.substring(3, 5));
        changeMenuCommentField.setText(choosedOrderLName);
        changeMenuUsersNameField.setText(choosedOrderFName);
        changeMenuUsersLNameField.setText(choosedOrderSUUsersComment);
        changeMenuUsersPhoneField.setText(choosedOrderPhone);
        //changeMenuCancelButton
        changeMenuCancelButton.setOnAction((event -> {

            changeMenuCancelButton.getScene().getWindow().hide();
        }));
    }
}
