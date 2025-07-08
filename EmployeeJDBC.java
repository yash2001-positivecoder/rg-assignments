import java.sql.*;

public class EmployeeJDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/employee";
    private static final String USER = "root"; // replace with your username
    private static final String PASSWORD = "Yash@2001"; // replace with your password

    public void addEmployee(Employee emp) throws SQLException {
        String query = "INSERT INTO employee (id, name, department) VALUES (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, emp.getId());
            pst.setString(2, emp.getName());
            pst.setString(3, emp.getDepartment());
            pst.executeUpdate();
        }
    }

    public Employee getEmployeeById(int id) throws SQLException {
        String query = "SELECT * FROM employee WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("department"));
            }
        }
        return null;
    }

    public boolean updateEmployee(Employee emp) throws SQLException {
        String query = "UPDATE employee SET name = ?, department = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, emp.getName());
            pst.setString(2, emp.getDepartment());
            pst.setInt(3, emp.getId());
            return pst.executeUpdate() > 0;
        }
    }

    public boolean deleteEmployee(int id) throws SQLException {
        String query = "DELETE FROM employee WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        }
    }

    public static void main(String[] args) {
        EmployeeJDBC jdbc = new EmployeeJDBC();
        try {
            jdbc.addEmployee(new Employee(1, "Yash", "SWE"));
            System.out.println("Inserted Yash.");

            jdbc.addEmployee(new Employee(2, "Sanyam", "SWE"));
            System.out.println("Inserted Sanyam.");

            Employee e = jdbc.getEmployeeById(1);
            System.out.println("Fetched: " + e);

            jdbc.updateEmployee(new Employee(1, "Yash Vardhan Gautam", "Software Engineer"));
            System.out.println("Updated Yash.");
            Employee e1 = jdbc.getEmployeeById(1);
            System.out.println("Fetched(updated): " + e1);

            jdbc.deleteEmployee(2);
            System.out.println("Deleted Sanyam.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
