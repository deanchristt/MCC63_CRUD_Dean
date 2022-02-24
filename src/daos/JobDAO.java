package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Job;

/**
 *
 * @author deanchristt
 */
public class JobDAO {

    private final Connection connection;

    public JobDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Job> getAll() {
        List<Job> jobs = new ArrayList<>();
        String query = "SELECT * FROM JOB";
        try {
            ResultSet resultSet = connection
                    .prepareStatement(query)
                    .executeQuery();
            while (resultSet.next()) {
                jobs.add(new Job(resultSet.getString(1), resultSet.getString(2), resultSet.getDouble(3),
                        resultSet.getDouble(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    public boolean insert(Job job) {
        String query = "INSERT INTO JOB(id, title, min_salary, max_salary) VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, job.getJobId());
            preparedStatement.setString(2, job.getJobTitle());
            preparedStatement.setDouble(3, job.getMinSalary());
            preparedStatement.setDouble(4, job.getMaxSalary());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String id) {
        String query = "DELETE FROM JOB WHERE id =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean update(String id, Job job) {
        String query = "UPDATE JOB SET min_salary=?, max_salary=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, job.getMinSalary());
            preparedStatement.setDouble(2, job.getMaxSalary());
            preparedStatement.setString(3, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Job getById(String id) {
        String query = "SELECT * FROM JOB where id = ?";
        Job job = new Job();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                job.setJobId(rs.getString(1));
                job.setJobTitle(rs.getString(2));
                job.setMinSalary(rs.getDouble(3));
                job.setMaxSalary(rs.getDouble(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return job;
    }

    public boolean getBye(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
