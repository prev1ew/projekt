package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * @author Vitalii
 * Zapisz informacje o przyjęciu do lekarza
 */
public class OrderDetails extends Const {
    private final StringProperty idorders;
    private final StringProperty dateOfCreation;
    private final StringProperty dateForOrder;
    private final StringProperty userName;
    private final StringProperty userPhone;
    private final StringProperty state;
    private final StringProperty timeForOrder;
    private final StringProperty comOfUser;
    private final StringProperty comOfSUUser;
    private final StringProperty userLName;
    private final StringProperty userFName;

    /**
     * @param idorders id
     * @param dateOfCreation Data utworzenia
     * @param dateForOrder Data przyjęcia
     * @param userName Login klienta
     * @param userPhone Telefon klienta
     * @param state Stan
     * @param timeForOrder Czas przyjęcia
     * @param comOfUser Komentarz klienta
     * @param comOfSUUser Komentarz lekarza
     * @param userLName Nazwisko klienta
     * @param userFName Imię klienta
     */
    public OrderDetails(String idorders, String dateOfCreation, String dateForOrder, String userName, String userPhone, String state, String timeForOrder,
                        String comOfUser, String comOfSUUser, String userLName, String userFName) {
        this.idorders = new SimpleStringProperty(idorders);
        this.dateOfCreation = new SimpleStringProperty(dateOfCreation);
        this.dateForOrder = new SimpleStringProperty(dateForOrder);
        this.userName = new SimpleStringProperty(userName);
        this.userPhone = new SimpleStringProperty(userPhone);
        this.state = new SimpleStringProperty(state);
        this.timeForOrder = new SimpleStringProperty(timeForOrder);
        this.comOfUser = new SimpleStringProperty(comOfUser);
        this.comOfSUUser = new SimpleStringProperty(comOfSUUser);
        this.userLName = new SimpleStringProperty(userLName);
        this.userFName = new SimpleStringProperty(userFName);
    }

    /**
     * Getters
     * @return
     */
    public String getIDorders() {
        return idorders.get();
    }
    public String getDateOfCreation() {
        return dateOfCreation.get();
    }
    public String getDateForOrder() {
        return dateForOrder.get();
    }
    public String getUserName() {
        return userName.get();
    }
    public String getUserPhone() {
        return userPhone.get();
    }
    public String getState() {
        return state.get();
    }
    public String getTimeForOrder() {
        return timeForOrder.get();
    }
    public String getComOfUser() {
        return comOfUser.get();
    }
    public String getComOfSUUser() {
        return comOfSUUser.get();
    }
    public String getUserLName() {
        return userLName.get();
    }
    public String getUserFName() {
        return userFName.get();
    }


    /**
     * Setters
     * @param value
     */
    public void setIDorders(String value) {
        idorders.set(value);
    }
    public void setDateOfCreation(String value) {
        dateOfCreation.set(value);
    }
    public void setDateForOrder(String value) {
        dateForOrder.set(value);
    }
    public void setUserName(String value) {
        userName.set(value);
    }
    public void setUserPhone(String value) {
        userPhone.set(value);
    }
    public void setState(String value) {
        state.set(value);
    }
    public void setTimeForOrder(String value) {
        timeForOrder.set(value);
    }
    public void setComOfUser(String value) {
        comOfUser.set(value);
    }
    public void setComOfSUUser(String value) {
        comOfSUUser.set(value);
    }
    public void setUserLName(String value) {
        userLName.set(value);
    }
    public void setUserFName(String value) {
        userFName.set(value);
    }



    /**
     * Dla observable lists
     * @return
     */
    public StringProperty idorders() {
        return idorders;
    }
    public StringProperty dateOfCreationProperty() {
        return dateOfCreation;
    }
    public StringProperty dateForOrderProperty() {
        return dateForOrder;
    }
    public StringProperty userNameProperty() {
        return userName;
    }
    public StringProperty userPhoneProperty() {
        return userPhone;
    }
    public StringProperty stateProperty() {
        return state;
    }
    public StringProperty timeForOrderProperty() {
        return timeForOrder;
    }
    public StringProperty comOfUserProperty() {
        return comOfUser;
    }
    public StringProperty comOfSUUserProperty() {
        return comOfSUUser;
    }
    public StringProperty userLName() {
        return userLName;
    }
    public StringProperty userFName() {
        return userFName;
    }
}

