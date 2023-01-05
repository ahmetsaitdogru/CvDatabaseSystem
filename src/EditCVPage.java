import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditCVPage extends JFrame implements ActionListener {

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
    int cvId;

    public EditCVPage(String username, int cvId) {
        super("CV Database - Edit Your CV");

        this.username = username;
        this.cvId = cvId;

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

        CV cv = retrieveCVFromDatabase(username);
        nameField.setText(cv.getName());
        skillField.setText(cv.getSkill());
        experienceField.setText(cv.getExperience());
        educationField.setText(cv.getEducation());
        addressField.setText(cv.address);

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
            updateCVInDatabase(cvId, name, skill, experience, education, address);
            MainMenu mainMenu = new MainMenu(username);
            mainMenu.setVisible(true);
            setVisible(false);
        } else if (e.getSource() == backButton) {
            MainMenu mainMenu = new MainMenu(username);
            mainMenu.setVisible(true);
            setVisible(false);
        }
    }

    public CV retrieveCVFromDatabase(String username) {
        CV cv = null;

        Connection conn = null;
        Statement stmt = null;
        try {      Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM CVs WHERE username='" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int cvId = rs.getInt("id");
                String name = rs.getString("name");
                String skill = rs.getString("skill");
                String experience = rs.getString("experience");
                String education = rs.getString("education");
                String address = rs.getString("address");
                cv = new CV(cvId, name, skill, experience, education, address);
            }
            rs.close();
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

        return cv;
    }

    public void updateCVInDatabase(int cvId, String name, String skill, String experience, String education, String address) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "UPDATE CVs SET name='" + name + "', skill='" + skill + "', experience='" + experience + "', education='" + education + "', address='" + address + "' WHERE id=" + cvId;
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


