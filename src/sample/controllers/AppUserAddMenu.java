package sample.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Const;
import sample.DatabaseHandler;
import sample.OrderDetails;

import static sample.DatabaseHandler.searched;
import static sample.User.currentUser;

/**
 * @author Vitalii
 * Tworzenie przyjęć po stronie klienta
 */
public class AppUserAddMenu {

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
    void initialize() {
        //addMenuCancelButton
        addMenuCancelButton.setOnAction((event -> {
            addMenuCancelButton.getScene().getWindow().hide();
        }));
        //addMenuCreateButton
        addMenuCreateButton.setOnAction(event -> {
            try {
                addMenuNewOrder();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            addMenuCreateButton.getScene().getWindow().hide();
        });

    }

    /**
     * Dodawanie do bazy danych
     * @throws SQLException
     */
    private void addMenuNewOrder() throws SQLException {

        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        DatabaseHandler dbHandler = new DatabaseHandler();
        String dateOfCreation = date;
        String dateForOrder = addMenuDateDDField.getText() + "-" + addMenuDateMMField.getText() + "-" + addMenuDateYYYYYField.getText();
        String userName = currentUser;
        String userPhone = null;
        try {
            dbHandler.search(Const.USER_TABLE, Const.USER_TELEFON);
            userPhone = searched;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String state = "New";
        String timeForOrder = addMenuTimeHourField.getText() + ":" + addMenuTimeMinField.getText();
        String comOfUser = addMenuCommentField.getText();
        String comOfSUUser = "";
        String userLName = null;
        try {
            dbHandler.search(Const.USER_TABLE, Const.USER_LNAME);
            userLName = searched;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String userFName = null;
        try {
            dbHandler.search(Const.USER_TABLE, Const.USER_NAME);
            userFName = searched;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        OrderDetails order = new OrderDetails(null,dateOfCreation, dateForOrder, userName, userPhone, state,
                timeForOrder, comOfUser, comOfSUUser, userLName, userFName);

        dbHandler.addNewOrder(order);

    }
}
