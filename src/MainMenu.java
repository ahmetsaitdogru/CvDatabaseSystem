import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame implements ActionListener {

    // GUI elements
    JLabel welcomeLabel;
    JButton searchButton;
    JButton viewCVButton;
    JButton logoutButton;

    // User's username
    String username;

    public MainMenu(String username) {
        super("CV Database - Main Menu");
        this.username = username;

        // Set layout
        setLayout(new FlowLayout());

        // Initialize GUI elements
        welcomeLabel = new JLabel("Welcome, " + username + "!");
        searchButton = new JButton("Search CVs");
        viewCVButton = new JButton("View My CV");
        logoutButton = new JButton("Logout");

        // Add action listeners to buttons
        searchButton.addActionListener(this);
        viewCVButton.addActionListener(this);
        logoutButton.addActionListener(this);

        // Add GUI elements to the frame
        add(welcomeLabel);
        add(searchButton);
        add(viewCVButton);
        add(logoutButton);

        // Set the size of the frame and make it visible
        setSize(300, 150);
        setVisible(true);
    }




            // Handle button clicks
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            // If the user clicks the search button, go to the search page
            SearchPage search = new SearchPage();
            search.setVisible(true);
            setVisible(false);
        } else if (e.getSource() == viewCVButton) {
            // If the user clicks the view CV button, go to the view CV page
            CV cv = null;
            cv = Main.retrieveCVFromDatabase(cv.getID());
            ViewCVPage viewCV = new ViewCVPage(cv);
            viewCV.setVisible(true);
            setVisible(false);
        } else if (e.getSource() == logoutButton) {
            // If the user clicks the logout button, go back to the login page
            LoginPage login = new LoginPage();
            login.setVisible(true);
            setVisible(false);
        }
    }


}
