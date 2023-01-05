import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddCVPage extends JFrame implements ActionListener {

    static final String JDBC_DRIVER = "org.sqlite.JDBC";
    static final String DB_URL = "jdbc:sqlite:CVDatabase.db";
    static final String USER = "username";
    static final String PASS = "password";

    JLabel nameLabel;
    JTextField nameField;
    JLabel skillLabel;
    JTextField skillField;
    JLabel experienceLabel;
    JTextField experienceField;
    JLabel educationLabel;
    JTextField educationField;
    JLabel addressLabel;
    JTextField addressField;
    JButton saveButton;
    JButton backButton;

    String username;

    public AddCVPage(String username) {
        super("CV Database - Add CV");

        this.username = username;

        setLayout(new GridLayout(5, 2));

        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        skillLabel = new JLabel("Skill:");
        skillField = new JTextField(20);
        experienceLabel = new JLabel("Experience:");
        experienceField = new JTextField(20);
        educationLabel = new JLabel("Education:");
        educationField = new JTextField(20);
        addressLabel = new JLabel("Address:");
        addressField = new JTextField(20);
        saveButton = new JButton("Save");
        backButton = new JButton("Back");
        add(nameLabel);
        add(nameField);
        add(skillLabel);
        add(skillField);
        add(experienceLabel);
        add(experienceField);
        add(educationLabel);
        add(educationField);
        add(addressLabel);
        add(addressField);
        add(saveButton);
        add(backButton);
        saveButton.addActionListener(this);
        backButton.addActionListener(this);

        setSize(350, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            String name = nameField.getText();
            String skill = skillField.getText();
            String experience = experienceField.getText();
            String education = educationField.getText();
            String address = addressField.getText();
            addCVToDatabase(name, skill, experience, education, address, username);
            MainMenu mainMenu = new MainMenu(username);
            mainMenu.setVisible(true);
            setVisible(false);
        } else if (e.getSource() == backButton) {
            MainMenu mainMenu = new MainMenu(username);
            mainMenu.setVisible(true);
            setVisible(false);
        }
    }

    public void addCVToDatabase(String name, String skill, String experience, String education, String address, String username)
    {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO CVs (name, skill, experience, education, address, username) VALUES ('" + name + "', '" + skill + "', '" + experience + "', '" + education + "', '" + address + "', '" + username + "')";
            stmt.executeUpdate(sql);
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
    }

}
