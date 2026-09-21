import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://host.docker.internal:3306/loandb";
        String user = "root";
        String password = "mypassword123";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL successfully!");

            Statement stmt = conn.createStatement();

            // Create table if it doesn't exist
            String createTable = "CREATE TABLE IF NOT EXISTS loans (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "borrower_name VARCHAR(100), " +
                    "amount DECIMAL(10,2), " +
                    "interest_rate DECIMAL(5,2))";
            stmt.executeUpdate(createTable);
            System.out.println("Table 'loans' ready.");

            // Insert a sample loan
            String insert = "INSERT INTO loans (borrower_name, amount, interest_rate) " +
                    "VALUES ('Rahul Sharma', 50000.00, 8.5)";
            stmt.executeUpdate(insert);
            System.out.println("Sample loan inserted.");

            // Read it back
            var rs = stmt.executeQuery("SELECT * FROM loans");
            while (rs.next()) {
                System.out.println("Loan ID: " + rs.getInt("id") +
                        ", Borrower: " + rs.getString("borrower_name") +
                        ", Amount: " + rs.getBigDecimal("amount") +
                        ", Rate: " + rs.getBigDecimal("interest_rate") + "%");
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("Something went wrong:");
            e.printStackTrace();
        }
    }
}
