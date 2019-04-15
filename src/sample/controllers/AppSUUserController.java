package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Const;
import sample.DatabaseHandler;
import sample.OrderDetails;

public class AppSUUserController {
    private DatabaseHandler dc;
    private ObservableList<OrderDetails> data;
    public static String choosedOrder = "";
    public static String choosedOrderDate = "";
    public static String choosedOrderTime = "";
    public static String choosedOrderSUUsersComment = "";
    public static String choosedOrderPhone = "";
    public static String choosedOrderLName = "";
    public static String choosedOrderFName = "";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<OrderDetails> appSUUserTable;

    @FXML
    private TableColumn<OrderDetails, String> tableDateCol;

    @FXML
    private TableColumn<OrderDetails, String> tableTimeCol;

    @FXML
    private TableColumn<OrderDetails, String> tableUsersComCol;

    @FXML
    private TableColumn<OrderDetails, String> tableSUComCol;

    @FXML
    private TableColumn<OrderDetails, String> tableStateCol;

    @FXML
    private Button appSUUserAddButton;

    @FXML
    private Button appSUUserChangeButton;

    @FXML
    private Button appSUUserRemoveButton;

    @FXML
    private Button appSUUserRefreshBut;

    @FXML
    private Button appSUUserInfoButton;

    @FXML
    private Button appSUUserAcceptButton;

    @FXML
    private Button appSUUserDeclineButton;

    @FXML
    void loadDataFromDatabase(ActionEvent event) {

        Connection conn = null;
        try {
            conn = dc.getDbConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        data = FXCollections.observableArrayList();

        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM " + Const.BD + "." + Const.ORDERS_TABLE);
            while (rs.next()) {
                data.add(new OrderDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)));
            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        tableDateCol.setCellValueFactory(new PropertyValueFactory<>("dateForOrder"));
        tableTimeCol.setCellValueFactory(new PropertyValueFactory<>("timeForOrder"));
        tableUsersComCol.setCellValueFactory(new PropertyValueFactory<>("comOfUser"));
        tableSUComCol.setCellValueFactory(new PropertyValueFactory<>("userLName"));
        tableStateCol.setCellValueFactory(new PropertyValueFactory<>("state"));

        appSUUserTable.setItems(null);
        appSUUserTable.setItems(data);

    }

    @FXML
    void initialize() {
        dc = new DatabaseHandler();

        appSUUserAddButton.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxml/app_suuser_addmenu.fxml"));

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

        appSUUserRemoveButton.setOnAction(event -> {
            OrderDetails orderDet = (OrderDetails) appSUUserTable.getSelectionModel().getSelectedItem();
            orderDet.getTimeForOrder();
            String remove = ("DELETE FROM " + Const.ORDERS_TABLE + " where " + Const.ORDERS_ID + "=\""
                    + orderDet.getIDorders() + "\"");
            System.out.println(remove);
            dc.sendCommandToSQL(remove);

            Connection conn = null;
            try {
                conn = dc.getDbConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            data = FXCollections.observableArrayList();

            try {
                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM " + Const.BD + "." + Const.ORDERS_TABLE);
                while (rs.next()) {
                    data.add(new OrderDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                            rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)));
                }
            } catch (SQLException e) {
                System.err.println("Error" + e);
            }
            tableDateCol.setCellValueFactory(new PropertyValueFactory<>("dateForOrder"));
            tableTimeCol.setCellValueFactory(new PropertyValueFactory<>("timeForOrder"));
            tableUsersComCol.setCellValueFactory(new PropertyValueFactory<>("comOfUser"));
            tableSUComCol.setCellValueFactory(new PropertyValueFactory<>("userLName"));
            tableStateCol.setCellValueFactory(new PropertyValueFactory<>("state"));

            appSUUserTable.setItems(null);
            appSUUserTable.setItems(data);

        });

        appSUUserChangeButton.setOnAction(event -> {
            OrderDetails orderDet = (OrderDetails) appSUUserTable.getSelectionModel().getSelectedItem();
            choosedOrder = orderDet.getIDorders();
            choosedOrderDate = orderDet.getDateForOrder();
            choosedOrderTime = orderDet.getTimeForOrder();
            choosedOrderSUUsersComment = orderDet.getComOfSUUser();
            choosedOrderPhone = orderDet.getUserPhone();
            choosedOrderFName = orderDet.getUserFName();
            choosedOrderLName = orderDet.getUserLName();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxml/app_suuser_changemenu.fxml"));

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

        appSUUserInfoButton.setOnAction(event -> {
            OrderDetails orderDet = (OrderDetails) appSUUserTable.getSelectionModel().getSelectedItem();
            choosedOrder = orderDet.getIDorders();
            choosedOrderDate = orderDet.getDateForOrder();
            choosedOrderTime = orderDet.getTimeForOrder();
            choosedOrderSUUsersComment = orderDet.getComOfSUUser();
            choosedOrderPhone = orderDet.getUserPhone();
            choosedOrderFName = orderDet.getUserFName();
            choosedOrderLName = orderDet.getUserLName();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxml/app_suuser_info.fxml"));

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
}
