import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    // Retrieve the hashed password for a given username and user type
    public String getPasswordHash(String username, String userType) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String passwordHash = null;

        try {
            // Obtain a database connection from the factory
            connection = DBConnectionFactory.getConnection();

            // Create a SQL query to retrieve the hashed password
            String query = "SELECT password FROM users WHERE username = ? AND user_type = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, userType);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                passwordHash = resultSet.getString("password");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close database resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return passwordHash;
    }
}
