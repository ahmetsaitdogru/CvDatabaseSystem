import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ViewYourCVPage extends JFrame implements ActionListener {

    static final String JDBC_DRIVER = "org.sqlite.JDBC";
    static final String DB_URL = "jdbc:sqlite:D://sqlitedb/CvDatabase.db";
    static final String USER = "username";
    static final String PASS = "password";

    JLabel nameLabel;
    JLabel skillLabel;
    JLabel experienceLabel;
    JLabel educationLabel;
    JLabel addressLabel;
    JButton editButton;
    JButton deleteButton;
    JButton addButton;
    JButton backButton;

    String username;
    CV cv;

    public ViewYourCVPage(String username) {
        super("CV Database - View Your CV");

        this.username = username;
        cv = retrieveCVFromDatabase(username);

        setLayout(new FlowLayout());

        if (cv != null) {
            nameLabel = new JLabel("Name: " + cv.getName());
            skillLabel = new JLabel("Skill: " + cv.getSkill());
            experienceLabel = new JLabel("Experience: " + cv.getExperience());
            educationLabel = new JLabel("Education: " + cv.getEducation());
            addressLabel = new JLabel("Address: " + cv.address);
            editButton = new JButton("Edit");
            deleteButton = new JButton("Delete");
            backButton = new JButton("Back");
            add(nameLabel);
            add(skillLabel);
            add(experienceLabel);
            add(educationLabel);
            add(addressLabel);
            add(editButton);
            add(deleteButton);
            add(backButton);
            editButton.addActionListener(this);
            deleteButton.addActionListener(this);
            backButton.addActionListener(this);
        } else {
            addButton = new JButton("Add CV");
            backButton = new JButton("Back");
            add(addButton);
            add(backButton);
            addButton.addActionListener(this);
            backButton.addActionListener(this);
        }




        setSize(350, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton) {
            EditCVPage editCV = new EditCVPage(username, cv.getID());
            editCV.setVisible(true);
            setVisible(false);
        } else if (e.getSource() == deleteButton) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete your CV?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                Main.deleteCVFromDatabase(cv.getID());
                MainMenu mainMenu = new MainMenu(username);
                mainMenu.setVisible(true);
                setVisible(false);
            }
        } else if (e.getSource() == addButton) {
            AddCVPage addCV = new AddCVPage(username);
            addCV.setVisible(true);
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
        try {
            Class.forName(JDBC_DRIVER);
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

    public static void main(String[] args) {
        ViewYourCVPage viewCV = new ViewYourCVPage("username");
        viewCV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }}
