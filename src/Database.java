import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    String url= "jdbc:sqlite:D://sqlitedb/CvDatabase.db";
    Connection conn = null;

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


}
