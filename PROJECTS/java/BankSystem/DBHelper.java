import java.sql.*;

public class DBHelper {
static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver Loaded Successfully");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver NOT found!");
            e.printStackTrace();
        }
    }
    private static final String URL = "jdbc:mysql://localhost:3306/bank?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // replace with your MySQL username
    private static final String PASSWORD = "password"; // replace with your MySQL password
    

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static int addUser(String name, double balance) {
        String sql = "INSERT INTO users (name, balance) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            System.out.println("DEBUG: Connected to DB successfully"); // debug

            ps.setString(1, name);
            ps.setDouble(2, balance);

            int affectedRows = ps.executeUpdate();
            System.out.println("DEBUG: Rows affected: " + affectedRows); // debug

            if (affectedRows == 0) {
                System.out.println("ERROR: Insertion failed, no rows affected!");
                return -1;
            }

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println("DEBUG: Inserted user ID: " + id);
                return id;
            }

        } catch (SQLException e) {
            System.out.println("ERROR: SQLException: " + e.getMessage());
            e.printStackTrace();
        }
        return -1; // insertion failed
    }

    public static double getBalance(int id) {
        String sql = "SELECT balance FROM users WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble("balance");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static boolean updateBalance(int id, double balance) {
        String sql = "UPDATE users SET balance=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, balance);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean userExists(int id) {
        String sql = "SELECT id FROM users WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
