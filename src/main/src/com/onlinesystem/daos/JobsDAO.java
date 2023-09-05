import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JobsDAO {
    private final DBConnectionFactory dbConnectionFactory;

    public JobsDAO(DBConnectionFactory dbConnectionFactory) {
        this.dbConnectionFactory = dbConnectionFactory;
    }

    public List<JobsEntity> getAllJobs() {
        List<JobsEntity> jobsList = new ArrayList<>();
        try (Connection connection = dbConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM jobs";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        JobsEntity job = new JobsEntity();
                        job.setId(resultSet.getLong("job_id"));
                        job.setCountry(resultSet.getString("country"));
                        job.setJobTitle(resultSet.getString("job_title"));
                        job.setJobType(resultSet.getString("job_type"));
                        job.setLocation(resultSet.getString("location"));
                        job.setPublishedDate(resultSet.getDate("published_date"));
                        job.setDescription(resultSet.getString("description"));
                        jobsList.add(job);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobsList;
    }

    public boolean addJob(JobsEntity job) {
        try (Connection connection = dbConnectionFactory.getConnection()) {
            String sql = "INSERT INTO jobs (country, job_title, job_type, location, published_date, description) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, job.getCountry());
                preparedStatement.setString(2, job.getJobTitle());
                preparedStatement.setString(3, job.getJobType());
                preparedStatement.setString(4, job.getLocation());
                preparedStatement.setDate(5, new java.sql.Date(job.getPublishedDate().getTime()));
                preparedStatement.setString(6, job.getDescription());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateJob(JobsEntity job) {
        try (Connection connection = dbConnectionFactory.getConnection()) {
            String sql = "UPDATE jobs SET country=?, job_title=?, job_type=?, location=?, " +
                    "published_date=?, description=? WHERE job_id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, job.getCountry());
                preparedStatement.setString(2, job.getJobTitle());
                preparedStatement.setString(3, job.getJobType());
                preparedStatement.setString(4, job.getLocation());
                preparedStatement.setDate(5, new java.sql.Date(job.getPublishedDate().getTime()));
                preparedStatement.setString(6, job.getDescription());
                preparedStatement.setLong(7, job.getId());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteJob(Long jobId) {
        try (Connection connection = dbConnectionFactory.getConnection()) {
            String sql = "DELETE FROM jobs WHERE job_id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, jobId);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public JobsEntity getJobById(Long jobId) {
        try (Connection connection = dbConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM jobs WHERE job_id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, jobId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        JobsEntity job = new JobsEntity();
                        job.setId(resultSet.getLong("job_id"));
                        job.setCountry(resultSet.getString("country"));
                        job.setJobTitle(resultSet.getString("job_title"));
                        job.setJobType(resultSet.getString("job_type"));
                        job.setLocation(resultSet.getString("location"));
                        job.setPublishedDate(resultSet.getDate("published_date"));
                        job.setDescription(resultSet.getString("description"));
                        return job;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<JobsEntity> searchByCountry(String countryName) {
        List<JobsEntity> jobsList = new ArrayList<>();
        try (Connection connection = dbConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM jobs WHERE country LIKE ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, "%" + countryName + "%");

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        JobsEntity job = new JobsEntity();
                        job.setId(resultSet.getLong("job_id"));
                        job.setCountry(resultSet.getString("country"));
                        job.setJobTitle(resultSet.getString("job_title"));
                        job.setJobType(resultSet.getString("job_type"));
                        job.setLocation(resultSet.getString("location"));
                        job.setPublishedDate(resultSet.getDate("published_date"));
                        job.setDescription(resultSet.getString("description"));
                        jobsList.add(job);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobsList;
    }

    public List<JobsEntity> searchByJobType(String jobType) {
        List<JobsEntity> jobsList = new ArrayList<>();
        try (Connection connection = dbConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM jobs WHERE job_type LIKE ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, "%" + jobType + "%");

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        JobsEntity job = new JobsEntity();
                        job.setId(resultSet.getLong("job_id"));
                        job.setCountry(resultSet.getString("country"));
                        job.setJobTitle(resultSet.getString("job_title"));
                        job.setJobType(resultSet.getString("job_type"));
                        job.setLocation(resultSet.getString("location"));
                        job.setPublishedDate(resultSet.getDate("published_date"));
                        job.setDescription(resultSet.getString("description"));
                        jobsList.add(job);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobsList;
    }
}
