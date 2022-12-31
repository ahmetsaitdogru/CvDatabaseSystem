import java.sql.*;

public class UserPage {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/cv_database";
    private static final String USER = "username";
    private static final String PASS = "password";

    private String username;

    public UserPage(String username) throws SQLException {
        this.username = username;
    }

    public void searchCVs(String keywords, String education) {

    }


    public void viewCV(String otherUsername) {

    }

    public void importCV(String filePath) {

    }


    public void writeCV(String name, String education, String skills, String experience) {


    }


    public void editCV(String name, String education, String skills, String experience) {


    }
}


