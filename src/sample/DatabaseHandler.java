package sample;

import java.sql.*;

/**
 * @author Vitalii
 *  Klasa ustanawiająca połączenie z bazą danych
 */
public class DatabaseHandler extends Configs {
    public static String path = "empty";
    public static String searched;

    private DatabaseHandler dc;
    Connection dbConnection;

    /**
     * Połączenie z bazą
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?verifyServerCertificate=false" +
                "&useSSL=false" + "&requireSSL=false" + "&useLegacyDatetimeCode=false" + "&amp" + "&serverTimezone=UTC";
        ;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    /**
     * Rejestracja użytkownika
     * @param user
     */
    public void signUpUser(User user) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_NAME + "," + Const.USER_LNAME + "," + Const.USER_TELEFON + "," + Const.USER_SEX +
                "," + Const.USER_LOGIN + "," + Const.USER_PASSWD + ")" + "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getFirstName());
            prSt.setString(2, user.getLastName());
            prSt.setString(3, user.getTelefon());
            prSt.setString(4, user.getSex());
            prSt.setString(5, user.getLogin());
            prSt.setString(6, user.getPasswd());

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sprawdzanie, czy użytkownik znajduje się w bazie danych (dla klienta)
     * @param user login i hasło
     * @return
     */
    public ResultSet getUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_LOGIN + "=? AND " +
                Const.USER_PASSWD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPasswd());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    /**
     * Sprawdzanie, czy użytkownik znajduje się w bazie danych (dla lekarza)
     * @param user login i hasło
     * @return
     */
    public ResultSet getSUUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.SUUSER_TABLE + " WHERE " + Const.SUUSER_LOGIN + "=? AND " +
                Const.SUUSER_PASSWD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPasswd());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    /**
     * Dodawanie rekordu w ORDERS
     * @param orderDtls Wszystkie informacje, aby dodać rekord do bazy danych
     */
    public void addNewOrder(OrderDetails orderDtls) {
        String insert = "INSERT INTO " + Const.ORDERS_TABLE + "(" + Const.ORDERS_DATE_OF_CREATION + "," + Const.ORDERS_DATE_FOR_ORDER + "," + Const.ORDERS_USERNAME +
                "," + Const.ORDERS_USERPHONE + "," + Const.ORDERS_STATE + "," + Const.ORDERS_TIME_FOR_ORDERS + "," +
                Const.ORDERS_USERS_COM + "," + Const.ORDERS_SUUSERS_COM + "," + Const.ORDERS_USERS_LNAME + "," + Const.ORDERS_USERS_FNAME + ")" +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement prStOrder = getDbConnection().prepareStatement(insert);
            prStOrder.setString(1, orderDtls.getDateOfCreation());
            prStOrder.setString(2, orderDtls.getDateForOrder());
            prStOrder.setString(3, orderDtls.getUserName());
            prStOrder.setString(4, orderDtls.getUserPhone());
            prStOrder.setString(5, orderDtls.getState());
            prStOrder.setString(6, orderDtls.getTimeForOrder());
            prStOrder.setString(7, orderDtls.getComOfUser());
            prStOrder.setString(8, orderDtls.getComOfSUUser());
            prStOrder.setString(9, orderDtls.getUserLName());
            prStOrder.setString(10, orderDtls.getUserFName());

            prStOrder.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Wyszukiwanie informacji o użytkowniku przez zalogowanie aktywnego użytkownika (za pomocą String currentUser)
     * @param where tabela, w której znajduje się żądany parametr
     * @param what żądany parametr
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void search(String where, String what) throws SQLException, ClassNotFoundException {
        if (where == Const.USER_TABLE) {
            path = Const.USER_LOGIN;
        } else if (where == Const.SUUSER_TABLE) {
            path = Const.SUUSER_LOGIN;
        } else if (where == Const.ORDERS_TABLE) {
            path = Const.ORDERS_USERNAME;
        } else {
            System.out.println("We have errors here, please check once again");
        }
        Statement statement = getDbConnection().createStatement();
        try {
            ResultSet rs = statement.executeQuery("SELECT " + what + " FROM " + Const.BD + "." + where + " WHERE " + path +
                    "=\"" + User.currentUser + "\"");
            while (rs.next()) {
                System.out.println(rs.getString(what));
                searched = rs.getString(what);

            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
    }


    /**
     * Wysyłanie do bazy danych String'a
     * @param command Wysłany zespół (String)
     */
    public void sendCommandToSQL(String command) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        try {
            PreparedStatement prSt = dbHandler.getDbConnection().prepareStatement(command);
            prSt.executeUpdate();
        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}