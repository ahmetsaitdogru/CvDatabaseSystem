import java.sql.*; //burayı database
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class Main {


    public static void main(String[] args) {


            LoginPage login = new LoginPage();
            login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        String url= "jdbc:sqlite:D://sqlitedb/CvDatabase.db";
        Connection conn = null;

        JFrame f = new JFrame ("Menü");
        JMenuBar mb = new JMenuBar () ;
        JMenu menu = new JMenu("Fİle");
        JMenu submenu = new JMenu ("Alt menü");



        JMenuItem i1 = new JMenuItem("CreateCV");
        JMenuItem i2 = new JMenuItem("İmportCV");
        JMenuItem i3 = new JMenuItem("WriteCV");
        JMenuItem i4 = new JMenuItem("ExportCV");

        i1.addActionListener(new ActionListener () {
            public void actionPerformed1(ActionEvent arg0) {

                JOptionPane.showMessageDialog(f, "Menüye Tıklandı");

            }

            public void actionPerformed(ActionEvent e) {

            }
        } );

        JMenuItem a1 = new JMenuItem ("ALtmenü 1");
        JMenuItem a2 = new JMenuItem ("Altmenu 2");
        submenu.add(a1);
        submenu.add(a2);

        menu.add(i1);
        menu.add(i2);
        menu.add(i3);
        menu.add(i4);
        mb.add(menu);
        f.setJMenuBar(mb);
        f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);




        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connected");


        } catch ( SQLException e) {
            System.out.println(e.getMessage());
            return;
        }





        insertCV(conn, "zilan", 2, "asda", "asdd","highasdasdaschool","asdasd", 2);
        insertCV(conn, "Ahmet", 1, "asda", "asdd","highasdasdaschool","asdasd" ,1);





        displayCVList(conn);
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the ID of the CV that you want to display: ");
        int id = scan.nextInt();
        selectCV(conn, id);

    }
    public static void insertCV(Connection conn, String name, int ID, String skill, String experience, String education, String address, int userId) {
        String sql = "INSERT INTO cv (name, ID,skill, experience, education, address , userId) VALUES (?, ?,?,?,?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, ID);
            pstmt.setString(3, skill);
            pstmt.setString(4, experience);
            pstmt.setString(5, education);
            pstmt.setString(6, address);
            pstmt.setInt(7, userId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void displayCV(Connection conn, int id) {
        String sql1 = "SELECT  name,skill,experience,education,address FROM cv WHERE ID = ?";


        try (PreparedStatement pstmt = conn.prepareStatement(sql1)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println(rs.getString("name") + "    " +
                            rs.getString("skill")+ "   " +
                            rs.getString("experience")+ "   " +
                            rs.getString("education")+ "   " +
                            rs.getString("address"));
                } else {
                    System.out.println("CV not found.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void editCV(Connection conn, int ID, String name, String skill, String experience, String education, String address) {
        String sql2 = "UPDATE cv SET name = ?, skill = ?, experience = ?, education = ?, address =? WHERE ID = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql2)) {
            pstmt.setString(1, name);
            pstmt.setString(2, skill);
            pstmt.setString(3, experience);
            pstmt.setString(4, education);
            pstmt.setInt(5, ID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void displayCVList(Connection conn) {
        String sql = "SELECT ID, name FROM cv";

        try ( Statement stmt = conn.createStatement();
              ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt("ID") + ": " + rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void selectCV(Connection conn, int id) {
        String sql = "SELECT name,skill,experience,education,address FROM cv WHERE ID = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println(rs.getString("name") + "    " +
                            rs.getString("skill") + "   " +
                            rs.getString("experience") + "   " +
                            rs.getString("education")+ "   " +
                            rs.getString("address"));
                } else {
                    System.out.println("CV not found.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void importDataToDatabase(String name, int ID, String skill, String education, String experience) {
        Connection conn = null;
        try {

            String sql = "INSERT INTO CV (name, ID, skill, education, experience) ";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setInt(2, ID);
            pstmt.setString(3, skill);
            pstmt.setString(4, education);
            pstmt.setString(5, experience);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void deleteCV(Connection conn, int id) {
        String sql = "DELETE FROM cv WHERE ID = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static CV retrieveCVFromDatabase(int ID) {
        CV cv = null;

        Connection conn = null;
        Statement stmt = null;
        try {

            String sql;
            sql = "SELECT * FROM CVs WHERE ID=" + ID;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int cvId = rs.getInt("ID");
                String name = rs.getString("name");
                String skill = rs.getString("skill");
                String experience = rs.getString("experience");
                String education = rs.getString("education");
                String address = rs.getString("address");
                cv = new CV(ID, name, skill, experience, education, address);
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
    public static void deleteCVFromDatabase(int cvId) {
        Connection conn = null;
        Statement stmt = null;
        try {

            stmt = conn.createStatement();
            String sql;
            sql = "DELETE FROM CVs WHERE id=" + cvId;
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
