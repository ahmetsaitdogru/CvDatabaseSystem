import java.sql.*; //burayı database
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        String url= "jdbc:sqlite:D://sqlitedb/CvDatabase.db";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connected");

          //  conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch ( SQLException e) {
            System.out.println(e.getMessage());
            return;
        }

        String username = "";
        String password = "";
        String accountType = "";




        //insertCV(conn, "John Doe", 2, "flying", "non","highschool",null);
        displayCV(conn, 2);
        editCV(conn, 1, "Sait dogru", "programming", "5 years", "Bachelor's degree", "xxxxx");
        displayCV(conn, 2);
        displayCVList(conn);
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the ID of the CV that you want to display: ");
        int id = scan.nextInt();
        selectCV(conn, id);

    }
    public static void insertCV(Connection conn, String name, int ID, String skill, String experience, String education, String address) {
        String sql = "INSERT INTO cv (name, ID,skill, experience, education, address ) VALUES (?, ?,?,?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, ID);
            pstmt.setString(3, skill);
            pstmt.setString(4, experience);
            pstmt.setString(5, education);
            pstmt.setString(6, address);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void displayCV(Connection conn, int id) {
        String sql1 = "SELECT * FROM cv WHERE ID = ?";


        try (PreparedStatement pstmt = conn.prepareStatement(sql1)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println(rs.getString("name") + "    " +
                            rs.getInt("ID") + "   " +
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
        String sql = "SELECT * FROM cv WHERE ID = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println(rs.getString("name") + "    " +
                            rs.getInt("ID") + "   " +
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



}
