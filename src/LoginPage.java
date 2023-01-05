import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame implements ActionListener {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.sqlite.JDBC";
    static final String DB_URL = "jdbc:sqlite:D://sqlitedb/CvDatabase.db";

    //  Database information
     String username = "username";
     String password = "password";

    // GUI elements
    JLabel usernameLabel;
    JLabel passwordLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;
    JButton signupButton;

    public LoginPage() {
        super("CV Database - Login");

        // Set layout
        setLayout(new FlowLayout());

        // Initialize GUI elements
        usernameLabel = new JLabel("Username: ");
        passwordLabel = new JLabel("Password: ");
        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        loginButton = new JButton("Login");
        signupButton = new JButton("Sign Up");

        // Add action listeners to buttons
        loginButton.addActionListener(this);
        signupButton.addActionListener(this);

        // Add GUI elements to the frame
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(signupButton);

        // Set the size of the frame and make it visible
        setSize(300, 150);
        setVisible(true);
    }

    // Handle button clicks
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            // Get the username and password from the text fields
            String UsNa = usernameField.getText();
            String Pass = String.valueOf(passwordField.getPassword());



            // Check if the username and password are correct
            if (checkinformation(UsNa,Pass)==true) {
                // If the information are correct, go to the main menu
                MainMenu menu = new MainMenu(UsNa);
                menu.setVisible(true);
                setVisible(false);
            } else {
                // If the information are incorrect, show an error message
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == signupButton) {
            // If the user clicks the signup button, go to the signup page
            SignUpPage signup = new SignUpPage();
            signup.setVisible(true);
            setVisible(false);
        }
    }

    // Check if the given username and password are correct
    public boolean checkinformation(String username, String password) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 2: Register JDBC driver

            Class.forName(JDBC_DRIVER);

            // STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);

            // STEP 4: Execute a query
            System.out.println("Checking information...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM User WHERE username='" + username + "' AND password='" + password + "'";
            ResultSet rs = stmt.executeQuery(sql);

            // If there is a result, the information are correct
            if (rs.next()) {
                return true;
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            } // Nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } // End finally try
        } // End try
        return false;
    }


}
