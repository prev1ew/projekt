package sample.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.DatabaseHandler;
import sample.OrderDetails;

import static sample.User.currentUser;

public class AppSUUserAddMenu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addMenuCreateButton;

    @FXML
    private Button addMenuCancelButton;

    @FXML
    private TextField addMenuCommentField;

    @FXML
    private TextField addMenuTimeHourField;

    @FXML
    private TextField addMenuTimeMinField;

    @FXML
    private TextField addMenuDateDDField;

    @FXML
    private TextField addMenuDateMMField;

    @FXML
    private TextField addMenuDateYYYYYField;

    @FXML
    private TextField addMenuUsersNameField;

    @FXML
    private TextField addMenuUsersLNameField;

    @FXML
    private TextField addMenuUsersPhoneField;

    @FXML
    void initialize() {

        addMenuCancelButton.setOnAction((event -> {
            addMenuCancelButton.getScene().getWindow().hide();
        }));

        addMenuCreateButton.setOnAction(event -> {
            try {
                addMenuNewOrder();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            addMenuCreateButton.getScene().getWindow().hide();
        });

    }

    private void addMenuNewOrder() throws SQLException {

        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        DatabaseHandler dbHandler = new DatabaseHandler();
        String dateOfCreation = date;
        String dateForOrder = addMenuDateDDField.getText() + "-" + addMenuDateMMField.getText() + "-" + addMenuDateYYYYYField.getText();
        String userName = "Created by " + currentUser;
        String userPhone = addMenuUsersPhoneField.getText();
        String state = "New";
        String timeForOrder = addMenuTimeHourField.getText() + ":" + addMenuTimeMinField.getText();
        String comOfUser = "Created by " + currentUser;
        String comOfSUUser = addMenuCommentField.getText();
        String userLName = (addMenuUsersLNameField.getText());
        String userFName = (addMenuUsersNameField.getText());


        OrderDetails order = new OrderDetails(null, dateOfCreation, dateForOrder, userName, userPhone, state,
                timeForOrder, comOfUser, comOfSUUser, userLName, userFName);

        dbHandler.addNewOrder(order);

    }
}