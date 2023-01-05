import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUpPage extends JFrame implements ActionListener {

    static final String JDBC_DRIVER = "org.sqlite.JDBC";
    static final String DB_URL = "jdbc:sqlite:CVDatabase.db";
    static final String USER = "username";
    static final String PASS = "password";

    JLabel usernameLabel;
    JTextField usernameField;
    JLabel passwordLabel;
    JPasswordField passwordField;
    JLabel emailLabel;
    JTextField emailField;
    JButton signUpButton;
    JButton backButton;

    public SignUpPage() {
        super("CV Database - Sign Up");

        setLayout(new FlowLayout());

        usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField(10);
        passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField(10);
        emailLabel = new JLabel("Email: ");
        emailField = new JTextField(10);
        signUpButton = new JButton("Sign Up");
        backButton = new JButton("Back");

        signUpButton.addActionListener(this);
        backButton.addActionListener(this);

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(emailLabel);
        add(emailField);
        add(signUpButton);
        add(backButton);

        setSize(300, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUpButton) {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            String email = emailField.getText();

            Connection conn = null;
            Statement stmt = null;
            try {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                stmt = conn.createStatement();
                String sql;
                sql = "INSERT INTO Users (username, password, email) VALUES ('" + username + "', '" + password + "', '" + email + "')";
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, "Sign up successful!");
                LoginPage logIn = new LoginPage();
                logIn.setVisible(true);
                setVisible(false);
            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException se2) {
                }
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        } else if (e.getSource() == backButton) {
            LoginPage logIn = new LoginPage();
            logIn.setVisible(true);
            setVisible(false);
        }
    }

}


