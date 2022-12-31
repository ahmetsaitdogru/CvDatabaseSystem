import java.sql.*; //burayı database

public class Login {
    private static final String JDBC_DRIVER = "database driverı buraya";
    private static final String DB_URL = "database url buraya gelecek";//
    private static final String USER = "username";
    private static final String PASS = "password";

    public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return;
        }

        String username = "";
        String password = "";
        String accountType = "";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM admin WHERE username='" + username + "' AND password='" + password + "'");
            if (rs.next()) {
                accountType = "admin";
            } else {
                rs = stmt.executeQuery("SELECT * FROM user WHERE username='" + username + "' AND password='" + password + "'");
                if (rs.next()) {
                    accountType = "user";
                } else {
                    System.out.println("Invalid username or password.");
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        if (accountType.equals("admin")) {
            // Show the admin page
        } else if (accountType.equals("user")) {
            // Show the user page
        }
    }
}
