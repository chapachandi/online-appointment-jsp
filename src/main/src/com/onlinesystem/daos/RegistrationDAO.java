import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationDAO {
    private final DBConnectionFactory dbConnectionFactory;

    public RegistrationDAO(DBConnectionFactory dbConnectionFactory) {
        this.dbConnectionFactory = dbConnectionFactory;
    }

    public boolean registerUser(Registration user) {
        try (Connection connection = dbConnectionFactory.getConnection()) {
            String sql = "INSERT INTO users (full_name, user_email, phone_number, user_type, user_username, user_password) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getPhoneNumber());
                preparedStatement.setString(4, user.getUserType());
                preparedStatement.setString(5, user.getUsername());
                preparedStatement.setString(6, user.getPassword());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
