import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchPage extends JFrame implements ActionListener {

    static final String JDBC_DRIVER = "org.sqlite.JDBC";
    static final String DB_URL = "jdbc:sqlite:CVDatabase.db";
    static final String USER = "username";
    static final String PASS = "password";

    JLabel searchLabel;
    JTextField searchField;
    JButton searchButton;
    JButton backButton;
    JList cvList;
    DefaultListModel model;

    public SearchPage() {
        super("CV Database - Search");

        setLayout(new FlowLayout());

        searchLabel = new JLabel("Search: ");
        searchField = new JTextField(10);
        searchButton = new JButton("Search");
        backButton = new JButton("Back");
        model = new DefaultListModel();
        cvList = new JList(model);
        cvList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cvList.setLayoutOrientation(JList.VERTICAL);

        searchButton.addActionListener(this);
        backButton.addActionListener(this);
        cvList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    CV cv = (CV)model.get(index);
                    ViewCVPage viewCV = new ViewCVPage(cv);
                    viewCV.setVisible(true);
                    setVisible(false);
                }
            }
        });

        add(searchLabel);
        add(searchField);
        add(searchButton);
        add(backButton);
        add(new JScrollPane(cvList));

        setSize(300, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String query = searchField.getText();
            search(query);
        } else if (e.getSource() == backButton) {
            MainMenu menu = new MainMenu(USER);
            menu.setVisible(true);
            setVisible(false);
        }
    }

    public void search(String query) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM CVs WHERE name LIKE %" + query;
            ResultSet rs = stmt.executeQuery(sql);
            model.clear();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String name = rs.getString("name");
                String skill = rs.getString("skill");
                String experience = rs.getString("experience");
                String education = rs.getString("education");
                String address = rs.getString("address");
                CV cv = new CV(ID, name, skill, experience, education, address);
                model.addElement(cv);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
