package sample.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Const;
import sample.DatabaseHandler;

import static sample.controllers.AppUserController.*;

/**
 * @author Vitalii
 * Zmiana przyjęć po stronie klienta
 */
public class AppUserChangemenu {

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
    void initialize() {


        changeMenuDateDDField.setText(choosedOrderDate.substring(0,2));
        changeMenuDateMMField.setText(choosedOrderDate.substring(3,5));
        changeMenuDateYYYYYField.setText(choosedOrderDate.substring(6,10));
        changeMenuTimeHourField.setText(choosedOrderTime.substring(0,2));
        changeMenuTimeMinField.setText(choosedOrderTime.substring(3,5));
        changeMenuCommentField.setText(choosedOrderUsersComment);


//changeMenuCancelButton
        changeMenuCancelButton.setOnAction((event -> {
            changeMenuCancelButton.getScene().getWindow().hide();
        }));

//changeMenuChangeButton
        changeMenuChangeButton.setOnAction(event -> {
            try {
                changeMenuChangeOrder();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            changeMenuChangeButton.getScene().getWindow().hide();
        });

    }

    /**
     * Zmiana do bazy danych
     * @throws SQLException
     */
    private void changeMenuChangeOrder() throws SQLException {
        String update = ("UPDATE " + Const.BD + "." + Const.ORDERS_TABLE + " SET " +
                Const.ORDERS_DATE_FOR_ORDER + "=\"" + changeMenuDateDDField.getText() + "-" + changeMenuDateMMField.getText() + "-" +
                changeMenuDateYYYYYField.getText() + "\", " + Const.ORDERS_TIME_FOR_ORDERS +
                "=\"" + changeMenuTimeHourField.getText() + ":" + changeMenuTimeMinField.getText() + "\", " + Const.ORDERS_USERS_COM +
                "=\"" + changeMenuCommentField.getText() + "\" WHERE (" + Const.ORDERS_ID + "=\"" + choosedOrder + "\")");
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.sendCommandToSQL(update);
    }
}