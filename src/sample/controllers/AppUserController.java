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
import sample.User;



public class AppUserController {
    public static String choosedOrder = "";
    public static String choosedOrderDate = "";
    public static String choosedOrderTime = "";
    public static String choosedOrderUsersComment = "";
    private ObservableList<OrderDetails>data;
    private DatabaseHandler dc;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<OrderDetails> appUserTable;

    @FXML
    private TableColumn<OrderDetails, String> tableDataCol;

    @FXML
    private TableColumn<OrderDetails, String> tableTimeCol;

    @FXML
    private TableColumn<OrderDetails, String> tableComCol;

    @FXML
    private TableColumn<OrderDetails, String> tableStateCol;

    @FXML
    private Button appUserAddButton;

    @FXML
    private Button appUserChangeButton;

    @FXML
    private Button appUserRemoveButton;

    @FXML
    private Button appUserRefreshBut;

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
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM " + Const.BD + "." + Const.ORDERS_TABLE + " WHERE " + Const.ORDERS_USERNAME +
                    "=\"" + User.currentUser + "\"");
            while (rs.next()) {
                data.add(new OrderDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)));
            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        tableDataCol.setCellValueFactory(new PropertyValueFactory<>("dateForOrder"));
        tableTimeCol.setCellValueFactory(new PropertyValueFactory<>("timeForOrder"));
        tableComCol.setCellValueFactory(new PropertyValueFactory<>("comOfUser"));
        tableStateCol.setCellValueFactory(new PropertyValueFactory<>("state"));

        appUserTable.setItems(null);
        appUserTable.setItems(data);
    }



    @FXML
    void initialize() {
        dc = new DatabaseHandler();

        appUserAddButton.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxml/app_user_addmenu.fxml"));

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

        appUserRemoveButton.setOnAction(event -> {
            DatabaseHandler dbHandler = new DatabaseHandler();
            OrderDetails orderDet = (OrderDetails)appUserTable.getSelectionModel().getSelectedItem();
            orderDet.getTimeForOrder();
            String remove = ("DELETE FROM " + Const.ORDERS_TABLE + " where " + Const.ORDERS_ID + "=\""
            + orderDet.getIDorders() + "\"");
            System.out.println(remove);
            dbHandler.sendCommandToSQL(remove);

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
                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM " + Const.BD + "." + Const.ORDERS_TABLE + " WHERE " + Const.ORDERS_USERNAME +
                        "=\"" + User.currentUser + "\"");
                while (rs.next()) {
                    data.add(new OrderDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                            rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)));
                }
            } catch (SQLException e) {
                System.err.println("Error" + e);
            }
            tableDataCol.setCellValueFactory(new PropertyValueFactory<>("dateForOrder"));
            tableTimeCol.setCellValueFactory(new PropertyValueFactory<>("timeForOrder"));
            tableComCol.setCellValueFactory(new PropertyValueFactory<>("comOfUser"));
            tableStateCol.setCellValueFactory(new PropertyValueFactory<>("state"));

            appUserTable.setItems(null);
            appUserTable.setItems(data);

        });

        appUserChangeButton.setOnAction(event -> {
            OrderDetails orderDet = (OrderDetails)appUserTable.getSelectionModel().getSelectedItem();
            choosedOrder = orderDet.getIDorders();
            choosedOrderDate = orderDet.getDateForOrder();
            choosedOrderTime = orderDet.getTimeForOrder();
            choosedOrderUsersComment = orderDet.getComOfUser();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxml/app_user_changemenu.fxml"));

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
