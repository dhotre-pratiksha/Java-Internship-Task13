import java.sql.*;

public class StudentJDBCApp {

    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "admin2633";

    public static void main(String[] args) {

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            System.out.println("Database connected successfully.");

            String insertSQL = "INSERT INTO students (name, email) VALUES (?, ?)";
            PreparedStatement psInsert = con.prepareStatement(insertSQL);
            psInsert.setString(1, "Pratiksha");
            psInsert.setString(2, "dhotrepratiksha@gmail.com");
            psInsert.executeUpdate();
            System.out.println("Record inserted.");

            String selectSQL = "SELECT * FROM students";
            PreparedStatement psSelect = con.prepareStatement(selectSQL);
            ResultSet rs = psSelect.executeQuery();

            System.out.println("\nStudent Records :");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("email")
                );
            }

            String updateSQL = "UPDATE students SET name=? WHERE email=?";
            PreparedStatement psUpdate = con.prepareStatement(updateSQL);
            psUpdate.setString(1, "Pratiksha Dhotre");
            psUpdate.setString(2, "dhotrepratiksha@gmail.com");
            psUpdate.executeUpdate();
            System.out.println("\nRecord updated.");

            String deleteSQL = "DELETE FROM students WHERE email=?";
            PreparedStatement psDelete = con.prepareStatement(deleteSQL);
            psDelete.setString(1, "dhotrepratiksha26@gmail.com");
            psDelete.executeUpdate();
            System.out.println("Record deleted.");

        }
        catch (SQLException e) {
            System.out.println("Database error : " + e.getMessage());
        }
    }
}
