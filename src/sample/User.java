package sample;

public class User {
    private String firstName;
    private String lastName;
    private String telefon;
    private String sex;
    private String login;
    private String passwd;


    public User(String firstName, String lastName, String telefon, String sex, String login, String passwd) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telefon = telefon;
        this.sex = sex;
        this.login = login;
        this.passwd = passwd;
    }

    public User() {}
    public static String currentUser = "Unknown";

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        currentUser = login;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

}

