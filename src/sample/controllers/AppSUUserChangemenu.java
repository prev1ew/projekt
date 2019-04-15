package sample.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Const;
import sample.DatabaseHandler;

import static sample.controllers.AppSUUserController.*;

public class AppSUUserChangemenu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button changeMenuChangeButton;

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
        System.out.println("changeMenuUsersPhoneField" + changeMenuUsersPhoneField.getText());
        System.out.println("changeMenuUsersNameField" + changeMenuUsersNameField.getText());
        System.out.println("changeMenuUsersLNameField" + changeMenuUsersLNameField.getText());



        changeMenuCancelButton.setOnAction((event -> {

            changeMenuCancelButton.getScene().getWindow().hide();
        }));


        changeMenuChangeButton.setOnAction(event -> {
            try {
                changeMenuChangeOrder();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            changeMenuChangeButton.getScene().getWindow().hide();
        });

    }

    private void changeMenuChangeOrder() throws SQLException {
        String update = ("UPDATE " + Const.BD + "." + Const.ORDERS_TABLE + " SET " +
                Const.ORDERS_DATE_FOR_ORDER + "=\"" + changeMenuDateDDField.getText() + "-" + changeMenuDateMMField.getText() + "-" +
                changeMenuDateYYYYYField.getText() + "\", " + Const.ORDERS_TIME_FOR_ORDERS +
                "=\"" + changeMenuTimeHourField.getText() + ":" + changeMenuTimeMinField.getText() + "\", " + Const.ORDERS_SUUSERS_COM +
                "=\"" + changeMenuCommentField.getText() + "\", " + Const.ORDERS_USERS_FNAME +
                "=\"" + changeMenuUsersNameField.getText() + "\", " +  Const.ORDERS_USERS_LNAME +
                "=\"" + changeMenuUsersLNameField.getText() + "\", " + Const.ORDERS_USERPHONE +
                "=\"" + changeMenuUsersPhoneField.getText() +
                "\" WHERE (" + Const.ORDERS_ID + "=\"" + choosedOrder + "\")");
        System.out.println(update);
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.sendCommandToSQL(update);
    }
}