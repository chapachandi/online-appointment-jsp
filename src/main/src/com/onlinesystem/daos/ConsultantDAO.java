import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultantDAO {
    private final DBConnectionFactory dbConnectionFactory;

    public ConsultantDAO(DBConnectionFactory dbConnectionFactory) {
        this.dbConnectionFactory = dbConnectionFactory;
    }

    public List<ConsultantEntity> getAllConsultants() {
        List<ConsultantEntity> consultantsList = new ArrayList<>();
        try (Connection connection = dbConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM consultants";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        ConsultantEntity consultant = new ConsultantEntity();
                        consultant.setId(resultSet.getLong("consultant_id"));
                        consultant.setName(resultSet.getString("name"));
                        consultant.setEmail(resultSet.getString("email"));
                        consultant.setPhoneNumber(resultSet.getString("phone_number"));
                        consultant.setUserType(resultSet.getString("user_type"));
                        consultant.setJobType(resultSet.getString("job_type"));
                        consultant.setUsername(resultSet.getString("username"));
                        consultant.setPassword(resultSet.getString("password"));
                        consultantsList.add(consultant);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultantsList;
    }

    public boolean addConsultant(ConsultantEntity consultant) {
        try (Connection connection = dbConnectionFactory.getConnection()) {
            String sql = "INSERT INTO consultants (name, email, phone_number, user_type, job_type, username, password) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, consultant.getName());
                preparedStatement.setString(2, consultant.getEmail());
                preparedStatement.setString(3, consultant.getPhoneNumber());
                preparedStatement.setString(4, consultant.getUserType());
                preparedStatement.setString(5, consultant.getJobType());
                preparedStatement.setString(6, consultant.getUsername());
                preparedStatement.setString(7, consultant.getPassword());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateConsultant(ConsultantEntity consultant) {
        try (Connection connection = dbConnectionFactory.getConnection()) {
            String sql = "UPDATE consultants SET name=?, email=?, phone_number=?, user_type=?, job_type=?, username=?, password=? WHERE consultant_id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, consultant.getName());
                preparedStatement.setString(2, consultant.getEmail());
                preparedStatement.setString(3, consultant.getPhoneNumber());
                preparedStatement.setString(4, consultant.getUserType());
                preparedStatement.setString(5, consultant.getJobType());
                preparedStatement.setString(6, consultant.getUsername());
                preparedStatement.setString(7, consultant.getPassword());
                preparedStatement.setLong(8, consultant.getId());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteConsultant(Long consultantId) {
        try (Connection connection = dbConnectionFactory.getConnection()) {
            String sql = "DELETE FROM consultants WHERE consultant_id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, consultantId);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ConsultantEntity getConsultantById(Long consultantId) {
        try (Connection connection = dbConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM consultants WHERE consultant_id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, consultantId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        ConsultantEntity consultant = new ConsultantEntity();
                        consultant.setId(resultSet.getLong("consultant_id"));
                        consultant.setName(resultSet.getString("name"));
                        consultant.setEmail(resultSet.getString("email"));
                        consultant.setPhoneNumber(resultSet.getString("phone_number"));
                        consultant.setUserType(resultSet.getString("user_type"));
                        consultant.setJobType(resultSet.getString("job_type"));
                        consultant.setUsername(resultSet.getString("username"));
                        consultant.setPassword(resultSet.getString("password"));
                        return consultant;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ConsultantEntity getConsultantByUsername(String username) {
        try (Connection connection = dbConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM consultants WHERE username=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        ConsultantEntity consultant = new ConsultantEntity();
                        consultant.setId(resultSet.getLong("consultant_id"));
                        consultant.setName(resultSet.getString("name"));
                        consultant.setEmail(resultSet.getString("email"));
                        consultant.setPhoneNumber(resultSet.getString("phone_number"));
                        consultant.setUserType(resultSet.getString("user_type"));
                        consultant.setJobType(resultSet.getString("job_type"));
                        consultant.setUsername(resultSet.getString("username"));
                        consultant.setPassword(resultSet.getString("password"));
                        return consultant;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
