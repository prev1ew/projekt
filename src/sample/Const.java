package sample;

/**
 * @author Vitalii
 * Wartości stali, dla łatwego wprowadzania zmian
 */
public class Const {

    /**
     * Nazwa bazy danych
     */
    public static final String BD = "projekt";

    /**
     * Wartość tabeli USER (klientów)
     */
    public static final String USER_TABLE = "users";
    public static final String USER_ID = "idusers";
    public static final String USER_NAME = "name";
    public static final String USER_LNAME = "lastname";
    public static final String USER_TELEFON = "telefon";
    public static final String USER_SEX = "sex";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWD = "password";

    /**
     * Wartość tabeli SUUSER (lekarze)
     */
    public static final String SUUSER_TABLE = "suusers";
    public static final String SUUSER_ID = "idsuusers";
    public static final String SUUSER_NAME = "suname";
    public static final String SUUSER_LNAME = "sulastname";
    public static final String SUUSER_TELEFON = "sutelefon";
    public static final String SUUSER_SEX = "susex";
    public static final String SUUSER_LOGIN = "sulogin";
    public static final String SUUSER_PASSWD = "supasswd";

    /**
     * Wartość tabeli ORDERS (zapisy na przyjęcia)
     */
    public static final String ORDERS_TABLE = "orders";
    public static final String ORDERS_ID = "idorders";
    public static final String ORDERS_DATE_OF_CREATION = "dateOfCreation";
    public static final String ORDERS_DATE_FOR_ORDER = "dateForOrder";
    public static final String ORDERS_USERNAME = "userName";
    public static final String ORDERS_USERPHONE = "userPhone";
    public static final String ORDERS_STATE = "state";
    public static final String ORDERS_TIME_FOR_ORDERS = "timeForOrder";
    public static final String ORDERS_USERS_COM = "comOfUser";
    public static final String ORDERS_SUUSERS_COM = "comOfSUUser";
    public static final String ORDERS_USERS_LNAME = "userLName";
    public static final String ORDERS_USERS_FNAME = "userFName";

}
